//Kyle 'Avoca' Abent
import javax.swing.JOptionPane;
import java.util.Random;
import java.util.ArrayList;


public class GoFishDialogue
{ 

  public static void main(String[] args)
  {
   //Arraylist because Integer Array's do not allow deletion simply.
   ArrayList<Integer>  deckDealer = new ArrayList<Integer>();
   for (int index = 0; index <4; index++)
   {
    deckDealer.add(1);deckDealer.add(2);deckDealer.add(3);deckDealer.add(4);deckDealer.add(5);
    deckDealer.add(6);deckDealer.add(7);deckDealer.add(8);deckDealer.add(9);deckDealer.add(10);
   }
  
     
    //for (int index = 0; index < deckDealer.size(); index++) {System.out.println("deckDealer(1) has card number" + deckDealer.get(index));}   	
      
     ArrayList<Integer>  handHuman = new ArrayList<Integer>();
     ArrayList<Integer>  handComputer = new ArrayList<Integer>();
     
      
        	   Random rand = new Random();
             for (int index = 0; index < 5; index++) 
           {
        int temp = deckDealer.get(rand.nextInt( deckDealer.size() ) );
        handHuman.add( temp );
        deckDealer.remove( temp );
        System.out.println("handHuman added card # " + temp);
        //System.out.println("deckDealer removed card # " + temp);
          }  
       
         
        for (int index = 0; index < 5; index++) 
            {
        int temp = deckDealer.get(rand.nextInt( deckDealer.size() ) );
        handComputer.add( temp );
          deckDealer.remove( temp );
            System.out.println("handComputer added card # " + temp);
          //  System.out.println("deckDealer removed card # " + temp);
             }  
 
         
     
  
  int numberToScan = 1;
  boolean match = false;
  boolean humanTurn = true;
  boolean computerTurn = false;
  //int humanPoints = 0;
  //int computerPoints = 0;
  //int gameMode = 0; 
  boolean cheating = true;
  String input = ""; 
  
  // for (int index = 0; index < handHuman.size(); index++){System.out.println("handHuman has card number" + handHuman.get(index));}
  // for (int index = 0; index < handComputer.size(); index++){System.out.println("handComputer has card number" + handComputer.get(index));}  
   //for (int index = 0; index < deckDealer.size(); index++){System.out.println("deckDealer(2) has card number" + deckDealer.get(index));}   	
    
   
  
  humanInput(input, numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn);
   

           
 }
  public static void computerInput(String input, int numberToScan, ArrayList handHuman,  ArrayList handComputer,  ArrayList deckDealer, boolean cheating, boolean match, boolean humanTurn, boolean computerTurn)
   {
   
   
    JOptionPane.showMessageDialog(null, "Computers Turn!");
   Random random = new Random();
   numberToScan = random.nextInt(handComputer.size());
         for (int i = 0; i < handHuman.size(); i++) 
          {
         if (handHuman.get(i).equals(numberToScan))
          {
            match = true;
            JOptionPane.showMessageDialog(null, "(2)Do you have a " + numberToScan + "?");
          }
          //  break;
         }
         
      if (match){removeCardHumanComputer(numberToScan, handHuman, handComputer);}
      else{addCard(humanTurn, computerTurn, handHuman, handComputer, deckDealer); humanTurn = true;computerTurn = false;}
      
      if (humanTurn){numberToScan = 0; humanInput(input, numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn);}
      else if (computerTurn){ match = false; numberToScan = 0;computerInput(input, numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn); }
      
       
   
      
   }
  public static void humanInput(String input, int numberToScan, ArrayList handHuman,  ArrayList handComputer,  ArrayList deckDealer, boolean cheating, boolean match, boolean humanTurn, boolean computerTurn)
  {

 String output = "";
     for(int i = 0; i<handHuman.size(); i++)
      {
      String cards = handHuman.get(i).toString();
      output += cards +" ";        
     }

   
    
  input = JOptionPane.showInputDialog(null, "Pick a card from your hand: " + output); 
   numberToScan = Integer.parseInt(input);
   //System.out.println("numberToscan is" + numberToScan);
   humanAlgorithm(input, numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn );
   
    
  }
  public static void humanAlgorithm(String input, int numberToScan, ArrayList handHuman, ArrayList handComputer,  ArrayList deckDealer, boolean cheating, boolean match, boolean humanTurn, boolean computerTurn)
  {
  
      for (int i = 0; i < handHuman.size(); i++) 
       {
        if (handHuman.get(i).equals(numberToScan))
         {
            cheating = false;
          }
       }
    
     
     if (cheating) {JOptionPane.showMessageDialog(null,"You are cheating. You do not own card # " + numberToScan + "! Try Again!");  }
     
      for (int i = 0; i < handComputer.size(); i++) 
       {
        System.out.println("NumberToScan is" + numberToScan + "index number is " + handComputer.get(i) ); 
        if (handComputer.get(i).equals(numberToScan))
         {
            match = true;
          }
       }
        
        //System.out.println("Match " + match);
        
            if (!computerTurn && humanTurn && !cheating)
              {
                 if (match)
                    {
                     JOptionPane.showMessageDialog(null, "Match!");
                     removeCardHumanComputer(numberToScan, handHuman, handComputer);
                     JOptionPane.showMessageDialog(null, "Go Again!");
                     match = false;
                    }
                 else
                    {
                     addCard(humanTurn, computerTurn, handHuman, handComputer, deckDealer);
                     humanTurn = false; 
                     computerTurn = true;
                    }
              }
              
              
               System.out.println("humanTurn is " + humanTurn);
               System.out.println("computerTurn is " + computerTurn);
               
                if  (humanTurn) 
                {
                   humanInput(input, numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn );
                }
               else if (computerTurn)
                { 
                   numberToScan = 0;
                   computerInput(input, numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn);
                }
                
                if (humanTurn)
                {
                humanInput(input, numberToScan, handHuman,handComputer, deckDealer, cheating, match, humanTurn, computerTurn );
                }
                
                
                
               
            
          
  }
  
