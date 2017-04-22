//Kyle 'Avoca' Abent
import javax.swing.JOptionPane;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;


public class GoFishDialogue
{ 
/////////////////////////////////////////////////////////////////////
 /////////////////////////Declarations///////////////////////////////
//////////////////////////////////////////////////////////////////////
static boolean match = false;
static boolean humanTurn = true;
static boolean computerTurn = false;
static int numberToScan = 0; //number chosen by human/computer to scan hands for
static int humanPoints = 0;
static int computerPoints = 0;
static int turnCounter = 0;
static boolean cheating = true; //always assume
static deckofDealer deckDealer = new deckofDealer();
static handofHuman handHuman = new handofHuman();
static handofComputer handComputer = new handofComputer();
static ArrayList<Integer> humanPairedCards = new ArrayList<Integer>();
static boolean gameEnd = false; //asserting turns like clocks
///////////////////////////////////////////////////////////////////////////
 /////////////////////////Declarations///////////////////////////////
///////////////////////////////////////////////////////////////////////////

  public static void main(String[] args)
  {
  Scanner keyboard = new Scanner(System.in);
  
     //Create deckdealer deck
    deckDealer.CreateDeck(); 	
    for (int i = 0; i <5; i++) 
    {
  // Create human && computer deck
   handofHuman.createHand(deckDealer.getDeck());
   handofComputer.createHand(deckDealer.getDeck());
   }
   //Start turns
  turnManage();     
 }

 public static void turnManage()
 {  //This way the turns go back and forth matching boolean
 boolean gameOver = getisDeckDealerEmpty(); //&& handHuman.getisEmpty() && handComputer.getisEmpty();
 while(!gameOver)
 {
           gameOver = getisDeckDealerEmpty();// && handHuman.getisEmpty() && handComputer.getisEmpty();
           if (humanTurn) { humanInput(); } else if (computerTurn) { computerInput() ; }
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
  public static boolean humanInput()
  {
   setScanNumber(0); 
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
  // for (int index = 0; index < handComputer.getHand().size(); index++) {System.out.println("handComputer has card number" + handComputer.getHand().get(index));}  
   System.out.print("\nYour already paired cards are: " + handHumanCardsPaired());
   System.out.print("\ndeckDealer: " + deckDealer.getDeck().size() + " cards left in the deck\n" );
   System.out.println("computerPoints = " + computerPoints + " humanPoints = " + humanPoints);
   System.out.print("\n (0 to draw card, double number to place down pair)"); // or double number to trade pair for points (eg: 99 for two 9's on hand) )" + output);
   System.out.print("\nPick a card from your hand: " + getHumanHandDisplay() + " \n");
      Scanner keyboard = new Scanner(System.in);
      setScanNumber(keyboard.nextInt());  
   System.out.println("\nhuman chose card #" + numberToScan);
   
   
   humanAlgorithm();
   return getHumanTurn();
   
    
  }
  public static void humanAlgorithm()
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
              
              
          
  }
   ///////////////////////////////////////////////////////////////////////////
 /////////////////////////human Main///////////////////////////////////////
///////////////////////////////////////////////////////////////////////////

 ///////////////////////////////////////////////////////////////////////////
 /////////////////////////computer Main///////////////////////////////////////
///////////////////////////////////////////////////////////////////////////
    public static boolean computerInput()
   {
     setScanNumber(0);
     match = false;
     computerTurn = checkforEmptyHands();
     if (!computerTurn) {return computerTurn;}
    System.out.println("\ncomputerPoints = " + computerPoints + ", humanPoints = " + humanPoints);
    System.out.println("\nturnCounter = " + turnCounter);
       //Grab a random number based on difficulty level
      setComputerNumber();
     boolean isZero = (getScanNumber() == 0);
   System.out.println("computer chose card #" + numberToScan);

       match = getIsComputerMatched(isZero);
      if (match){computerPoints = computerPoints + 1; removeCardHumanComputer();}
      else{ if (!match && !isZero && !getisDeckDealerEmpty()) {handComputer.addCard(handHuman, handComputer, deckDealer);} turnCounter = turnCounter + 1; humanTurn = true;computerTurn = false;}
      
      return computerTurn;
       
   }
  ///////////////////////////////////////////////////////////////////////////
 /////////////////////////computer Main///////////////////////////////////////
///////////////////////////////////////////////////////////////////////////


 ///////////////////////////////////////////////////////////////////////////
 /////////////////////////computer Misc///////////////////////////////////////
///////////////////////////////////////////////////////////////////////////  
 public static void setComputerNumber()
 {
      Random random = new Random();
      //Min 10% Max 30% of correct card choosing else pure random
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
  public static boolean getIsComputerMatched(boolean isZero)
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

       public static String handHumanCardsPaired()
   {
     
      String handHumanCardsPaired = "";
     for(int i = 0; i<humanPairedCards.size(); i++)
      {
      String pairedCards = humanPairedCards.get(i).toString();
      handHumanCardsPaired += pairedCards +" ";        
     }

   return handHumanCardsPaired; 
   }
   public static String getHumanHandDisplay()
   {
    String handHumanCards = "";
     for(int i = 0; i<handHuman.getHand().size(); i++)
      {
      String handCards = handHuman.getHand().get(i).toString();
      handHumanCards += handCards +" ";        
     }
   
   return handHumanCards;
   }
  public static boolean getIsHumanCheating()
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
  public static void humanPairNonCheating(int one, int two)
  {

                handHuman.removePair(one, two); 
                humanPoints = humanPoints + 1;
                turnCounter = turnCounter + 1;
               // setComputerTurn(true); 
                humanPairedCards.add( one ); humanPairedCards.add( two );
  }
  public static boolean getIsHumanMatch(boolean isZero)
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

     public static void removeCardHumanComputer()
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
  public static boolean checkforEmptyHands()
  {
      boolean continueTurn = true;
      if ( handComputer.getisEmpty() )
       {handComputer.EmptyHand(handHuman,handComputer, deckDealer);
       continueTurn = false;
     }

       return continueTurn;
  }
  
  public static int getScanNumber()
{
 return numberToScan;
}
public static void setScanNumber(int num)
{
  numberToScan = num;
}
public static boolean getHumanTurn()
{
 return humanTurn;
}
public static void setHumanTurn(boolean bool)
{
  humanTurn = bool;
}
public static boolean getComputerTurn()
{
 return computerTurn;
}
public static void setComputerTurn(boolean bool)
{
  computerTurn = bool;
}
public static boolean getisDeckDealerEmpty()
{
  return deckDealer.getisEmpty();
}

 ///////////////////////////////////////////////////////////////////////////
 ///////////////////////// Misc///////////////////////////////////////
/////////////////////////////////////////////////////////////////////////// 

  

 }