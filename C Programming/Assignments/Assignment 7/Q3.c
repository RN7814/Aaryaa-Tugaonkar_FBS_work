//3. Write a program to check whether a given year is a leap year.
#include<stdio.h>
void cleap(int*);

void main ()
{
    int a;
    printf("Enter a year :");
    scanf("%d",&a);
    cleap(&a);
}

void cleap(int *pt)
{
    if(*pt % 4 == 0 && *pt % 100 != 0)
    printf("the year is leap");
    else
    printf("the year is not leap");
}
