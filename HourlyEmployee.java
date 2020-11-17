import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HourlyEmployee extends Employee
{
	private double salary;
	boolean bonus;
	
	public HourlyEmployee(int employeeID) 
	{
		super(employeeID);
	}
	public void setSalary(double salary) 
	{
		if(isBonus() == false) 
		{
			this.salary = salary;
		}
		else this.salary = 0;
	}
	
	public boolean isBonus() 
	{
		return bonus;
	}
		
	public void setBonus(boolean bonus)
	{
		this.bonus = bonus;
	}
	
	@Override
	public void printEmployee() 
	{
		System.out.println(" Hourly:\t\t|" + this.salary);
	}
	public void getEmployee() 
	{
		String connectionString = "jdbc:mysql://localhost/company?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; 
		String dbLogin = "javauser2";
		String dbPassword = "j4v4us3r?";
		
		//getting data from DB using iD	
		String sql = "SELECT salary, bonus FROM employees WHERE employee_id =" + getEmployeeID() + ";";
		
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
		    	    	setSalary(rs.getDouble("salary"));
		    	    	setBonus(rs.getBoolean("bonus"));
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
