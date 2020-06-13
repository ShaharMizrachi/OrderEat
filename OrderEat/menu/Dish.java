package menu;

import java.util.ArrayList;

public class Dish {
	

	private String name;
	private double price;
	private ArrayList<String> ingridients;
	private int amountInStock;
	
	public Dish(String name, double price, ArrayList<String> ingridients, int amountInStock) {
		super();
		this.name = name;
		this.price = price;
		this.ingridients = ingridients;
		this.amountInStock = amountInStock;
	}

	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public ArrayList<String> getIngridients() {
		return ingridients;
	}
	public void setIngridients(ArrayList<String> ingridients) {
		this.ingridients = ingridients;
	}
	public int getAmountInStock() {
		return amountInStock;
	}
	public void setAmountInStock(int amountInStock) {
		this.amountInStock = amountInStock;
	}

	public String toString() {
		return name;
	}
	


}
