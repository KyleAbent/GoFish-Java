import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import java.io.Serializable;

public class handofHuman implements Serializable
{
   protected static ArrayList<Integer>  handHuman = new ArrayList<Integer>();

   public static void main(String[] args)
   {
   }
   public void resetHand(){
       handHuman = new ArrayList<Integer>();
   }
   public static void createHand(ArrayList deckDealer)
   {
      Random rand = new Random();
      int idx = rand.nextInt(deckDealer.size());
      int value = (int)deckDealer.get(idx);
      int temp = idx;
      deckDealer.remove( deckDealer.get( idx) ); //object not int
      handHuman.add( value );
      deckDealer.trimToSize();
   }
   public static ArrayList getHand()
   {
      return handHuman;
   }
   public static boolean getisEmpty()
   {
      return (handHuman.size() == 0);
   }
   
   public static void EmptyHand(handofHuman handHuman,  handofComputer handComputer,  deckofDealer deckDealer)
   {
      //JOptionPane.showMessageDialog(null,"Empty hand, drawing card."); 
      addCard(handComputer,deckDealer);
   }
   
   public static void addCard(handofComputer handComputer, deckofDealer deckDealer)
   {    
   
      deckDealer.getDeck().trimToSize(); 
           
           
     
     
      Random rand = new Random();     
      int idx = rand.nextInt(deckDealer.getDeck().size());
      int value = (int)deckDealer.getDeck().get(idx);
      int temp = idx;
      deckDealer.getDeck().remove( deckDealer.getDeck().get( idx) ); //object not int
   
      deckDealer.getDeck().trimToSize();  
                   
      handHuman.add( value );
      System.out.println("handHuman added card # " + value);
      //JOptionPane.showMessageDialog(null, "GoFish! (humanTurn)");
      System.out.println("GoFish! (humanTurn)");
   }
   
   
   public static void removePair(int one, int two)
   {
      //{JOptionPane.showMessageDialog(null,"removing pairs " + one + two); }
     
      for (int i = 0; i < handHuman.size(); i++) 
      { 
         if (handHuman.get(i).equals(one))
         {
            System.out.println("removing from human card # " + handHuman.get(i));
            handHuman.remove(i);
            break;
         }
      }  
       
       
      for (int i = 0; i < handHuman.size(); i++) 
      {
         if (handHuman.get(i).equals(two))
         {
            System.out.println("removing from human card # " + handHuman.get(i));
            handHuman.remove(i);
            break;
         }
      }        
   } 



 
 

}