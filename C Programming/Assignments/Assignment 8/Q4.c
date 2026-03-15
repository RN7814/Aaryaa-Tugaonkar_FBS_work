//4. Find odd and even among the numbers. 

#include<stdio.h>

void main()
{
    int no[10]={30,25,42,68,31,58,75,77,98,18};
    printf("Evens :\n");
    for(int i=0 ; i<10 ;i++)
    {
        if(no[i]%2==0)
        printf("    %d\n",no[i]);
    }
    printf("odds :\n");
    for(int i=0 ; i<10 ;i++)
    {
        if(no[i]%2!=0)
        printf("    %d\n",no[i]);
    }
}
