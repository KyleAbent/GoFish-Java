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
   public ArrayList<String> getListOfCards(){
       ArrayList<String> tmp = new ArrayList<String>();
      for (int index = 0; index < 4; index++)
      {
         tmp.add("Ace");tmp.add("2");tmp.add("3");tmp.add("4");tmp.add("5");
         tmp.add("6");tmp.add("7");tmp.add("8");tmp.add("9");tmp.add("10");
         tmp.add("Jack");tmp.add("Queen");tmp.add("King");
      }
      return tmp;
   }
   public void CreateDeck()
   {  
      for (int index = 0; index < 4; index++)
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
     public int getSize()
   {
      return (deckDealer.size());
   }
}
