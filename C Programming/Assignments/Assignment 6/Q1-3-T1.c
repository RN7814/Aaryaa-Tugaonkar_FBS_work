void leap();
void main(){
    leap();
}
void leap(){
int year;
    printf("Enter a year: ");
    scanf("%d",&year);
	if(year%4==0&&year%100!=0||year%400==0){
		printf("Ths year is leap");
		
	}else{
		printf("year is not leap");
	}
}