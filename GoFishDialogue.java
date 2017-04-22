//Kyle 'Avoca' Abent
import javax.swing.JOptionPane;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;


public class GoFishDialogue
{ 
  public static void CreateDealer(ArrayList deckDealer)
  {
     for (int index = 0; index <= 4; index++)
   {
    deckDealer.add(1);deckDealer.add(2);deckDealer.add(3);deckDealer.add(4);deckDealer.add(5);
    deckDealer.add(6);deckDealer.add(7);deckDealer.add(8);deckDealer.add(9);deckDealer.add(10);
   }
   
  }
  
     public static void createComputerHand(ArrayList deckDealer, ArrayList handComputer)
  {
      
            Random rand = new Random();
          int idx = rand.nextInt(deckDealer.size());
          int value = (int)deckDealer.get(idx);
          int temp = idx;
          deckDealer.remove( deckDealer.get( idx) ); //object not int
           handComputer.add( value );
           deckDealer.trimToSize();
           //System.out.println("computerHand added card # " + temp) ;
           //System.out.println("deckDealer removed card # " + temp) ;
  }
   public static void createHumanHand(ArrayList deckDealer, ArrayList handHuman)
  {
        
          Random rand = new Random();
          int idx = rand.nextInt(deckDealer.size());
          int value = (int)deckDealer.get(idx);
          int temp = idx;
          deckDealer.remove( deckDealer.get( idx) ); //object not int
           handHuman.add( value );
           deckDealer.trimToSize();
           //System.out.println("handHuman added card # " + temp);
           //System.out.println("deckDealer removed card # " + temp);
  }
  public static void main(String[] args)
  {
  Scanner keyboard = new Scanner(System.in);
   //Arraylist because Integer Array's do not allow deletion simply.
   ArrayList<Integer>  deckDealer = new ArrayList<Integer>();
   
   CreateDealer(deckDealer);
    
     
    //for (int index = 0; index < deckDealer.size(); index++) {System.out.println("deckDealer(1) has card number" + deckDealer.get(index));}   	
      
     ArrayList<Integer>  handHuman = new ArrayList<Integer>();
     ArrayList<Integer>  handComputer = new ArrayList<Integer>();
     ArrayList<Integer>  humanPairedCards = new ArrayList<Integer>();
   
      
     createHumanHand(deckDealer, handHuman);
     createHumanHand(deckDealer, handHuman);
     createHumanHand(deckDealer, handHuman);
     createHumanHand(deckDealer, handHuman);
     createHumanHand(deckDealer, handHuman);
     createComputerHand(deckDealer, handComputer);
     createComputerHand(deckDealer, handComputer);
     createComputerHand(deckDealer, handComputer);
     createComputerHand(deckDealer, handComputer);
     createComputerHand(deckDealer, handComputer);
   
     
 
         
     
  int numberToScan = 1;
  boolean match = false;
  boolean humanTurn = true;
  boolean computerTurn = false;
  int humanPoints = 0;
  int computerPoints = 0;
  int turnCounter = 0;
  //int gameMode = 0; 
  boolean cheating = true;
  int difficultyLevel = 0;
   //for (int index = 0; index < handHuman.size(); index++){System.out.println("handHuman has card number" + handHuman.get(index));}
   //for (int index = 0; index < handComputer.size(); index++){System.out.println("handComputer has card number" + handComputer.get(index));}  
   //for (int index = 0; index < deckDealer.size(); index++){System.out.println("deckDealer(2) has card number" + deckDealer.get(index));}   	
   
     
      System.out.print("\nChoose difficulty level (1-99 as in percent of odds getting a correct card): ");
      difficultyLevel = keyboard.nextInt(); 
    
  
  humanInput(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn,humanPoints, computerPoints, humanPairedCards, turnCounter, difficultyLevel);
           
 }
   public static void computerMatchPicked(int numberToScan, boolean match, boolean hasPicked)
 {
            // match = true;
            //hasPicked = true;
            JOptionPane.showMessageDialog(null, "(computer):Do you have a " + numberToScan + "?" + "(matched)");
 }
    public static void computerEmptyHand(int numberToScan, ArrayList handHuman,  ArrayList handComputer,  ArrayList deckDealer, boolean cheating, boolean match, boolean humanTurn, boolean computerTurn, int humanPoints, int computerPoints, ArrayList humanPairedCards, int turnCounter, int difficultyLevel)
 {
        JOptionPane.showMessageDialog(null,"(computer):Empty hand, drawing card."); 
     addCard(humanTurn, computerTurn, handHuman, handComputer, deckDealer,  humanPoints,  computerPoints);
     humanTurn = true; 
     computerTurn = false;
     humanInput(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn,  humanPoints, computerPoints,  humanPairedCards, turnCounter, difficultyLevel);
 }
     public static void humanEmptyHand(int numberToScan, ArrayList handHuman,  ArrayList handComputer,  ArrayList deckDealer, boolean cheating, boolean match, boolean humanTurn, boolean computerTurn, int humanPoints, int computerPoints, ArrayList humanPairedCards, int turnCounter, int difficultyLevel)
 {
      JOptionPane.showMessageDialog(null,"Empty hand, drawing card."); 
     addCard(humanTurn, computerTurn, handHuman, handComputer, deckDealer, humanPoints, computerPoints);
     humanTurn = false; 
     computerTurn = true;
     computerInput(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn, humanPoints, computerPoints, humanPairedCards, turnCounter, difficultyLevel);
 }
     public static void humanEmptyHandComputerTurn(int numberToScan, ArrayList handHuman,  ArrayList handComputer,  ArrayList deckDealer, boolean cheating, boolean match, boolean humanTurn, boolean computerTurn, int humanPoints, int computerPoints, ArrayList humanPairedCards, int turnCounter, int difficultyLevel)
 {
      JOptionPane.showMessageDialog(null,"Empty hand, drawing card."); 
     addCard(humanTurn, computerTurn, handHuman, handComputer, deckDealer, humanPoints, computerPoints);
     humanTurn = true; 
     computerTurn = false;
     humanInput(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn,  humanPoints, computerPoints,  humanPairedCards, turnCounter, difficultyLevel);
 }
  public static void computerInput(int numberToScan, ArrayList handHuman,  ArrayList handComputer,  ArrayList deckDealer, boolean cheating, boolean match, boolean humanTurn, boolean computerTurn, int humanPoints, int computerPoints, ArrayList humanPairedCards, int turnCounter, int difficultyLevel)
   {
   boolean skipTurn = false;
  if ( handComputer.size() == 0 ){computerEmptyHand(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn,  humanPoints, computerPoints,  humanPairedCards, turnCounter, difficultyLevel);}
  if  ( handHuman.size() == 0 ){skipTurn = true; humanEmptyHandComputerTurn(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn = false,  humanPoints, computerPoints,  humanPairedCards, turnCounter, difficultyLevel);}
   System.out.println("\ncomputerPoints = " + computerPoints + ", humanPoints = " + humanPoints);
    System.out.println("\nturnCounter = " + turnCounter);
    //JOptionPane.showMessageDialog(null, "Computers Turn!");
   Random random = new Random();
   int tunedNumber = random.nextInt(100);
    System.out.println("\ntunedNumber = " + tunedNumber + " , difficulty level is " + difficultyLevel);

    if (difficultyLevel >= tunedNumber) 
      {
          int idx = random.nextInt(handHuman.size());
          int value = (int)handHuman.get(idx);
       numberToScan = value ;
     }
       else
       { 
       numberToScan = random.nextInt(handComputer.size()) ;
       }
   System.out.println("computer chose card #" + numberToScan);
    boolean isZero = (numberToScan == 0);
       
       if (isZero){System.out.println("computer is drawing card");addCard(humanTurn, computerTurn, handHuman, handComputer, deckDealer,  humanPoints,  computerPoints);} 
       
       boolean hasPicked = false;
          
         for (int i = 0; i < handHuman.size(); i++) 
          {
         if ( !hasPicked && !isZero && handHuman.get(i).equals(numberToScan))
          { 
           computerMatchPicked( numberToScan, match, hasPicked );
            match = true;
            hasPicked = true;
          }
         }
   
      if (match){computerPoints = computerPoints + 1; removeCardHumanComputer(numberToScan, handHuman, handComputer, humanPoints, computerPoints);}
      else{ if (!isZero) {addCard(humanTurn, computerTurn, handHuman, handComputer, deckDealer, humanPoints, computerPoints);} turnCounter = turnCounter + 1; humanTurn = true;computerTurn = false;}
      
      if (humanTurn){numberToScan = 0; humanInput(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn,  humanPoints, computerPoints,  humanPairedCards, turnCounter, difficultyLevel);}
      else if (computerTurn){ match = false; numberToScan = 0;computerInput(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn, humanPoints, computerPoints,  humanPairedCards, turnCounter, difficultyLevel); }  
   }
  public static void humanInput(int numberToScan, ArrayList handHuman,  ArrayList handComputer,  ArrayList deckDealer, boolean cheating, boolean match, boolean humanTurn, boolean computerTurn, int humanPoints, int computerPoints, ArrayList humanPairedCards, int turnCounter, int difficultyLevel)
  {

 String handHumanCards = "";
 
     if  ( handHuman.size() == 0 ){humanEmptyHand(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn,  humanPoints, computerPoints,  humanPairedCards, turnCounter, difficultyLevel);}
     

      
     for(int i = 0; i<handHuman.size(); i++)
      {
      String handCards = handHuman.get(i).toString();
      handHumanCards += handCards +" ";        
     }
     
      String handHumanCardsPaired = "";
     for(int i = 0; i<humanPairedCards.size(); i++)
      {
      String pairedCards = humanPairedCards.get(i).toString();
      handHumanCardsPaired += pairedCards +" ";        
     }
     
          if  ( deckDealer.size() == 0 ) 
     {
     JOptionPane.showMessageDialog(null,"deckDealer deck is empty!"); 
     }

   System.out.print("\nYour already paired cards are: " + handHumanCardsPaired);
   System.out.print("\ndeckDealer: " + deckDealer.size() + " cards left in the deck\n" );
   System.out.println("computerPoints = " + computerPoints + " humanPoints = " + humanPoints);
   System.out.print("\n (0 to draw card, double number to place down pair)"); //, or double number to trade pair for points (eg: 99 for two 9's on hand) )" + output);
   System.out.print("\nPick a card from your hand: " + handHumanCards + " \n");
      Scanner keyboard = new Scanner(System.in);
   numberToScan = keyboard.nextInt();  
   System.out.println("\nhuman chose card #" + numberToScan);
   humanAlgorithm(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn, humanPoints,  computerPoints,  humanPairedCards, turnCounter, difficultyLevel );
   
    
  }
  public static void humanAlgorithm(int numberToScan, ArrayList handHuman, ArrayList handComputer,  ArrayList deckDealer, boolean cheating, boolean match, boolean humanTurn, boolean computerTurn, int humanPoints, int computerPoints, ArrayList humanPairedCards, int turnCounter, int difficultyLevel)
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
    
