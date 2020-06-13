package Employee;

import java.awt.EventQueue;

import javax.swing.JFrame;

import menu.EditMenu;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class Employee_Managed {

	private JFrame frame;
	private JTable tableEmployee;
	private DefaultTableModel employeeModel;
	private ArrayList<Employee> list;
	private JTextField textField_Name;
	private JTextField textField_phone;
	private JTextField textField_Address;
	private JComboBox comboBox_Role;
	private String managerPasswordString;

		/**
		 * @wbp.parser.entryPoint
		 */
		public void show(String managerPasswordString) {
			frame = new JFrame();
			tableEmployee = new JTable();
			employeeModel= new DefaultTableModel();
			this.managerPasswordString=managerPasswordString;
			initialize();
		}
	

	public Employee_Managed() {
		
	}


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
				updateEmployeeList();
				frame.setVisible(false);
				
			}
		});
		btnBack.setBounds(36, 42, 126, 41);
		frame.getContentPane().add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(295, 173, 428, 370);
		frame.getContentPane().add(scrollPane);
		
		
		scrollPane.setViewportView(tableEmployee);
		
		textField_Name = new JTextField();
		textField_Name.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_Name.setBounds(995, 190, 195, 41);
		frame.getContentPane().add(textField_Name);
		textField_Name.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblName.setBounds(876, 188, 88, 45);
		frame.getContentPane().add(lblName);
		
		textField_phone = new JTextField();
		textField_phone.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_phone.setBounds(995, 271, 195, 41);
		frame.getContentPane().add(textField_phone);
		textField_phone.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Phone:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(876, 271, 88, 41);
		frame.getContentPane().add(lblNewLabel);
		
		textField_Address = new JTextField();
		textField_Address.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_Address.setBounds(995, 351, 195, 41);
		frame.getContentPane().add(textField_Address);
		textField_Address.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblAddress.setBounds(876, 346, 107, 41);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblRole = new JLabel("Role:");
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblRole.setBounds(876, 435, 88, 26);
		frame.getContentPane().add(lblRole);
		
		comboBox_Role = new JComboBox();
		comboBox_Role.setBounds(995, 439, 185, 29);
		frame.getContentPane().add(comboBox_Role);
		
		setComboBox(comboBox_Role);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreatNewEmployee();
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnAdd.setBounds(935, 544, 117, 45);
		frame.getContentPane().add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=tableEmployee.getSelectedRow();
				if(selectedRow>-1) {
					int choose= JOptionPane.showConfirmDialog(null,"Are you sure?");
					if(choose==0) {
						employeeModel.removeRow(selectedRow);
						list.remove(selectedRow);
					}

				}
	
			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnRemove.setBounds(1064, 544, 126, 45);
		frame.getContentPane().add(btnRemove);
		setCol();
		setRow();
		tableEmployee.setModel(employeeModel);
		
		frame.setVisible(true);
	}
	private void setCol() {
		employeeModel.addColumn("Name");
		employeeModel.addColumn("Id");
		employeeModel.addColumn("Role");
	}

	private void setRow() {
		try {
			ObjectInputStream input= new ObjectInputStream(new FileInputStream("Employee.txt"));
			String [] employeeDetailStrings= new String [3];
			input.readObject(); // jump the first row
			list=(ArrayList<Employee>) input.readObject();
			int size = list.size();
			for (int i = 0; i <size; i++) {
				employeeDetailStrings[0]= list.get(i).getName();
				employeeDetailStrings[1]= list.get(i).getId();
				employeeDetailStrings[2]=list.get(i).getRole();
				employeeModel.addRow(employeeDetailStrings);
			}
			input.close();

		} catch (Exception e) {
			list= new ArrayList<Employee>();
		}
	
	}
	private void setComboBox(JComboBox<String> roles) {
		roles.addItem("");
		roles.addItem("whitres");
		roles.addItem("Cook");
		roles.addItem("Hostess");
		roles.addItem("Manager");
	}
	private boolean IdIsAvilabile(int id) {
		String strId= String.valueOf(id);
		int size=list.size();
		for (int i = 0; i < size; i++) {
			if(strId.equals(list.get(i).getId())){
				return false;
			}
		}
		return true;
	}
	private String getId() {
		int randId;
		Random rand= new Random();
		randId= rand.nextInt(9000) + 1000;
		while(!IdIsAvilabile(randId)) {
			randId=rand.nextInt(9000) + 1000;
		}
		return String.valueOf(randId);	

	}
	public void CreatNewEmployee() {
		String nameString=textField_Name.getText();
		String addressString=textField_Address.getText();
		String phoneString=textField_phone.getText();
		String roleString = (String) comboBox_Role.getSelectedItem();
		if(nameString.length()>0 && addressString.length()>0 && phoneString.length()>0 && roleString.length()>0){
			Employee employee= Employee.builder().name(nameString).address(addressString).phone(phoneString).role(roleString).id(getId()).build();
			String [] employeeDetailStrings= {nameString,employee.getId(),roleString};
			employeeModel.addRow(employeeDetailStrings);
			list.add(employee);
			resertFelid();
		}

	}
	private void resertFelid() {
		textField_Address.setText("");
		textField_Name.setText("");
		textField_phone.setText("");
		comboBox_Role.setSelectedIndex(0);
	}
	
	
	
	private void updateEmployeeList() {
		try {
			ObjectOutputStream outputStream= new ObjectOutputStream(new FileOutputStream("Employee.txt"));
			outputStream.writeObject(managerPasswordString);
			outputStream.writeObject(list);
			outputStream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	
}
