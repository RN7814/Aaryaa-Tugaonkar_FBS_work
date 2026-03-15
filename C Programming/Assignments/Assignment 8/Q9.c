//9. Reverse the given array.

#include<stdio.h>

void main ()
{
    int arr1[]={4,10,520,14,283,246,85,55};
    int size= sizeof(arr1)/sizeof(int);

    int rev[size];
    for(int i=0,j=size-1 ;i<size ; i++ , j--)
    rev[i]=arr1[j];

    for(int i=0 ; i<size ; i++)
    printf("%d \n",rev[i]);


}