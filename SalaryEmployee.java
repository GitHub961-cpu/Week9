import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SalaryEmployee extends Employee
{
	private double weeklySalary;
	int insurance;
	boolean bonus;
	
	public SalaryEmployee(int employeeID) 
	{
		super(employeeID); 
	}
	
	public void setWeeklySalary(double salary) 
	{
		double newsalary = 0.0;
		if(getStatus() != "C") 
		{
			if(isBonus() == true) 
			{
				newsalary = salary + 1000.00;
			}
			else newsalary = salary;
		
			switch(getInsurance()) 
			{
				case 0: newsalary = salary; break;
				case 1:	newsalary = salary - 2000.00; break;
				case 2:	newsalary = salary - 1500.00; break;
				case 3:	newsalary = salary - 1000.00; break;
				case 4: newsalary = salary - 500.00;
			}
		}
		else newsalary = 0.0;
		this.weeklySalary = newsalary;
	}
	public boolean isBonus() 
	{
		return bonus;
	}
		
	public void setBonus(boolean bonus)
	{
		this.bonus = bonus;
	}
	public int getInsurance() 
	{
		return insurance;
	}
	
	public void setInsurance(int insurance)
	{
		this.insurance = insurance;
	}
	public String getStatus() 
	{
		return status;
	}
	
	public void setStatus(String status) 
	{
		this.status = status;
	}
	
	@Override
	public void printEmployee() 
	{
		System.out.print(" Salary:\t\t|" + this.weeklySalary);
		System.out.println("\n Insurance:\t\t|" + getInsurance());
	}
	public void getEmployee() 
	{
		String connectionString = "jdbc:mysql://localhost/company?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; 
		String dbLogin = "javauser2";
		String dbPassword = "j4v4us3r?";
		
		//getting data from DB using iD	
		String sql = "SELECT salary, bonus, insurance, status FROM employees WHERE employee_id =" + getEmployeeID() + ";";
		
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
		    	    	setBonus(rs.getBoolean("bonus"));
		    	    	setInsurance(rs.getInt("insurance"));
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
