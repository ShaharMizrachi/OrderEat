package Employee;

import java.io.FileInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Employee implements Serializable{
	
	
	private String name;
	private String id;
	private String role;
	private String phone;
	private String address;

	public Employee(String id, String role, String phone, String address, String name) {
		super();
		this.id = id;
		this.role = role;
		this.phone = phone;
		this.address = address;
		this.Name = name;
	}

	public static EmployeeBuilder builder() {
		return new EmployeeBuilder();
	}
	
	private String Name;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	

}
