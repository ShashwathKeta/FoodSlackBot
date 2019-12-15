

import org.springframework.web.socket.WebSocketSession;

import me.ramswaroop.jbot.core.common.Controller;
import me.ramswaroop.jbot.core.common.EventType;
import me.ramswaroop.jbot.core.slack.*;
import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.Message;;

public class SlackBot extends Bot {
	
	private String name = "";
	private String rest = "";

	@Override
	public String getSlackToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bot getSlackBot() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//All DM's
	@Controller(events = {EventType.DIRECT_MENTION, EventType.DIRECT_MESSAGE})
	public void onReceiveDM(WebSocketSession session, Event event) 
	{
		//Introduction
	    if (event.getText().contains("Hello")||event.getText().contains("Hi")) 
	    {
	        reply(session, event, new Message("Hi, I am the food bot!"));
	    }
	    
	    //Help Menu
	    else if(event.getText().contains("help")||event.getText().contains("Help"))
		{
			reply(session,event,new Message("Things you can do with me: "
					+ "\nAdd new People: add person"
					+ "\nAdd new Restraunts to existing people: add restaurant and order to person"
					+ "\nChange a persons order for a restaurant: change order for a person"
					+ "\nGet all peoples orders for a restaurant: get all orders"
					+ "\nGet a persons order for a restaurant: get persons order for a restaurant"
					+ "\nGet all people: get all people"
					+ "\nGet all people for a restaurant: get all people for a restaurant"));
		}
	    
	    //Get all people
	    else if(event.getText().contains("get all people"))
	    {
	    	reply(session, event, new Message(PersonManager.getAllPeople()));
	    }
	    
	}
	
	
	
	//Get all people who have orders for a restaurant
	@Controller(pattern = "get all people for a restaurant", next = "getR")
	public void getAllPeopleForARestaurant(WebSocketSession session, Event event)
	{
		startConversation(event, "getR");
		reply(session, event, new Message("What is the name of the Restaurant?"));
	}
	
	@Controller
	public void getR(WebSocketSession session, Event event) 
	{
		rest = event.getText();
		
		reply(session, event, new Message(PersonManager.getAllPeopleForRestaurant(rest)));
		stopConversation(event);
	}
	//End of get all people who have orders for a restaurant
	
	//Get Persons order for a restaurant
	@Controller(pattern = "get persons order for a restaurant", next = "getN")
	public void getPersonsOrder(WebSocketSession session, Event event)
	{
		startConversation(event, "getRest");
		reply(session, event, new Message("What is the name of the Person?"));
	}
	
	@Controller(next = "getRest")
	public void getN(WebSocketSession session, Event event)
	{
		name = event.getText();
		
		reply(session, event, new Message("Which Restraunt order do you need"));
		nextConversation(event);
	}
	
	@Controller
	public void getRest(WebSocketSession session, Event event)
	{
		rest = event.getText();
		
		reply(session, event, new Message(PersonManager.getPersonsOrder(name, rest)));
		stopConversation(event);
	}
	
	//Get All Orders Request
	@Controller(pattern = "(get all orders)", next = "sendOrders")
    public void getAllOrders(WebSocketSession session, Event event)
	{
        startConversation(event, "sendOrders");   // start conversation
        reply(session, event, new Message("What Restaurant would you like all orders for?"));
    }
	
	@Controller
	public void sendOrders(WebSocketSession session, Event event)
	{
		String rest = event.getText();
		
		PersonManager.getAllOrders(rest);
		stopConversation(event);
	}
	//End of Get all Orders Request
	
	
	
	
	//Change order for a person's restaurant
	@Controller(pattern = "change order for a peron", next = "getName")
	public void editOrder(WebSocketSession session, Event event)
	{
		startConversation(event,"getName");
		reply(session, event, new Message("What is the name of the person?"));
	}
	
	@Controller(next = "getRestaurant")
	public void getName(WebSocketSession session, Event event) 
	{
		name = event.getText();
		
		reply(session, event, new Message("What is the name of the Restaurant?"));
		nextConversation(event);
	}
	
	@Controller(next = "getOrder")
	public void getRestaurant(WebSocketSession session, Event event)
	{
		rest = event.getText();
		
		reply(session, event, new Message("What is the new order?"));
		nextConversation(event);
	}
	
	@Controller
	public void getOrder(WebSocketSession session, Event event) 
	{
		String order = event.getText();
		PersonManager.addRestaurant(name, rest, order);
		
		reply(session, event, new Message("Ok, my task was completed"));
		stopConversation(event);
	}
	//End of Change order for a persons restaurant
	
	
	
	
	//Add Restaurant and order to existing person
	@Controller(pattern = "add restaurant to person", next = "RequestRestaurant")
	public void RequestNameForRestaurant(WebSocketSession session, Event event) 
	{
		startConversation(event, "RequestRestaurant");
		
		reply(session, event, new Message("Whose list do you want to add a restaurant for?"));
	}
	
	@Controller(next = "RequestOrder")
	public void RequestRestaurant(WebSocketSession session, Event event) 
	{
		name = event.getText();
		
		reply(session, event, new Message("Ok, which restaurant do you want to add"));
		nextConversation(event);
	}
	
	@Controller(next = "receiveOrder")
	public void RequestOrder(WebSocketSession session, Event event)
	{
		rest = event.getText();
		
		reply(session, event, new Message("What is the order for this restaurant?"));
		nextConversation(event);
	}
	
	@Controller
	public void recieveOrder(WebSocketSession session, Event event)
	{
		String order = event.getText();
		PersonManager.addRestaurant(name, rest, order);
		
		reply(session, event, new Message("Ok, I have accomplished my task, master"));
		stopConversation(event);
	}
	//End of add Restaurant and order to existing person
	
	
	
	
	//Adding person Conversation
	@Controller(pattern = "(add person)", next = "nameConfirmation")
    public void addPerson(WebSocketSession session, Event event)
	{
        startConversation(event, "nameConfirmation");   // start conversation
        reply(session, event, new Message("Ok, What is his/her name?"));
    }
	
	@Controller(next = "AskForRestaurant")
	public void nameConfirmation(WebSocketSession session, Event event)
	{
		name = event.getText();
		PersonManager.addPerson(name);
		
		reply(session, event, 
				new Message("Ok, Do you want to add a list of Restaurants and Orders as well?"));
		nextConversation(event);
	}
	
	@Controller(next = "RecieveRestaurant")
	public void AskForRestaurant(WebSocketSession session, Event event)
	{
		String answer = event.getText();
		
		if(answer.contains("yes"))
		{
			reply(session,event, new Message("Ok, What restaurant do you want to add?"));
			nextConversation(event);
		}
		else 
		{
			reply(session, event, new Message("Ok, until next time then!"));
			stopConversation(event);
		}
	}
	
	@Controller(next = "ReceiveOrder")
	public void RecieveRestaurant(WebSocketSession session, Event event) 
	{
		rest = event.getText();
		PersonManager.addRestaurantWithoutOrder(PersonManager.getLastPerson(), rest);
		
		reply(session, event, new Message("What is their order for that restaurant?"));
		nextConversation(event);
	}
	
	@Controller(next = "AskForRestaurant")
	public void RecieveOrder(WebSocketSession session, Event event)
	{
		String ord = event.getText();
		PersonManager.addRestaurant(PersonManager.getLastPerson(), PersonManager.getLastRestaurant(), ord);
		
		reply(session, event, new Message("Ok, would you like to add another restaurant?"));
		nextConversation(event);
	}
	//End of New Person Conversation
	
}
