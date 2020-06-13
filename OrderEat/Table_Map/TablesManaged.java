package Table_Map;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.GrayFilter;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import Manager.Manager_window;
import javax.swing.JComboBox;
import javax.naming.SizeLimitExceededException;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TablesManaged {

	private JFrame frame;
	private Point barNextTablePoint;
	private Point balconyNextTablePoint;
	private Point resturantNextTablePoint;
	private Manager_window mgManager_window;
	private  ArrayList<Table> tableList;
	public ArrayList<JLabel> tableLabalListArrayList;
	private JLabel lastPicJLabel;
	private int nextTableNum;
	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
	public void show() {
		frame = new JFrame();
		barNextTablePoint= new Point(76,270);
		balconyNextTablePoint= new Point(76,270);
		resturantNextTablePoint= new Point(76,551);
		frame.getContentPane().setLayout(null);
		tableList= new ArrayList<Table>();
		tableLabalListArrayList= new ArrayList<JLabel>();
		lastPicJLabel= new JLabel();
		
		initialize();
	}
	/**
	 * Create the application.
	 */
	public TablesManaged() {
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame.setBounds(0, 0, 1600, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.MAXIMIZED_BOTH); // to open in full size window 
		frame.setUndecorated(true); // the x out 
	
		
		
		JButton btnAdd = new JButton("Add");
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				
				
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnBack.setBounds(49, 46, 127, 41);
		frame.getContentPane().add(btnBack);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lastPicJLabel!=null) {
					updateScreenTable(Integer.parseInt(lastPicJLabel.getText()));
				lastPicJLabel=null;
				}
			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnRemove.setBounds(482, 59, 158, 41);
		frame.getContentPane().add(btnRemove);
		

		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnAdd.setBounds(733, 63, 145, 33);
		frame.getContentPane().add(btnAdd);
		
		JComboBox comboBoxArea = new JComboBox();
		comboBoxArea.setModel(new DefaultComboBoxModel(new String[] {"", "bar", "rasturant", "balcony"}));
		comboBoxArea.setBounds(513, 173, 127, 36);
		frame.getContentPane().add(comboBoxArea);
		
		JLabel lblCapacity = new JLabel("Capacity:");
		lblCapacity.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblCapacity.setBounds(733, 178, 117, 31);
		frame.getContentPane().add(lblCapacity);
		
		JComboBox comboBoxCapacity = new JComboBox();
		comboBoxCapacity.setModel(new DefaultComboBoxModel(new String[] {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"}));
		comboBoxCapacity.setBounds(877, 173, 117, 36);
		frame.getContentPane().add(comboBoxCapacity);
		
		
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		String areaString= (String) comboBoxArea.getSelectedItem();
		String capacityString= (String)comboBoxCapacity.getSelectedItem();
		if(areaString.length()>0 && capacityString.length()>0) {
			int TableNum=getNextTableNum();
			tableList.add(new Table(Integer.parseInt(capacityString), areaString,TableNum));
			getArea(areaString,String.valueOf(TableNum));
			frame.repaint();
		}
			}
		});
		
		JLabel lblArea = new JLabel("Area:");
		lblArea.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblArea.setBounds(416, 183, 91, 26);
		frame.getContentPane().add(lblArea);
		getTables();
		getNextTableNum();
		frame.setVisible(true);
	}
	
	
	
	
	public void setBarTable(String tableNUmString) {
		int x=barNextTablePoint.x, y=barNextTablePoint.y;
		JLabel table= new JLabel(tableNUmString,SwingConstants.CENTER);
		table.setBounds(barNextTablePoint.x, barNextTablePoint.y, 103, 41);
		table.setOpaque(true);
		table.setBackground(Color.ORANGE);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(lastPicJLabel!=null) {
					lastPicJLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
				}
				table.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
				lastPicJLabel=table;
			}
		});	

		frame.getContentPane().add(table);// adding the label intp the fram
		tableLabalListArrayList.add(table);
		if(x+103>=631) {
			barNextTablePoint.setLocation(76, y+41+10);
		}
		else {
			barNextTablePoint.setLocation(x+103+10, y);
		}
	}
	
		
	
	private void setBalconytable(String tableNUmString) {
		int x=balconyNextTablePoint.x, y=balconyNextTablePoint.y;
		JLabel table= new JLabel(tableNUmString,SwingConstants.CENTER);
		table.setBounds(x,y, 103, 41);
		table.setOpaque(true);
		table.setBackground(Color.LIGHT_GRAY);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(lastPicJLabel!=null) {
					lastPicJLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
				}
				table.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
				lastPicJLabel=table;
			}
		});
		frame.getContentPane().add(table);// adding the label intp the fram
		if(x+103>=1186) {
			balconyNextTablePoint.setLocation(76, y+41+10);
		}
		else {
			balconyNextTablePoint.setLocation(x+103+10, y);
		}
	}
	
	
	private void setResturantTable(String tableNUmString) {
		int x=resturantNextTablePoint.x, y=resturantNextTablePoint.y;
		JLabel table= new JLabel(tableNUmString,SwingConstants.CENTER);
		tableLabalListArrayList.add(table);
		table.setBounds(x,y, 103, 41);
		table.setOpaque(true);
		table.setBackground(Color.CYAN);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(lastPicJLabel!=null) {
					lastPicJLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
				}
				table.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
				lastPicJLabel=table;
			}
		});
		frame.getContentPane().add(table);// adding the label intp the fram
		if(x+103>=1355) {
			resturantNextTablePoint.setLocation(76, y+41+10);
		}
		else {
			resturantNextTablePoint.setLocation(x+103+10, y);
		}
	}
	
	private void getTables() {
		try {
			BufferedReader bReader=new BufferedReader(new FileReader("Tables.txt"));
			String[] tableDetailString= new String[3];
			int i=0;
			bReader.readLine();
			while ( (tableDetailString[i++]= bReader.readLine())!=null ) {
				if(i==3) {
					tableList.add(new Table(Integer.parseInt(tableDetailString[1]),tableDetailString[2] ,Integer.parseInt(tableDetailString[0])));  
						getArea(tableDetailString[2],tableDetailString[1]);		
					i=0;	
				}
			}
			bReader.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	private void updateScreenTable(int tableNum ) {
		barNextTablePoint.setLocation(76, 270);
		balconyNextTablePoint.setLocation(76, 270);
		resturantNextTablePoint.setLocation(76, 551);
		int size=tableLabalListArrayList.size();
		for (int i = 0; i < size; i++) {
			frame.getContentPane().remove(tableLabalListArrayList.get(i));
		}
				tableLabalListArrayList.clear();
				size=tableList.size();
				int indexToDelete=0;
				Table table;
				for (int i = 0; i < size; i++) {
					table=tableList.get(i);
					if ((table.getTableNumber()==tableNum)) {
						indexToDelete=i;
					}
					else {
						getArea(table.getArea(), String.valueOf(table.getTableNumber()));
					}
				}
				tableList.remove(indexToDelete);
				frame.repaint();
	}
	
	private void getArea(String Area, String TableNum) {
		if(Area.equals("bar"))
			setBarTable(TableNum);
		if(Area.equals("balcony"))
			setBalconytable(TableNum);
		if(Area.equals("rasurant"))
			setResturantTable(TableNum);
	}
	public int getNextTableNum() {
		int size=tableList.size();
		int counter[]= new int[size+1]; // array with the size of table list 
		Arrays.fill(counter, 0);
		Table table; 
		for (int i = 0; i < size; i++) {
			table=tableList.get(i);
			if(table.getTableNumber()<=size)
			counter[table.getTableNumber()]=1;
		}
		for (int i = 0; i<=size; i++) {
			if (counter[i]==0) {
				nextTableNum=i;
				return nextTableNum;
			}
		}
		return size+1;
	}
	
}