  public static void addCard(boolean humanTurn, boolean computerTurn, ArrayList handHuman, ArrayList handComputer, ArrayList deckDealer)
   {    
    
                         // System.out.println("humanTurn ended");
                     //JOptionPane.showMessageDialog(null, "GoFish!");
       Random rand = new Random();    	  
         if (humanTurn)
         {
            for (int index = 0; index < 1; index++) 
            {
            int temp = (int)deckDealer.get(rand.nextInt( deckDealer.size() ) );
            handComputer.add( temp );
            deckDealer.remove( temp );
            System.out.println("handComputer added card # " + temp);
            System.out.println("deckDealer removed card # " + temp);
            JOptionPane.showMessageDialog(null, "GoFish! (humanTurn)");
            break;
            }  
         }
         else if (computerTurn)
         {
         
            for (int index = 0; index < 1; index++) 
            {
            int temp = (int)deckDealer.get(rand.nextInt( deckDealer.size() ) );
            handComputer.add( temp );
            deckDealer.remove( temp );
            System.out.println("handComputer added card # " + temp);
            JOptionPane.showMessageDialog(null, "GoFish! (computerTurn)");
            System.out.println("deckDealer removed card # " + temp);
            break;
             }  
         }
   }
   
   public static void removeCardHumanComputer(int numberToScan, ArrayList handHuman, ArrayList handComputer)
    {
    
       for (int i = 0; i < handHuman.size(); i++) 
       {
       // System.out.println("NumberToScan is" + numberToScan + "index number is " + handHuman.get(i) ); 
        if (handHuman.get(i).equals(numberToScan))
         {
          System.out.println("removing from human card # " + handHuman.get(i));
           handHuman.remove(i);
           break;
          }
       }    
        
        for (int i = 0; i < handComputer.size(); i++) 
       {
       // System.out.println("NumberToScan is" + numberToScan + "index number is " + handComputer.get(i) ); 
        if (handComputer.get(i).equals(numberToScan))
         {
          System.out.println("removing from computer card # " + handComputer.get(i));
           handComputer.remove(i);
           break;
          }
        }               

      
    for (int index = 0; index < handHuman.size(); index++) {System.out.println("handHuman has card number" + handHuman.get(index));}
    for (int index = 0; index < handComputer.size(); index++) {System.out.println("handComputer has card number" + handComputer.get(index));}    
     
    
  }
   
 }

  //public static void computerAlgorithm 