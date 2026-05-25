class Date {
	int day,month,year;
	String dow;
	
	Date()
	{
		this.day=1;
		this.month=1;
		this.year=1;
		this.dow="blank";
	}
	void setDay(int d){
		this.day=d;
	}
	void setMonth(int m){
		this.month=m;
	}
	void setYear(int y){
		this.year=y;
	}
	void setDow(String str){
		this.dow=str;
	}
}
class Test{
	public static void main (String[] args){
		Date d1= new Date();
	d1.setDay(7);
	d1.setMonth(8);
	d1.setYear(2005);
	d1.setDow("Thursday");
	
	System.out.printf("%d-%d-%d-%s\n",d1.day,d1.month,d1.year,d1.dow);
	}
}