#include<stdio.h>
void greatest(int no1, int no2, int no3);
void main()
{
    int no1=250, no2=456, no3=678;
    greatest(no1, no2, no3);
}

void greatest(int no1, int no2, int no3)
{
    if(no1 > no2)
    {
        if(no1 > no3)
        {
            printf("%d is greatest no", no1);
        }
    }
    else if(no1 < no2)
    {
        if(no2 > no3)
        {
            printf("%d is greatest no", no2);
        }
        else
        {
            printf("%d is greatest no", no3);
        }
    }
}