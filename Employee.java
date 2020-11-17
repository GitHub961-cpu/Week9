import java.sql.*;

public class Employee 
{
	int employeeID;
	String firstName;
	String lastName;
	byte age;
	String title;
	String status;
	int supervisor_id;
	String department;
	String hiredate;
	String phone;
	
	public Employee(int employeeID) 
	{
		this.employeeID = employeeID;
	}
	public int getEmployeeID() 
	{
		return employeeID;
	}
	
	public String getFirstName() 
	{
		return firstName;
	}
	
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	
	public String getLastName() 
	{
		return lastName;
	}
	
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	
	public byte getAge() 
	{
		return age;
	}
	
	public void setAge(byte age) 
	{
		this.age = age;
	}
	
	public String getTitle() 
	{
		return title;
	}
	
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	public String getStatus() 
	{
		return status;
	}
	
	public void setStatus(String status) 
	{
		String replace = "";
		if(status.equals("P")) 
		{
			replace = status.replaceAll("P", "Part-time");
		}
		else if(status.equals("F")) 
		{
			replace = status.replaceAll("F", "Full-time");
		}
		else if(status.equals("S")) 
		{
			replace = status.replaceAll("S", "Salary");
		}
		else if(status.equals("C")) 
		{
			replace = status.replaceAll("C", "Comission");
		}
		else {replace = "undefined";}
		this.status = replace;
	}
	
	public int getSupervisor_id() 
	{
		return supervisor_id;
	}
	
	public void setSupervisor_id(int supervisor_id) 
	{
		this.supervisor_id = supervisor_id;
	}
	
	public String getDepartment() 
	{
		return department;
	}
	
	public void setDepartment(String department) 
	{
		this.department = department;
	}
	
	public String getHiredate() 
	{
		return hiredate;
	}
	
	public void setHiredate(String hiredate) 
	{
		//could not get the converted version work
		this.hiredate = hiredate;
	}
	
	public String getPhone() 
	{
		return phone;
	}
	
	public void setPhone(String phone) 
	{
		String number = phone.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1)$2-$3");
		this.phone = number;
	}
	
	public void printEmployee() 
	{ 
		System.out.println(" ID:\t\t\t|" + getEmployeeID() + "\n First Name:\t\t|" + getFirstName() + "\n Last Name:\t\t|" + getLastName() + "\n Age:\t\t\t|"
			+ getAge() + "\n Title:\t\t\t|" + getTitle() + "\n Status:\t\t|" + getStatus() + "\n Supervisor ID:\t\t|" 
			+ getSupervisor_id() + "\n Department:\t\t|" + getDepartment() + "\n Hire Date:\t\t|" 
			+ getHiredate() + "\n Phone:\t\t\t|" + getPhone());
	}
	public void getEmployee() 
	{
		//mysql connection start
		//config error for timezone, now it's working 
		String connectionString = "jdbc:mysql://localhost/company?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; 
		String dbLogin = "javauser2";
		String dbPassword = "j4v4us3r?";
		
		//getting data from DB using iD	
		String sql = "SELECT employee_id, first_name, last_name, age, title, salary, status,+"
				+ " supervisor_id, bonus, department, insurance, hiredate, +"
				+ "phone FROM employees WHERE employee_id =" + getEmployeeID() + ";";
		
		Connection conn = null;
			try 
		    {
				conn = DriverManager.getConnection(connectionString, dbLogin, dbPassword);
		        if (conn != null) 
		        {
		        	//System.out.println("Database connection successful.");
		        	Statement stmt = conn.createStatement();
		    	    ResultSet rs = stmt.executeQuery(sql);
		    	    if(rs.next()) 
		    	    {
		    	    	setFirstName(rs.getString("first_name"));
		    	    	setLastName(rs.getString("last_name"));
		    	    	setAge(rs.getByte("age"));
		    	    	setTitle(rs.getString("title"));
		    	    	setStatus(rs.getString("status"));
		    	    	setSupervisor_id(rs.getInt("supervisor_id"));
		    	    	setDepartment(rs.getString("department"));
		    	    	setHiredate(rs.getString("hiredate"));
		    	    	setPhone(rs.getString("phone"));
		    	    }
		        }
		    }
		  catch (Exception e) 
		    {
		       System.out.println("Database connection failed.");
		       e.printStackTrace();
		    }
		    //mysql connection ends 
	}
}
