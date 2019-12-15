


import java.util.*;

public class People {
  
  //Instance Variables
  private String name;
  private int slackID;
  private ArrayList<String> order;
  private ArrayList<String> restaurant;
  
  
  //Constructors
  public People(String n, int sl, ArrayList<String> ord, ArrayList<String> rest) 
  { 
    name = n;
    slackID = sl;
    order = ord;
    restaurant = rest;
  }
  
  public People(String n, int sl)
  {
    name = n;
    slackID = sl;
    order = new ArrayList<String>();
    restaurant = new ArrayList<String>();
  }
  
  public People(String n)
  {
    name = n;
    slackID = 0;
    order = new ArrayList<String>();
    restaurant = new ArrayList<String>();
  }
  
  public People(int sl)
  {
    slackID = sl;
    name = "";
    order = new ArrayList<String>();
    restaurant = new ArrayList<String>();
    
  }
  
  public People(ArrayList<String> ord)
  {
    slackID = 0;
    name = "";
    order = ord;
    restaurant = new ArrayList<String>();
  }
  
  public People(int sl, ArrayList<String> ord)
  {
    slackID = sl;
    name = "";
    order = ord;
    restaurant = new ArrayList<String>();
  }
  
  public People(String n, ArrayList<String> ord)
  {
    slackID = 0;
    name = n;
    order = ord;
    restaurant = new ArrayList<String>();
  }
  
  public People(String n, int sl, ArrayList<String> rest)
  {
    name = n;
    slackID = sl;
    order = new ArrayList<String>();
    restaurant = rest;
  }
  
  public People(ArrayList<String> ord, ArrayList<String> rest)
  {
    slackID = 0;
    name = "";
    order = ord;
    restaurant = rest;
  }
  
  public People(int sl, ArrayList<String> ord, ArrayList<String> rest)
  {
    slackID = sl;
    name = "";
    order = ord;
    restaurant = rest;
  }
  
  public People(String n, ArrayList<String> ord, ArrayList<String> rest)
  {
    slackID = 0;
    name = n;
    order = ord;
    restaurant = rest;
  }
  
  
  //Setters
  public void setName(String n)
  {
    name = n;
  }
  
  public void setSlack(int sl)
  {
    slackID = sl;
  }
  
  
  //Getters
  public String getName() 
  {
	return name;  
  }
  
  public int getSlackID()
  {
	  return slackID;
  }
  
  //Getting Order
  public String getOrder(String rest)
  {
	  String ord = "";
	  for(int i = 0; i<restaurant.size(); i++) 
	  {
		  if(restaurant.get(i).equals(rest))
		  {
			  ord = order.get(i);
		  }
	  }
	  
	  return ord;
  }
  
  //Checking if this person have this restaurant on their list
  public boolean doesRestaurantExist(String rest) 
  {
	  for(int i = 0; i<restaurant.size(); i++)
	  {
		  if(restaurant.get(i).equals(rest))
		  {
			  return true;
		  }
	  }
	  
	  return false;
  }
  
  //Adding a Restaurant to the list of available options for this person
  public void addRestaurant(String rest, String ord)
  {
    restaurant.add(rest);
    order.add(ord);
  }
  
  
  //Adding Restaurant only if order was not sent with Restaurant
  public void addRestaurantWithoutOrder(String rest) 
  {
	  restaurant.add(rest);
	  order.add("");
  }
  
  
  //Editing the order of a restaurant that already exists
  public void editOrder(String rest, String newOrder)
  {
    int index = 0;
    
    for(int i = 0; i<restaurant.size(); i++)
    {
      if(restaurant.get(i) == rest)
      {
        index = i;
        break;
      } 
    }
    order.set(index, newOrder);
  }
  /* ADD YOUR CODE HERE */
  
}