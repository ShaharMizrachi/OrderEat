package Table_Map;



import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.channels.NonWritableChannelException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableStringConverter;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.omg.PortableInterceptor.ACTIVE;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableMap {

	public JFrame frame;
	private JTable table;
	private JButton btn_Back;
	JScrollPane scrollPane;
	private DefaultTableModel model;
	private JButton btn_Open_Table;
	private OpenTable ot;
	private EditTable et;
	private JButton btnCloseTable;
	private CloseTable ct;
	private JButton btnGoToTable;


	/**
	 * @wbp.parser.entryPoint
	 */
	public void show() {
		 btn_Back = new JButton("Back");
		 scrollPane = new JScrollPane();
		 frame = new JFrame();
		 model = new DefaultTableModel();
		 table = new JTable();
		 et= new EditTable();
		 ot= new OpenTable();
		 ct= new CloseTable();
		 btnGoToTable = new JButton("Go To Table");
		 btn_Open_Table = new JButton("Open Table");
		 initialize(this);
	}
	public TableMap() {
		
	}

	
	
	

	private void initialize(TableMap tm) {
		
		frame.setBounds(0, 0, 1600, 1500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.MAXIMIZED_BOTH); // to open in full size window 
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		
		
		btn_Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				
				
			}
		});
		btn_Back.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btn_Back.setBounds(76, 82, 164, 55);
		frame.getContentPane().add(btn_Back);
		setTable();
		table.setDefaultEditor(Object.class, null);// avoiding from changing the table 
		
		
		
		scrollPane.setBounds(131, 179, 887, 359);
		frame.getContentPane().add(scrollPane);
		
		scrollPane.setViewportView(table);
		
		btn_Open_Table.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ot.show(tm);
				
				
			}
		});
		btn_Open_Table.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btn_Open_Table.setBounds(151, 659, 184, 69);
		frame.getContentPane().add(btn_Open_Table);
		
		btnGoToTable.setEnabled(false);
		btnGoToTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow =table.getSelectedRow();
				int tableNum=Integer.parseInt((String)table.getModel().getValueAt(selectedRow,0));
				String weiterName =(String) table.getModel().getValueAt(table.getSelectedRow(),1);
				int dinersNum =Integer.parseInt((String) table.getModel().getValueAt(selectedRow,2));
				et.show(tm, tableNum, weiterName, null, dinersNum);
			}
		});
		 table.addMouseListener(new MouseAdapter() {
			 	public void mousePressed(MouseEvent e) {
			 		btnGoToTable.setEnabled(true);

			 		
			 	}
			 });
		btnGoToTable.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnGoToTable.setBounds(509, 659, 176, 69);
		frame.getContentPane().add(btnGoToTable);
		
		btnCloseTable = new JButton("Close Table");
		btnCloseTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tableNum = Integer.parseInt(((String)table.getModel().getValueAt(table.getSelectedRow(),0)));
				ct.show(tm,tableNum);
				
			}
		});
		btnCloseTable.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnCloseTable.setBounds(831, 667, 260, 51);
		frame.getContentPane().add(btnCloseTable);
		
		frame.setVisible(true);
		
		
	}
	
	public void setTable() {
		DefaultTableModel model=new DefaultTableModel();
		setCol(model);
		setRow(model);
		table.setModel(model);
		btnGoToTable.setEnabled(false);
	}
	
	
	


public  void setCol(DefaultTableModel model) {
	model.addColumn("Table number");
	model.addColumn("Whither");
	model.addColumn("Diners");
	model.addColumn("Bill");
	
}

public void setRow(DefaultTableModel model){
	try {
		BufferedReader bf= new BufferedReader(new FileReader("Active Tables.txt"));
		String [] row= new String[4];
		int i=0;
		while ((row[i++]= bf.readLine())!=null) {
			if(i==4) {
				model.addRow(row);
				bf.readLine();
				i=0;
			}
		}
		bf.close();
		
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
	
	
	
	
}
}
