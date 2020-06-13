package Table_Map;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JLabel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OpenTable {

	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private JButton btnback;
	private JList<Integer> tablenum;
	private JScrollPane scrollPane_1;
	private JLabel lbldinersNum;
	private JButton btnPluse;
	private JButton btnNegative;
	private JButton btnopenTable;
	private EditTable et;
	

	public OpenTable() {

		
		
		
		

	}
		
		/**
		 * @wbp.parser.entryPoint
		 */
		public void show(TableMap tm) {
			frame = new JFrame();
			frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 31));
			table = new JTable();
			scrollPane = new JScrollPane();
			 model=new DefaultTableModel();
			 tablenum = new JList<Integer>();
			 scrollPane_1 = new JScrollPane();
			 lbldinersNum = new JLabel("1");
			 btnPluse = new JButton("+");
			 btnNegative = new JButton("-");
			 setBtnopenTable(new JButton("Open Table"));
			 et=new EditTable();
			initialize(tm);
			
		}
		
		
		
		
		
	private void initialize(TableMap tm) {
		frame.getContentPane().setLayout(null);
		scrollPane.setBounds(27, 89, 530, 310);
		frame.getContentPane().add(scrollPane);
		
		setCol();
		setRow();
		table.setModel(model);
		frame.setBounds(0, 0, 1600, 1500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.MAXIMIZED_BOTH); // to open in full size window 
		frame.setUndecorated(true);
		
		
		
		scrollPane.setViewportView(table);
		
		btnback = new JButton("back");
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnPluse.addActionListener(new ActionListener() { //listener of Plus //lbldinersNum
			 	public void actionPerformed(ActionEvent e) {
			 		int dinerN=Integer.parseInt(lbldinersNum.getText());
			 		lbldinersNum.setText(String.valueOf(dinerN+1));
			 	}
			 });
		btnNegative.addActionListener(new ActionListener() {// listener of negative 
			 	public void actionPerformed(ActionEvent e) {
			 		int dinerN=Integer.parseInt(lbldinersNum.getText());
			 		if(dinerN>1) {
			 			lbldinersNum.setText(String.valueOf(dinerN-1));
			 		}
	
			 	}
			 });
		btnback.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnback.setBounds(82, 30, 97, 25);
		frame.getContentPane().add(btnback);
		
		
		scrollPane_1.setBounds(718, 89, 185, 303);
		frame.getContentPane().add(scrollPane_1);
		
		getFreeTables();
		scrollPane_1.setViewportView(tablenum);
		
		
		lbldinersNum.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lbldinersNum.setBounds(1119, 127, 43, 53);
		frame.getContentPane().add(lbldinersNum);
		
		
		btnPluse.setFont(new Font("Tahoma", Font.PLAIN, 32));
		btnPluse.setBounds(996, 251, 97, 37);
		frame.getContentPane().add(btnPluse);
		
		
		btnNegative.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnNegative.setBounds(1191, 248, 97, 37);
		frame.getContentPane().add(btnNegative);
		
		JButton btnopenTable = new JButton("Open Table");
		btnopenTable.setEnabled(false);
		btnopenTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String weiterNameString= (String) table.getModel().getValueAt(table.getSelectedRow(),1);
				String weitereID=(String) table.getModel().getValueAt(table.getSelectedRow(),0);
				int tableN= tablenum.getModel().getElementAt(tablenum.getSelectedIndex()); 
				int dinerN= Integer.parseInt(lbldinersNum.getText());
				et.show(tm,tableN,weiterNameString,weitereID,dinerN);
				frame.setVisible(false);
	
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			if(tablenum.getSelectedIndex()>-1)
				btnopenTable.setEnabled(true);
			}
		});
		 tablenum.addMouseListener(new MouseAdapter() {
			 	@Override
			 	public void mousePressed(MouseEvent e) {
			 		if(table.getSelectedRow()>-1)
			 			btnopenTable.setEnabled(true);
			 	}
			 });
		 
		btnopenTable.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnopenTable.setBounds(504, 517, 239, 53);
		frame.getContentPane().add(btnopenTable);
		frame.setBounds(0, 0, 1600, 1500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.MAXIMIZED_BOTH); // to open in full size window 
		frame.setUndecorated(true);
		frame.setVisible(true);
		
	}
	public void getFreeTables() {
		try {
			BufferedReader br_1= new BufferedReader(new FileReader("Tables.txt"));
			int numOfTabls= Integer.parseInt(br_1.readLine());
			int[] counter= new int[numOfTabls+1];
			br_1.close();
			Arrays.fill(counter,0);
			BufferedReader br_2= new BufferedReader(new FileReader("Active Tables.txt"));
			String reader;
			int i=0;
			while((reader=br_2.readLine())!=null) {
				if(i%5==0) {
					counter[Integer.parseInt(reader)] = 1;
				}
				i++;
			}
			br_2.close();
			DefaultListModel <Integer> freeTablesList =new DefaultListModel<Integer>();
			for (i = 1; i <= numOfTabls; i++) {
				if(counter[i]==0) {
					freeTablesList.addElement(i);
				}
			}
			tablenum.setModel(freeTablesList);// put the model inside the Jlist 
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	

	public void setCol() {
		model.addColumn("Name");
		model.addColumn("ID");
	}
	
	public void setRow() {
		try {
			BufferedReader br=new BufferedReader(new FileReader("Active Employee.txt"));
			String [] row=new String [2];
			String type;
			int i=0;
			while((row[i++]=br.readLine())!=null) {
				if(i==2) {
					type=br.readLine();
					if(type.equals("waiter")) {
						model.addRow(row);
					}
				br.readLine();
					i=0;
				}
			}
			br.close();
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	
	public ArrayList<Integer> getActiveTables() {
		try {
			BufferedReader br= new BufferedReader(new FileReader("Active Tables.txt"));
			ArrayList<Integer> tables= new ArrayList<Integer>();
			String readerString;
			int i=0;
			while((readerString=br.readLine())!=null) {
				if(i%5==0)
					tables.add(Integer.parseInt(readerString));
				i++;
			}
			br.close();
			return tables;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
			return null;
	}
	
	public boolean isAvailble(ArrayList<Integer> list, int tablenum) {
		int size=list.size();
		for (int i = 0; i < size; i++) {
			if(list.get(i).intValue()==tablenum)
			return false;
		}
		return true;
	}
	public void setFreeTables() {
		try {
			BufferedReader bReader=new BufferedReader(new FileReader("Table.java"));
			String readerString;
			ArrayList<Integer> list= getActiveTables();
			DefaultListModel<Integer> model= new DefaultListModel<Integer>();
			int i=0;
			bReader.readLine();
			while((readerString=bReader.readLine())!=null) {
				if(i%3==0 && isAvailble(list, Integer.parseInt(readerString))){
					model.addElement(Integer.parseInt(readerString));
				}
				i++;
			}
			tablenum.setModel(model);
			bReader.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public JButton getBtnopenTable() {
		return btnopenTable;
	}

	public void setBtnopenTable(JButton btnopenTable) {
		this.btnopenTable = btnopenTable;
	}

	
	
	
	
}
