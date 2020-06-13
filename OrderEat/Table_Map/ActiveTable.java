package Table_Map;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;


import menu.Dish;

public class ActiveTable extends Table{
	
	private int dinersNum;
	private double bill;
	private String weiterIDString;
	private String weitherName;

	ArrayList<Dish> orderArrayList;
	
	public ActiveTable(int tableNum,int dinersNum,String weiterId,String weiterName) {
		super(0,null, tableNum);
		
		this.dinersNum= dinersNum;
		this.weiterIDString=weiterId;
		this.bill=0;
		this.orderArrayList= new ArrayList<Dish>();
		this.weitherName=weiterName;
		
		try {
			BufferedReader br= new BufferedReader(new FileReader("Active Tables.txt"));
			String reader;
			int i=0;
			while((reader=br.readLine())!=null) {
				if(i%5==0 && Integer.parseInt(reader)==tableNum) {
					// table is already open function
					this.weitherName=br.readLine();
					this.dinersNum=Integer.parseInt(br.readLine());
					this.bill=Double.parseDouble(br.readLine());
					this.weiterIDString=br.readLine();
					getOpenTable();
					br.close();
					return;
				}
				i++;
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
	}

	public void updateActiveTable(boolean  toDelete) {
		try {
			File oldFile= new File("Active Tables.txt");
			File newFile= new File("temp.txt");
			BufferedReader br = new BufferedReader(new FileReader(oldFile));
			BufferedWriter bWriter= new BufferedWriter(new FileWriter(newFile));
			String  reader;
			boolean flag= true;
			int i=0;
			while((reader=br.readLine())!=null){
				if(i%5==0) {
						flag = Integer.parseInt(reader)!=this.getTableNumber();
					}
				if(flag==true) {
					bWriter.write(reader+ "\n");
				}
				i++;
			}
			if(!toDelete) {
				System.out.println("shahar");
			bWriter.write(this.getTableNumber()+"\n");
			bWriter.write(this.weitherName+"\n");
			bWriter.write(this.dinersNum+"\n");
			bWriter.write(String.valueOf(this.bill)+"\n");
			bWriter.write(this.weiterIDString+"\n");
			}
			bWriter.close();
			br.close();
			oldFile.delete();
			newFile.renameTo(oldFile);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	public void updateTableOrde(boolean toDelete) {
		try {
			File oldFile= new File("Table Orders.txt");
			File newFile= new File("temp.txt");
			BufferedReader br = new BufferedReader(new FileReader(oldFile));
			BufferedWriter bWriter= new BufferedWriter(new FileWriter(newFile));
			String  reader;
			boolean flag= true;
			while((reader=br.readLine())!=null) {
				flag=Integer.parseInt(reader)!=this.getTableNumber();
				if (flag) {
						bWriter.write(reader+"\n");
						while(!(reader=br.readLine()).equals("$")) {
							bWriter.write(reader+"\n");
						}
						bWriter.write("$\n");
					}
				else {
					while(!(reader=br.readLine()).equals("$"));
				}
			}
			if(!toDelete) {
				bWriter.write(getTableNumber()+"\n");
				int size=orderArrayList.size();
				Dish dish;
				for (int i = 0; i < size; i++) {
					dish= this.orderArrayList.get(i);
					bWriter.write(dish.getName()+"\n");
					bWriter.write(dish.getAmountInStock()+"\n");
					bWriter.write(dish.getPrice()+"\n");
				}
				bWriter.write("$\n");
			}
			bWriter.close();
			br.close();
			oldFile.delete();
			newFile.renameTo(oldFile);	

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	
	
	public String getWeitherName() {
		return weitherName;
	}
	public void setWeitherName(String weitherName) {
		this.weitherName = weitherName;
	}
	
	public void getOpenTable() {
		try {
			BufferedReader br= new BufferedReader(new FileReader("Table Orders.txt"));
			String [] dishDetail= new String [3];
			String reader,tableNum= String.valueOf(this.getTableNumber());
			int i=0;
			while((reader=br.readLine())!=null){
				if(reader.equals(tableNum)) {
					String dishNameString;
					double price;
					int amount;
					
					while(!((dishDetail[i++]=br.readLine()).equals("$"))) {
						if(i==3) {
							dishNameString=dishDetail[0];
							amount=Integer.parseInt(dishDetail[1]);
							price=Double.parseDouble(dishDetail[2]) ;
							orderArrayList.add(new Dish(dishNameString,price,null,amount ));
							i=0;
						}
					}
					br.close();
					return;
				}
				else {
					while(!((reader=br.readLine()).equals("$")));
				}	
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void addDish(Dish dish, int amount) {
		int size=orderArrayList.size();
		Dish listDish;
		dish.setAmountInStock(amount);
		this.bill += dish.getPrice()*amount;
		for (int i = 0; i < size; i++) {
			listDish= orderArrayList.get(i);
			if(dish.getName().equals(listDish.getName())) {
				listDish.setAmountInStock(dish.getAmountInStock()+listDish.getAmountInStock());
				return;
			}
		}
		orderArrayList.add(dish);
	}
	 public void removeDish(String name, int amount) {
		int size= orderArrayList.size();
		int i=0;
		Dish dish =orderArrayList.get(i);
		for (i = 0; i < size; i++) {
			if(dish.getName().equals(name)) {
				dish.setAmountInStock(dish.getAmountInStock()-amount);
				this.bill-= dish.getPrice()*amount;
				if(dish.getAmountInStock()==0) {
					orderArrayList.remove(i);
				}
				return;
			}
		}
	}
	
	public int getDinersNum() {
		return dinersNum;
	}
	public void setDinersNum(int dinersNum) {
		this.dinersNum = dinersNum;
	}
	public double getBill() {
		return bill;
	}
	public void setBill(double bill) {
		this.bill = bill;
	}
	public String getWeiterIDString() {
		return weiterIDString;
	}
	public void setWeiterIDString(String weiterIDString) {
		this.weiterIDString = weiterIDString;
	}
	public ArrayList<Dish> getOrderArrayList() {
		return orderArrayList;
	}
	public void setOrderArrayList(ArrayList<Dish> orderArrayList) {
		this.orderArrayList = orderArrayList;
	}
	
	
	
	
	

}
