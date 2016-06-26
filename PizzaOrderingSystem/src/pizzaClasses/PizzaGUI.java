/**
 * @author Robert Davis, Brennen Davis
 * CSIS2420 - PizzaOrderingClasses
 * Dec 7, 2015
 */
package pizzaClasses;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.DropMode;

public class PizzaGUI {
	
	
	private Customer currentCust;
	private Order newOrder;
	
	
	private JFrame frame;
	private JTextField txtPhoneNumber;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtInstructions;
	
	private JTextArea txtSummary;
	private JScrollPane sumPane;
	private JTextArea txtEmpInst;
	
	private JLabel lblPizzaToppings;
	private JLabel lblMeatToppings;
	private JLabel lblVeggieToppings;
	private JLabel lblPizzaSizes;
	private JLabel lblFinishTheOrder;
	
	private JCheckBox chckbxAnchovies;
	private JCheckBox chckbxSausage;
	private JCheckBox chckbxPepperoni;
	private JCheckBox chckbxItalianSausage;
	private JCheckBox chckbxBlackOlives;
	private JCheckBox chckbxMushroom;
	private JCheckBox chckbxOnions;
	private JCheckBox chckbxRoastedPeppers;
	
	private JRadioButton rdbtnSmall;
	private JRadioButton rdbtnMedium;
	private JRadioButton rdbtnLarge;
	
	private JButton btnSubmitPhone;
	private JButton btnAddPizzaTo;
	private JButton btnTotalThisOrder;
	private JButton btnUpdCusInfo;
	private JButton btnSubmit;
	private JButton btnClear;
	
	private JCheckBox[] chckbxs;
	private JRadioButton[] rdobuts;
	
	private JLabel lblEmpInstructions;
	
	
	private PizzaOrderModel db;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PizzaGUI window = new PizzaGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PizzaGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 850);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setLayout(null);
		
		phoneNumber();
		customerDetails();
		pizzaToppings();
		sizes();
		orderSummary();
		db = new PizzaOrderModel();
		
		chckbxs = new JCheckBox[] {chckbxAnchovies, chckbxSausage, chckbxPepperoni, chckbxItalianSausage, chckbxBlackOlives, chckbxMushroom, chckbxOnions, chckbxRoastedPeppers};
		rdobuts = new JRadioButton[] {rdbtnSmall, rdbtnMedium, rdbtnLarge};
		for(int i = 0; i < chckbxs.length; i++){
			chckbxs[i].setEnabled(false);
		}
		for(int i = 0; i < rdobuts.length; i++){
			rdobuts[i].setEnabled(false);
		}
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				db.shutdown();
			}
		});
		
		
	}

	/**
	 * The Order Summary portion of the GUI
	 * Submit Pizza
	 * Clear GUI
	 * Employee Instruction Text Area
	 */
	private void orderSummary() {
		JLabel lblOrderSummary = new JLabel("Order Summary");
		lblOrderSummary.setBounds(10, 481, 100, 16);
		frame.getContentPane().add(lblOrderSummary);
		
		txtSummary = new JTextArea();
		txtSummary.setEditable(false);
		//txtSummary.setBounds(10, 503, 385, 85);
		//frame.getContentPane().add(txtSummary);
		
		sumPane = new JScrollPane(txtSummary);
		sumPane.setBounds(10, 503, 412, 98);
		frame.getContentPane().add(sumPane);
		
		lblFinishTheOrder = new JLabel("Finish the Order");
		lblFinishTheOrder.setBounds(10, 637, 331, 16);
		frame.getContentPane().add(lblFinishTheOrder);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(10, 666, 200, 27);
		btnSubmit.setEnabled(false);
		frame.getContentPane().add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				db.add(newOrder);
				btnSubmitPhone.setEnabled(false);
				btnSubmit.setEnabled(false);
				btnUpdCusInfo.setEnabled(false);
				btnAddPizzaTo.setEnabled(false);
				btnTotalThisOrder.setEnabled(false);
				for(int i = 0; i < chckbxs.length; i++){
					chckbxs[i].setEnabled(false);
				}
				
				for(int i = 0; i < rdobuts.length; i++){
					rdobuts[i].setEnabled(false);
				}
				
				newOrder.setTimeVar();
				txtSummary.setText(newOrder.toString());
				txtEmpInst.setText("Hit clear and make another order if there is another"
								+ " Customer on the phone");
			}
		});
		
		btnClear = new JButton("Clear");
		btnClear.setBounds(222, 666, 200, 27);
		frame.getContentPane().add(btnClear);
		btnClear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				btnSubmitPhone.setEnabled(true);
				txtSummary.setText("");
				txtEmpInst.setText("Enter the customer's phone number.");
				
				btnSubmit.setEnabled(false);
				btnUpdCusInfo.setEnabled(false);
				btnAddPizzaTo.setEnabled(false);
				btnTotalThisOrder.setEnabled(false);
				for(int i = 0; i < chckbxs.length; i++){
					chckbxs[i].setSelected(false);
					chckbxs[i].setEnabled(false);
				}
				
				for(int i = 0; i < rdobuts.length; i++){
					rdobuts[i].setEnabled(false);
				}
				
				txtPhoneNumber.setText("");
