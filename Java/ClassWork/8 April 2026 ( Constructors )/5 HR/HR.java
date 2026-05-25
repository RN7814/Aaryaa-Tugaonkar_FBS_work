
class Hr
{
	int id;
	String name;
	double salary,commission;
	
	Hr()
	{
		this.id=0;
		this.name="Default";
		this.salary=000;
		this.commission=0000;
	}
	Hr(int q,String stre,double salar,double commissio)
	{
		this.id=q;
		this.name=stre;
		this.salary=salar;
		this.commission=commissio;
	}
	
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
	class Test
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
			
			Hr h3= new Hr(03,"Alex",46878.9,4687.89);
			h3.display();
			
			Hr h4= new Hr();
			h4.display();
		}
	}