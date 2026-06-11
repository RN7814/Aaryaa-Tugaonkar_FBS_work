
public abstract class Employee {
//////////
/// 
///   Class Is Abstract in this and all the Methods are Concreat.
/// 
/// 
/// /////
	int id;
	String name;
	double salary;
	static int count;
	static
	{ count = 0; };
	
	Employee()
	{
		this.id = 0;
		this.name = "Not Given";
		this.salary = 00;
		count++;
	}
	
	Employee(int i, String n , double sal)
	{
		this.id = i;
		this.name = n;
		this.salary = sal;
		count ++;
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
	
	 abstract double CalSal();
//	{
//		return this.salary;
//	}
	 void display()
	{
		System.out.println(this.getId() + " " + this.getName() + " " + this.getSalary()+"\n");
	}
	
	
}
/////////////////////////////////////  Employee Class Ends Here ////////////////////////////////////////

class salesManager extends Employee
{
	int target;

	double incentive;
	
	salesManager()
	{
		this.target=1;
		this.incentive=00;
	}
	
	salesManager(int in,String st,double d,int ta,double i)
	{
		super(in,st,d);
		this.target=ta;
		this.incentive=i;
	}
	//salesManager e4 = new salesManager(04,"Brock Lesnar",88133,55,4456)

	void settarget( int t)
	{
		this.target = t;
	}
	
	void setincentive ( double c)
	{
		this.incentive = c/2.0;	
	}
	
	double getincentive()
	{
		return this.incentive;
	}
	
	int gettarget()
	{
		return this.target;
	}
	public double CalSal()
	{
		return this.salary + this.incentive;
	}
	void display()
	{
		System.out.println(this.getId() + " " + this.getName() + " " + this.getSalary() + " " + this.gettarget() + " " + this.getincentive()+"\n");
	}
	
}
/////////////////////////////////////////////// Sales Manager Class Ends Here /////////////////////////////////////

class Admin extends Employee
{
	double allowance;
	
	Admin()
	{
		this.allowance=0000;
	}
	Admin(int in, String na, double sar, double allow)
	{
		super(in,na,sar);
		this.allowance=allow;
	}
	void setallowance ( double c)
	{ 
		this.allowance = c/2.0;	
	}
	
	double getallowance()
	{
		return this.allowance;
	}
	public double CalSal()
	{
		return this.salary+this.allowance;
	}
	void display()
	{
		System.out.println(this.getId() + " " + this.getName() + " " + this.getSalary() + " " + this.getallowance()+"\n");
	}
	
}
/////////////////////////////////////////////// Admin Class Ends Here ////////////////////////////////////////////////////

class Hr extends Employee
{

	double commission;
	
	Hr()
	{
		
		this.commission=0000;
	}
	Hr(int q,String stre,double salar,double commissio)
	{
		super(q,stre,salar);
		this.commission=commissio/2.0;
	}
	
	void setcommission ( double c)
	{
		this.commission = c/2.0;	
	}

	double getcommission()
	{
		return this.commission;
	}
	public double CalSal()
	{
		return this.salary+this.commission;
	}
	void display()
	{
		System.out.println(this.getId() + " " + this.getName() + " " + this.getSalary() + " " + this.getcommission()+"\n");
	}
}

/////////////////////////////// HR Class Ends Here ////////////////////////////////


class Test {
	public static void main (String[] args) {
		
		Employee e1 = new Hr(01,"Anthony Starr", 45786,7845);
		Hr e2 = new Hr(02,"Jhon Moriison",35688,12000);
		Admin e3 = new Admin(03,"Seline Sharma",49999,20000);
		salesManager e4 = new salesManager(04,"Brock Lesnar",88133,55,4456);
		//////////////////////////////////
		e1.display();
		e2.display();
		e3.display();
		e4.display();
		System.out.println("Total No of Employees :- " + Employee.count);

		//////////////////////////////////
		System.out.println("Salaries of all employees respectively := \n" + e1.CalSal());
		System.out.println(e2.CalSal());
		System.out.println(e3.CalSal());
		System.out.println(e4.CalSal());
		
		
	}
}

