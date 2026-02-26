#include<stdio.h>

void calculate(int *a,int *b,char *op);
void main()
{
    int a,b;
    char op;

    printf("Enter first number: ");
    scanf("%d",&a);

    printf("Enter second number: ");
    scanf("%d",&b);

    printf("Enter operator (+,-,*,/,%%): ");
    scanf(" %c",&op);  

    calculate(&a,&b,&op);   
}

void calculate(int *a,int *b,char *op)
{
    switch(*op)
    {
        case '+':
            printf("Result = %d",*a + *b);
            break;

        case '-':
            printf("Result = %d",*a - *b);
            break;

        case '*':
            printf("Result = %d",*a * *b);
            break;

        case '/':
            printf("Result = %d",*a / *b);
            break;

        case '%':
            printf("Result = %d",*a % *b);
            break;

        default:
            printf("Invalid operator");
    }
}