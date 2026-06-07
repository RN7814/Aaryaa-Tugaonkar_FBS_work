package assignment_1;

class Hr
{
	int id;
	String name;
	double salary,commission;
	
	void setid(int i)
	{
		this.id = i;
	}
	
	void setsalary( double s)
	{
		this.salary = s;
	}
	
	void setcommission ( double c)
	{
		this.commission = c/2.0;	
	}
	 
	void setname( String n)
	{
		this.name = n;
	}
	
	double getsalary()
	{
		return this.salary;
	}
	
	double getcommission()
	{
		return this.commission;
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
		System.out.println ( this.getid()+"--"+this.getname()+"--"+this.getsalary()+"--"+this.getcommission()+"\n");
	}
}
	class Test5
	{
		public static void main ( String[] args)
		{
			Hr h1= new Hr();
			Hr h2= new Hr();
			
			h1.setid(01);
			h1.setsalary(456523);
			h1.setname("Aryan");
			h1.setcommission(456523);
			h1.display();
			
			
			h2.setid(02);
			h2.setsalary(45623);
			h2.setname("Arya");
			h2.setcommission(45623);
			h2.display();
		}
	}