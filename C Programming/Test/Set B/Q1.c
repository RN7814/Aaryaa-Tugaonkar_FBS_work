//1. Write a program to count number of words in a string. 
#include<stdio.h>
#include<string.h>
void main()
{
	char str[]={"This is a String Operaion."};
//	int len=str[25];
//printf("%d\n",len);
	int wcount=0;
	
	
	for(int i=0;str[i]!='\0';i++)
		{
			if(str[i]==105 || str[i]==46)
				wcount++;
				
		}
	
	printf("%d are words present in Given String",wcount);
}
