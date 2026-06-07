
class Admin
{
	int id;
	String name;
	double salary,allowance;
	
	void setid(int i)
	{
		this.id = i;
	}
	
	void setsalary( double s)
	{
		this.salary = s;
	}
	
	void setallowance ( double c)
	{
		this.allowance = c/2.0;	
	}
	 
	void setname( String n)
	{
		this.name = n;
	}
	
	double getsalary()
	{
		return this.salary;
	}
	
	double getallowance()
	{
		return this.allowance;
	}
	
	int getid()
	{
		 return this.id;
	}
	
	String getname()
	{
		return this.name;
	}
	
	void display()
	{
		System.out.println ( this.getid()+"--"+this.getname()+"--"+this.getsalary()+"--"+this.getallowance()+"--"+"\n");
	}
}
	class Test
	{
		public static void main ( String[] args)
		{
			Admin h1= new Admin();
			Admin h2= new Admin();
			
			h1.setid(01);
			h1.setsalary(1545655);
			h1.setname("Rohit");
			h1.setallowance(44573);
			
			h1.display();
			
			
			h2.setid(02);
			h2.setsalary(45623);
			h2.setname("Aryan");
			h2.setallowance(45623);
			
			h2.display();
		}
	}