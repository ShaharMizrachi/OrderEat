package Delivery;

import java.awt.EventQueue;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreatDelivery {

	private JFrame frame;
	private DeliveriesManaged dm;
	/**
	 * @wbp.parser.entryPoint
	 */
	public void show() {
		dm= new DeliveriesManaged();
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnBack.setBounds(39, 37, 139, 44);
		frame.getContentPane().add(btnBack);
		
		initialize();
	}
	
	public CreatDelivery() {
		
	}


	private void initialize() {
		
		frame.setBounds(0, 0, 1600, 1500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.MAXIMIZED_BOTH); // to open in full size window 
		frame.setUndecorated(true); // the x out 
		frame.setVisible(true);
	}

	

}
