import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


//Kyle 'Avoca' Abent loves his grandma Rose 'Howard St' Abent.
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
   private JTextArea turnCounterFBArea = new JTextArea("", 25, 30);  
   //protected  JTextArea feedBack = new JTextArea("", 25, 30); 
   protected  JTextPane feedBack = new JTextPane();//("", 25, 30); 

   protected  JTextArea pile = new JTextArea("", 25, 30); 
   
   private JButton endTurn = new JButton("GoFish");
   private JButton newGame = new JButton("newGame");
   
   
   JScrollPane  scroll = new JScrollPane(turnCounterFBArea);
   JScrollPane  scrollTwo = new JScrollPane(feedBack);
   JScrollPane  scrollThree = new JScrollPane(pile);
   
   
   JButton[] humanHand = new JButton[10];
   
   JLabel  cCards = new JLabel("45 cards left in the deck");  
   JLabel  eTitle = new JLabel("Computer Pts:)");   
   JLabel  cPts = new JLabel(" "); 
   JLabel  wTitle = new JLabel("Human Pts:)"); 
   JLabel  hPts = new JLabel(" "); 
   private JCheckBox cpuHand = new JCheckBox("Show Computer Hand"); 
   private JCheckBox dckHand = new JCheckBox("Show Deck Dealer Hand"); 
   private int humanChoseForDup = 0;
 
   
   public static void main(String[] args)
   {
      Scanner keyboard = new Scanner(System.in);
      //fEngine.turnIgnition();
      GoFish GF = new GoFish();
      GF.setSize(900,800);
      GF.setVisible(true);
      GF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      fishEngine fEngine = new fishEngine();
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
      northP.add(cpuHand);
      northP.add(dckHand);
      CheckBoxListener myCheckBoxListener = new CheckBoxListener();
      cpuHand.addItemListener(myCheckBoxListener);
      cpuHand.setSelected(false);
      dckHand.addItemListener(myCheckBoxListener);
      dckHand.setSelected(false);
      northP.add(jmb);
      
      centerP.setLayout(new GridLayout(2,2)); // 2, 2
      centerP.add(scroll);
      centerP.add(scrollTwo);
      centerP.add(scrollThree);
      centerP.add(cCards);
      
      newGame.addActionListener(this);
      
      southP.setLayout(new FlowLayout()); // new GridLayout(10,1) // 2, 2
      
      eastP.setLayout(new GridLayout(4,1)); // 4,1 GridBagLayout??
      westP.setLayout(new GridLayout(4,1)); // 4,1 GridBagLayout??
      
      northP.setLayout(new FlowLayout());
      myContainer.add(northP, BorderLayout.NORTH);
      myContainer.add(centerP, BorderLayout.CENTER);
      myContainer.add(southP, BorderLayout.SOUTH);
      myContainer.add(westP, BorderLayout.WEST);
      myContainer.add(eastP, BorderLayout.EAST);
      southP.add(endTurn);
      endTurn.addActionListener(this);
      Icon icon = new ImageIcon("C:\\Users\\kylea\\Documents\\GoFish-Java\\cardPics\\larger Y\\gofish.jpg"); //Try a less ... demanding path.. heh.
      endTurn.setIcon(icon);
      endTurn.setPreferredSize(new Dimension(75, 98));
      for(int i = 0; i < 10; i++) {
         humanHand[i] = new JButton(String.valueOf(i));
         //humanHand[i].setPreferredSize(new Dimension(73, 98));
         southP.add(humanHand[i]);
         humanHand[i].addActionListener(this);
      }
      
      westP.add(wTitle);
      westP.add(hPts);
      westP.setLayout(new GridLayout(4,5));
      eastP.add(eTitle);
      eastP.add(cPts);
      eastP.setLayout(new GridLayout(4,5));

       startFresh();
     
   }
   public void startFresh(){
       System.out.println("Size of Deck: " + fEngine.deckDealer.getSize());
        fEngine.deckDealer.ResetDeck();
        fEngine.handComputer.resetHand();
        fEngine.handHuman.resetHand();
        fEngine.createDeck();
        System.out.println("Size of Deck: " + fEngine.deckDealer.getSize());
        fEngine.resetFeedbacks();
        fEngine.humanPoints = 0;
        fEngine.computerPoints = 0;
        fEngine.turnIgnition();
        updateGUI();
        System.out.println("Size of Deck: " + fEngine.deckDealer.getSize());
   }
   public void updateGUI()
   {
      
      for(int i = 0; i<fEngine.handHuman.getHand().size(); i++)
      {
         String value = fEngine.handHuman.getHand().get(i).toString(); 
         humanHand[i].setText(value); //hide rest
         humanHand[i].setVisible(true);   
         humanHand[i].setBackground(null);
          try {
            String searchString = value.toLowerCase();
            //System.out.println("searchString is " + searchString);
            Icon icon = new ImageIcon("C:\\Users\\kylea\\Documents\\GoFish-Java\\cardPics\\larger Y\\"+searchString+".jpg"); //Try a less ... demanding path.. heh.
            //Icon icon = new ImageIcon(getClass().getResource("cardPics\\"+searchString+".jpg"));  
            humanHand[i].setIcon(icon);
            humanHand[i].setPreferredSize(new Dimension(75, 98));
          } 
            catch (Exception ex) {
            System.out.println("Unable to add card image");
            System.out.println(ex);
          }

      }
      
      
       for(int i = 0; i<fEngine.handHuman.getHand().size(); i++)
      {
         //check for duplicates
         for (int j = i + 1; j < fEngine.handHuman.getHand().size(); j ++){
             //System.out.println("humanHand[i] is " + humanHand[i].getText());
             //System.out.println("humanHand[j] is " + humanHand[j].getText());
             if (humanHand[i].isVisible() && humanHand[i].getText().equals((humanHand[j].getText()))) {
                 //System.out.println("Found Matching Numbers: " + humanHand[i].getText());
                 humanHand[i].setBackground(Color.RED);
                 humanHand[j].setBackground(Color.RED);
             }
         }
         
      }
      
      pile.setText("human Pile" + fEngine.humanPile.toString() + "\n computer Pile" + fEngine.computerPile.toString());
      turnCounterFBArea.setText(fEngine.turnCounterFB.toString());
      if (cpuHand.isSelected()){
          fEngine.turnCounterFB.append("\n["+fEngine.turnCounter+"] "+"Computer Hand: " + fEngine.getComputerHandDisplay()+"\n");
          System.out.println("Computer Hand: "+fEngine.getComputerHandDisplay());
      }
      if (dckHand.isSelected()){
          fEngine.turnCounterFB.append("\n"+"["+fEngine.turnCounter+"] "+"Deck Hand: " + fEngine.getdeckHandDisplay()+"\n");
          System.out.println("Deck Hand:" + fEngine.getComputerHandDisplay());
      }
      feedBack.setText(fEngine.feedBack.toString());
      
      for(int i = 0; i < 10; i++) 
      {
         if (i > fEngine.handHuman.getHand().size() -1) 
         {
            humanHand[i].setVisible(false);
            humanHand[i].setBackground(null);
         }
      }  
      hPts.setText(String.valueOf(fEngine.humanPoints));
      cPts.setText(String.valueOf(fEngine.computerPoints));
      cCards.setText(" " + fEngine.deckDealer.getDeck().size() + " cards left in the deck");  
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
      
         updateGUI();
      }
      else if (e.getSource() == saveGame)
      {
         SaveBSAFile();
      }
      else if (e.getSource() == newF)
      { 
       startFresh();
       fEngine.turnManage();
      }
      else if (e.getSource() == endTurn)
      {
         
         updateGUI();
         fEngine.setScanValue("0");
         fEngine.humanAlgorithm();
         //fEngine.humanInput(); 
         updateGUI();
         System.out.println("GoFish");
      }
      else if (e.getSource() == newGame)
      {
         System.out.println("New Game Selected");
         //fEngine.turnIgnition(); 
         //fishEngine fEngine = new fishEngine();
         //fEngine.turnIgnition();
         fEngine.humanInput(); 
         
         for(int i = 0; i < fEngine.gethandHuman().getHand().size(); i++) {
            String number = fEngine.gethandHuman().getHand().get(i).toString();
            humanHand[i].setText(number); //hide rest
         }
         turnCounterFBArea.setText(fishEngine.feedBack.toString());
      }
      else // card # button
      {
         if (fEngine.humanTurn) 
         {  
           //Check for duplicates
            updateGUI();
            JButton button = (JButton) e.getSource();
            String value = (button.getText());
            System.out.println("button clicked is " + value);
            boolean isDuplicate = false;
            isDuplicate = checkHumanForDuplicate(value);
            if (isDuplicate){
                button.setBackground(Color.green);
                if (humanChoseForDup >= 2){//Meant to allow the choice of removing pair. Ah well. One day. lol.
                    removeHumanDuplicate(value);
                }
            }
            else{
                fEngine.valueToScan = value;
                fEngine.humanInput(); 
                fEngine.humanAlgorithm();
            }
            humanChoseForDup = 0;
            updateGUI();
            
         }
         else
         {
            JButton button = (JButton) e.getSource();
            updateGUI();
            System.out.println(button.getText());
         }
      }
   }
   public boolean checkHumanForDuplicate(String value){
       if (value == "0"){
           return false;
       }
       boolean isDup = false;
       int count = 0;
         //check for duplicates
         for (int i = 0; i < fEngine.handHuman.getHand().size(); i ++){
             if (fEngine.handHuman.handHuman.get(i).equals(value)) {
                 System.out.println("Found Matching Numbers: " + value);
                 count = count + 1;
                 humanChoseForDup = humanChoseForDup + 1;
             }
         }
         if (count >= 2){
             isDup = true;
         }
         return isDup;
   }
   public void removeHumanDuplicate(String value){
         fEngine.feedBack.append("\n"+"["+fEngine.turnCounter+"] "+"Human placing down pair ("+ value+")");
         fEngine.handHuman.removePair(value);//Why two paremeter? lol.
         fEngine.humanPile.append(" ("+value+")");
         fEngine.humanPoints += 1;
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
   

      try 
      {
         
         FileInputStream fis = new FileInputStream(gFile);
         sin  =  new ObjectInputStream(fis);
         System.out.println("dsds Load Game Selected");
        
        fishEngine fEngineRead;
            fEngineRead = (fishEngine) sin.readObject();
            deckofDealer.deckDealer = (ArrayList) sin.readObject();
            handofHuman.handHuman = (ArrayList) sin.readObject();
            handofComputer.handComputer = (ArrayList) sin.readObject();
            fEngineRead.feedBack = new StringBuilder();
            fEngineRead.feedBack.append ( (String) sin.readUTF() );
            fEngineRead.humanPile.append ( (String) sin.readUTF() );
            fEngineRead.computerPile.append ( (String) sin.readUTF() );
            fEngineRead.turnCounterFB.append( (String) sin.readUTF() );
            
            fEngine = fEngineRead;
            fEngine.deckDealer = fEngineRead.deckDealer;
            fEngine.handHuman = fEngineRead.handHuman;
            fEngine.handComputer = fEngineRead.handComputer;
            fEngine.feedBack = fEngineRead.feedBack;
            fEngine.humanPile = fEngineRead.humanPile;
            fEngine.computerPile = fEngineRead.computerPile;
            fEngine.turnCounterFB = fEngineRead.turnCounterFB;

            sin.close();
            updateGUI();
      
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
            sout.writeObject(deckofDealer.deckDealer); 
            sout.writeObject(handofHuman.handHuman); 
            sout.writeObject(handofComputer.handComputer); 
            sout.writeUTF(fEngine.feedBack.toString());
            sout.writeUTF(fEngine.humanPile.toString());
            sout.writeUTF(fEngine.computerPile.toString());
            sout.writeUTF(fEngine.turnCounterFB.toString());
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
   
   public class CheckBoxListener implements ItemListener
   {  
      public void itemStateChanged(ItemEvent e)
      {	// process checkbox events
         if ( e.getSource() == cpuHand )	
         {
            //cpuHand.setSelected(false);
            //System.out.println(fEngine.getComputerHandDisplay());
         }
         else if ( e.getSource() == dckHand )	
         {
            //dckHand.setSelected(false);
            //System.out.println(fEngine.getdeckHandDisplay());
         }
      }
   }
   
   
}
   
