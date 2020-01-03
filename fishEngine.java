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
   protected String valueToScan = "0"; //number chosen by human/computer to scan hands for
   protected int humanPoints = 0;
   protected int computerPoints = 0;
   protected int turnCounter = 1; //Starts at 1!
   private boolean cheating = true; //always assume
   protected deckofDealer deckDealer = new deckofDealer();
   protected handofHuman handHuman = new handofHuman();
   protected handofComputer handComputer = new handofComputer();
   
   protected static ArrayList<String> humanPairedCards = new ArrayList<String>();//J2
   protected static ArrayList<String> ComputerPairedCards = new ArrayList<String>();//J2
   private boolean gameEnd = false; //asserting turns like clocks
   ///////////Obj. 2///////////////////////////
   protected static StringBuilder turnCounterFB = new StringBuilder(""); 
   protected  JTextArea textArea = new JTextArea("", 25, 30); 
   
   protected  static StringBuilder feedBack = new StringBuilder(); 
   protected  static StringBuilder humanPile = new StringBuilder(""); 
   protected  static StringBuilder computerPile = new StringBuilder(""); 
 
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
   
   public void resetFeedbacks(){
        feedBack = new StringBuilder();
        humanPile = new StringBuilder(""); 
        computerPile = new StringBuilder(""); 
        turnCounterFB = new StringBuilder(""); 
        StringBuilder computerPile = new StringBuilder(""); 
        humanPairedCards = new ArrayList<String>();
        ComputerPairedCards = new ArrayList<String>();
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
      //while(!gameOver)
      //{
      gameOver = getisDeckDealerEmpty();// && handHuman.getisEmpty() && handComputer.getisEmpty();
         //if (humanTurn) { humanInput(); } 
         //else if (computerTurn) { computerInput() ; }
      //}
   
      if (gameOver)
      {
          /*
         feedBack.append("\n!!Game Over!! (Warning 1 of 4)"); 
         feedBack.append("\n!!Game Over!! (Warning 2 of 4)"); 
         feedBack.append("\n!!Game Over!! (Warning 3 of 4)"); 
         feedBack.append("\n!!Game Over!! (Warning 4 of 4)"); 
         feedBack.append("\nhumanPoints = " + humanPoints + " , computerPoints = " + computerPoints); 
         //System.exit(0);
        */
      }
   
   }
 ///////////////////////////////////////////////////////////////////////////
 /////////////////////////human Main///////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
   public void humanInput()
   {
      turnManage();

      if  ( handHuman.getisEmpty() && !getisDeckDealerEmpty()){ 
         feedBack.append( "\nEmpty hand, drawing card."); //fix
      }
     
     
      if  ( getisDeckDealerEmpty() ) {
         feedBack.append("\ndeckDealer deck is empty!"); 
      }

      System.out.print("\n turncounter: " + turnCounter + " Pick a card from your hand: " + getHumanHandDisplay() + " \n");
      turnCounterFB.append("\n turncounter: " + turnCounter + " Pick a card from your hand: " + getHumanHandDisplay() + " \n");

      if (valueToScan == "0"){
        feedBack.append("\n"+"["+turnCounter+"] "+"Human calls GoFish!");
      }
      else{
        feedBack.append("\n"+"["+turnCounter+"] "+"Human chose card #" + valueToScan);
      }
    
   }
   public void humanAlgorithm()
   {
      boolean isZero = (getScanValue() == "0");
      cheating = getIsHumanCheating();
      match = getIsHumanMatch();
      if (!computerTurn && humanTurn && !cheating )//&& !pair)
      {
         System.out.println("Match is " + match);
         if (!isZero && match)
         {
            feedBack.append( "\n"+"["+turnCounter+"] "+"Match! Go Again!");
            humanPairedCards.add( valueToScan );
            humanPairedCards.add( valueToScan );
            humanPile.append(" ("+valueToScan+")");
            removeCardHumanComputer();
            match = false;
            humanPoints = humanPoints + 1;
         }
         else
         {
            if (!getisDeckDealerEmpty()) 
            { 
               handHuman.addCard(handComputer, deckDealer);
               feedBack.append("\n"+"["+turnCounter+"] "+"GoFish! (humanTurn)");
               turnCounter += 1;
            }
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
   public void doDuplicatesFirst(){
       //Lets just do one pair right now and later on worry about two pairs.
      for(int i = 0; i<this.handComputer.getHand().size(); i++)
      {
         //check for duplicates
         for (int j = i + 1; j < this.handComputer.getHand().size(); j ++){
             if (this.handComputer.handComputer.get(i).equals(this.handComputer.handComputer.get(j))) {
                 System.out.println("[doDuplicatesFirst] Found Matching Numbers: " + this.handComputer.handComputer.get(i));
                 computerPoints = computerPoints + 1; 
                 feedBack.append("\n"+"["+turnCounter+"] "+"Computer placing down pair ("+ this.handComputer.handComputer.get(i)+")");
                 String value = this.handComputer.handComputer.get(i);
                 computerPile.append(" ("+value+")");
                 handComputer.removePair(value);
                 break;//One pair at the moment
             }
         }
         
      }
   }
   public  void computerInput()
   {
      turnManage();
      setScanValue("0");
      match = false;
      computerTurn = true;
      if ( handComputer.getisEmpty() ){ 
        if (!getisDeckDealerEmpty()){
            handComputer.addCard(handHuman, handComputer, deckDealer);
            feedBack.append("\n"+"["+turnCounter+"] "+"Computer Hand Empty, Drawing Card");
            }
        else{
            feedBack.append("\n"+"["+turnCounter+"] "+"Computer Hand Empty, Deck Dealer Empty");
            computerTurn = false;
            }
        }
      //if (!computerTurn) {
         //return computerTurn;}
      System.out.println("\ncomputerPoints = " + computerPoints + ", humanPoints = " + humanPoints);
      System.out.println("\nturnCounter = " + turnCounter);
       //Grab a random number based on difficulty level
      doDuplicatesFirst();
      setComputerNumber();
      boolean isZero = (getScanValue() == "0");
      if (isZero){
        feedBack.append("\n"+"["+turnCounter+"] "+"GoFish! (computerTurn)");  
      }
      else{
        feedBack.append("\n"+"["+turnCounter+"] "+"Computer chose card #" + valueToScan);
      }
   
      match = getIsComputerMatched(isZero);
      if (match)
      {
         computerPoints = computerPoints + 1; 
         removeCardHumanComputer();
         ComputerPairedCards.add( valueToScan ); ComputerPairedCards.add( valueToScan );
         computerPile.append(" ("+valueToScan+")");
      }
      else
      { 
         if (!match && !isZero && !getisDeckDealerEmpty()) 
         {
            handComputer.addCard(handHuman, handComputer, deckDealer);
            feedBack.append("\n"+"["+turnCounter+"] "+"GoFish! (computerTurn)");
            
         } 
         turnCounter = turnCounter + 1; 
         humanTurn = true;
         computerTurn = false;
      }
      
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

   public void setComputerNumber()
   {
                
      Random random = new Random();
      int tunedNumber = random.nextInt(100);
      int difficulty = random.nextInt(30 + 1 - 10) + 10;
      if (difficulty >= tunedNumber) 
      {
         int idx = random.nextInt(handHuman.getHand().size());
         String value = handHuman.getHand().get(idx).toString();
         setScanValue(value);
      }
      else
      { 
         int idx = random.nextInt(handComputer.getHand().size());
         setScanValue( handComputer.getHand().get(idx).toString() ); 
      }
   }
   public boolean getIsComputerMatched(boolean isZero)
   {
       //If zero then draw card
      if (isZero && !getisDeckDealerEmpty())
      {
         System.out.println("computer is drawing card");
         handComputer.addCard(handHuman, handComputer, deckDealer);
         //feedBack.append("\nGoFish! (computerTurn)");
      } 
       //Scan human hand w. valueToScan 
      boolean hasPicked = false;
          
      for (int i = 0; i < handHuman.getHand().size(); i++) 
      {
         if ( !hasPicked && !isZero && handHuman.getHand().get(i).equals(valueToScan))
         { 
            //handComputer.MatchPicked(getScanNumber(), hasPicked );
            //feedBack.append( "\n(computer):Do you have a " + valueToScan + "?" + "(matched)");
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
   public String handComputerCardsPaired()
   {
     
      String handComputerPairedCards = "";
      for(int i = 0; i<handComputer.getHand().size(); i++)
      {
         String pairedCards = handComputer.getHand().get(i).toString();
         handComputerPairedCards += pairedCards +" ";        
      }
   
      return handComputerPairedCards; 
   }
   public String getHumanHandDisplay()
   {
      String handHumanCards = "";
      for(int i = 0; i<handHuman.getHand().size(); i++)
      {
         String handCards = handHuman.getHand().get(i).toString();
         handHumanCards += handCards +" ";        
      }
   
      return handHumanCards;
   }
   
   public String getComputerHandDisplay()
   {
      String handComputerCards = "";
      for(int i = 0; i<handComputer.getHand().size(); i++)
      {
         String handCards = handComputer.getHand().get(i).toString();
         handComputerCards += handCards +" ";        
      }
   
      return handComputerCards;
   }
   
   public    boolean getIsHumanCheating()
   {
      cheating = true;
      for (int i = 0; i < handHuman.getHand().size(); i++) 
      {
         if (handHuman.getHand().get(i).equals(valueToScan)){cheating = false;}
      }
      if (getScanValue() == "0" ) {cheating = false;}
      if (cheating) {feedBack.append("\nYou are cheating. You do not own card # " + valueToScan + "! Try Again!");  }
      return cheating;
   }
   public void humanPairNonCheating(String value)
   {
   
      handHuman.removePair(value); 
      humanPoints = humanPoints + 1;
      turnCounter = turnCounter + 1;
               // setComputerTurn(true); 
      humanPairedCards.add( value ); humanPairedCards.add( value );
   }
   public boolean getIsHumanMatch()
   {
      boolean isMatch = false;
      for (int i = 0; i < handComputer.getHand().size(); i++) 
      {
         if (handComputer.getHand().get(i).equals(valueToScan))
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
         if (handHuman.getHand().get(i).equals(valueToScan))
         {
            System.out.println("removing from human card # " + handHuman.getHand().get(i));
            handHuman.getHand().remove(i);
            handHuman.getHand().trimToSize();
            break;
         }
      }    
        
      for (int i = 0; i < handComputer.getHand().size(); i++) 
      {
         if (handComputer.getHand().get(i).equals(valueToScan))
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

      if  (handHuman.getisEmpty() )
      {
         handHuman.EmptyHand(handHuman,handComputer, deckDealer);
         continueTurn = false;
      }
      return continueTurn;
   }
  
   public String getScanValue()
   {
      return valueToScan;
   }
   public void setScanValue(String val)
   {
      valueToScan = val;
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

