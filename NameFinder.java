import java.util.ArrayList;
import java.util.Random;

public class NameFinder
{
 public static ArrayList<String> firstName = new ArrayList<>();
 public static ArrayList<String> lastName = new ArrayList<>();
   
    private static void generateLists()
    {
  lastName.add("Investment");
  lastName.add("Bank");
  lastName.add("Software");
  lastName.add("Technologies");
  lastName.add("Incorporated");
  lastName.add("Games");
  lastName.add("Industries");
  lastName.add("Union");
  lastName.add("Ventures");
  lastName.add("Enterptrises");
  lastName.add("Corporation");
  lastName.add("LLC");
  lastName.add("");
  lastName.add("Entertainment");
  lastName.add("Airlines");
  firstName.add("James");
  firstName.add("Brown");
  firstName.add("J.P");
  firstName.add("Gates");
  firstName.add("Rink");
  firstName.add("WorldWide");
  firstName.add("Fusion");
  firstName.add("Beta");
  firstName.add("Liberty");
  firstName.add("Budget");
  firstName.add("Choice");
  firstName.add("3 Guys");
  firstName.add("Donald");
  firstName.add("McCalister");
  firstName.add("Peach");
  firstName.add("Peace");
  firstName.add("King");
  firstName.add("Spot");  
  firstName.add("Mario's");
  firstName.add("Lead");
  firstName.add("Arbuckles");
  firstName.add("Spot");
  firstName.add("Fate");
  firstName.add("Ash");
  firstName.add("Stark");
  firstName.add("Gold");
  firstName.add("Con");
    }
   
   
   public static String generateName()
   {
	 Random r = new Random();
     generateLists();
	 String returnStr = firstName.get(r.nextInt(firstName.size())) + " " + lastName.get(r.nextInt(lastName.size()));
	 for(Company c: StockMarket.listOfCompanies)
	 {
		 if(c.getName().equals(returnStr))
		 {
			 return generateName();
		 }
	 }
	 return returnStr;
   }
   
   public static void main(String args[])
   {
    generateLists();
    for(int i = 0; i < 30; i++)
    {
     System.out.println(generateName());
    }
   }
}