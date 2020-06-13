package Table_Map;

import java.util.ArrayList;

import javax.management.modelmbean.ModelMBean;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import menu.Dish;
import menu.Menu;

import javax.swing.JList;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.channels.SelectableChannel;

public class EditTable {

	private JFrame frame;
	private JList<Dish> list;
	private Menu menu;
	private JButton btnBack;
	private JLabel lblNumberOfPpl;
	private JLabel lbltableNu;
	private ActiveTable at;
	private JTable tableOrder;
	private JLabel lblSelectedDishName;
	private JButton btnPlusAmount;
	private JButton btnMinusAmount;
	private JLabel lblAmount;
	private JButton btnAddToTable;
	private DefaultTableModel model;
	private JButton btnSendOrder;
	private JButton btnDeleteDish;
	private int numOfDishes;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void show(TableMap tm,int tableNum, String weitherNameString ,String weitherID, int dishesNum) {
		frame = new JFrame();
		list= new JList<Dish>();

		menu = menu.getInstamce();
	
		btnBack = new JButton("back");
		at = new ActiveTable(tableNum, dishesNum, weitherID, weitherNameString);
		tableOrder = new JTable();
		model= new DefaultTableModel();
		numOfDishes=at.orderArrayList.size();
		initialize(tm , tableNum,weitherNameString,dishesNum);
		
	}
	
	
	
	public void setMenu() {
		DefaultListModel<Dish> model= new DefaultListModel<Dish>();
		ArrayList<Dish> menuDishList = menu.getDishes();
		int size=menuDishList.size();
		for (int i = 0; i < size; i++) {
			model.addElement(menuDishList.get(i));
		}
		list.setModel(model);
	}
	public void setCol(DefaultTableModel model) {
		// TODO Auto-generated method stub
		model.addColumn("Name");
		model.addColumn("Amount");
		model.addColumn("Price");
	}
	public void setRow(DefaultTableModel model) {
		String[] row = new String[3];
		ArrayList<Dish> orderList=at.getOrderArrayList();
		int size=orderList.size();
		for (int i = 0; i <size; i++) {
			Dish dish= orderList.get(i);
			row[0] =dish.getName();
			row[1]=String.valueOf(dish.getAmountInStock());
			row[2]=String.valueOf(dish.getAmountInStock()*dish.getPrice());
			model.addRow(row);
		}
		
		
	}
	public void setTable() {
		
		setCol(model);
		setRow(model);
		tableOrder.setModel(model);
	}
	
	
	
	public EditTable() {
		
	}

	private void initialize(TableMap tm,int tableNum, String weitherNameString ,int dishesNum) {
		setMenu();
		setTable();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 1600, 1500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.MAXIMIZED_BOTH); // to open in full size window 
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 49, 533, 624);
		frame.getContentPane().add(scrollPane);
		frame.setVisible(true);
		
		scrollPane.setViewportView(list);
		
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				tm.setTable();
				
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnBack.setBounds(790, 628, 117, 45);
		frame.getContentPane().add(btnBack);
		tableOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
					btnDeleteDish.setEnabled(tableOrder.getSelectedRow()>=numOfDishes);

			}
		});
		JLabel lblnameWiethere = new JLabel(at.getWeitherName());
		lblnameWiethere.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblnameWiethere.setHorizontalAlignment(SwingConstants.TRAILING);
		lblnameWiethere.setBounds(726, 73, 204, 50);
		frame.getContentPane().add(lblnameWiethere);
		
		lblNumberOfPpl = new JLabel(String.valueOf(at.getDinersNum()));
		lblNumberOfPpl.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNumberOfPpl.setBounds(845, 163, 117, 24);
		frame.getContentPane().add(lblNumberOfPpl);
		
		lbltableNu = new JLabel(String.valueOf(tableNum));
		lbltableNu.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lbltableNu.setHorizontalAlignment(SwingConstants.RIGHT);
		lbltableNu.setBounds(748, 216, 117, 37);
		frame.getContentPane().add(lbltableNu);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(996, 73, 412, 486);
		frame.getContentPane().add(scrollPane_1);
		
		scrollPane_1.setViewportView(tableOrder);
		
		lblSelectedDishName = new JLabel("SelectedDishName");
		lblSelectedDishName.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblSelectedDishName.setBounds(243, 803, 223, 24);
		frame.getContentPane().add(lblSelectedDishName);
		
		btnPlusAmount = new JButton("+");
		btnPlusAmount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int amount= Integer.parseInt(lblAmount.getText());
				lblAmount.setText(String.valueOf(amount+1));
			}
		});
		btnPlusAmount.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnPlusAmount.setBounds(162, 924, 97, 25);
		frame.getContentPane().add(btnPlusAmount);
		
		btnMinusAmount = new JButton("-");
		btnMinusAmount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int amount= Integer.parseInt(lblAmount.getText());
				if(amount>1) {
				lblAmount.setText(String.valueOf(amount-1));
				}
			}
		});
		btnMinusAmount.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnMinusAmount.setBounds(365, 924, 97, 25);
		frame.getContentPane().add(btnMinusAmount);
		
		lblAmount = new JLabel("1");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblAmount.setBounds(624, 918, 128, 36);
		frame.getContentPane().add(lblAmount);
		
		btnAddToTable = new JButton("Add to the Table");
		btnAddToTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] dishdatials =new String[3];
				Dish dish= list.getModel().getElementAt(list.getSelectedIndex());
				int amount=Integer.parseInt(lblAmount.getText());
				dishdatials[0] =dish.getName();
				dishdatials[1]=lblAmount.getText();
				dishdatials[2]=String.valueOf(amount*dish.getPrice());
				model.addRow(dishdatials);
				at.addDish(dish,amount);
			}
		});
		btnAddToTable.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnAddToTable.setBounds(688, 897, 274, 52);
		frame.getContentPane().add(btnAddToTable);
		
		btnSendOrder = new JButton("Send Order");
		btnSendOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				at.updateActiveTable(false);
				at.updateTableOrde(false);
				frame.setVisible(false);
				tm.setTable();
			}
		});
		btnSendOrder.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnSendOrder.setBounds(1250, 900, 216, 49);
		frame.getContentPane().add(btnSendOrder);
		
		btnDeleteDish = new JButton("Delete Dish");
		btnDeleteDish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow=tableOrder.getSelectedRow();
				String name= (String) tableOrder.getModel().getValueAt(tableOrder.getSelectedRow(), 0);
				int amount =Integer.parseInt((String)tableOrder.getModel().getValueAt(tableOrder.getSelectedRow(), 1));
				at.removeDish(name, amount);
				model.removeRow(selectedRow);
			}
		});
		btnDeleteDish.setEnabled(false);
		btnDeleteDish.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnDeleteDish.setBounds(1230, 631, 197, 42);
		frame.getContentPane().add(btnDeleteDish);
		
		list.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				String name=list.getModel().getElementAt(list.getSelectedIndex()).getName();
				lblSelectedDishName.setText(name);
				
				
			}
		});
		
	}
}
