package pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDb {

	public Connection getConnected() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/university","root","12345");
	}
	
	public ResultSet getEmployees() throws SQLException {
		Statement st = getConnected().createStatement();
		ResultSet rs = st.executeQuery("select * from employee");
		return rs;
	}
	
	public void saveEmployee(Employee e) throws SQLException {
		String query = "insert into employee values(?,?,?,?,?)";
		PreparedStatement ps = getConnected().prepareStatement(query);
		ps.setInt(1, e.getEmpId());
		ps.setString(2, e.getName());
		ps.setString(3, e.getSurname());
		ps.setString(4, e.getGender());
		ps.setString(5, e.getCity());
		ps.executeUpdate();
	}
	
	public void deleteEmployee(int empId) throws SQLException {
		String query = "delete from employee where id=?";
		PreparedStatement ps = getConnected().prepareStatement(query);
		ps.setInt(1, empId);
		ps.executeUpdate();
	}
}
