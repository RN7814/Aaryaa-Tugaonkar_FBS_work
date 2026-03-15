#include<stdio.h>
int isPrime(int);
void main()
{
    int no=7;
    int prime=isPrime(no);
    
    if(prime==1)
    printf("No is Prime");
    else
    printf("NOT prime");
    
}
    int isPrime(int n)
{
    
    for(int i=2;i<n;i++)
    {
        if(n%i==0 )
        return 0;
        else
        return 1;
    }

}