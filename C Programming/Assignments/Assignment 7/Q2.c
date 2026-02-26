//2. Write a program to check whether a given 3-digit number is palindrome or not.
#include<stdio.h>
void pali(int*);

void main ()
{
    int a;
    printf("Enter a no :");
    scanf("%d",&a);
    pali(&a);
}

void pali(int *pt)  //basically *pt=a && pt=&a
{
    int t1,res=0,t2;
    t1=*pt;
    for(;*pt!=0;)
    {
        t2=*pt%10;
        *pt=*pt/10;
        res=(res*10)+t2;
    }

    if(t1==res)
        printf("THe no is PAli");
    else
        printf("Not a pali");


}