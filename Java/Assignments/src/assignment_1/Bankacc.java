package assignment_1;

class Bankacc
{
	int accno;
	String name;
	double cubal,intrest;
	
	void setaccno( int a)
	{
		this.accno = a;
	}
	
	void setname(String n)
	{
		this.name = n;
	}
	
	void setcubal( double cu)
	{
		this.cubal = cu;
	}
	
	void setintrest( double i)
	{
		this.intrest = i;
	}
	
	int getaccno()
	{
		return this.accno;
	}
	
	double getcubal()
	{
		return this.cubal;
	}
	
	double getintrest()
	{
		return this.intrest;
	}
	
	String getname()
	{
		return this.name;
	}
	
	void display()
	{
		System.out.println(this.getaccno()+"//"+this.getname()+"//"+this.getcubal()+"//"+this.getintrest()+"/n");
	}
}
class Test2
{
	public static void main (String [] args)
	{
		Bankacc a1 = new Bankacc();
		Bankacc a2 = new Bankacc();
		
		
		a1.setaccno(6456321);
		a2.setaccno(1456321);
		
		a1.setname("Pritam");
		a2.setname("Virat");
		
		a1.setcubal(652355.6);
		a2.setcubal(678951.78);
		
		a1.setintrest(8599.2);
		a2.setintrest(7568.9);
		
		a1.display();
		a2.display();
		
	
	}
}