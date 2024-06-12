package pack1;

public class Employee {

	int empId;
	String name, surname, gender, city;
	
	public Employee() {
		
	}
	
	public Employee(int empId, String name, String surname, String gender, String city) {
		this.empId = empId;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.city = city;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
}
