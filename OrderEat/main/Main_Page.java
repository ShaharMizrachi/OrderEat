package main;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Delivery.DeliveriesManaged;
import Manager.Manager_window;
import Table_Map.EditTable;
import Table_Map.TableMap;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.awt.event.ActionEvent;



public class Main_Page {
	
	private JFrame frame;
	private  TableMap tp;
	private Manager_window mg;
	private String managerPasswordString;
	private DeliveriesManaged dManaged;
	
	

	public static void main(String[] args) {
		
		new Main_Page();
		//new EditTable();
		

	}
	
	
	
	
	
	public Main_Page() {
		this.tp=new TableMap();
		this.mg=new Manager_window();
		this.dManaged= new DeliveriesManaged();
		getManagerPassword();
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1600, 1500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.MAXIMIZED_BOTH); // to open in full size window 
		frame.setUndecorated(true); // the x out 
		frame.getContentPane().setLayout(null);
		
		JButton btn_Maneger = new JButton("Maneger ");
		btn_Maneger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String passwordString =JOptionPane.showInputDialog(null, "Enter Manager Password","Manager",2);
				if(passwordString !=null) {
					if(passwordString.equals(managerPasswordString))
							mg.show(managerPasswordString);
					else {
						JOptionPane.showMessageDialog(null, "Ivalid Password DO NOT FUACK EITH THE MANAGER!");					}
				}
			}
		});
		btn_Maneger.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btn_Maneger.setBounds(63, 306, 230, 95);
		frame.getContentPane().add(btn_Maneger);
		
		JButton btn_Table_map = new JButton("Table Map");
		btn_Table_map.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tp.show();
			}
		});
		btn_Table_map.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btn_Table_map.setBounds(381, 307, 196, 95);
		frame.getContentPane().add(btn_Table_map);
		
		JButton btn_Booking = new JButton("Booking ");
		btn_Booking.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btn_Booking.setBounds(673, 306, 206, 95);
		frame.getContentPane().add(btn_Booking);
		
		JButton btn_Delivary = new JButton("Delivary");
		btn_Delivary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dManaged.show();
				
				
				
			}
		});
		btn_Delivary.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btn_Delivary.setBounds(974, 306, 206, 95);
		frame.getContentPane().add(btn_Delivary);
		
		JButton btn_Exit = new JButton("Exit");
		btn_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btn_Exit.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btn_Exit.setBounds(63, 76, 140, 53);
		frame.getContentPane().add(btn_Exit);
		frame.setVisible(true); //show window
		
		
	}
	
	private void getManagerPassword() {
		try {
			ObjectInputStream inputStream= new ObjectInputStream(new FileInputStream("Employee.txt"));
			managerPasswordString = (String)inputStream.readObject();
			inputStream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
