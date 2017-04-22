import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
public class handofComputer


{
private static ArrayList<Integer>  handComputer = new ArrayList<Integer>();

      public static void main(String[] args)
  {
  }
  
     public static void createHand(ArrayList deckDealer)
  {
      
            Random rand = new Random();
          int idx = rand.nextInt(deckDealer.size());
          int value = (int)deckDealer.get(idx);
          int temp = idx;
          deckDealer.remove( deckDealer.get( idx) ); //object not int
           handComputer.add( value );
           deckDealer.trimToSize();
  }
    public static ArrayList getHand()
  {
     return handComputer;
  }
      public static boolean getisEmpty()
  {
     return (handComputer.size() == 0);
  }
     public static void MatchPicked(int numberToScan, boolean hasPicked)
 {
            JOptionPane.showMessageDialog(null, "(computer):Do you have a " + numberToScan + "?" + "(matched)");
 }
    public static void EmptyHand(handofHuman handHuman,  handofComputer handComputer,  deckofDealer deckDealer, int humanPoints, int computerPoints)
 {
     JOptionPane.showMessageDialog(null,"(computer):Empty hand, drawing card."); 
     addCard(handHuman, handComputer, deckDealer,  humanPoints,  computerPoints);
 }
     public static void humanEmptyHandComputerTurn(handofHuman handHuman, handofComputer handComputer, deckofDealer deckDealer, int humanPoints, int computerPoints, ArrayList humanPairedCards)
 {

     addCard(handHuman, handComputer, deckDealer, humanPoints, computerPoints);
 }
  public static void addCard(handofHuman handHuman, handofComputer handComputer, deckofDealer deckDealer, int humanPoints, int computerPoints)
   {    
   
           deckDealer.getDeck().trimToSize(); 
           
           
     if  ( deckDealer.getisEmpty() ) 
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
    int idx = rand.nextInt(deckDealer.getDeck().size());
    int value = (int)deckDealer.getDeck().get(idx);
    int temp = idx;
     
      if (value == 0) 
      {
      System.out.println("addcard drew 0, therefore stopping draw");
       JOptionPane.showMessageDialog(null,"(!!BUG!! try your turn again)"); 
      //addCard(humanTurn, computerTurn, handHuman, handComputer, deckDealer,  humanPoints,  computerPoints);
      return;
      }
      
     deckDealer.getDeck().remove( deckDealer.getDeck().get( idx) ); //object not int
 	
       deckDealer.getDeck().trimToSize();  
      // System.out.println("deckDealer removed card # " + temp);
       
            getHand().add( temp );
           // System.out.println("handComputer added card # " + temp);
            JOptionPane.showMessageDialog(null, "GoFish! (computerTurn)");
            System.out.println("GoFish! (computerTurn)");
            return;
   }
   
   public static void removeCard(int numberToScan, ArrayList handHuman, ArrayList handComputer, int humanPoints, int computerPoints)
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