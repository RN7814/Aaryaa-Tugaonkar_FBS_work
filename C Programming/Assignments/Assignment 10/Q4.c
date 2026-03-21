//4.strcat(char *dest, const char *src)

void mystrcat(char str[],char str1[]);

#include<stdio.h>
#include<string.h>

void main()
{
    char str[100]="This is my Fourth string\n";

    char str1[]="4TH string\n";

    //strcat(str, str1);

    printf("%s this Concat string funtion\n\n\n",str);

    ///////////////////////////////////////////////////////////////

    mystrcat(str, str1);
    printf("%s this is my Concat string funtion",str);

}

void mystrcat(char str[],char str1[])
{
    int count=0;
    for( int i=0 ; str[i]!='\0' ; i++)
        count++;

    int j=0;
    while(str1[j] != '\0')
    {
        str[count] = str1[j];
        count++;
        j++;
    }
    str[count] = '\0';
}
