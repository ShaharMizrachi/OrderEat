package Delivery;

import java.sql.Date;
import java.util.ArrayList;

import menu.Dish;



public class Delivery {
	
	private String clientname;
	private String clientPhone;
	private String deliveryId;
	private ArrayList<Dish> disheslist;
	private String status;
	private Date date;
	private double bill;
	private String addressString;
	
	
		public Delivery(String clientname, String clientPhone, String deliveryId, ArrayList<Dish> disheslist, String status,
			Date date, double bill, String addressString) {
		super();
		this.clientname = clientname;
		this.clientPhone = clientPhone;
		this.deliveryId = deliveryId;
		this.disheslist = disheslist;
		this.status = status;
		this.date = date;
		this.bill = bill;
		this.addressString = addressString;
	}
		public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public String getClientPhone() {
		return clientPhone;
	}
	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}
	public String getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}
	public ArrayList<Dish> getDishesList() {
		return disheslist;
	}
	public void setDishesList(ArrayList<Dish> dishesList) {
		this.disheslist = dishesList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

		public double getBill() {
			return bill;
		}
		public void setBill(double bill) {
			this.bill = bill;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public String getAddressString() {
			return addressString;
		}
		public void setAddressString(String addressString) {
			this.addressString = addressString;
		}
		
		
		


	
}
