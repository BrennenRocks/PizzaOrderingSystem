/**
 * @author Robert Davis, Brennen Davis
 * CSIS2420 - PizzaOrderingClasses
 * Dec 7, 2015
 */
package pizzaClasses;

public class Pizza {
	
	private static double price;
	private int size; // 0=Small 1=Med 2=Large
	private StringBuilder sb;
	private double smallPrice = 5.99;
	private double medPrice = 6.99;
	private double largePrice = 7.99;
	private boolean anchovies = false;
	private boolean pepperoni = false;
	private boolean sausage = false;
	private boolean italian = false;
	private boolean blkOlives = false;
	private boolean onions = false;
	private boolean rstPeppers = false;
	private boolean mushrooms = false;
	
	public enum Toppings {
		//Prices can be changed and everything else will be changed.
		ANCHOVIES(.50, "Anchovies"),
		PEPPERONI(.50, "Pepperoni"),
		SAUSAGE(.50, "Sausage"),
		ITALIANSAUSAGE(.50, "Italian Sausage"),
		BLACKOLIVES(.50, "Black Olives"),
		ONIONS(.50, "Onions"),
		ROASTEDPEPPERS(.50, "Roasted Peppers"),
		MUSHROOMS(.50, "Mushrooms");
		
		public double value;
		public String string;
		
		
		private Toppings(double value, String string) {
			this.value = value;
			this.string = "\t";
			this.string += string;
			this.string += String.format("(+$%.2f)", value);

		}
	}
	/**
	 * Creates a pizza with the params
	 * 
	 * @param size
	 * @param anchovies
	 * @param pepperoni
	 * @param sausage
	 * @param italian
	 * @param blkOlives
	 * @param onions
	 * @param rstedPeprs
	 */
	public Pizza(int size, boolean anchovies, 
			boolean pepperoni, boolean sausage, 
			boolean italian, boolean blkOlives, 
			boolean onions, boolean rstedPeprs) {
		sb = new StringBuilder();
		selectSize(size);
		addAnchovies(anchovies);
		addPepperoni(pepperoni);
		addSausage(sausage);
		addItalianSausage(italian);
		addBlackOlives(blkOlives);
		addOnions(onions);
		addRoastedPeppers(rstedPeprs);
		sb.append(String.format("Price: $%.2f%n", price));
		
	}
	
	public Pizza() {
		sb = new StringBuilder();
		size = 5;
	}
	
	public void selectSize(int n){
		this.size = n;
	}
	public void addAnchovies(boolean b) {
		anchovies = b;
	}
	public void addPepperoni(boolean b) {
		pepperoni = b;
	}
	public void addSausage(boolean b) {
		sausage = b;
	}
	public void addItalianSausage(boolean b) {
		italian = b;
	}
	public void addBlackOlives(boolean b) {
		blkOlives = b;
	}
	public void addOnions(boolean b) {
		onions = b;
	}
	public void addRoastedPeppers(boolean b) {
		rstPeppers = b;
	}
	public void addMushrooms(boolean b) {
		mushrooms = b;
	}
	
	/**
	 * Returns the price of the pizza
	 * @return price
	 */
	public double getPrice() {
		price = 0.0;
		switch(size) {
		case 0:
			price = 5.99;
			break;
		case 1:
			price = 6.99;
			break;
		case 2:
			price = 7.99;
			break;
		default:
			break;
		}
		if (anchovies) {
			price += Toppings.ANCHOVIES.value;
		} 
		if (pepperoni) {
			price += Toppings.PEPPERONI.value;
		}
		if (sausage) {
			price += Toppings.SAUSAGE.value;
		}
		if (italian) {
			price += Toppings.ITALIANSAUSAGE.value;
		}
		if (blkOlives) {
			price += Toppings.BLACKOLIVES.value;
		}
		if (onions) {
			price += Toppings.ONIONS.value;
		}
		if (rstPeppers) {
			price += Toppings.ROASTEDPEPPERS.value;
		}
		if (mushrooms) {
			price += Toppings.MUSHROOMS.value;
		}
		return price;
	}
	
	/**
	 * Returns a string of the pizza breakdown and price
	 */
	public String toString() {
		sb = new StringBuilder();
		switch(size) {
		case 0:
			sb.append(String.format("Small Pizza ($%.2f)%n", smallPrice));
			break;
		case 1:
			sb.append(String.format("Medium Pizza ($%.2f)%n", medPrice));
			break;
		case 2:
			sb.append(String.format("Large Pizza ($%.2f)%n", largePrice));
			break;
		default:
			sb.append("Please select a size of Pizza\n");
			break;
		}
		if (anchovies) {
			sb.append(Toppings.ANCHOVIES.string+"\n");
		} 
		if (pepperoni) {
			sb.append(Toppings.PEPPERONI.string+"\n");
		}
		if (sausage) {
			sb.append(Toppings.SAUSAGE.string+"\n");
		}
		if (italian) {
			sb.append(Toppings.ITALIANSAUSAGE.string+"\n");
		}
		if (blkOlives) {
			sb.append(Toppings.BLACKOLIVES.string+"\n");
		}
		if (onions) {
			sb.append(Toppings.ONIONS.string+"\n");
		}
		if (rstPeppers) {
			sb.append(Toppings.ROASTEDPEPPERS.string+"\n");
		}
		if (mushrooms) {
			sb.append(Toppings.MUSHROOMS.string+"\n");
		}
		sb.append(String.format("Price: %.2f", getPrice()));
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Pizza p = new Pizza();
		
		
		System.out.println("Adding Pepperoni");
		p.addPepperoni(true);
		System.out.println(p.toString());
		System.out.println("Selecting size");
		p.selectSize(2);
		System.out.println(p.toString());
		System.out.println("Adding Sausage");
		p.addSausage(true);
		System.out.println(p.toString());
		System.out.println("Remove Sausage");
		p.addSausage(false);
		System.out.println(p.toString());
	}
}