//				txtName.setEnabled(false);
				txtName.setText("");
//				txtAddress.setEnabled(false);
				txtAddress.setText("");
//				txtInstructions.setEnabled(false);
				txtInstructions.setText("");
				

			}
		});
		
		lblEmpInstructions = new JLabel("Employee Instructions:");
		lblEmpInstructions.setBounds(10, 706, 331, 16);
		frame.getContentPane().add(lblEmpInstructions);
		
		
		txtEmpInst = new JTextArea();
		txtEmpInst.setEditable(false);
		txtEmpInst.setBounds(10, 735, 422, 55);
		txtEmpInst.setText("Enter the customer's phone number.");
		frame.getContentPane().add(txtEmpInst);
		
		
		
	}

	/**
	 * Customer Details
	 */
	private void customerDetails() {
		JLabel lblCustomerDetails = new JLabel("Customer Details");
		lblCustomerDetails.setBounds(10, 90, 200, 22);
		frame.getContentPane().add(lblCustomerDetails);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(40, 111, 200, 22);
		frame.getContentPane().add(lblName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setBounds(40, 128, 200, 27);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblInstructions = new JLabel("Instructions");
		lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions.setBounds(40, 146, 200, 27);
		frame.getContentPane().add(lblInstructions);
		
		txtName = new JTextField();
		txtName.setBounds(195, 111, 182, 22);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(195, 130, 182, 22);
		frame.getContentPane().add(txtAddress);
		txtAddress.setColumns(10);
		
		txtInstructions = new JTextField();
		txtInstructions.setBounds(195, 148, 182, 22);
		frame.getContentPane().add(txtInstructions);
		txtInstructions.setColumns(10);
		
		btnUpdCusInfo = new JButton("Update Customer Info");
		btnUpdCusInfo.setBounds(119, 181, 177, 25);
		frame.getContentPane().add(btnUpdCusInfo);
		btnUpdCusInfo.setEnabled(false);
		btnUpdCusInfo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				btnSubmitPhone.setEnabled(false);
				if(txtName.getText().equals("") || txtAddress.getText().equals("") || txtInstructions.getText().equals("") ){
					btnSubmit.setEnabled(false);
					btnAddPizzaTo.setEnabled(false);
					btnTotalThisOrder.setEnabled(false);
					for(int i = 0; i < chckbxs.length; i++){
						chckbxs[i].setEnabled(false);
					}
					
					for(int i = 0; i < rdobuts.length; i++){
						rdobuts[i].setEnabled(false);
					}
					
					txtEmpInst.setText("You must fill out customer info!");
				}else{
					btnAddPizzaTo.setEnabled(true);
					for(int i = 0; i < chckbxs.length; i++){
						chckbxs[i].setEnabled(true);
					}
					
					for(int i = 0; i < rdobuts.length; i++){
						rdobuts[i].setEnabled(true);
					}
					
					txtEmpInst.setText("Update Customer info if needed.\nTake customer's order.");
					
				}
				currentCust.setName(txtName.getText());
				currentCust.setAddress(txtAddress.getText());
				currentCust.setInstructions(txtInstructions.getText());
				db.add(currentCust);
				
				
			}
		});
		
	}

	/**
	 * Pizza Toppings!
	 * 
	 */
	private void pizzaToppings() {
		lblPizzaToppings = new JLabel("Pizza Toppings");
		lblPizzaToppings.setBounds(10, 219, 200, 22);
		frame.getContentPane().add(lblPizzaToppings);
		
		lblMeatToppings = new JLabel("Meat Toppings");
		lblMeatToppings.setBounds(40, 245, 105, 32);
		frame.getContentPane().add(lblMeatToppings);
		
		lblVeggieToppings = new JLabel("Veggie Toppings");
		lblVeggieToppings.setBounds(204, 242, 147, 39);
		frame.getContentPane().add(lblVeggieToppings);
				
		chckbxAnchovies = new JCheckBox("Anchovies");
		chckbxAnchovies.setBounds(40, 274, 113, 25);
		frame.getContentPane().add(chckbxAnchovies);
		chckbxAnchovies.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				newOrder.currentPizza().addAnchovies(chckbxAnchovies.isSelected());
				txtSummary.setText(newOrder.orderSummary());
			}
		});
		
		chckbxSausage = new JCheckBox("Sausage");
		chckbxSausage.setBounds(40, 296, 113, 25);
		frame.getContentPane().add(chckbxSausage);
		chckbxSausage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				newOrder.currentPizza().addSausage(chckbxSausage.isSelected());
				txtSummary.setText(newOrder.orderSummary());			
			}
		});
		
		chckbxPepperoni = new JCheckBox("Pepperoni");
		chckbxPepperoni.setBounds(40, 320, 113, 25);
		frame.getContentPane().add(chckbxPepperoni);
		chckbxPepperoni.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				newOrder.currentPizza().addPepperoni(chckbxPepperoni.isSelected());
				txtSummary.setText(newOrder.orderSummary());
			}
		});
		
		chckbxItalianSausage = new JCheckBox("Italian Sausage");
		chckbxItalianSausage.setBounds(40, 343, 128, 25);
		frame.getContentPane().add(chckbxItalianSausage);
		chckbxItalianSausage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				newOrder.currentPizza().addItalianSausage(chckbxItalianSausage.isSelected());
				txtSummary.setText(newOrder.orderSummary());
			}
		});
		
		chckbxBlackOlives = new JCheckBox("Black Olives");
		chckbxBlackOlives.setBounds(183, 274, 113, 25);
		frame.getContentPane().add(chckbxBlackOlives);
		chckbxBlackOlives.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				newOrder.currentPizza().addBlackOlives(chckbxBlackOlives.isSelected());
				txtSummary.setText(newOrder.orderSummary());
			}
		});
		
		chckbxMushroom = new JCheckBox("Mushrooms");
		chckbxMushroom.setBounds(183, 296, 113, 25);
		frame.getContentPane().add(chckbxMushroom);
		chckbxMushroom.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				newOrder.currentPizza().addMushrooms(chckbxMushroom.isSelected());
				txtSummary.setText(newOrder.orderSummary());
			}
		});
		
		chckbxOnions = new JCheckBox("Onions");
		chckbxOnions.setBounds(183, 320, 113, 25);
		frame.getContentPane().add(chckbxOnions);
		chckbxOnions.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				newOrder.currentPizza().addOnions(chckbxOnions.isSelected());
				txtSummary.setText(newOrder.orderSummary());
			}
		});
		
		chckbxRoastedPeppers = new JCheckBox("Roasted Peppers");
		chckbxRoastedPeppers.setBounds(183, 343, 128, 25);
		frame.getContentPane().add(chckbxRoastedPeppers);
		chckbxRoastedPeppers.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				newOrder.currentPizza().addRoastedPeppers(chckbxRoastedPeppers.isSelected());
				txtSummary.setText(newOrder.orderSummary());
			}
		});
		
	}

	/**
	 * Pizza Sizes 
	 * Add Pizza to Order
	 * Total the Order
	 */
	private void sizes() {
		lblPizzaSizes = new JLabel("Pizza Sizes");
		lblPizzaSizes.setBounds(12, 377, 98, 32);
		frame.getContentPane().add(lblPizzaSizes);
		
		rdbtnSmall = new JRadioButton("Small");
		rdbtnSmall.setBounds(35, 407, 75, 27);
		rdbtnSmall.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				rdbtnMedium.setSelected(false);
				rdbtnLarge.setSelected(false);
				newOrder.currentPizza().selectSize(0);
				txtSummary.setText(newOrder.orderSummary());
			}
		});
		frame.getContentPane().add(rdbtnSmall);
		
		rdbtnMedium = new JRadioButton("Medium");
		rdbtnMedium.setBounds(137, 404, 85, 32);
		rdbtnMedium.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				rdbtnSmall.setSelected(false);
				rdbtnLarge.setSelected(false);
				newOrder.currentPizza().selectSize(1);
				txtSummary.setText(newOrder.orderSummary());
			}
		});
		frame.getContentPane().add(rdbtnMedium);
		
		rdbtnLarge = new JRadioButton("Large");
		rdbtnLarge.setBounds(243, 401, 98, 39);
		rdbtnLarge.setSelected(true);
		rdbtnLarge.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				rdbtnMedium.setSelected(false);
				rdbtnSmall.setSelected(false);
				newOrder.currentPizza().selectSize(2);
				txtSummary.setText(newOrder.orderSummary());
			}
		});
		frame.getContentPane().add(rdbtnLarge);
		
		btnAddPizzaTo = new JButton("Add Pizza to this Order");
		btnAddPizzaTo.setBounds(40, 443, 170, 25);
		btnAddPizzaTo.setEnabled(false);
		frame.getContentPane().add(btnAddPizzaTo);
		btnAddPizzaTo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				txtSummary.setText(newOrder.orderSummary());
				newOrder.startNewPizza();
				btnTotalThisOrder.setEnabled(true);
				
				for(int i = 0; i < chckbxs.length; i++){
					chckbxs[i].setSelected(false);
				}
				rdbtnSmall.setSelected(false);
				rdbtnMedium.setSelected(false);
				rdbtnLarge.setSelected(true);
				
				txtEmpInst.setText("Get another pizza order taken if customer wants it."
								+ "\nIf customer doesn't want another pizza click \"Total this Order\"");
			}
		});
		
		btnTotalThisOrder = new JButton("Total this Order");
		btnTotalThisOrder.setBounds(222, 443, 129, 25);
		btnTotalThisOrder.setEnabled(false);
		frame.getContentPane().add(btnTotalThisOrder);
		btnTotalThisOrder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				newOrder.deleteCurrentPizza();
				txtSummary.setText(newOrder.toString());
				
				for(int i = 0; i < chckbxs.length; i++){
					chckbxs[i].setEnabled(false);
				}
				for(int i = 0; i < rdobuts.length; i++){
					rdobuts[i].setEnabled(false);
				}
				
				btnSubmit.setEnabled(true);
				btnAddPizzaTo.setEnabled(false);
				btnTotalThisOrder.setEnabled(false);
				
				txtEmpInst.setText("Give customer the total price and submit the order.");
			}
		});
	
	}

	/**
	 * Search for Customer by phone number
	 */
	private void phoneNumber() {
		JLabel lblPhoneNumber = new JLabel("Customer Phone Number");
		lblPhoneNumber.setBounds(10, 0, 200, 32);
		frame.getContentPane().add(lblPhoneNumber);
		
		//load in customer info
		btnSubmitPhone = new JButton("Submit Phone #");
		btnSubmitPhone.setBounds(10, 45, 135, 32);
		frame.getContentPane().add(btnSubmitPhone);
		btnSubmitPhone.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
					currentCust = db.find(txtPhoneNumber.getText());
					
					if (currentCust!=null) {
						System.out.println("Found Customer");
						txtName.setText(currentCust.getName());
						txtAddress.setText(currentCust.getAddress());
						txtInstructions.setText(currentCust.getInstructions());
						btnUpdCusInfo.setEnabled(true);
						btnAddPizzaTo.setEnabled(true);
						btnSubmit.setEnabled(false);
						btnTotalThisOrder.setEnabled(false);
						for(int i = 0; i < chckbxs.length; i++){
							chckbxs[i].setEnabled(true);
						}
						
						for(int i = 0; i < rdobuts.length; i++){
							rdobuts[i].setEnabled(true);
						}
						
						txtEmpInst.setText("Update Customer info if needed.\nTake customer's order.");
						
					} else {
						currentCust = new Customer(txtPhoneNumber.getText()); //if customer is not found					
						System.out.println("Didn't find customer created new one");
						
						btnUpdCusInfo.setEnabled(true);
						txtEmpInst.setText("Be sure to fill out all information about the customer!");
					}
					
				btnSubmitPhone.setEnabled(false);
				newOrder = new Order(currentCust);
				newOrder.startNewPizza();
				
				
			}
		});
	
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setBounds(177, 45, 200, 27);
		frame.getContentPane().add(txtPhoneNumber);
		txtPhoneNumber.setColumns(10);
		
		
	}
}
