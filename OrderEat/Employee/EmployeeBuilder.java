package Employee;

public class EmployeeBuilder {

	
	private String id;
	private String role;
	private String phone;
	private String address;
	private String name;
	
	public EmployeeBuilder name(String name) {
		this.name=name;
		return this;
	}
	
	public EmployeeBuilder id(String id) {
		this.id=id;
		return this;
	}
	
	
	public EmployeeBuilder role(String role) {
		this.role=role;
		return this;
	}
	
	
	public EmployeeBuilder phone(String phone) {
		this.phone=phone;
		return this;
	}


	public EmployeeBuilder address(String address) {
		this.address=address;
		return this;
	}
	
	public Employee build() {
		return new Employee(id, role, phone, address,name);
		
		
		
	}
	
	
}
