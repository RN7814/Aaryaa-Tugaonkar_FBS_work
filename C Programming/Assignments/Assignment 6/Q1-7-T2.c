#include<stdio.h>
void tot1(int);
void tot2(int);
void main ()
{
    int sal;
    printf("Enter The Basic Salary: ");
    scanf("%d",&sal);
    if(sal<=5000)
    {
        tot1(sal);  // 10%=da,20%=ta,25%=hra
    }
    else if(sal>5000)
    {
        tot2(sal); // 15%=da,25%=ta,30%=hra
    }
    else
    printf("Wrong Input");
}

void tot1(int sal)
{
    float da,ta,hra;
    da=sal * 0.10;
    ta=sal * 0.20;
    hra=sal * 0.25;
    float total=da+ta+hra+sal;
    printf("%f is the total salary as base salary is <=5000",total);
}
void tot2(int sal)
{
     float da,ta,hra;
    da=sal * 0.15;
    ta=sal * 0.25;
    hra=sal * 0.30;
    float total=da+ta+hra+sal;
    printf("%f is the total salary as base salary is >5000",total);
    
}