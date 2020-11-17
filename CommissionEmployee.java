import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CommissionEmployee extends Employee
{
	private double weeklySalary;
	String status;
	public CommissionEmployee(int employeeID) 
	{
		super(employeeID);
	}
	public void setWeeklySalary(double salary) 
	{
		double newsalary = 0.0;
		if(getStatus() == "Comission") 
		{
			newsalary = salary;
		}
		else newsalary = 0.0;
		this.weeklySalary = newsalary;
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
	
	@Override
	public void printEmployee() 
	{
		System.out.println(" Comission:\t\t|" + this.weeklySalary);
	}
	public void getEmployee() 
	{
		String connectionString = "jdbc:mysql://localhost/company?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; 
		String dbLogin = "javauser2";
		String dbPassword = "j4v4us3r?";
		
		//getting data from DB using iD	
		String sql = "SELECT salary, status FROM employees WHERE employee_id =" + getEmployeeID() + ";";
		
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
		    	    	setWeeklySalary(rs.getDouble("salary"));
		    	    	setStatus(rs.getString("status"));
		    	    }
		        }
		    }
		  catch (Exception e) 
		    {
		       System.out.println("Database connection failed.");
		       e.printStackTrace();
		    }
	}
}
