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
   JMenu debugM = new JMenu("Debug");
   private JCheckBoxMenuItem cpuHand = new JCheckBoxMenuItem("Show Computer Hand"); 
   private JCheckBoxMenuItem dckHand = new JCheckBoxMenuItem("Show Deck Dealer Hand"); 
   private JCheckBoxMenuItem trnLog = new JCheckBoxMenuItem("Show Turn Log"); 
   private JCheckBoxMenuItem playerPiles = new JCheckBoxMenuItem("Show Player Piles");
   private JCheckBoxMenuItem allDebug = new JCheckBoxMenuItem("Show Turn Match Scan");
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
   protected  JTextArea feedBack = new JTextArea("", 25, 30);//("", 25, 30); 
   protected  JTextArea pile = new JTextArea("", 25, 30); 
   protected  JTextArea allprints = new JTextArea("", 25, 30); 
   private JButton endTurn = new JButton("GoFish");
   private JButton newGame = new JButton("newGame");
   JScrollPane  scroll = new JScrollPane(turnCounterFBArea);
   JScrollPane  scrollTwo = new JScrollPane(feedBack);
   JScrollPane  scrollThree = new JScrollPane(pile);
   JScrollPane  scrollFour = new JScrollPane(allprints);
   JButton[] humanHand = new JButton[10];
   JButton[] computerHandBacks = new JButton[10];
   JButton[] humanPileCards = new JButton[26];
   JButton[] computerPileCards = new JButton[26];
   JLabel  hmnPileCards = new JLabel("Human Pair Pile: ");  
   JLabel  cppuCards = new JLabel("Computer Pair Pile: ");   
   JLabel  cCards = new JLabel("45 cards left in the deck");  
   JLabel  eTitle = new JLabel("Computer Pts:)");   
   JLabel  cPts = new JLabel(" "); 
   JLabel  wTitle = new JLabel("Human Pts:)"); 
   JLabel  hPts = new JLabel(" "); 
   private int humanChoseForDup = 0;
   private boolean deckHandWindowOpen = false;
   private boolean isTurnLogWindowOpen = false;
   private boolean isPlayerPileWindowOpen = false;
   private boolean isallDebugWindowOpen = false;
 
   
   public static void main(String[] args)
   {
      Scanner keyboard = new Scanner(System.in);
      //fEngine.turnIgnition();
      GoFish GF = new GoFish();
      GF.setSize(900,800);
      GF.setVisible(true);
      GF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      GF.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
      debugM.add(cpuHand);
      debugM.add(dckHand);
      debugM.add(trnLog);
      debugM.add(playerPiles);
      debugM.add(allDebug);
      jmb.add(debugM);
      CheckBoxListener myCheckBoxListener = new CheckBoxListener();
      cpuHand.addItemListener(myCheckBoxListener);
      cpuHand.setSelected(false);
      dckHand.addItemListener(myCheckBoxListener);
      dckHand.setSelected(false);
      trnLog.addItemListener(myCheckBoxListener);
      trnLog.setSelected(false);
      playerPiles.addItemListener(myCheckBoxListener);
      playerPiles.setSelected(false);
      allDebug.addItemListener(myCheckBoxListener);
      allDebug.setSelected(false);
      northP.add(jmb);
      centerP.setLayout(new GridLayout(10,3)); 
      eastP.add(cppuCards);
      westP.add(hmnPileCards);
      newGame.addActionListener(this);
      southP.setLayout(new FlowLayout());
      eastP.setLayout(new FlowLayout()); 
      westP.setLayout(new FlowLayout());
      northP.setLayout(new FlowLayout());
      myContainer.add(northP, BorderLayout.NORTH);
      myContainer.add(centerP, BorderLayout.CENTER);
      myContainer.add(southP, BorderLayout.SOUTH);
      myContainer.add(westP, BorderLayout.WEST);
      myContainer.add(eastP, BorderLayout.EAST);
      westP.setBackground(Color.WHITE);
      centerP.setBackground(Color.lightGray);
      eastP.setBackground(Color.WHITE);
      southP.add(endTurn);
      endTurn.addActionListener(this);
      Icon humanHandPiles = new ImageIcon("C:\\Users\\kylea\\Documents\\GoFish-Java\\cardPics\\larger Y\\gofish.jpg"); //Try a less ... demanding path.. heh.
      endTurn.setIcon(humanHandPiles);
      endTurn.setPreferredSize(new Dimension(75, 98));
      for(int i = 0; i < 10; i++) {
         humanHand[i] = new JButton(String.valueOf(i));
         southP.add(humanHand[i]);
         humanHand[i].addActionListener(this);
      }
      Icon backofCard = new ImageIcon("C:\\Users\\kylea\\Documents\\GoFish-Java\\cardPics\\larger Y\\back.jpg"); //Try a less ... demanding path.. heh.
      for(int i = 0; i < 10; i++) {
          computerHandBacks[i] = new JButton();
          computerHandBacks[i].setPreferredSize(new Dimension(10, 10));
          computerHandBacks[i].setIcon(backofCard);
         eastP.add(computerHandBacks[i]);
      } 
      for(int i = 0; i < humanPileCards.length; i++) {
          humanPileCards[i] = new JButton();
          humanPileCards[i].setPreferredSize(new Dimension(10, 10));
          westP.add(humanPileCards[i]);
          humanPileCards[i].setVisible(false);
      } 
       for (int j = 0; j < fEngine.deckDealer.getListOfCards().size(); j ++){
                    if (j == humanPileCards.length){
          break;
          }
          String crd = fEngine.deckDealer.getListOfCards().get(j).toString().toLowerCase();
          System.out.println("crd is " + crd);
          Icon whichCrd = new ImageIcon("C:\\Users\\kylea\\Documents\\GoFish-Java\\cardPics\\"+crd+".jpg"); //Try a less ... demanding path.. heh.
          humanPileCards[j].setIcon(whichCrd);
        }
      
      centerP.add(cppuCards);
        for(int i = 0; i < computerPileCards.length; i++) {
            computerPileCards[i] = new JButton();
            computerPileCards[i].setPreferredSize(new Dimension(73, 98));
            String crd = fEngine.deckDealer.getListOfCards().get(i).toString().toLowerCase();
            System.out.println("crd is " + crd);
            Icon whichCrd = new ImageIcon("C:\\Users\\kylea\\Documents\\GoFish-Java\\cardPics\\"+crd+".jpg"); //Try a less ... demanding path.. heh.
            computerPileCards[i].setIcon(whichCrd);
            computerPileCards[i].setVisible(false);
            centerP.add(computerPileCards[i]);
        }   
      westP.add(wTitle);
      westP.add(hPts);
      westP.setLayout(new GridLayout(10,5));
      eastP.add(eTitle);
      eastP.add(cPts);
      eastP.setLayout(new GridLayout(4,5));
      centerP.add(cCards);
       startFresh();
     
   }
   public void startFresh(){
        fEngine.deckDealer.ResetDeck();
        fEngine.handComputer.resetHand();
        fEngine.handHuman.resetHand();
        fEngine.createDeck();
        fEngine.resetFeedbacks();
        for (int i = 0; i < computerPileCards.length; i++){
            computerPileCards[i].setVisible(false);
        }
        for (int i = 0; i < humanPileCards.length; i++){
            humanPileCards[i].setVisible(false);
        }
        fEngine.humanPoints = 0;
        fEngine.computerPoints = 0;
        fEngine.turnIgnition();
        updateGUI();
   }
   public void updateGUI()
   {
      int handSize = fEngine.handComputer.getHand().size();
      if (fEngine.handComputer.getisEmpty()){
        for(int i = 0; i<9; i++)
        {
           computerHandBacks[i].setVisible(false);   

        }
      }
      else{//Computer hand not empty
        for(int i = 0; i<10; i++)
        {
           boolean shouldShow =  i<handSize;
           //fEngine.debugInfo.append("\n I is " + i + " , handSize is " + handSize + " i < hanSize is " + shouldShow); too spammy
           computerHandBacks[i].setVisible(shouldShow);   

        }
      }
      
      for(int i = 0; i<fEngine.handHuman.getHand().size(); i++)
      {
         String value = fEngine.handHuman.getHand().get(i).toString(); 
         humanHand[i].setText(value); //hide rest
         humanHand[i].setVisible(true);   
         humanHand[i].setBackground(null);
          try {
            String searchString = value.toLowerCase();
            Icon icon = new ImageIcon("C:\\Users\\kylea\\Documents\\GoFish-Java\\cardPics\\larger Y\\"+searchString+".jpg"); //Try a less ... demanding path.. heh.
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
             if (humanHand[i].isVisible() && humanHand[i].getText().equals((humanHand[j].getText()))) {
                 humanHand[i].setBackground(Color.RED);
                 humanHand[j].setBackground(Color.RED);
             }
         }
         
      }
      
      pile.setText("human Pile" + fEngine.humanPile.toString() + "\n computer Pile" + fEngine.computerPile.toString());
      allprints.setText(fEngine.debugInfo.toString());
      turnCounterFBArea.setText(fEngine.turnCounterFB.toString());
      if (cpuHand.isSelected()){
          fEngine.turnCounterFB.append("\n["+fEngine.turnCounter+"] "+"Computer Hand: " + fEngine.getComputerHandDisplay()+"\n");
      }
      if (dckHand.isSelected()){
          fEngine.turnCounterFB.append("\n"+"["+fEngine.turnCounter+"] "+"Deck Hand: " + fEngine.getdeckHandDisplay()+"\n");
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
      
      //Human pair
      if (fEngine.humanPileList.size() > 0){
        for (int i = 0; i < fEngine.humanPileList.size(); i ++){
            for (int j = 0; j < humanPileCards.length; j ++){
            //System.out.println("humanPileCards[j].getIcon().toString() is " + humanPileCards[j].getIcon().toString());
            //System.out.println("fEngine.humanPileList.get(i).toString().toLowerCase()) is " + fEngine.humanPileList.get(i).toString().toLowerCase());
             if (!humanPileCards[j].isVisible() && humanPileCards[j].getIcon().toString().contains(fEngine.humanPileList.get(i).toString().toLowerCase())){
                 humanPileCards[j].setVisible(true);
                 fEngine.humanPileListForLoading.add(fEngine.humanPileList.get(i));
                 fEngine.humanPileList.remove(i);//So duplicates aren't re-added. This list will be updated.
                 break; 
             }
            }

         }
      }
      
      //Coomputer pair
      if (fEngine.computerPileList.size() > 0){
        for (int i = 0; i < fEngine.computerPileList.size(); i ++){
            for (int j = 0; j < computerPileCards.length; j ++){
            //System.out.println("computerPileCards[j].getIcon().toString() is " + computerPileCards[j].getIcon().toString());
            //System.out.println("fEngine.computerPileList.get(i).toString().toLowerCase()) is " + fEngine.computerPileList.get(i).toString().toLowerCase());
             if (!computerPileCards[j].isVisible() && computerPileCards[j].getIcon().toString().contains(fEngine.computerPileList.get(i).toString().toLowerCase())){
                 computerPileCards[j].setVisible(true);
                 fEngine.computerPileListForLoading.add(fEngine.computerPileList.get(i));
                 fEngine.computerPileList.remove(i);//So duplicates aren't re-added. This list will be updated.
                 break;
             }
            }

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
      }
      else if (e.getSource() == newGame)
      {
         System.out.println("New Game Selected");
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
         fEngine.handHuman.removePair(value);
         fEngine.humanPile.append(" ("+value+")");
         fEngine.humanPileList.add(value);
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
            fEngineRead.computerPileList = ( (ArrayList) sin.readObject() );
            fEngineRead.humanPileList = ( (ArrayList) sin.readObject() );
            
            fEngine = fEngineRead;
            fEngine.deckDealer = fEngineRead.deckDealer;
            fEngine.handHuman = fEngineRead.handHuman;
            fEngine.handComputer = fEngineRead.handComputer;
            fEngine.feedBack = fEngineRead.feedBack;
            fEngine.humanPile = fEngineRead.humanPile;
            fEngine.computerPile = fEngineRead.computerPile;
            fEngine.turnCounterFB = fEngineRead.turnCounterFB;
            fEngine.computerPileList = fEngineRead.computerPileListForLoading;
            fEngine.humanPileList = fEngineRead.humanPileListForLoading;

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
            sout.writeObject(fEngine.computerPileListForLoading); //Not useful because the old values are removed
            sout.writeObject(fEngine.humanPileListForLoading);//Not useful because the old values are removed
            //Once the first from PileList is added, the gui removes from list to not add duplicate
            //Unless we have ANOTHER which lists all o_O .. or ... uhhh ..? lol.
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
   
   public void openWindowsForDeckInfo(){
       
       JFrame frame = new JFrame("DeckInfo");
       frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try 
           {
              UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
           } catch (Exception e) {
              e.printStackTrace();
           }
       JPanel aPanel = new JPanel();  
       aPanel.setLayout(new FlowLayout());
       aPanel.add(scroll);
        frame.getContentPane().add(BorderLayout.CENTER, aPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
       deckHandWindowOpen = true;
       
        frame.addWindowListener(new WindowAdapter(){
                        public void windowClosing(WindowEvent e){
                                cpuHand.setSelected(false);
                                dckHand.setSelected(false);
                                deckHandWindowOpen = false;
                                frame.setVisible(false);
                                frame.dispose();
                        }
                    });
   }
   public void openWindowsForTurnInfo(){
       
       JFrame frame = new JFrame("TurnCounter");
       frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try 
           {
              UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
           } catch (Exception e) {
              e.printStackTrace();
           }
       JPanel aPanel = new JPanel();  
       aPanel.setLayout(new FlowLayout());
       aPanel.add(scrollTwo);
       //JScrollPane  scrollNewWindow = new JScrollPane(turnCounterFBArea);
        frame.getContentPane().add(BorderLayout.CENTER, aPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
       isTurnLogWindowOpen = true;
        frame.addWindowListener(new WindowAdapter(){
                        public void windowClosing(WindowEvent e){
                                trnLog.setSelected(false);
                                 isTurnLogWindowOpen = false;
                                frame.setVisible(false);
                                frame.dispose();
                        }
                    });
   }  
      
   public void openWindowsForPlayerPile(){
       
       JFrame frame = new JFrame("Player Piles");
       frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try 
           {
              UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
           } catch (Exception e) {
              e.printStackTrace();
           }
       JPanel aPanel = new JPanel();  
       aPanel.setLayout(new FlowLayout());
       aPanel.add(scrollThree);
        frame.getContentPane().add(BorderLayout.CENTER, aPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
       isPlayerPileWindowOpen = true;
        frame.addWindowListener(new WindowAdapter(){
                        public void windowClosing(WindowEvent e){
                                //System.exit(0);
                                playerPiles.setSelected(false);
                                isPlayerPileWindowOpen = false;
                                frame.setVisible(false);
                                frame.dispose();
                                //frame.removeAll();
                        }
                    });

   }
      public void openWindowsForAllDebug(){
       
       JFrame frame = new JFrame("Turn Match Scan");
       frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try 
           {
              UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
           } catch (Exception e) {
              e.printStackTrace();
           }
       JPanel aPanel = new JPanel();  
       aPanel.setLayout(new FlowLayout());
       aPanel.add(scrollFour);
        frame.getContentPane().add(BorderLayout.CENTER, aPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
       isallDebugWindowOpen = true;
       
        frame.addWindowListener(new WindowAdapter(){
                        public void windowClosing(WindowEvent e){
                                allDebug.setSelected(false);
                                isallDebugWindowOpen = false;
                                frame.setVisible(false);
                                frame.dispose();
                        }
                    });
   }
   public class CheckBoxListener implements ItemListener
   {  
      public void itemStateChanged(ItemEvent e)
      {	// process checkbox events
         if ( e.getSource() == cpuHand )	
         {
             if (!deckHandWindowOpen){
                 openWindowsForDeckInfo();
             }
         }
         else if ( e.getSource() == dckHand )	
         {
             if (!deckHandWindowOpen){
                 openWindowsForDeckInfo();
             }
         }
         else if (e.getSource() == trnLog){
             if (!isTurnLogWindowOpen){
                 openWindowsForTurnInfo();
             }
         }
         else if (e.getSource() == playerPiles){
             if (!isPlayerPileWindowOpen){
                 openWindowsForPlayerPile();
             }
         }
         else if (e.getSource() == allDebug){
             if (!isallDebugWindowOpen){
                 openWindowsForAllDebug();
             }
         }
      }
   }  
}