import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import java.io.Serializable;

public class handofHuman implements Serializable
{
   protected static ArrayList<String>  handHuman = new ArrayList<String>();

   public static void main(String[] args)
   {
   }
   public void resetHand(){
       handHuman = new ArrayList<String>();
   }
   public static void createHand(ArrayList deckDealer)
   {
      Random rand = new Random();
      int idx = rand.nextInt(deckDealer.size());
      String card = deckDealer.get(idx).toString();
      int temp = idx;
      deckDealer.remove( deckDealer.get( idx) ); //object not int
      handHuman.add( card );
      deckDealer.trimToSize();
   }
   public ArrayList getHand()
   {
      return handHuman;
   }
   
   public boolean getisEmpty()
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
      String value = deckDealer.getDeck().get(idx).toString();
      deckDealer.getDeck().remove( deckDealer.getDeck().get( idx) ); //object not int
   
      deckDealer.getDeck().trimToSize();  
                   
      handHuman.add( value );
      System.out.println("handHuman added card # " + value);
      //JOptionPane.showMessageDialog(null, "GoFish! (humanTurn)");
      System.out.println("GoFish! (humanTurn)");
   }
   
   
   public static void removePair(String value)
   {
      //{JOptionPane.showMessageDialog(null,"removing pairs " + one + two); }
     //just do a count int do twice o_O rather than write a for loop twice.. lol
      for (int i = 0; i < handHuman.size(); i++) 
      { 
         if (handHuman.get(i).equals(value))
         {
            System.out.println("removing from human card # " + handHuman.get(i));
            handHuman.remove(i);
            break;
         }
      }  
       
       
      for (int i = 0; i < handHuman.size(); i++) 
      {
         if (handHuman.get(i).equals(value))
         {
            System.out.println("removing from human card # " + handHuman.get(i));
            handHuman.remove(i);
            break;
         }
      }        
   } 



 
 

}