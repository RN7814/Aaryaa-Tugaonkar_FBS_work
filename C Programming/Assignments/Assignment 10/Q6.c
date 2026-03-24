///6. strcmp()=strcmp(const char *str1, const char *str2)


#include<stdio.h>
#include<string.h>
int mystrcmp(char str1[], char str2[]);
void main()
{
    char str[100]="Apple \n";

    char str1[]="Apple\n";

	//strcmp(str, str1);
	
	printf("%d\n",mystrcmp(str, str1));
	
	if(mystrcmp(str, str1)==0)
	printf("string is equal");
	else if(mystrcmp(str, str1)>=0)
	printf("string 1 is greter");
	else if(mystrcmp(str, str1)<=0)
	printf("string 1 is smaller");
	
	
}
int mystrcmp(char str1[], char str2[])
{
	int i=0;
	while(str1[i]==str2[i])
	{
		if(str1[i]=='\0')
		return 0;
		break;
	}
	return str1[i] - str2[i];
}