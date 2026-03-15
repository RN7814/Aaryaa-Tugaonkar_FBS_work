//Find factorial of given number.

#include<stdio.h>
void factorial(int);
void main()
{
    int n;
    printf("Enter a no : ");
    scanf("%d",&n);
    factorial(n);
}
void factorial(int no)
{
    int t=1;
    for(int i=1;i<=no;i++)
    {
        t=t*i;
    }
    printf("%d is factorial of yo no",t);

}
