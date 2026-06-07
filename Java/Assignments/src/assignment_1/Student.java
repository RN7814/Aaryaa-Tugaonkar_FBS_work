package assignment_1;
class Student
{
	String Frn,Name;
	int DC;

	void setFrn(String f)
	{
		this.Frn = f;
	}
	
	void setName(String n)
	{
		this.Name = n;
	}
	
	void setDC( int d)
	{
		this.DC = d;
	}
	
	String getFrn()
	{
		return this.Frn;
	}
	
	String getName()
	{
		return this.Name;
	}
	
	int getDC()
	{
		return this.DC;
	}
	
	void display()
	{
		System.out.println(this.getDC()+" "+ this.getFrn()+" "+ this.getName());
	}
	
	
}
class Test7
{
	public static void main (String[] args)
	{
		Student s1 = new Student();
		
		s1.setFrn("02J0126/022");
		s1.setDC(11);
		s1.setName("Aryan");
		
		s1.display();
		
		System.out.print(s1);
	}
}
	