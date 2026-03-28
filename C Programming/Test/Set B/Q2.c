//2. Write a program to print following series: 
////1 + 11+ 111+ 1111+ ….. + n times 1 

#include<stdio.h>

void main()
{
	int n;
	printf("Enter no of Times :");
	scanf("%d",&n);
	int no=1;
	for(int i=0; i!=n ;i++)
	{
		printf("%d ",no);
		if(i!=n-1)
			printf("+");
			
		no=no*10+1;
		
	}
	
}
