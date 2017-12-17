//Kyle 'Avoca' Abent loves his grandma Rose 'Howard St' Abent.
import javax.swing.JOptionPane;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.StringTokenizer;


public class GoFish  extends JFrame implements ActionListener
{ 

   File gFile = new File("a:\\"); 
   JFileChooser jfc = new JFileChooser(gFile);  
   
   JMenuBar jmb = new JMenuBar();
   JMenu aboutM = new JMenu("File");
   JMenuItem newF = new JMenuItem("New");
   JMenuItem openF = new JMenuItem("Open");
   JMenuItem saveGame = new JMenuItem("Save");
   
   JMenu gameM = new JMenu("Game");
   JMenuItem LoadG = new JMenuItem("Load");
   JPanel  northP = new JPanel();  
   JPanel  centerP = new JPanel();  
   JPanel  southP = new JPanel(); 
   JPanel  westP = new JPanel(); 
   JPanel  eastP = new JPanel(); 
     
   private ObjectOutputStream sout;
   private ObjectInputStream sin;
   fishEngine fEngine = new fishEngine();
   private JTextArea textArea = new JTextArea("", 25, 30); 
   
   private JButton endTurn = new JButton("endTurn");
   private JButton newGame = new JButton("newGame");
   
   
   JScrollPane  scroll = new JScrollPane(textArea);
   
   
   JButton[] humanHand = new JButton[10];
   
   
   public static void main(String[] args)
   {
      Scanner keyboard = new Scanner(System.in);
      //fEngine.turnIgnition();
      GoFish GF = new GoFish();
      GF.setSize(900,800);
      GF.setVisible(true);
      GF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      fishEngine fEngine = new fishEngine();
      fEngine.turnIgnition();
      
   }
 
   
   public GoFish()       {
   
      Container myContainer = getContentPane();
      myContainer.setLayout(new BorderLayout());
      setTitle("Go Fish"); 
      aboutM.add(newF);
      newF.addActionListener(this);
      aboutM.add(openF);
      openF.addActionListener(this);
      aboutM.add(saveGame);
      saveGame.addActionListener(this);
      gameM.add(LoadG);
      LoadG.addActionListener(this);
      
      jmb.add(aboutM);
      jmb.add(gameM);
      northP.add(jmb);
      
      centerP.setLayout(new GridLayout(3,1)); // 2, 2
      centerP.add(scroll);
      //centerP.add(endTurn);
      endTurn.addActionListener(this);
      centerP.add(newGame);
      newGame.addActionListener(this);
      
      southP.setLayout(new GridLayout(10,1)); // 2, 2
      
      
      
      northP.setLayout(new FlowLayout());
      myContainer.add(northP, BorderLayout.NORTH);
      myContainer.add(centerP, BorderLayout.CENTER);
      myContainer.add(southP, BorderLayout.SOUTH);
      myContainer.add(westP, BorderLayout.WEST);
      myContainer.add(eastP, BorderLayout.EAST);
      //myContainer.add(textArea);
      //myContainer.add(goldFishBtn);
      //System.out.println("c = " + myContainer + " humanPoints = " + humanPoints); 
      //fEngine.turnIgnition(); 
      
      for(int i = 0; i < 10; i++) {
         humanHand[i] = new JButton(String.valueOf(i));
         southP.add(humanHand[i]);
         humanHand[i].addActionListener(this);
      }
      
     
   }
   public void updateHumanHand()
   {
      StringTokenizer hand = new StringTokenizer(fEngine.getHumanHandDisplay());
     
      int handCount = hand.countTokens();
      System.out.println("" + hand.toString() + handCount);
      for(int i = 0; i < handCount; i++) {
         String number = hand.nextToken();
         humanHand[i].setText(number); //hide rest
         humanHand[i].setVisible(true);
      }
      for(int i = 0; i < 10; i++) {
         if (i > handCount) 
         {
            humanHand[i].setVisible(false);
         }
      }
   }
   public void actionPerformed(ActionEvent e)
   {
   
      
      if (e.getSource() == openF)
      {
         OpenBSAFile();
      }
      else if (e.getSource() == LoadG)
      {
         ReadBSAFile();
         textArea.setText(fishEngine.stringBuilder.toString());
         updateHumanHand();
      }
      else if (e.getSource() == saveGame)
      {
         SaveBSAFile();
      }
      else if (e.getSource() == newF)
      {
         //fEngine.turnIgnition(); 
      }
      else if (e.getSource() == endTurn)
      {
         fEngine.turnManage(); 
      }
      else if (e.getSource() == newGame)
      {
         //fEngine.turnIgnition(); 
         //fishEngine fEngine = new fishEngine();
         //fEngine.turnIgnition();
         fEngine.humanInput(); 
         
         for(int i = 0; i < fEngine.gethandHuman().getHand().size(); i++) {
            String number = fEngine.gethandHuman().getHand().get(i).toString();
            humanHand[i].setText(number); //hide rest
         }
         textArea.setText(fishEngine.stringBuilder.toString());
      }
      else // card # button
      {
         if (fEngine.humanTurn) 
         {  
            updateHumanHand();
            JButton button = (JButton) e.getSource();
            fEngine.setScanNumber(Integer.parseInt(button.getText()));
            fEngine.humanAlgorithm();
            updateHumanHand();
            textArea.setText(fishEngine.stringBuilder.toString());
            fEngine.humanInput(); 
            System.out.println(button.getText());
         }
         else
         {
            JButton button = (JButton) e.getSource();
            updateHumanHand();
            System.out.println(button.getText());
         }
      }
   }
   
