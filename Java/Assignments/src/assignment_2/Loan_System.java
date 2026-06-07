package assignment_2;

class Student
{
	int rollNO;
	String name;
	double percen;
	
	Student(int r,String n,double p)
	{
		this.rollNO = r;
		this.name =n;
		this.percen = p;
	}
}
class Employee 
{
	int id;
	String name;
	int salary;
	
	Employee(int i, String n, int s)
	{
		this.id=i;
		this.name=n;
		this.salary=s;
	}
}

class Bank {
    // Overloaded for Student object
    void approveLoan(Student s) {
        double p = s.percen;
        if (p > 80) {
            System.out.println("Loan Approved for Student: ₹2,00,000");
        } else if (p >= 60) {
            System.out.println("Loan Approved for Student: ₹1,00,000");
        } else if (p >= 40) {
            System.out.println("Loan Approved for Student: ₹50,000");
        } else {
            System.out.println("No loan approved for Student.");
        }
    }


    void approveLoan(Employee e) {
        int s = e.salary;
        if (s > 12) {
            System.out.println("Loan Approved for Employee: ₹7,00,000");
        } else if (s >= 10) {
            System.out.println("Loan Approved for Employee: ₹6,00,000");
        } else if (s >= 6) {
            System.out.println("Loan Approved for Employee: ₹5,00,000");
        } else if (s >= 4) {
            System.out.println("Loan Approved for Employee: ₹4,00,000");
        } else {
            System.out.println("No loan approved for Employee.");
        }
    }
}

public class Loan_System {
	public static void main (String[] args)
	{
		Student s1 = new Student(01,"Swamini Warale",93);
		Employee e1 = new Employee(501,"Aryan Tugaonkar",9);
		
		Bank b1= new Bank();
		
		b1.approveLoan(e1);
		b1.approveLoan(s1);
	}

}
