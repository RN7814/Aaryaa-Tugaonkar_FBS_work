void main(){
	int year=2001;
	if(year%4==0&&year%100!=0||year%400==0){
		printf("Ths year is leap");
		
	}else{
		printf("year is not leap");
	}
}