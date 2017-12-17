import javax.swing.JOptionPane;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class fishEngine  implements Serializable
{

   /////////////////////////////////////////////////////////////////////
 /////////////////////////Declarations///////////////////////////////
//////////////////////////////////////////////////////////////////////
   private boolean match = false;
   protected boolean humanTurn = true;
   private boolean computerTurn = false;
   private int numberToScan = 0; //number chosen by human/computer to scan hands for
   private int humanPoints = 0;
   private int computerPoints = 0;
   private int turnCounter = 0;
   private boolean cheating = true; //always assume
   protected deckofDealer deckDealer = new deckofDealer();
   protected handofHuman handHuman = new handofHuman();
   protected handofComputer handComputer = new handofComputer();
   private ArrayList<Integer> humanPairedCards = new ArrayList<Integer>();//J2
   private boolean gameEnd = false; //asserting turns like clocks
   ///////////Obj. 2///////////////////////////
   protected static StringBuilder stringBuilder = new StringBuilder(""); 
   protected  JTextArea textArea = new JTextArea("", 25, 30); 
   
   //-------JButton------//
   private JButton goldFishBtn = new JButton("GoldFish");
   
   
   JPanel  northP = new JPanel();
   JLabel  northTitle = new JLabel("                          Tickets Tigers Tickets            ");
   
   
   ///////////Obj. 2///////////////////////////
   
///////////////////////////////////////////////////////////////////////////
 /////////////////////////Declarations///////////////////////////////
///////////////////////////////////////////////////////////////////////////
   public fishEngine()
   {
   }
   
   public handofHuman gethandHuman()
   {
      return handHuman;
   }
   
   
   public void turnIgnition()
   {
       //Create deckdealer deck
      createDeck();
   
   //Start turns
      turnManage();  
   }
   
   public   String getTextArea()
   {
      return textArea.toString();
   }
   //}
   public void createDeck()
   {
      deckDealer.CreateDeck(); 	
      for (int i = 0; i <5; i++) 
      {
      // Create human && computer deck
         handofHuman.createHand(deckDealer.getDeck());
         handofComputer.createHand(deckDealer.getDeck());
      }
   }
   public void turnManage()
   {  //This way the turns go back and forth matching boolean
      boolean gameOver = getisDeckDealerEmpty(); //&& handHuman.getisEmpty() && handComputer.getisEmpty();
      while(!gameOver)
      {
         gameOver = getisDeckDealerEmpty();// && handHuman.getisEmpty() && handComputer.getisEmpty();
         //if (humanTurn) { humanInput(); } 
         //else if (computerTurn) { computerInput() ; }
      }
   
      if (gameOver)
      {
         JOptionPane.showMessageDialog(null,"!!Game Over!! (Warning 1 of 4)"); 
         JOptionPane.showMessageDialog(null,"!!Game Over!! (Warning 2 of 4)"); 
         JOptionPane.showMessageDialog(null,"!!Game Over!! (Warning 3 of 4)"); 
         JOptionPane.showMessageDialog(null,"!!Game Over!! (Warning 4 of 4)"); 
         JOptionPane.showMessageDialog(null,"humanPoints = " + humanPoints + " , computerPoints = " + computerPoints); 
         System.exit(0);
      }
   
   }
 ///////////////////////////////////////////////////////////////////////////
 /////////////////////////human Main///////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
   public void humanInput()
   {
    
      //setScanNumber(0); 
      match = false;
   
         //Add card so error doesn't appear!sd
      if  ( handHuman.getisEmpty() && !getisDeckDealerEmpty() )
      { 
         handHuman.EmptyHand(handHuman,handComputer, deckDealer);
      }
     
     
      if  ( deckDealer.getisEmpty() ) 
      {
         JOptionPane.showMessageDialog(null,"deckDealer deck is empty!"); 
      }
      
      
   
      System.out.print("\nYour already paired cards are: " + handHumanCardsPaired());
      System.out.print("\ndeckDealer: " + deckDealer.getDeck().size() + " cards left in the deck\n" );
      System.out.println("computerPoints = " + computerPoints + " humanPoints = " + humanPoints);
      System.out.print("\n (0 to draw card, double number to place down pair)"); // or double number to trade pair for points (eg: 99 for two 9's on hand) )" + output);
      System.out.print("\nPick a card from your hand: " + getHumanHandDisplay() + " \n");
    //Change from print to GUI obj. 2 && APPEND
      stringBuilder.append("\nYour already paired cards are: " + handHumanCardsPaired());
      stringBuilder.append("\ndeckDealer: " + deckDealer.getDeck().size() + " cards left in the deck\n" );
      stringBuilder.append("computerPoints = " + computerPoints + " humanPoints = " + humanPoints);
      stringBuilder.append("\n (0 to draw card, double number to place down pair)"); // or double number to trade pair for points (eg: 99 for two 9's on hand) )" + output);
      stringBuilder.append("\nPick a card from your hand: " + getHumanHandDisplay() + " \n");
   //stringBuilder.append();
      //textArea.setText(stringBuilder.toString());
   
      //Scanner keyboard = new Scanner(System.in);
      //setScanNumber(keyboard.nextInt());  
      System.out.println("\nhuman chose card #" + numberToScan);
   
   
      //humanAlgorithm();
      //super.updateArea();
      //return getHumanTurn();
   
    
   }
   public void humanAlgorithm()
   {
      int one = numberToScan / 10;
      int two = numberToScan % 10;
      boolean pair = (one == two && numberToScan != 0); //tough lesson
      int tunedNumber = 0;
      boolean isZero = (getScanNumber() == 0);
      if (pair) { tunedNumber = one; }
      if (tunedNumber != 0) { setScanNumber(tunedNumber); }
    
    
    //Check if player has desired card otherwise cheating!
      cheating = getIsHumanCheating();
   
     //Remove two of the same card
      if (pair && !cheating){ humanPairNonCheating(one, two);}
      //Scan computers hand w. numberToScan returning if match
      match = getIsHumanMatch(isZero);
           //Match
      if (!computerTurn && humanTurn && !cheating && !pair)
      {
         if (match)
         {
            JOptionPane.showMessageDialog(null, "Match! Go Again!");
            humanPairedCards.add( numberToScan ); humanPairedCards.add( numberToScan );
            removeCardHumanComputer();
            match = false;
            humanPoints = humanPoints + 1;
         }
         else //GoFish
         {
            if (!getisDeckDealerEmpty()) { handHuman.addCard(handComputer, deckDealer); }
            humanTurn = false; 
            computerTurn = true;
         }
      }
      
      if (computerTurn)
      { 
         computerInput() ;
      }
              
              
          
   }
   ///////////////////////////////////////////////////////////////////////////
 /////////////////////////human Main///////////////////////////////////////
///////////////////////////////////////////////////////////////////////////

 ///////////////////////////////////////////////////////////////////////////
 /////////////////////////computer Main///////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
   public  void computerInput()
   {
      setScanNumber(0);
      match = false;
      computerTurn = checkforEmptyHands();
      //if (!computerTurn) {
         //return computerTurn;}
      System.out.println("\ncomputerPoints = " + computerPoints + ", humanPoints = " + humanPoints);
      System.out.println("\nturnCounter = " + turnCounter);
       //Grab a random number based on difficulty level
      setComputerNumber();
      boolean isZero = (getScanNumber() == 0);
      System.out.println("computer chose card #" + numberToScan);
   
      match = getIsComputerMatched(isZero);
      if (match){computerPoints = computerPoints + 1; removeCardHumanComputer();}
      else{ 
         if (!match && !isZero && !getisDeckDealerEmpty()) {handComputer.addCard(handHuman, handComputer, deckDealer);} turnCounter = turnCounter + 1; humanTurn = true;computerTurn = false;}
      
      //return computerTurn;
      if (computerTurn)
      { 
         computerInput() ;
      }
       
   }
  ///////////////////////////////////////////////////////////////////////////
 /////////////////////////computer Main///////////////////////////////////////
///////////////////////////////////////////////////////////////////////////


 ///////////////////////////////////////////////////////////////////////////
 /////////////////////////computer Misc///////////////////////////////////////
///////////////////////////////////////////////////////////////////////////  
   public    void setComputerNumber()
   {
      Random random = new Random();
      System.out.println("wtf");
      int tunedNumber = random.nextInt(100);
      int difficulty = random.nextInt(30 + 1 - 10) + 10;
      if (difficulty >= tunedNumber) 
      {
         int idx = random.nextInt(handHuman.getHand().size());
         int value = (int)handHuman.getHand().get(idx);
         setScanNumber(value);
      }
      else
      { 
         setScanNumber( random.nextInt(handComputer.getHand().size()) );
      }
   }
   public    boolean getIsComputerMatched(boolean isZero)
   {
       //If zero then draw card
      if (isZero && !getisDeckDealerEmpty()){System.out.println("computer is drawing card");handComputer.addCard(handHuman, handComputer, deckDealer);} 
       //Scan human hand w. numberToScan 
      boolean hasPicked = false;
          
      for (int i = 0; i < handHuman.getHand().size(); i++) 
      {
         if ( !hasPicked && !isZero && handHuman.getHand().get(i).equals(numberToScan))
         { 
            handComputer.MatchPicked(getScanNumber(), hasPicked );
            match = true;
            hasPicked = true;
         }
      }
      return match;
   }
 
 ///////////////////////////////////////////////////////////////////////////
 /////////////////////////computer Misc///////////////////////////////////////
///////////////////////////////////////////////////////////////////////////  


 ///////////////////////////////////////////////////////////////////////////
 /////////////////////////human Misc///////////////////////////////////////
///////////////////////////////////////////////////////////////////////////  

   public    String handHumanCardsPaired()
   {
     
      String handHumanCardsPaired = "";
      for(int i = 0; i<humanPairedCards.size(); i++)
      {
         String pairedCards = humanPairedCards.get(i).toString();
         handHumanCardsPaired += pairedCards +" ";        
      }
   
      return handHumanCardsPaired; 
   }
   public    String getHumanHandDisplay()
   {
      String handHumanCards = "";
      for(int i = 0; i<handHuman.getHand().size(); i++)
      {
         String handCards = handHuman.getHand().get(i).toString();
         handHumanCards += handCards +" ";        
      }
   
      return handHumanCards;
   }
   public    boolean getIsHumanCheating()
   {
      cheating = true;
      for (int i = 0; i < handHuman.getHand().size(); i++) 
      {
         if (handHuman.getHand().get(i).equals(numberToScan)){cheating = false;}
      }
      if (getScanNumber() == 0 ) {cheating = false;}
      if (cheating) {JOptionPane.showMessageDialog(null,"You are cheating. You do not own card # " + numberToScan + "! Try Again!");  }
      return cheating;
   }
   public    void humanPairNonCheating(int one, int two)
   {
   
      handHuman.removePair(one, two); 
      humanPoints = humanPoints + 1;
      turnCounter = turnCounter + 1;
               // setComputerTurn(true); 
      humanPairedCards.add( one ); humanPairedCards.add( two );
   }
   public    boolean getIsHumanMatch(boolean isZero)
   {
      boolean isMatch = false;
      for (int i = 0; i < handComputer.getHand().size(); i++) 
      {
         if (!isZero && handComputer.getHand().get(i).equals(numberToScan))
         {
            isMatch = true;
         }
      }
      return isMatch;
   }
  
 ///////////////////////////////////////////////////////////////////////////
 /////////////////////////human Misc///////////////////////////////////////
/////////////////////////////////////////////////////////////////////////// 

 ///////////////////////////////////////////////////////////////////////////
 ///////////////////////// Misc///////////////////////////////////////
/////////////////////////////////////////////////////////////////////////// 

   public void removeCardHumanComputer()
   {
    
      for (int i = 0; i < handHuman.getHand().size(); i++) 
      {
         if (handHuman.getHand().get(i).equals(numberToScan))
         {
            System.out.println("removing from human card # " + handHuman.getHand().get(i));
            handHuman.getHand().remove(i);
            handHuman.getHand().trimToSize();
            break;
         }
      }    
        
      for (int i = 0; i < handComputer.getHand().size(); i++) 
      {
         if (handComputer.getHand().get(i).equals(numberToScan))
         {
            System.out.println("removing from computer card # " + handComputer.getHand().get(i));
            handComputer.getHand().remove(i);
            handComputer.getHand().trimToSize();
            break;
         }
      }                  
   }
   public  boolean checkforEmptyHands()
   {
      boolean continueTurn = true;
      if ( handComputer.getisEmpty() )
      {handComputer.EmptyHand(handHuman,handComputer, deckDealer);
         continueTurn = false;
      }
      if  ( handHuman.getisEmpty() )
      {
         handHuman.EmptyHand(handHuman,handComputer, deckDealer);
         continueTurn = false;
      }
      return continueTurn;
   }
  
   public    int getScanNumber()
   {
      return numberToScan;
   }
   public    void setScanNumber(int num)
   {
      numberToScan = num;
   }
   public    boolean getHumanTurn()
   {
      return humanTurn;
   }
   public    void setHumanTurn(boolean bool)
   {
      humanTurn = bool;
   }
   public    boolean getComputerTurn()
   {
      return computerTurn;
   }
   public    void setComputerTurn(boolean bool)
   {
      computerTurn = bool;
   }
   public    boolean getisDeckDealerEmpty()
   {
      return deckDealer.getisEmpty();
   }
   
}


 ///////////////////////////////////////////////////////////////////////////
 ///////////////////////// Misc///////////////////////////////////////
/////////////////////////////////////////////////////////////////////////// 

