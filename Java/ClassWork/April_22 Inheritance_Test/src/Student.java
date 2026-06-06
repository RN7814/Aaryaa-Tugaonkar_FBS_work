class Student
{
	String Name;
	double DC;
	double Frn;
	static int count;
	static
	{
		count=0;
	}
	Student()
	{
		this.Frn=00;
		this.Name="No input";
		this.DC=00;
		count++;
	}
	
	Student(double f, String n, double dc)
	{
		this.Frn = f;
		this.Name = n;
		this.DC = dc;
		count++;
	}
	
	void setFrn(double f)
	{
		this.Frn = f;
	}
	
	void setName(String n)
	{
		this.Name = n;
	}
	
	void setDC( double d)
	{
		this.DC = d;
	}
	
	double getFrn()
	{
		return this.Frn;
	}
	
	String getName()
	{
		return this.Name;
	}
	
	double getDC()
	{
		return this.DC;
	}
	
	void display()
	{
		System.out.println(this.getDC()+" "+ this.getFrn()+" "+ this.getName());
	}
}
class PlacedStudent
{
	String studentName,companyName,designation;
	double frn,distanceCovered;
	
	PlacedStudent(double f, String n, double d, String dc, String desig)
	{
		this.frn=f;
		this.studentName=n;
		this.distanceCovered=d;
		this.companyName=dc;
		this.designation=desig;
		
		
	}
	void display()
	{
		System.out.println(this.distanceCovered+" "+ this.frn+" "+ this.studentName + " "+ this.companyName + " "+ this.designation);
	}
		

}
class Test
{
	public static void main (String[] args)
	{
		//Student s1 = new Student();
		
		//s1.setFrn(020126.022);
		//s1.setDC(11);
		//s1.setName("Aryan");	
		//s1.display();
		//System.out.print(s1);
		
		Student s1=new Student(101, "Rahul", 300);
		Student s2=new Student(105, "Sanket", 400) ; 
		Student s4=new Student(208, "Sahil", 500) ; 
		Student s6=new Student(107, "Harshal", 300);
		
		PlacedStudent s3 = new PlacedStudent(205,"Sahil",436,"Accenture","Head Developer");
		PlacedStudent s5 = new PlacedStudent(206,"Swamini",884,"Amazon","Senior Developer");
		
		s1.display();
		s2.display();
		s3.display();
		s4.display();
		s5.display();
		s6.display();
		
		System.out.println("Total Student in Record :" + Student.count);
		
	}
}
