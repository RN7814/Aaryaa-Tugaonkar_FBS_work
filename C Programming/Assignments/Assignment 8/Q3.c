//3. Find sum of all numbers. 
#include<stdio.h>
int sum(int*,int);

void main ()
{
    int add[]={10,12,14,158,147,168,15,52};
    int tot=sum(&add[0],sizeof(add)/sizeof(int));
    printf("%d is the total",tot);

}
int sum( int * ptr,int size)
{
    int addi=0;
    for(int i=0; i<size;i++)
    {
        addi=addi+*ptr;
        ptr++;
    }
    return addi;
}