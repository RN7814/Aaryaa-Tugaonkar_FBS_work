
class Hotel
{
	int hno;
	String name;
	int stars,rooms_avail;
	
	void sethno(int i)
	{
		this.hno = i;
	}
	
	void  setstars( int s)
	{
		this.stars = s;
	}
	
	void setrooms_avail ( int c)
	{
		this.rooms_avail = c;	
	}
	 
	void  setname( String n)
	{
		this.name = n;
	}
	
	int getstars()
	{
		return this.stars;
	}
	
	int getrooms_avail()
	{
		return this.rooms_avail;
	}
	
	int gethno()
	{
		 return this.hno;
	}
	
	String getname()
	{
		return this.name;
	}
	
	void display()
	{
		System.out.println ( this.gethno()+"--"+this.getname()+"--"+this.getstars()+"--"+this.getrooms_avail()+"\n");
	}
}
	class Test
	{
		public static void main ( String[] args)
		{
			Hotel h1= new Hotel();
			Hotel h2= new Hotel();
			
			h1.sethno(10);
			h1.setstars(4);
			h1.setname("Hotel MoonShine");
			h1.setrooms_avail(37);
			h1.display();
			
			
			h2.sethno(02);
			h2.setstars(5);
			h2.setname("Hotel Royal Fern");
			h2.setrooms_avail(16);
			h2.display();
		}
	}