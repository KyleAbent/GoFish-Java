import java.util.ArrayList;
import java.util.Random;
import java.io.Serializable;
public class deckofDealer implements Serializable
{
   private double length;
   private static ArrayList<Integer> deckDealer = new ArrayList<Integer>();
    
    
   public static void main(String[] args)
   {
   }
  
   public static void CreateDeck()
   {  //A, J, K, Q??
      for (int index = 0; index <= 4; index++)
      {
         deckDealer.add(1);deckDealer.add(2);deckDealer.add(3);deckDealer.add(4);deckDealer.add(5);
         deckDealer.add(6);deckDealer.add(7);deckDealer.add(8);deckDealer.add(9);deckDealer.add(10);
      }
   
   }
  
   public static ArrayList getDeck()
   {
      return deckDealer;
   } 
  
   public static boolean getisEmpty()
   {
      return (deckDealer.size() == 0);
   }
  
}
