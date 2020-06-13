package Delivery;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.management.modelmbean.ModelMBean;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DeliveriesManaged {

	private JFrame frame;
	private JTable tableOrder;
	private DefaultTableModel model;
	private ArrayList<Delivery>deliveries;
	private CreatDelivery ct;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void show() {
		ct= new CreatDelivery();
		frame = new JFrame();
		model=new DefaultTableModel();
		ct= new CreatDelivery();
		initialize();

}
	public DeliveriesManaged() {
		
	}

	
	private void initialize() {
		frame.setBounds(0, 0, 1600, 1500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.MAXIMIZED_BOTH); // to open in full size window 
		frame.setUndecorated(true); // the x out 
		
		
		
		
		
		
		frame.getContentPane().setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnBack.setBounds(65, 51, 171, 48);
		frame.getContentPane().add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(210, 232, 598, 550);
		frame.getContentPane().add(scrollPane);
		
		tableOrder = new JTable();
		scrollPane.setViewportView(tableOrder);
		
		JButton btnNewDelivery = new JButton("New Delivery");
		btnNewDelivery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ct.show();

			}
		});
		btnNewDelivery.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewDelivery.setBounds(263, 864, 211, 48);
		frame.getContentPane().add(btnNewDelivery);
		setDeliveriesList();
		setCol();
		setRow();
		frame.setVisible(true);
	

	}
	private void setCol() {
		model.addColumn("ID");
		model.addColumn("Coustumer Name");
		model.addColumn("Address");
		model.addColumn("Bill");
		model.addColumn("status");

	}
	private void setDeliveriesList() {
		try {
			ObjectInput input= new ObjectInputStream(new FileInputStream("Deliveries.txt"));
			deliveries =(ArrayList<Delivery>) input.readObject();
			input.close();
		} catch (Exception e) {
			deliveries= new ArrayList<Delivery>();
		}
	}
	private void setRow() {
		int size= deliveries.size();
		String [] infoStrings= new String [5];
		Delivery delivery;
		for (int i = 0; i <size; i++) {
			delivery=deliveries.get(i);
			infoStrings[0]= delivery.getDeliveryId();
			infoStrings[1]=delivery.getClientname();
			infoStrings[2]=delivery.getAddressString();
			infoStrings[3]=String.valueOf(delivery.getBill());
			infoStrings[4]=delivery.getStatus();
			
			model.addRow(infoStrings);
		}
		tableOrder.setModel(model);

	}
}
