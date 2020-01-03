import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import java.io.Serializable;
public class handofComputer implements Serializable


{
   protected static ArrayList<String>  handComputer = new ArrayList<String>();

   public static void main(String[] args)
   {
   }
  
  
   public static void createHand(ArrayList deckDealer)
   {
      
      Random rand = new Random();
      int idx = rand.nextInt(deckDealer.size());
      String value = deckDealer.get(idx).toString();
      int temp = idx;
      deckDealer.remove( deckDealer.get( idx) ); //object not int
      handComputer.add( value );
      deckDealer.trimToSize();
      System.out.println("Computer added card" + value);
   }
   public void resetHand(){
       handComputer = new ArrayList<String>();
   }
   public ArrayList getHand()
   {
      return handComputer;
   }
   public boolean getisEmpty()
   {
      return (handComputer.size() == 0);
   }
   /*
   public static void MatchPicked(int numberToScan, boolean hasPicked)
   {
      JOptionPane.showMessageDialog(null, "(computer):Do you have a " + numberToScan + "?" + "(matched)");
   }
   */
   public void EmptyHand(handofHuman handHuman,  handofComputer handComputer,  deckofDealer deckDealer)
   {
      //JOptionPane.showMessageDialog(null,"(computer):Empty hand, drawing card."); 
      addCard(handHuman, handComputer, deckDealer);
   }
   public void humanEmptyHandComputerTurn(handofHuman handHuman, handofComputer handComputer, deckofDealer deckDealer)
   {
   
      addCard(handHuman, handComputer, deckDealer);
   }
   public void addCard(handofHuman handHuman, handofComputer handComputer, deckofDealer deckDealer)
   {    
   
      deckDealer.getDeck().trimToSize(); 
     
     
      Random rand = new Random();     
      int idx = rand.nextInt(deckDealer.getDeck().size());
      String value = deckDealer.getDeck().get(idx).toString();
      int temp = idx;
     
      deckDealer.getDeck().remove( deckDealer.getDeck().get( idx) ); //object not int
   
      deckDealer.getDeck().trimToSize();  
      // System.out.println("deckDealer removed card # " + temp);
       
      getHand().add( value );
           // System.out.println("handComputer added card # " + temp);
      //JOptionPane.showMessageDialog(null, "GoFish! (computerTurn)");
      System.out.println("GoFish! (computerTurn)");
      System.out.println("Computer added card" + value);
      return;
   }
   
   public static void removePair(String value)
   {
      //{JOptionPane.showMessageDialog(null,"removing pairs " + one + two); }
     
      for (int i = 0; i < handComputer.size(); i++) 
      { 
         if (handComputer.get(i).equals(value))
         {
            System.out.println("removing from handComputer card # " + handComputer.get(i));
            handComputer.remove(i);
            break;
         }
      }  
       
       
      for (int i = 0; i < handComputer.size(); i++) 
      {
         if (handComputer.get(i).equals(value))
         {
            System.out.println("removing from handComputer card # " + handComputer.get(i));
            handComputer.remove(i);
            break;
         }
      }        
   } 

}