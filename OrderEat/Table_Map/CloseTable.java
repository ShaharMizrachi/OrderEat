package Table_Map;

import java.awt.EventQueue;
import java.util.MissingFormatArgumentException;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.plaf.TextUI;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;

public class CloseTable {

	private JFrame frame;
	private ActiveTable at;
	private JTextField TextFiled_CreditCard;
	private JTextField textField_amount;
	private TableMap tm;
	private boolean billHasChanged;
	private JLabel lblTotalPrice;
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void show(TableMap tm,int tableNum) {
		frame= new JFrame();
		at=new ActiveTable(tableNum, 0, null, null);
		this.tm = tm;
		billHasChanged=false;
		lblTotalPrice = new JLabel("Total Price: " + at.getBill());
		initialize();
	}
	
	
	public CloseTable() {
	
	}
	

	
	private void initialize() {
		textField_amount = new JTextField();
		textField_amount.setFont(new Font("Tahoma", Font.PLAIN, 22));
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 1600, 1500);
		frame.setExtendedState(frame.MAXIMIZED_BOTH); // to open in full size window 
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		
		
		lblTotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblTotalPrice.setBounds(148, 583, 193, 32);
		frame.getContentPane().add(lblTotalPrice);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(billHasChanged)
					goBack(false);
				frame.setVisible(false);
				
				
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnBack.setBounds(48, 32, 119, 41);
		frame.getContentPane().add(btnBack);
		
		JLabel lblCreditCard = new JLabel("Credit Card Number:");
		lblCreditCard.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblCreditCard.setBounds(87, 281, 254, 32);
		frame.getContentPane().add(lblCreditCard);
		
		TextFiled_CreditCard = new JTextField();
		TextFiled_CreditCard.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String text=TextFiled_CreditCard.getText();
				int size=text.length();
				int numOfSpaces= (size/6)*2;
				if(e.getKeyChar()!=8) { // 8 is back space siymbol
					if(e.getKeyChar()<'0' || e.getKeyChar()>'9' || size-numOfSpaces>=16) {
						e.consume();
					}
					else if(size>0 && (size-numOfSpaces)%4==0) {
						TextFiled_CreditCard.setText(text +"  ");
					}
				}
				else {
					if(size>0 && text.charAt(size-1)==' ') {
						TextFiled_CreditCard.setText(text.substring(0,size-2)); // rang in string 
					}
				}	
			}
		});

		TextFiled_CreditCard.setBounds(412, 261, 210, 52);
		frame.getContentPane().add(TextFiled_CreditCard);
		TextFiled_CreditCard.setColumns(10);
		
		JLabel lblExDate = new JLabel("Expiration date");
		lblExDate.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblExDate.setBounds(74, 423, 225, 26);
		frame.getContentPane().add(lblExDate);
		
		JButton btnPayCredit = new JButton("Pay with Credit Card");
		btnPayCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(TextFiled_CreditCard.getText().length()<22) {
					 JOptionPane.showMessageDialog(null, "Invalid credit card niv gay ");
				 }
				 else {
					pay();
				}
			}
		});
		btnPayCredit.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnPayCredit.setBounds(249, 493, 334, 52);
		frame.getContentPane().add(btnPayCredit);
		
		JComboBox comboBoxYear = new JComboBox();
		comboBoxYear.setBounds(330, 427, 109, 29);
		frame.getContentPane().add(comboBoxYear);
		
		JComboBox comboBoxMonuth = new JComboBox();
		comboBoxMonuth.setBounds(523, 427, 99, 29);
		frame.getContentPane().add(comboBoxMonuth);
		setMounth(comboBoxMonuth);
		setYear(comboBoxYear);
		
		JButton btnCash = new JButton("Pay Cash ");
		btnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pay();
			}
		});
		btnCash.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnCash.setBounds(1158, 499, 193, 41);
		frame.getContentPane().add(btnCash);
		
		JLabel lblAmount_Pay = new JLabel("Amount to Pay");
		lblAmount_Pay.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblAmount_Pay.setBounds(279, 110, 267, 41);
		frame.getContentPane().add(lblAmount_Pay);
		
		
		textField_amount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar()!=8 && e.getKeyChar()!='.' &&(e.getKeyChar()<'0' || e.getKeyChar()>'9'))
					e.consume(); // throw it to grabeg
			}
		});
		textField_amount.setBounds(504, 110, 193, 36);
		frame.getContentPane().add(textField_amount);
		textField_amount.setColumns(10);
		frame.setVisible(true);
		
		
	}
	
	private void setMounth(JComboBox<Integer> comboBoxMonuth ) {
		for (int i = 1; i < 13; i++)
			comboBoxMonuth.addItem(i);
	}
	private void setYear(JComboBox<Integer> comboBoxYear) {
		for (int i = 2020; i < 2030; i++)
			comboBoxYear.addItem(i);
	}
	private double amountIsValid(String amount) {
		try {
			double money= Double.parseDouble(amount);
			return money >0 ? money: -1;
		} catch (Exception e) {
			return -1;
		}
			
	}
	public void goBack(boolean toDelete) {
		at.updateActiveTable(toDelete);  // if to update our table
		tm.setTable();
		frame.setVisible(false);
	
	}
	private void pay() {
		double amount= amountIsValid(textField_amount.getText());
		if(amount==-1)
			JOptionPane.showMessageDialog(null, "amount of money is invalid fuck you");
		else {
			double bill = at.getBill()-amount;
			at.setBill(bill);
			if(bill <=0 )
				goBack(true);
			else {
				billHasChanged=true;
				lblTotalPrice.setText(String.valueOf(bill));

			}
		}
		
		
		
		
		
		
		
		
		
		

	}
	


}
