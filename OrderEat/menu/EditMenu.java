package menu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class EditMenu {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private Menu menu;
	private JLabel lblName;
	private JTextField textFiled_Dish;
	private JLabel lblPrice;
	private JTextField textField_Price;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnRemove;
	private JScrollPane scrollPane_1;
	private JLabel lblIngridients;
	private JList list_ingridants;
	private JTextField textField_Ingridients;
	private DefaultListModel<String> ingridientModel;
	private JList <String> ingridies;
	private ArrayList<Dish> dishes;
	private JButton btnReset;
	
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void show() {
		frame = new JFrame();	
		model = new DefaultTableModel();
		menu = new Menu();
		list_ingridants = new JList();
		dishes= new ArrayList<Dish>(menu.getDishes());
		ingridientModel= new DefaultListModel <String>();
		initialize();

	}
	
	
	
	public EditMenu() {
		
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
				menu.updateMenu();
				frame.setVisible(false);
			}
		});
		
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnBack.setBounds(34, 35, 143, 39);
		frame.getContentPane().add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(206, 134, 424, 684);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String name =(String) table.getModel().getValueAt(table.getSelectedRow(),0);
				String priceString= (String) table.getModel().getValueAt(table.getSelectedRow(),1);
				textFiled_Dish.setText(name);
				textField_Price.setText(priceString);
				getSelectedIngridients(table.getSelectedRow());
			}
			
		});
		scrollPane.setViewportView(table);
		
		lblName = new JLabel("Dish Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblName.setBounds(775, 101, 178, 39);
		frame.getContentPane().add(lblName);
		
		textFiled_Dish = new JTextField();
		textFiled_Dish.setHorizontalAlignment(SwingConstants.LEFT);
		textFiled_Dish.setForeground(Color.BLACK);
		textFiled_Dish.setFont(new Font("Tahoma", Font.PLAIN, 26));
		textFiled_Dish.setBounds(965, 101, 166, 35);
		frame.getContentPane().add(textFiled_Dish);
		textFiled_Dish.setColumns(10);
		
		lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblPrice.setBounds(775, 175, 129, 27);
		frame.getContentPane().add(lblPrice);
		
		list_ingridants.setModel(ingridientModel);
		
		textField_Price = new JTextField();
		textField_Price.setHorizontalAlignment(SwingConstants.LEFT);
		textField_Price.setFont(new Font("Tahoma", Font.PLAIN, 26));
		textField_Price.setBounds(967, 177, 164, 35);
		frame.getContentPane().add(textField_Price);
		textField_Price.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDish();
		
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnAdd.setBounds(749, 315, 143, 38);
		frame.getContentPane().add(btnAdd);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateDish();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnUpdate.setBounds(951, 315, 143, 37);
		frame.getContentPane().add(btnUpdate);
		
		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row= table.getSelectedRow();
				model.removeRow(row);
				dishes.remove(row);
				resetFileds();
			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnRemove.setBounds(1172, 315, 137, 37);
		frame.getContentPane().add(btnRemove);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(888, 454, 434, 379);
		frame.getContentPane().add(scrollPane_1);
		
		
		scrollPane_1.setViewportView(list_ingridants);
		
		lblIngridients = new JLabel("Ingridients:");
		lblIngridients.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblIngridients.setBounds(703, 466, 227, 49);
		frame.getContentPane().add(lblIngridients);
		
		textField_Ingridients = new JTextField();
		textField_Ingridients.setBounds(1408, 586, 143, 22);
		frame.getContentPane().add(textField_Ingridients);
		textField_Ingridients.setColumns(10);
		
		JButton btnAddIngridients = new JButton("+");
		btnAddIngridients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String  ingridientString= textField_Ingridients.getText();
				if(ingridientString.length() > 0) {
				ingridientModel.addElement(textField_Ingridients.getText());
				textField_Ingridients.setText("");
				}
			}
		});
		btnAddIngridients.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnAddIngridients.setBounds(1353, 645, 97, 25);
		frame.getContentPane().add(btnAddIngridients);
		
		JButton btnDeleteIngidients = new JButton("-");
		btnDeleteIngidients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedingridient= list_ingridants.getSelectedIndex();
				System.out.println(selectedingridient);
				if(selectedingridient>-1) {
					ingridientModel.remove(selectedingridient);
				}
			}
		});
		btnDeleteIngidients.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnDeleteIngidients.setBounds(1491, 645, 97, 25);
		frame.getContentPane().add(btnDeleteIngidients);
		
		btnReset = new JButton("Rreset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFileds();
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnReset.setBounds(1391, 314, 143, 37);
		frame.getContentPane().add(btnReset);
		setCol();
		setRow();
		frame.setVisible(true);
	}
	
	
	private void setCol() {
			model.addColumn("Name");
			model.addColumn("price");
	}
	
	
	private void setRow() {
		ArrayList<Dish> dishs= menu.getDishes();
		int size= dishs.size();
		String [] row= new String [2];
		for (int i = 0; i < size; i++) {
			row [0]=dishs.get(i).getName();
			row [1]=String.valueOf(dishs.get(i).getPrice());
			model.addRow(row);
		}
		table.setModel(model);
	}
	
	
	private void getSelectedIngridients(int selectedRow) {
		ingridientModel.removeAllElements();
		ArrayList<String> ingridient=  menu.getDishes().get(selectedRow).getIngridients();
		int size = ingridient.size();
		for (int i = 0; i < size; i++) {
			ingridientModel.addElement(ingridient.get(i));
		}
		list_ingridants.setModel(ingridientModel);
	}
	
	private void resetFileds() {
		textField_Price.setText(null);
		textFiled_Dish.setText(null);
		ingridientModel.removeAllElements();
	}
	
	private void addDish() {
		String name = textFiled_Dish.getText();
		String dishPriceString= textField_Price.getText();
		if(name.length()>0 && dishPriceString.length()>0) {
		boolean valid =menu.addDish(new Dish(name,Double.parseDouble(dishPriceString),creatIngridientList(),300));
			if(valid) {
				String[] row= {name,dishPriceString};
				model.addRow(row);
			}
			else {
				JOptionPane.showMessageDialog(null, name+"Already exist", "Error",0);
			}			
		}

	}
	
	private ArrayList<String> creatIngridientList() { // replay my ingridients
		ArrayList<String> ingridientStrings= new ArrayList<String>();
		int size= ingridientModel.size();
		for (int i = 0; i < size; i++) {
			ingridientStrings.add(ingridientModel.get(i));	
		}
		return ingridientStrings;
	}
	
	
	private void updateDish() {
		int selectedRow= table.getSelectedRow();
		String dishNameString= textFiled_Dish.getText();
		String dishpriceString= textField_Price.getText();
		if(selectedRow>-1 && dishNameString.length()>0 && dishpriceString.length()>0) {
			Dish dish=menu.getDishes().get(selectedRow);
			if((!dishNameString.equals(dish.getName())&& menu.dishIsExist(dishNameString))) {
				JOptionPane.showMessageDialog(null, dishNameString+ "is alredy exist");
			}
			else {
			dish.setName(dishNameString);
			dish.setPrice(Double.parseDouble(dishpriceString));
			dish.setIngridients(creatIngridientList());
			model.setValueAt(dishNameString,selectedRow,0);
			model.setValueAt(dishpriceString, selectedRow, 1);
			resetFileds();
			}
		}
		else {
			System.out.println(" Error select somthing");
		}
			
	}

}
