//11. Accept marks and display result (Distinction / First Class / Second Class / Pass / Fail).
#include<stdio.h>

void result(int *marks);

void main()
{
    int marks;

    printf("Enter marks: ");
    scanf("%d",&marks);
    result(&marks);
}

void result(int *marks)
{
    if(*marks >= 75)
        printf("Distinction");
    else if(*marks >= 60)
        printf("First Class");
    else if(*marks >= 50)
        printf("Second Class");
    else if(*marks >= 35)
        printf("Pass");
    else
        printf("Fail")
}