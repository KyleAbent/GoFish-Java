import java.util.ArrayList;
import java.util.Random;


public class handofHuman
{
private ArrayList<Integer>  handHuman = new ArrayList<Integer>();
}


  
   public static ArrayList createHand(ArrayList deckDealer)
  {
          Random rand = new Random();
          int idx = rand.nextInt(deckDealer.size());
          int value = (int)deckDealer.get(idx);
          int temp = idx;
          deckDealer.remove( deckDealer.get( idx) ); //object not int
          handHuman.add( value );
          deckDealer.trimToSize();
  }
      public static void humanEmptyHand(int numberToScan, ArrayList handHuman,  ArrayList handComputer,  ArrayList deckDealer, boolean cheating, boolean match, boolean humanTurn, boolean computerTurn, int humanPoints, int computerPoints, ArrayList humanPairedCards, int turnCounter, int difficultyLevel)
 {
      JOptionPane.showMessageDialog(null,"Empty hand, drawing card."); 
     addCard(humanTurn, computerTurn, handHuman, handComputer, deckDealer, humanPoints, computerPoints);
     humanTurn = false; 
     computerTurn = true;
     computerInput(numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn, humanPoints, computerPoints, humanPairedCards, turnCounter, difficultyLevel);
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
