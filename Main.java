import java.util.Scanner;
import java.sql.*;

public class Main 
{
	public static void main(String[] args) 
	{	
		int ID;
		Scanner input = new Scanner(System.in);
		//passing ID
		System.out.println("Enter employee's ID: ");
		Employee e = new Employee(ID = input.nextInt());
		SalaryEmployee s = new SalaryEmployee(ID);
		HourlyEmployee h = new HourlyEmployee(ID);
		CommissionEmployee c = new CommissionEmployee(ID);
		
		e.getEmployee();
		s.getEmployee();
		h.getEmployee();
		c.getEmployee();
		
		displayMenu();
		e.printEmployee();
		s.printEmployee();
		h.printEmployee();
		c.printEmployee();
		printSign(70);
	}
	

	public static void displayMenu() 
	{
		printSign(70);
		System.out.println("Employee Information");
		printSign(70);
	}
	private static void printSign(int sign) 
	{
		for(int i = 1; i <= sign; i++) 
		{
			System.out.print("~");
		}
		System.out.println("\n");
	}
}
