#include<stdio.h>
void range(int,int);
void main()
{
    int start=1,end=5;
    range(start,end);
}
void range(int start,int end)
{
    int s=0;
    for (;start<=end;start++){
        s=s+start;   
    }
    printf("%d is the sum of no from start to end",s);

}