      for (int i = 0; i < handHuman.size(); i++) 
       {
        if (handHuman.get(i).equals(numberToScan)){cheating = false;}
       }
    
     
     if (cheating) {JOptionPane.showMessageDialog(null,"You are cheating. You do not own card # " + numberToScan + "! Try Again!");  }
     
     
     if (pair && !cheating) { 
                removePairHuman(one, two, handHuman); 
                humanPoints = humanPoints + 1;
                humanTurn = false; 
                turnCounter = turnCounter + 1;
                computerTurn = true;             
              }
      for (int i = 0; i < handComputer.size(); i++) 
       {
      //  System.out.println("humanAlgorithm NumberToScan is" + numberToScan + "index number is " + handComputer.get(i) ); 
        if (!isZero && handComputer.get(i).equals(numberToScan))
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
                     addCard(humanTurn, computerTurn, handHuman, handComputer, deckDealer,  humanPoints, computerPoints);
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
  
  public static void addCard(boolean humanTurn, boolean computerTurn, ArrayList handHuman, ArrayList handComputer, ArrayList deckDealer, int humanPoints, int computerPoints)
   {    
   
           deckDealer.trimToSize(); 
           
           
     if  ( deckDealer.size() == 0 ) 
     {
     //JOptionPane.showMessageDialog(null,"deckDealer is empty!"); 
     JOptionPane.showMessageDialog(null,"!!Game Over!! (Warning 1 of 4)"); 
     JOptionPane.showMessageDialog(null,"!!Game Over!! (Warning 2 of 4)"); 
     JOptionPane.showMessageDialog(null,"!!Game Over!! (Warning 3 of 4)"); 
     JOptionPane.showMessageDialog(null,"!!Game Over!! (Warning 4 of 4)"); 
     JOptionPane.showMessageDialog(null,"humanPoints = " + humanPoints + " , computerPoints = " + computerPoints); 
     System.exit(0);
     }
     
     
    Random rand = new Random();     
    int idx = rand.nextInt(deckDealer.size());
    int value = (int)deckDealer.get(idx);
    int temp = idx;
     
      if (value == 0) 
      {
      System.out.println("addcard drew 0, therefore stopping draw");
       JOptionPane.showMessageDialog(null,"(!!BUG!! try your turn again)"); 
      //addCard(humanTurn, computerTurn, handHuman, handComputer, deckDealer,  humanPoints,  computerPoints);
      return;
      }
      
     deckDealer.remove( deckDealer.get( idx) ); //object not int
 	
       deckDealer.trimToSize();  
      // System.out.println("deckDealer removed card # " + temp);
       
         if (humanTurn)
         {                
            handHuman.add( value );
            System.out.println("handHuman added card # " + value);
            JOptionPane.showMessageDialog(null, "GoFish! (humanTurn)");
            System.out.println("GoFish! (humanTurn)");
         }
         else if (computerTurn)
         {
            handComputer.add( temp );
           // System.out.println("handComputer added card # " + temp);
            JOptionPane.showMessageDialog(null, "GoFish! (computerTurn)");
            System.out.println("GoFish! (computerTurn)");
            return;
         }
   }
   
   public static void removeCardHumanComputer(int numberToScan, ArrayList handHuman, ArrayList handComputer, int humanPoints, int computerPoints)
    {
    
       for (int i = 0; i < handHuman.size(); i++) 
       {
      //  System.out.println("removeCardHumanComputer handHuman NumberToScan is" + numberToScan + "index number is " + handHuman.get(i) ); 
        if (handHuman.get(i).equals(numberToScan))
         {
           System.out.println("removing from human card # " + handHuman.get(i));
           handHuman.remove(i);
           handHuman.trimToSize();
           break;
          }
       }    
        
        for (int i = 0; i < handComputer.size(); i++) 
       {
      //  System.out.println("removeCardHumanComputer handComputer NumberToScan is" + numberToScan + "index number is " + handComputer.get(i) ); 
        if (handComputer.get(i).equals(numberToScan))
         {
           System.out.println("removing from computer card # " + handComputer.get(i));
           handComputer.remove(i);
           handComputer.trimToSize();
           break;
          }
        }               

      
  //  for (int index = 0; index < handHuman.size(); index++) {System.out.println("handHuman has card number" + handHuman.get(index));}
    //for (int index = 0; index < handComputer.size(); index++) {System.out.println("handComputer has card number" + handComputer.get(index));}    
    
  }
     public static void removePairHuman(int one, int two, ArrayList handHuman)
    {
              {JOptionPane.showMessageDialog(null,"removing pairs " + one + two); }
               for (int i = 0; i < handHuman.size(); i++) 
       {
      //  System.out.println("removeCardHumanComputer handHuman NumberToScan is" + numberToScan + "index number is " + handHuman.get(i) ); 
        if (handHuman.get(i).equals(one))
         {
           System.out.println("removing from human card # " + handHuman.get(i));
           handHuman.remove(i);
           break;
          }
       }  
       
       
                      for (int i = 0; i < handHuman.size(); i++) 
       {
      //  System.out.println("removeCardHumanComputer handHuman NumberToScan is" + numberToScan + "index number is " + handHuman.get(i) ); 
        if (handHuman.get(i).equals(two))
         {
           System.out.println("removing from human card # " + handHuman.get(i));
           handHuman.remove(i);
           break;
          }
       }        
    } 
 }