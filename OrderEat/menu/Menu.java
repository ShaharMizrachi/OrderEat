package menu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.jar.Attributes.Name;


public class Menu {
	
	private static Menu INSTANCE;
	private ArrayList<Dish> dishes;
	
	
	public Menu getInstamce() {
			if(INSTANCE==null) {
				INSTANCE=new Menu();
			}
			return INSTANCE;
	}
	
	
	public ArrayList<Dish> getDishes() {
		return dishes;
	}
	
	
	public Menu() {
		dishes= new ArrayList<Dish>() ;
		try {
			BufferedReader bReader= new BufferedReader(new FileReader("dishes.txt"));
			int i=0;
			String [] DishDetail= new String [3];
			String reader;
			while ((reader=bReader.readLine())!=null) {
				DishDetail[i++]=reader;
				if(i==3) {
					dishes.add(CreatDish(bReader, DishDetail));
					i=0;	
				}
			}
			bReader.close();			
		} catch (Exception e) {
			
		}
	}
	
	private Dish CreatDish(BufferedReader br, String[] dishStrings) { // if i am read from file i have to use  try catch 
		try {
			ArrayList<String> ingridients=new ArrayList <String>();
			String  reader;
			
			while (!((reader=br.readLine()).equals("$"))) {
				ingridients.add(reader);
			}
			return new Dish(dishStrings[0], Double.parseDouble(dishStrings[1]), ingridients, Integer.parseInt(dishStrings[2]));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	public void deleteDish(int index) {
		dishes.remove(index);
	}
	public void updateMenu() {
		try {
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("dishes.txt"));
		
		int size= this.dishes.size();
		for (int i = 0; i < size; i++) {
		bWriter.write(dishes.get(i).getName()+"\n");
		bWriter.write(dishes.get(i).getPrice()+"\n");	
		bWriter.write(dishes.get(i).getAmountInStock()+"\n");	
		updateIngidiation(bWriter,dishes.get(i));
		}
			bWriter.close();
			return;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	    private void updateIngidiation(BufferedWriter bWriter, Dish dish) {
			try {
				int size= dish.getIngridients().size();
				for (int i = 0; i < size; i++) {
					bWriter.write(dish.getIngridients().get(i)+"\n");
				}
				bWriter.write("$\n");
				return;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}
	    public boolean addDish(Dish dish) {
		int size= dishes.size();
		String dishNameString= dish.getName().toLowerCase();
		String listDishNameString;
		for (int i = 0; i < size; i++) {
			listDishNameString= dishes.get(i).getName().toLowerCase();
			if(listDishNameString.equals(dishNameString)) {
				return false;
			}		
			
		}
		dishes.add(dish);
		return true;
	} 
	    public boolean dishIsExist(String name) {
			int size= dishes.size();
			String dishName = name.toLowerCase(),listDishName;
			for (int i = 0; i < size; i++) {
				listDishName=dishes.get(i).getName().toLowerCase();
				if(dishName.equals(listDishName)) {
					return true;
				}
			}
	    	return false;
		}
	    
	    
	    
	    
}

