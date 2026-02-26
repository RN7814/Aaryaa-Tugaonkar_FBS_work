//Even Or odd using pointer
#include<stdio.h>
void eod(int*);
void main ()
{
    int a;
    printf("Enter a no :");
    scanf("%d",&a);
    eod(&a);

}
void eod(int* add)
{
    if(*add%2==0)
    printf("The no is even");
    else
    printf("The no is odd");
}