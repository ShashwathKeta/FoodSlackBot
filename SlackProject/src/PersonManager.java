


import java.util.ArrayList;

public class PersonManager {
	
	private static ArrayList<People> people;
	
	//Constructor
	public PersonManager() 
	{
		people = new ArrayList<People>();
	}

	//Getters
	public static ArrayList<People > getPeople() 
	{
		return people;
	}
	
	public static String getAllPeople() {
		String allPeople = "";
		
		for(int i = 0; i<people.size(); i++)
		{
			allPeople = allPeople + "\n" + people.get(i).getName();
		}
		
		return allPeople;
	}
	
	public static String getPersonsOrder(String name, String rest) 
	{
		String order = "";
		
		for(int i = 0; i<people.size(); i++) 
		{
			if((people.get(i)).getName().equals(name))
			{
				order = (people.get(i)).getOrder(rest);
				break;
			}
		}
		
		return order;
	}
	
	public static String getLastPerson() 
	{
		String name = "";
		
		name = (people.get(people.size()-1)).getName();
		
		return name;
	}
	
	public static String getLastRestaurant() 
	{
		String rest = "";
		
		rest = ( people.get(people.size()-1)).getName();
		
		return rest;
	}
	
	public static String getAllPeopleForRestaurant(String rest) {
		String allPeople = "";
		
		for(int i = 0; i<people.size(); i++) {
			if(people.get(i).doesRestaurantExist(rest))
			{
				allPeople = allPeople + "\n" + people.get(i).getName();
			}
		}
		
		return allPeople;
	}
	
	public static String getAllOrders(String rest) 
	{
		String allOrders = "";
		
		for(int i = 0; i<people.size(); i++) 
		{
			if((people.get(i)).doesRestaurantExist(rest)) 
			{
				allOrders = allOrders + "\n" + (people.get(i)).getName() + 
						": " + (people.get(i)).getOrder(rest);
			}
		}
		
		return allOrders;
	}
	
	//Setters
	public static void setOrder(String name, String rest, String order)
	{
		for(int i = 0; i<people.size(); i++)
		{
			if((people.get(i)).getName().equals(name)) 
			{
				(people.get(i)).editOrder(rest, order);
				break;
			}
		}
	}
	
	public static void editName(String oldName, String newName) 
	{
		for(int i = 0; i<people.size(); i++) 
		{
			if((people.get(i)).getName().equals(oldName)) 
			{
				(people.get(i)).setName(newName);
				break;
			}
		}
	}
	
	//Other Methods
	public static void addPerson(String name) 
	{
		People n = new People(name);
		people.add(n);
	}
	
	public static void addRestaurant(String name, String rest, String order) 
	{
		for(int i = 0; i<people.size(); i++) 
		{
			if((people.get(i)).getName().equals(name)) 
			{
				(people.get(i)).addRestaurant(rest, order);
				break;
			}
		}
	}
	
	public static void addRestaurantWithoutOrder(String name, String rest) 
	{
		for(int i = 0; i<people.size(); i++) 
		{
			if((people.get(i)).getName().equals(name)) 
			{
				(people.get(i)).addRestaurantWithoutOrder(rest);
				break;
			}
		}
	}
}