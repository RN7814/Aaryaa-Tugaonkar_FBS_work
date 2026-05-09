class Date {
	int day,month,year;
	String dow;
	
	void setDay(int d){
		this.day=d;
	}
	void setMonth(int m){
		this.month=m;
	}
	void setYear(int y){
		this.year=y;
	}
}
class Test{
	public static void main (String[] args){
		Date d1= new Date();
	d1.setDay(7);
	d1.setMonth(8);
	d1.setYear(2005);
	
	System.out.printf("%d-%d-%d\n",d1.day,d1.month,d1.year);
	}
}