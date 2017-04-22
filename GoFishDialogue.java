//Kyle 'Avoca' Abent
import javax.swing.JOptionPane;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;


public class GoFishDialogue
{ 


  public static void main(String[] args)
  {
  Scanner keyboard = new Scanner(System.in);
  
   deckofDealer deckDealer = new deckofDealer();
   handofHuman handHuman = new handofHuman();
   handofComputer handComputer = new handofComputer();
    //for (int index = 0; index < deckDealer.size(); index++) {System.out.println("deckDealer(1) has card number" + deckDealer.get(index));}  
   deckDealer.CreateDeck(); 	
   
    for (int i = 0; i <5; i++) 
    {
   handofHuman.createHand(deckDealer.getDeck());
   handofComputer.createHand(deckDealer.getDeck());
   }
   
   
   ArrayList<Integer> humanPairedCards = new ArrayList<Integer>();
     
  int numberToScan = 1;
  boolean match = false;
  boolean humanTurn = true;
  boolean computerTurn = false;
  int humanPoints = 0;
  int computerPoints = 0;
  int turnCounter = 0;
  boolean cheating = true;
  int difficultyLevel = 0;
     
      System.out.print("\nChoose difficulty level (1-99 as in percent of odds getting a correct card): ");
      difficultyLevel = keyboard.nextInt(); 
    
  
  humanInput(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn,humanPoints, computerPoints, humanPairedCards, turnCounter, difficultyLevel);
           
 }
   public static void removeCardHumanComputer(int numberToScan, handofHuman handHuman, handofComputer handComputer, int humanPoints, int computerPoints)
    {
    
       for (int i = 0; i < handHuman.getHand().size(); i++) 
       {
      //  System.out.println("removeCardHumanComputer handHuman NumberToScan is" + numberToScan + "index number is " + handHuman.get(i) ); 
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
      //  System.out.println("removeCardHumanComputer handComputer NumberToScan is" + numberToScan + "index number is " + handComputer.get(i) ); 
        if (handComputer.getHand().get(i).equals(numberToScan))
         {
           System.out.println("removing from computer card # " + handComputer.getHand().get(i));
           handComputer.getHand().remove(i);
           handComputer.getHand().trimToSize();
           break;
          }
        }               

      
  //  for (int index = 0; index < handHuman.size(); index++) {System.out.println("handHuman has card number" + handHuman.get(index));}
    //for (int index = 0; index < handComputer.size(); index++) {System.out.println("handComputer has card number" + handComputer.get(index));}    
    
  }
    public static void computerInput(int numberToScan, handofHuman handHuman, handofComputer handComputer, deckofDealer deckDealer, boolean cheating, boolean match, boolean humanTurn, boolean computerTurn, int humanPoints, int computerPoints, ArrayList humanPairedCards, int turnCounter, int difficultyLevel)
   {
    if ( handComputer.getisEmpty() )
       {handComputer.EmptyHand(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn,  humanPoints, computerPoints,  humanPairedCards, turnCounter, difficultyLevel);
        humanInput(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn,  humanPoints, computerPoints,  humanPairedCards, turnCounter, difficultyLevel);
     }
       if  ( handHuman.getisEmpty() )
       {
       handHuman.EmptyHand(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn,  humanPoints, computerPoints,  humanPairedCards, turnCounter, difficultyLevel);
       humanInput(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn,  humanPoints, computerPoints,  humanPairedCards, turnCounter, difficultyLevel);
       //computerInput(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn, humanPoints, computerPoints, humanPairedCards, turnCounter, difficultyLevel);
       }
   System.out.println("\ncomputerPoints = " + computerPoints + ", humanPoints = " + humanPoints);
    System.out.println("\nturnCounter = " + turnCounter);
    //JOptionPane.showMessageDialog(null, "Computers Turn!");
   Random random = new Random();
   int tunedNumber = random.nextInt(100);
    System.out.println("\ntunedNumber = " + tunedNumber + " , difficulty level is " + difficultyLevel);

    if (difficultyLevel >= tunedNumber) 
      {
          int idx = random.nextInt(handHuman.getHand().size());
          int value = (int)handHuman.getHand().get(idx);
       numberToScan = value ;
     }
       else
       { 
       numberToScan = random.nextInt(handComputer.getHand().size()) ;
       }
   System.out.println("computer chose card #" + numberToScan);
    boolean isZero = (numberToScan == 0);
       
       if (isZero){System.out.println("computer is drawing card");handComputer.addCard(humanTurn, computerTurn, handHuman, handComputer, deckDealer,  humanPoints,  computerPoints);} 
       
       boolean hasPicked = false;
          
         for (int i = 0; i < handHuman.getHand().size(); i++) 
          {
         if ( !hasPicked && !isZero && handHuman.getHand().get(i).equals(numberToScan))
          { 
           handComputer.MatchPicked( numberToScan, match, hasPicked );
            match = true;
            hasPicked = true;
          }
         }
   
      if (match){computerPoints = computerPoints + 1; removeCardHumanComputer(numberToScan, handHuman, handComputer, humanPoints, computerPoints);}
      else{ if (!isZero) {handComputer.addCard(humanTurn, computerTurn, handHuman, handComputer, deckDealer, humanPoints, computerPoints);} turnCounter = turnCounter + 1; humanTurn = true;computerTurn = false;}
      
      if (humanTurn){numberToScan = 0; humanInput(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn,  humanPoints, computerPoints,  humanPairedCards, turnCounter, difficultyLevel);}
      else if (computerTurn){ match = false; numberToScan = 0;computerInput(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn, humanPoints, computerPoints,  humanPairedCards, turnCounter, difficultyLevel); }  
   }
  public static void humanInput(int numberToScan, handofHuman handHuman,  handofComputer handComputer,  deckofDealer deckDealer, boolean cheating, boolean match, boolean humanTurn, boolean computerTurn, int humanPoints, int computerPoints, ArrayList humanPairedCards, int turnCounter, int difficultyLevel)
  {

 String handHumanCards = "";
 
     if  ( handHuman.getisEmpty() )
     
     { 
     handHuman.EmptyHand(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn,  humanPoints, computerPoints,  humanPairedCards, turnCounter, difficultyLevel);
     computerInput(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn, humanPoints, computerPoints, humanPairedCards, turnCounter, difficultyLevel);
     }
     

      
     for(int i = 0; i<handHuman.getHand().size(); i++)
      {
      String handCards = handHuman.getHand().get(i).toString();
      handHumanCards += handCards +" ";        
     }
     
      String handHumanCardsPaired = "";
     for(int i = 0; i<humanPairedCards.size(); i++)
      {
      String pairedCards = humanPairedCards.get(i).toString();
      handHumanCardsPaired += pairedCards +" ";        
     }
     
          if  ( deckDealer.getisEmpty() ) 
     {
     JOptionPane.showMessageDialog(null,"deckDealer deck is empty!"); 
     }

   System.out.print("\nYour already paired cards are: " + handHumanCardsPaired);
   System.out.print("\ndeckDealer: " + deckDealer.getDeck().size() + " cards left in the deck\n" );
   System.out.println("computerPoints = " + computerPoints + " humanPoints = " + humanPoints);
   System.out.print("\n (0 to draw card, double number to place down pair)"); //, or double number to trade pair for points (eg: 99 for two 9's on hand) )" + output);
   System.out.print("\nPick a card from your hand: " + handHumanCards + " \n");
      Scanner keyboard = new Scanner(System.in);
   numberToScan = keyboard.nextInt();  
   System.out.println("\nhuman chose card #" + numberToScan);
   humanAlgorithm(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn, humanPoints,  computerPoints,  humanPairedCards, turnCounter, difficultyLevel );
   
    
  }
  public static void humanAlgorithm(int numberToScan, handofHuman handHuman,  handofComputer handComputer,  deckofDealer deckDealer, boolean cheating, boolean match, boolean humanTurn, boolean computerTurn, int humanPoints, int computerPoints, ArrayList humanPairedCards, int turnCounter, int difficultyLevel)
  {
    cheating = true; //always assume
    int one = numberToScan / 10;
    int two = numberToScan % 10;
    boolean pair = (one == two && numberToScan != 0); //tough lesson
    int tunedNumber = 0;
    boolean isZero = (numberToScan == 0);
    
    if (pair) { tunedNumber = one; }
    
      
        //System.out.print("\n one is: " + one + " , two is " + two +"pair is +"+  pair + "\n");
         
    if (numberToScan == 0 ) {cheating = false;}
    
    if (tunedNumber != 0) { numberToScan = tunedNumber; }
    
      for (int i = 0; i < handHuman.getHand().size(); i++) 
       {
        if (handHuman.getHand().get(i).equals(numberToScan)){cheating = false;}
       }
    
     
     if (cheating) {JOptionPane.showMessageDialog(null,"You are cheating. You do not own card # " + numberToScan + "! Try Again!");  }
     
     
     if (pair && !cheating) { 
                handHuman.removePair(one, two); 
                humanPoints = humanPoints + 1;
                humanTurn = false; 
                turnCounter = turnCounter + 1;
                computerTurn = true;             
              }
      for (int i = 0; i < handComputer.getHand().size(); i++) 
       {
      //  System.out.println("humanAlgorithm NumberToScan is" + numberToScan + "index number is " + handComputer.get(i) ); 
        if (!isZero && handComputer.getHand().get(i).equals(numberToScan))
         {
            match = true;
          }
       }
        
        //System.out.println("Match " + match);
        
            if (!computerTurn && humanTurn && !cheating && !pair)
              {
                 if (match)
                    {
                     JOptionPane.showMessageDialog(null, "Match! Go Again!");
                     humanPairedCards.add( numberToScan ); humanPairedCards.add( numberToScan );
                     removeCardHumanComputer(numberToScan, handHuman, handComputer,  humanPoints, computerPoints);
                     match = false;
                     humanPoints = humanPoints + 1;
                    }
                 else
                    //humanNotMatch(humanTurn, computerTurn, handHuman, handComputer, deckDealer,  humanPoints, computerPoints)
                    {
                     handHuman.addCard(handComputer, deckDealer,  humanPoints, computerPoints);
                     humanTurn = false; 
                     computerTurn = true;
                    }
              }
              
              
              // System.out.println("humanTurn is " + humanTurn);
              // System.out.println("computerTurn is " + computerTurn);
               
                if  (humanTurn) 
                {
                   humanInput(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn, humanPoints,computerPoints, humanPairedCards, turnCounter, difficultyLevel );
                }
               else if (computerTurn)
                { 
                   numberToScan = 0;
                   computerInput(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn, humanPoints, computerPoints, humanPairedCards, turnCounter, difficultyLevel);
                }
                
                if (humanTurn)
                {
                humanInput(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn, humanPoints, computerPoints, humanPairedCards, turnCounter, difficultyLevel );
                }  
          
  }
  

 }