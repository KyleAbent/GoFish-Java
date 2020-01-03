import java.util.ArrayList;
import java.util.Random;
import java.io.Serializable;
public class deckofDealer implements Serializable
{
   protected static ArrayList<String> deckDealer = new ArrayList<String>();
    
    
   public static void main(String[] args)
   {
   }
  
   public void ResetDeck()
   { 
     deckDealer = new ArrayList<String>();
   }
   
   public void CreateDeck()
   {  //A, J, K, Q??
      for (int index = 0; index < 5; index++)
      {
         deckDealer.add("Ace");deckDealer.add("2");deckDealer.add("3");deckDealer.add("4");deckDealer.add("5");
         deckDealer.add("6");deckDealer.add("7");deckDealer.add("8");deckDealer.add("9");deckDealer.add("10");
         deckDealer.add("Jack");deckDealer.add("Queen");deckDealer.add("King");
      }
   
   }
  
   public ArrayList getDeck()
   {
      return deckDealer;
   } 
  
   public boolean getisEmpty()
   {
      return (deckDealer.size() == 0);
   }
  
}
