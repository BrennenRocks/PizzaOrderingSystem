/**
 * @author Robert Davis, Brennen Davis
 * CSIS2420 - PizzaOrderingClasses
 * Dec 7, 2015
 */
package pizzaClasses;

import java.io.Serializable;

public class Customer implements Serializable {

	private String number;
	private String name = "";
	private String address = "";
	private String instructions = "";
	
	/**
	 * @param number
	 */
	public Customer(String number) {
		super();
		setNumber(number);
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the instructions
	 */
	public String getInstructions() {
		return instructions;
	}

	/**
	 * @param instructions the instructions to set
	 */
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	
	
	public static void main(String[] args) {
		Customer c = new Customer("8015555555");
		c.setName("Bob");
		c.setAddress("105 Billy Lane");
		c.setInstructions("None");
		
		System.out.println(c.getAddress());
		System.out.println(c.getName());
		System.out.println(c.getInstructions());
		
		
	}
	
}
