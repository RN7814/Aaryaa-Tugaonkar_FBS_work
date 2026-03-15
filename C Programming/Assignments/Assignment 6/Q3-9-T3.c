#include<stdio.h>
void pali(int);

void main()
{
    int a;
    printf("Enter a No : ");
    scanf("%d",&a);
    pali(a);

}

void pali(int no)
{
    int t2=0,temp,t1; 
    temp=no;
    for(;no!=0;){
    
    t1=no%10;
    no=no/10;
    t2=(t2*10)+t1;
    }
    if(t2==temp)
    printf("\nIT is a palindrome\n");

    if(t2!=temp)
    printf("Not a Palidrome");
    
}