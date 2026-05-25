
class SM
{
	int id,target;
	String name;
	double salary,incentive;
	
	SM()
	{
		this.id=0;
		this.target=1;
		this.name="2";
		this.salary=00;
		this.incentive=00;
	}
	SM(int in,int ta,String st,double d,double i)
	{
		this.id=in;
		this.target=ta;
		this.name="2";
		this.salary=d;
		this.incentive=i;
	}
	
	void setid(int i)
	{
		this.id = i;
	}
	void settarget( int t)
	{
		this.target = t;
	}
	
	void setsalary( double s)
	{
		this.salary = s;
	}
	
	void setincentive ( double c)
	{
		this.incentive = c/2.0;	
	}
	 
	void setname( String n)
	{
		this.name = n;
	}
	
	double getsalary()
	{
		return this.salary;
	}
	
	double getincentive()
	{
		return this.incentive;
	}
	
	int getid()
	{
		 return this.id;
	}
	
	int gettarget()
	{
		return this.target;
	}
	
	String getname()
	{
		return this.name;
	}
	
	void display()
	{
		System.out.println ( this.getid()+"--"+this.getname()+"--"+this.getsalary()+"--"+this.getincentive()+"--"+this.gettarget()+"\n");
	}
}
	class Test
	{
		public static void main ( String[] args)
		{
			SM h1= new SM();
			SM h2= new SM();
			
			h1.setid(01);
			h1.setsalary(56523);
			h1.setname("Nikhil");
			h1.setincentive(56523);
			h1.settarget(50);
			h1.display();
			
			
			h2.setid(02);
			h2.setsalary(45623);
			h2.setname("Rahul");
			h2.setincentive(7563);
			h2.settarget(100);
			h2.display();
			
			SM h3=new SM();
			h3.display();
			
			SM h4=new SM(03,44444,"Satish",7946,7542);
			h4.display();
		}
	}