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
   
   protected static ArrayList<String> ComputerPairedCards = new ArrayList<String>();//J2
   private boolean gameEnd = false; //asserting turns like clocks
   ///////////Obj. 2///////////////////////////
   protected static StringBuilder turnCounterFB = new StringBuilder(""); 
   protected  JTextArea textArea = new JTextArea("", 25, 30); 
   
   protected  static StringBuilder feedBack = new StringBuilder(); 
   protected  static StringBuilder humanPile = new StringBuilder(""); 
   protected  ArrayList<String> computerPileList = new ArrayList<String>();
   protected  static StringBuilder computerPile = new StringBuilder(""); 
   protected  ArrayList<String> humanPileList = new ArrayList<String>();
   protected  static StringBuilder debugInfo = new StringBuilder(""); 
 
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
        computerPileList = new ArrayList<String>();
        humanPileList = new ArrayList<String>();
        feedBack = new StringBuilder();
        humanPile = new StringBuilder(""); 
        computerPile = new StringBuilder(""); 
        turnCounterFB = new StringBuilder(""); 
        computerPile = new StringBuilder(""); 
        ComputerPairedCards = new ArrayList<String>();
        humanPileList = new ArrayList<String>();
        computerPileList = new ArrayList<String>();
   }
   
   
   public handofHuman gethandHuman()
   {
      return handHuman;
   }
   
   
   public void turnIgnition()
   {
       //Create deckdealer deck
      //createDeck();
   
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
      debugInfo.append("\nSize of Deck: " + this.deckDealer.getSize());
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
        //feedBack.append("\n"+"["+turnCounter+"] "+"humanInput");
      /*
      if  ( handHuman.getisEmpty() && !getisDeckDealerEmpty()){ 
         feedBack.append( "\nEmpty hand, drawing card."); //fix
         //But its not o_O lol
      }
     */
     
      if  ( getisDeckDealerEmpty() ) {
         feedBack.append("\ndeckDealer deck is empty!"); 
      }

      //System.out.print("\n turncounter: " + turnCounter + " Pick a card from your hand: " + getHumanHandDisplay() + " \n");
      //turnCounterFB.append("\n turncounter: " + turnCounter + " Pick a card from your hand: " + getHumanHandDisplay() + " \n");
      
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
         debugInfo.append("\nMatch is " + match);
         if (!isZero && match)
         {
            feedBack.append( "\n"+"["+turnCounter+"] "+"Match! Go Again!");

            humanPile.append(" ("+valueToScan+")");
            humanPileList.add(valueToScan);
            removeCardHumanComputer();
            match = false;
            humanPoints = humanPoints + 1;
         }
         else//Zero or not match
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
      else if (humanTurn){
          humanInput();
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
                 debugInfo.append("\n[doDuplicatesFirst] Found Matching Numbers: " + this.handComputer.handComputer.get(i));
                 computerPoints = computerPoints + 1; 
                 feedBack.append("\n"+"["+turnCounter+"] "+"Computer placing down pair ("+ this.handComputer.handComputer.get(i)+")");
                 String value = this.handComputer.handComputer.get(i);
                 computerPile.append(" ("+value+")");
                 computerPileList.add(value);
                 handComputer.removePair(value);
                 break;//One pair at the moment
             }
         }
         
      }
   }
   public void computerInput()
   {
      debugInfo.append("\nComputer Hand Prior to turn: " + this.getComputerHandDisplay());
      turnManage();
      setScanValue("0");
      match = false;
      computerTurn = true;
      if ( handComputer.getisEmpty() ){ 
        if (!getisDeckDealerEmpty()){
            handComputer.addCard(handHuman, handComputer, deckDealer);
            feedBack.append("\n"+"["+turnCounter+"] "+"GoFish! Computer Hand Empty, Drawing Card");
                //Gofish
                turnCounter = turnCounter + 1; 
                humanTurn = true;
                computerTurn = false;
                return;
            }
        else{
            feedBack.append("\n"+"["+turnCounter+"] "+"Computer Hand Empty, Deck Dealer Empty");
                turnCounter = turnCounter + 1; 
                humanTurn = true;
                computerTurn = false;
                return;
            }
        }
      if (handHuman.getisEmpty() ){
          if (!getisDeckDealerEmpty()){
            feedBack.append("\n"+"["+turnCounter+"] "+"Human Hand Empty, Drawing Card for human then continuing computer turn");
            handHuman.addCard(handComputer, deckDealer);
            debugInfo.append("\nHuman Hand Empty, Drawing Card for human then continuing computer turn");
          }
          else{
              feedBack.append("\n"+"["+turnCounter+"] "+"Human Hand Empty, Deck Empty, Computer Turn. Error!");
              debugInfo.append("\nHuman Hand Empty, Deck Empty, Computer Turn. Error!");
          }
      }
      //if (!computerTurn) {
         //return computerTurn;}
      debugInfo.append("\ncomputerPoints = " + computerPoints + ", humanPoints = " + humanPoints);
      debugInfo.append("\nturnCounter = " + turnCounter);
       //Grab a random number based on difficulty level
      doDuplicatesFirst();
      setComputerNumber();
      boolean isZero = (getScanValue() == "0");
      if (isZero){ // Gofish
        handComputer.addCard(handHuman, handComputer, deckDealer);
        feedBack.append("\n"+"["+turnCounter+"] "+"GoFish! (computerTurn)");
        turnCounter = turnCounter + 1; 
        humanTurn = true;
        computerTurn = false;
        return;
      }
      else{
        feedBack.append("\n"+"["+turnCounter+"] "+"Computer chose card #" + valueToScan);
      }
   
      match = getIsComputerMatched();
      if (match)
      {
         computerPoints = computerPoints + 1; 
         removeCardHumanComputer();
         ComputerPairedCards.add( valueToScan ); ComputerPairedCards.add( valueToScan );
         computerPile.append(" ("+valueToScan+")");
         computerPileList.add(valueToScan);
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
      debugInfo.append("\nComputer Hand Post to turn: " + this.getComputerHandDisplay());
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
      int difficulty = random.nextInt(30 + 1 - 10) + 10; // Computer is not matching
      ArrayList<String> tempEligable = new ArrayList<String>();
      for(int i = 0; i<this.handComputer.getHand().size(); i++)
      {
         //check for Commonalities
         for (int j = i + 1; j < this.handHuman.getHand().size(); j ++){
             if (this.handComputer.handComputer.get(i).equals(this.handHuman.handHuman.get(j))) {
                 String value = this.handComputer.handComputer.get(i);
                 tempEligable.add(value);
             }
         }
         
      }
       if (tempEligable.isEmpty()){
           setScanValue("0");
       }
      if (difficulty >= tunedNumber && getScanValue() != "0") 
      {
         int idx = random.nextInt(tempEligable.size()); //Can error here if human hand is empty. 
         String value = tempEligable.get(idx).toString();
         setScanValue(value);
      }
      else
      { 
         if (handComputer.handComputer.isEmpty()){
             setScanValue("0");
         }
         else{
            int idx = random.nextInt(handComputer.getHand().size()); //Errors here if computer hand is empty
            setScanValue( handComputer.getHand().get(idx).toString() ); 
          }
      }
      if (getScanValue() == "0" && getisDeckDealerEmpty()){
          setComputerNumber(); // how long can this recursion go? o_O dividing by ZERO!!!
          debugInfo.append("\nUhh.. Computer is calling 0 and the deck is empty.. divide by 0 recursion!!!!");
      }
   }
   public boolean getIsComputerMatched()
   {
       //If zero then draw card
       debugInfo.append("\ngetIsComputerMatched");
       debugInfo.append("\n----------------------");
       debugInfo.append("\nvalueToScan is " + valueToScan);
       if (valueToScan == "0"){
           return false;
       }
       //Scan human hand w. valueToScan 
      boolean hasPicked = false;
          
      for (int i = 0; i < handHuman.getHand().size(); i++) 
      {
          debugInfo.append("\nhandHuman.getHand().get(i) is " + handHuman.getHand().get(i));
         if ( !hasPicked && handHuman.getHand().get(i).equals(valueToScan))
         { 
            //handComputer.MatchPicked(getScanNumber(), hasPicked );
            //feedBack.append( "\n(computer):Do you have a " + valueToScan + "?" + "(matched)");
            debugInfo.append("\nMatch is True!!!");
            match = true;
            hasPicked = true;
         }
         else{
             debugInfo.append("\nMatch is false!!!");
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
   public String getdeckHandDisplay()
   {
      String handDeckCards = "";
      for(int i = 0; i<deckofDealer.deckDealer.size(); i++)
      {
         String handCards = deckDealer.deckDealer.get(i).toString();
         handDeckCards += handCards +" ";        
      }
   
      return handDeckCards;
   }
   public boolean getIsHumanCheating()//Not relevant
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
   public boolean getIsHumanMatch()
   {
      debugInfo.append("\ngetIsHumanMatch");
      boolean isMatch = false;
      if (valueToScan == "0"){
          return isMatch;
      }
      for (int i = 0; i < handComputer.getHand().size(); i++) 
      {
         debugInfo.append("\n----------------------------------------");
         debugInfo.append("\nhandComputer.getHand().get(i) is " + handComputer.getHand().get(i));
         debugInfo.append("\nvalueToScan is " + valueToScan);
         if (handComputer.getHand().get(i).equals(valueToScan))
         {
            isMatch = true;
            debugInfo.append("\nisMatch is " + isMatch);
         }
      }
      debugInfo.append("\n----------------------------------------");
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
    debugInfo.append("\n-----------------------------");
    debugInfo.append("\nremoveCardHumanComputer");
    debugInfo.append("\nvalueToScan is " + valueToScan);
      for (int i = 0; i < handHuman.getHand().size(); i++) 
      {
         debugInfo.append("\nhumanHand card is " + handHuman.getHand().get(i));
         if (handHuman.getHand().get(i).equals(valueToScan))
         {
            debugInfo.append("\nremoving from human card # " + handHuman.getHand().get(i));
            handHuman.getHand().remove(i);
            handHuman.getHand().trimToSize();
            break;
         }
      }    
        
      for (int i = 0; i < handComputer.getHand().size(); i++) 
      {
         debugInfo.append("\nhandComputer card is " + handComputer.getHand().get(i));
         if (handComputer.getHand().get(i).equals(valueToScan))
         {
            debugInfo.append("\nremoving from computer card # " + handComputer.getHand().get(i));
            handComputer.getHand().remove(i);
            handComputer.getHand().trimToSize();
            break;
         }
         else{
             debugInfo.append("\nComputer NOT Match!");
         }
      }  
      debugInfo.append("\n-----------------------------");
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

