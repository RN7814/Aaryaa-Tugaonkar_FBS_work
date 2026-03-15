void main(){
	int no,r1,sum=0;
	printf("Enter a no :");
	scanf("%d",&no);
	while(no!=0){
		r1=no%10;
		sum=sum+r1;
		no=no/10;
		
	}
	printf("%d",sum);
}