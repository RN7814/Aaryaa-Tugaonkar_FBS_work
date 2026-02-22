#include<stdio.h>
int UPLP(char);
void main(){
	
	char a='T';
	int d=UPLP(a);
	if(d==1)
	printf("char is lowercase");
	else
	printf("Uppercase");
}
	int UPLP(char a)
{
	
	if(a-32>=65&&a-32<=91){
		return 1;
	}else{
		return 0;
	}
}