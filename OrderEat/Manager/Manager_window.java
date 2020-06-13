package Manager;

import java.awt.EventQueue;

import javax.swing.JFrame;

import menu.EditMenu;
import menu.Menu;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

import Employee.Employee_Managed;
import Table_Map.TablesManaged;

public class Manager_window {

	private JFrame frame;
	private EditMenu emenu;
	private Employee_Managed em;
	private String managerPasswordString; 



	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void show( String managerPasswordString) {
		frame = new JFrame();
		emenu = new EditMenu();
		em= new Employee_Managed();
		
		this.managerPasswordString=managerPasswordString;
		initialize();
	}
	
	
	
	
	
	public Manager_window() {
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame.setBounds(0, 0, 1600, 1500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.MAXIMIZED_BOTH); // to open in full size window 
		frame.setUndecorated(true); // the x out 
		

		frame.getContentPane().setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				
			}
		});
		btnBack.setBounds(68, 59, 145, 37);
		frame.getContentPane().add(btnBack);
		
		JButton btnEditMenu = new JButton("Edit Menu");
		btnEditMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emenu.show();
				
				
			}
		});
		btnEditMenu.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnEditMenu.setBounds(192, 319, 174, 37);
		frame.getContentPane().add(btnEditMenu);
		
		JButton btnEmployeeManaged = new JButton("Employee Managed");
		btnEmployeeManaged.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				em.show(managerPasswordString);
			}
		});
		btnEmployeeManaged.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnEmployeeManaged.setBounds(440, 313, 311, 37);
		frame.getContentPane().add(btnEmployeeManaged);
		
		JButton btnTablesManaged = new JButton("Tables Managed");
		btnTablesManaged.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
			}
		});
		btnTablesManaged.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnTablesManaged.setBounds(848, 319, 231, 37);
		frame.getContentPane().add(btnTablesManaged);
		frame.setVisible(true);
		
	}
}
