/**
 * @author Code Online
 * CSIS2420 - PizzaOrderingClasses
 * Dec 8, 2015
 */
package pizzaClasses;


 import java.io.*;
import java.util.HashMap;
import java.util.Map;

 /**
 * Home Delivery Pizza System: Solution to Focus on
 * Problem Solving Section for Chapter 12.
 */
 public class PizzaOrderModel {
 private Map<String, Customer> customerDB;
 private Queue<Order> orders;
 private boolean customerDBModified;
 private String customerDBfilename =
 new String( System.getProperty("java.io.tmpdir")+"/customers.dat" );

 /**
 * Constructor - initialize the model to handle orders.
 */
 public PizzaOrderModel(){
 customerDB = new HashMap<String, Customer>();
 loadCustomerDB( customerDBfilename );

 this.orders = new Queue<Order>();
 this.customerDBModified = false;
 }

 /**
 * Add an <tt>Order</tt> for a home delivery.
 * @param theOrder the <tt>Order</tt> to add
 * to the queue of orders; cannot be <tt>null</tt>
 * @throws <tt>IllegalArgumentException</tt> if
 * <tt>theOrder</tt> is <tt>null</tt>
 */
 public void add( Order theOrder ){
 if ( theOrder == null )
 throw new IllegalArgumentException();
 orders.enqueue( theOrder );
 }

 /**
 * Add a <tt>Customer</tt> to the customer database.
 * @param theCustomer the customer to add;
 * can't be <tt>null</tt>
 * @throws <tt>IllegalArgumentException</tt> if
 * <tt>Customer</tt> is <tt>null</tt>
 */
 public void add( Customer theCustomer ){
 if ( theCustomer == null )
 throw new IllegalArgumentException();
 this.customerDB.put( theCustomer.getNumber(),
 theCustomer );
 this.customerDBModified = true;
 }
 

 /**
 * Find a customer profile in the customer database given a
 * telephone number.
 * @param phoneNumber the phone number of the customer
 * @return <tt>Customer</Code> a customer if
 * <tt>phoneNumber</tt> is found in the database, <tt>null</tt>
 * otherwise
 * @throws <tt>IllegalArgumentException</tt> if
 * <tt>phoneNumber</tt> is <tt>null</tt>
 */
 public Customer find( String phoneNumber ){
 if ( phoneNumber == null )
 throw new IllegalArgumentException();
 return (Customer)customerDB.get( phoneNumber );
 }


 /**
 * Shutdown the model. This gives the model a chance to save
 * the customer database if it was modified.
 */
 public void shutdown(){
 if ( this.customerDBModified )
 saveCustomerDB( customerDBfilename );
 }

 private void loadCustomerDB( String filename ){
	 try{
		 FileInputStream fiStream = new FileInputStream( filename );
		 ObjectInputStream oiStream = new ObjectInputStream( fiStream );
		 this.customerDB = (HashMap<String, Customer>)(oiStream.readObject());
		 oiStream.close();
	 	} catch ( FileNotFoundException fnfe ) {
	 		this.customerDB = new HashMap<String, Customer>();
	 		} catch( Exception e ) {
	 		System.out.println( "Error during input: " + e.toString());
	 	}
 }

 private void saveCustomerDB( String filename ){
	 try{ 
		FileOutputStream foStream = new FileOutputStream( filename ) ;
 		ObjectOutputStream ooStream = new ObjectOutputStream( foStream );
 		ooStream.writeObject( this.customerDB );
 		ooStream.flush();
 		ooStream.close();
	 	} catch ( Exception e ) {
	 		System.out.println( "Error during output: " + e.toString());
	 	}
 	}
 }