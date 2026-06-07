package assignment_1;
class PStudent
{
	String frn,studentName,distanceCovered,companyName,designation;
	
	void setfrn(String f)
	{
		this.frn = f;
	}
	
	void setstudentName( String s)
	{
		this.studentName = s;
	}
	
	void setdistanceCovered (String dis)
	{
		this.distanceCovered = dis;
	}
	
	void setcompanyName(String cname)
	{
		this.companyName = cname;
	}
	
	void setdisgnation(String desig)
	{
		this.designation = desig;
	}
	
	String getfrn()
	{
		return this.frn;
	}
	String getstudentName()
	{
		return this.studentName;
	}
	String getdistanceCovered()
	{
		return this.distanceCovered;
	}
	String getcompanyName()
	{
		return this.companyName;
	}
	String getdesignation()
	{
		return this.designation;
	}
	
	void display()
	{
		System.out.println(this.getfrn()+" "+this.getstudentName()+" "+ this.getdistanceCovered()+" "+ this.getcompanyName()+" "+ this.getdesignation()+"\n");
	}
}
	class Test9
	{
		public static void main (String [] args)
		{
			PStudent s1 = new PStudent();
			
			s1.setfrn("02J0126/022");
			s1.setstudentName("Aryan Tugaonkar");
			s1.setdistanceCovered("45KM");
			s1.setcompanyName("Cognizant");
			s1.setdisgnation("Manager");
			s1.display();
			
			PStudent s2 = new PStudent();
			s2.setfrn("02J0126/021");
			s2.setstudentName("Swamini Warale");
			s2.setdistanceCovered("21KM");
			s2.setcompanyName("AMAZON");
			s2.setdisgnation("Manager");
			
			System.out.println(s2.getfrn()+" "+s2.getstudentName()+" "+ s2.getdistanceCovered()+" "+ s2.getcompanyName()+" "+ s2.getdesignation());
		}
	}