   public void OpenBSAFile()
   {
      jfc.setFileSelectionMode(JFileChooser.FILES_ONLY );
      int result = jfc.showOpenDialog( this );
      if ( result == JFileChooser.CANCEL_OPTION )
      {
         return;
      }
      gFile = jfc.getSelectedFile();
      if ( gFile == null || gFile.getName().equals( "" ) )
      {
         JOptionPane.showMessageDialog( this, "Invalid File Name",
            "fileInfo", JOptionPane.ERROR_MESSAGE );
      }
   }
   
   
   
   
   public void ReadBSAFile()
   {
   
      fishEngine fEngine;
      try 
      {
         
         FileInputStream fis = new FileInputStream(gFile);
         sin  =  new ObjectInputStream(fis);
      
         while (true)
         {
            fEngine = (fishEngine) sin.readObject();
         }
      }
      catch (EOFException eof)
      {	JOptionPane.showMessageDialog(this,"File Read Successfully",
            "File Status",JOptionPane.INFORMATION_MESSAGE);
      }
      
      catch (IOException e) 
      {
         JOptionPane.showMessageDialog( this, ""+e, "Error", JOptionPane.ERROR_MESSAGE );
       
      }
      catch (ClassNotFoundException e) 
      {
         JOptionPane.showMessageDialog( this, ""+e, "Error", JOptionPane.ERROR_MESSAGE );
       
      }
   
   
   }
   public void updateArea() {
      System.out.println("Printed in Superclass.");
   
   }
   
   public void SaveBSAFile ()
   {
      int result = jfc.showSaveDialog(this);
      if (result == JFileChooser.APPROVE_OPTION) {
         gFile = jfc.getSelectedFile();
      
         try 
         {
         
            gFile.createNewFile();
            sout  =  new ObjectOutputStream(new FileOutputStream(gFile));
            sout.writeObject(fEngine); 
            JOptionPane.showMessageDialog( this, "File " + gFile + " saved" );
            sout.close();   
         } 
         catch (IOException e) 
         {
            JOptionPane.showMessageDialog( this, ""+e, "Error", JOptionPane.ERROR_MESSAGE );
         }
         catch(ArrayIndexOutOfBoundsException e)
         {
               //fdbkField.setText("too many tokens! " + e.toString());
         }
         catch(NumberFormatException e)
         {
            // fdbkField.setText("Decimal Errors! " + e.toString());
         }
        
         
      }
   
   }
   
}
   
