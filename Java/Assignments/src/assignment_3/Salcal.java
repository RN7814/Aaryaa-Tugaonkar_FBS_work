//Question 2: Employee Salary Calculation
//Problem Statement
//Design a class named Employee to calculate employee salary including company-wide bonus.
//Requirements
//
//The class should contain:
//• Employee name
//• Basic salary
//• Bonus rate applicable to all employees
//• A method to update the bonus rate
//• A method to calculate the total salary
//Salary Calculation Formula
//Total Salary = Basic Salary + (Basic Salary × Bonus Rate / 100)

package assignment_3;

class Employee
{
	String name;
	double salary;
	
	static double bonusrate;
	static {
		bonusrate=0.45;
	}
	static void updaterate(double newrate)
	{
		bonusrate=newrate;
	}
	
	Employee(String n, double s)
	{
		this.name= n;
		this.salary= s;	
	}
	Employee()
	{
	 this.name="Not GIven";
	 this.salary=0;
	}
	
	double totsal() {
        return this.salary + (this.salary * bonusrate) / 100;
    }
    
    // Method to handle data display cleanly
    void display() {
        System.out.println("Employee Details:");
        System.out.println("Name: " + this.name);
        System.out.println("Basic Salary: " + this.salary);
        System.out.println("Bonus Rate: " + bonusrate + "%");
        System.out.printf("Total Salary: %.2f%n%n", this.totsal());
    }
}

public class Salcal {

	public static void main(String[] args) {
		
		Employee e1 = new Employee("Shady",75400);
		e1.display();

		Employee.updaterate(30);
		
		e1.display();
	}

}
