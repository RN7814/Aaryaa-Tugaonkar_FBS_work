//strncat() =strncat(char *dest, const char *src, size_t n)

#include<stdio.h>
#include<string.h>

void mystrncat(char str[],char str1 [], int);
void main( )
{
    char str[100]="This is my Fifth string\n";

    char str1[]="5TH string\n";

    //strncat(str,str1,10);

    //printf("%s",str);
    ///////////////////////////////////////////////////////////

    mystrncat(str , str1 , 5);
    printf("%s\n\n\n\n",str);
}
void mystrncat(char str1[], char str2 [], int n)

{
int count=0;
for( ;str1[count] != '\0'; count++);

for(int i=0; i!=n ;i++,count++)
    str1[count]=str2[i];
}  