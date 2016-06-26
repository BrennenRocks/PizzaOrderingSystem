/**
 * @author Robert Davis, Brennen Davis
 * CSIS2420 - PizzaOrderingClasses
 * Dec 7, 2015
 */
package pizzaClasses;

import java.util.ArrayList;
import java.util.Date;

public class Order {
	
	private Date date = null;
	private Customer c;
	private double price;
	private static ArrayList<Pizza> pizzas;
	
	public Order(Customer c) {
		this.c = c;
		this.price = 0.0;
		pizzas = new ArrayList<Pizza>();
	}
	
	public void addPizza(Pizza p) {
		pizzas.add(p);
		price += p.getPrice();
	}
	
	public void startNewPizza() {
		pizzas.add(new Pizza());
		currentPizza().selectSize(2);
	}
	
	public Pizza currentPizza() {
		return pizzas.get(pizzas.size() - 1);
	}
	
	public void deleteCurrentPizza() {
		pizzas.remove(pizzas.size() - 1);
	}
	
	public static String orderSummary() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%d - Total Pizzas%n%n", pizzas.size()));
		for(Pizza p: pizzas)
			sb.append(p.toString()+"\n\n");
		return sb.toString();
	}
	
	public void setTimeVar(){
		this.date = new java.util.Date();
	}
	
	public String toString() {
		price = 0;
		StringBuilder sb = new StringBuilder();
		sb.append(c.getName() + " - " + c.getNumber() +"\n");
		sb.append(c.getAddress() +"\n");
		sb.append(c.getInstructions() +"\n");
		sb.append(String.format("%n%d - Total Pizzas%n%n", pizzas.size()));
		for(Pizza p: pizzas) {
			sb.append(p.toString()+"\n\n");
			price += p.getPrice();
		}
		sb.append(String.format("Total Price: $%.2f", price));
		
		if(date != null)
			sb.append("\n" + date);
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Customer rob = new Customer("8015555555");
		rob.setName("Rob");
		rob.setAddress("Some cool place");
		rob.setInstructions("No instructions");
		Order nOrder = new Order(rob);
		System.out.println("Creating Pizza 1");
		nOrder.startNewPizza();
		nOrder.currentPizza().selectSize(2);
		nOrder.currentPizza().addAnchovies(true);
		System.out.println(orderSummary());
		System.out.println("Creating pizza 2");
		nOrder.startNewPizza();
		nOrder.currentPizza().selectSize(1);
		nOrder.currentPizza().addPepperoni(true);
		System.out.println("Call mid creation");
		System.out.println(orderSummary());
		nOrder.currentPizza().addSausage(true);
		System.out.println(orderSummary());
		
		System.out.println("Finished Order");
		System.out.println(nOrder.toString());
	}
}
