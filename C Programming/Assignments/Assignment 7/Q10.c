//10. Write a program to find the greatest of three numbers using nested if-else.
#include<stdio.h>

void greatest(int *a, int *b, int *c);   // function declaration

void main()
{
    int a,b,c;

    printf("Enter three numbers: ");
    scanf("%d%d%d",&a,&b,&c);

    greatest(&a,&b,&c);   // passing addresses
}

// function definition
void greatest(int *a, int *b, int *c)
{
    if(*a > *b)
    {
        if(*a > *c)
            printf("Greatest number = %d", *a);
        else
            printf("Greatest number = %d", *c);
    }
    else
    {
        if(*b > *c)
            printf("Greatest number = %d", *b);
        else
            printf("Greatest number = %d", *c);
    }
}