//1.strlen()

#include<stdio.h>
#include<string.h>
int mystrlen(char str2[]);
int main()
{
	char str1[]="this is my first string operation\n";
	printf("%s",str1);
	
	printf("%d \n",strlen(str1));
	
	printf("%d s the no of Length strng",mystrlen(str1));
	return 0;
}

int mystrlen(char str2[])
{
	int count=0;
	int i=0;
	while(str2[i] != '\0' )
	{
		i++;
		count++;
	}
	return count;
}