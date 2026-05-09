
class Team
{
	int jercey;
	String name;
	int runs,mplayed;
	
	void setjercey(int i)
	{
		this.jercey = i;
	}
	
	void  setruns( int s)
	{
		this.runs = s;
	}
	
	void setmplayed ( int c)
	{
		this.mplayed = c;	
	}
	 
	void  setname( String n)
	{
		this.name = n;
	}
	
	int getruns()
	{
		return this.runs;
	}
	
	int getmplayed()
	{
		return this.mplayed;
	}
	
	int getjercey()
	{
		 return this.jercey;
	}
	
	String getname()
	{
		return this.name;
	}
	
	void display()
	{
		System.out.println ( this.getjercey()+"--"+this.getname()+"--"+this.getruns()+"--"+this.getmplayed()+"\n");
	}
}
	class Test
	{
		public static void main ( String[] args)
		{
			Team h1= new Team();
			Team h2= new Team();
			
			h1.setjercey(10);
			h1.setruns(104500);
			h1.setname("Sachin");
			h1.setmplayed(4100);
			h1.display();
			
			
			h2.setjercey(02);
			h2.setruns(110456);
			h2.setname("Virat");
			h2.setmplayed(4103);
			h2.display();
		}
	}