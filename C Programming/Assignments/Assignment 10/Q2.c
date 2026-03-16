//2.strcpy()
void mystrcpy(char desti[],char sour[]);

#include<stdio.h>
#include<string.h>


int main()
{
	char abc[]="hii this is my second string Operation\n";
	printf("%s",abc);
	int size= sizeof(abc);
	char cop[size];
	strcpy(cop,abc);
	printf("%s",cop);
	
///////////////////////////////////////////////////////////////////////////////////////////////////	
	
	char sou[]="This is MY Second Manual string fucntion";
			int size1= sizeof(sou);
	char dest[size1];
	mystrcpy(dest,sou);
	printf("%s",dest);
	
	
	return 0;
}
void mystrcpy(char desti[],char sour[])
{
	int i;
	for( i=0;sour[i]!= '\0';i++)
	{
		desti[i]=sour[i];
	}
	desti[i]='\0';
}

