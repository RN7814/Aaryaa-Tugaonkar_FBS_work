class Q3
{
public static void main(String args[]){
	int year=2001;
	if(year%4==0&&year%100!=0||year%400==0){
		System.out.printf("Ths year is leap");
		
	}else{
		System.out.printf("year is not leap");
	}
}
}