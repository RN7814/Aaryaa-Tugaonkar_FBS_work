//7. Write a program to calculate total salary based on basic salary (DA, TA, HRA conditions).
#include<stdio.h>
void sal(float*);
void sal2(float*);

void main()
{
    float a;
    printf("Enter a Salary :");
    scanf("%f",&a);
    if(a<=5000)
    sal(&a);
    else if(a>5000)
    sal2(&a);
}

void sal(float *salary)
{
    float da,ta,hra;
    da=*salary * 0.10;
    ta=*salary * 0.20;
    hra=*salary * 0.25;
    printf("The total Salary is = %f",da+ta+hra+ *salary);
}

void sal2(float *salary)
{
    float da,ta,hra;
    da=*salary * 0.15;
    ta=*salary * 0.25;
    hra=*salary * 0.30;
    printf("The total Salary is = %f",da+ta+hra+ *salary);
}