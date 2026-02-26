#include<stdio.h>
void sod(int);

void main ()
{
    int a;
    printf("ENter a No : ");
    scanf("%d",&a);
    sod(a);
}

void sod(int no)
{
    int fd,ld;
    ld=no%10;
    for(;no>=9;no=no/10);
    fd=no;
    printf("%d is the addsitiion of first digit and last digit",fd+ld);
}