package assignment_4;

public class Employee {
	int id;
	String name;
	double salary;
	
	Employee(int i , String n , double salary)
	{
		this.id=i;
		this.name=n;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	double calSal() {return this.salary;}
	
	void display()
	{
		System.out.println("ID :" + this.id +"  Name : " +this.name+ "  Salary : "+ this.salary);
	}
}
class Admin extends Employee
{
	double allowance;
	
	Admin(int i , String n ,double s, double allow)
	{
		super(i,n,s);
		this.allowance = allow;
	}

	public double getAllowance() {
		return allowance;
	}

	public void setAllowance(double allowance) {
		this.allowance = allowance;
	}
	
	double calSal()
	{
		return super.salary + this.allowance; 
	}
	
	void display()
	{
		super.display();
		System.out.println("-Allowance : " + this.allowance);
	}
}

class salesManager extends Employee{
	int target;
	double incentive;
	
	salesManager(int i, String n ,double s , int target , double incentive)
	{
		super(i,n,s);
		this.target=target;
		this.incentive = incentive;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public double getIncentive() {
		return incentive;
	}

	public void setIncentive(double incentive) {
		this.incentive = incentive;
	}
	double calSal()
	{
		return super.salary + this.incentive; 
	}
	
	void display()
	{
		super.display();
		System.out.println(" Incentive : " + this.incentive);
	}
}
class HR extends Employee
{
	double commission;
	
	HR(int i ,String n , double d , double c)
	{
		super(i,n,d);
		this.commission=c;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}
	
	double calSal()
	{
		return super.calSal() + this.commission;
		
	
	}
	void display()
	{
		super.display();
		
		System.out.println(" Commission : " + this.commission);
	}
}

class Test
{
	 public static void main(String[] args) {
		Employee arya = new Employee(01,"Aryan Tugaonkar",95746);
		Employee Swamini = new Admin ( 02,"Swamini Warale",98745,7458);
		
		arya.display();
		Swamini.display();
		
	}
}
