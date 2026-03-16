//3.strncpy() = strncpy(dest, src, n);
void mystrncpy(char des[], char src[], int );

#include<stdio.h>
#include<string.h>


int main()
{
	int n=15;
	char str1[]="Hi this Third String Function\n\n\n";
	char str2[n];
	
	strncpy(str2,str1,n);
	printf("%s",str2);
	
	///////////////////////////////////////////////////////////////////////////////////
	

	char src[]="\n\nHi This is Third Manual Fucntion";
	int no= 20                   ;           //strlen(src);
	char des[no];
	mystrncpy(des, src, no);
	printf("%s",des);

}
void mystrncpy(char des[], char src[], int no)
{
	for(int i=0; i!=no ;i++)
	{
		des[i]=src[i];
	}
}






