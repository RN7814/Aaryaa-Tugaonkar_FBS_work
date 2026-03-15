//9. Accept three sides of a triangle and determine whether it is equilateral, isosceles, or scalene.
#include<stdio.h>
void Type(int*,int*,int*);
void main ()
{
    int a;
    printf("Enter a First side :");
    scanf("%d",&a);

    int b;
    printf("Enter a Second side :");
    scanf("%d",&b);

    int c;
    printf("Enter a Third side :");
    scanf("%d",&c);
    
    Type(&a,&b,&c);
}

void Type ( int* s1,int* s2,int* s3)
{

    if(*s1 == *s2 && *s2 == *s3)
    printf("Equilateral");

    else if(*s1 == *s2 || *s1 == *s3 || *s2 == *s3)
    printf("Isosceles");

    else
    printf("Scalene");
}