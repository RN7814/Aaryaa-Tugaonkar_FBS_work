#include<stdio.h>
int add(int,int);
int sub(int,int);
int mul(int,int);
int div(int,int);
int mod(int,int);

void main ()
{
    int a,b;
    char op;
    printf("Enter a no : ");
    scanf("%d",&a);
    printf("Enter a no : ");
    scanf("%d",&b);
    printf("Enter a Operator : ");
    op=getch();
    if(op=='+')
    {
    int sum=add(a,b);
    printf("\nsum=%d",sum);
    }
    else if(op=='-')
    {
    int sub1=sub(a,b);
    printf("\nsub=%d",sub1);
    }
    else if(op=='*'||op=='x')
    {    
    int mul1=mul(a,b);
    printf("\nmultiplication=%d",mul1);
    }
    else if(op=='/')
    {    
    int div1=div(a,b);
    printf("\ndivision=%d",div1);
    }
    else if(op=='%')
    {
    int mod1=mod(a,b);
    printf("\nmodulus=%d",mod1);
    }
    else
    printf("Wrong inpur or operator");
}
 

int add(int a,int b)
{
    return a+b;
}
int sub(int a,int b)
{
    return a-b;
}
int mul(int a,int b)
{
    return a*b;
}
int div(int a,int b)
{
    return a/b;
}
int mod(int a,int b)
{
    return a%b;
}