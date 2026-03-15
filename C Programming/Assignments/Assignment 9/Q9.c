//8. Merge two arrays
void rev(int arr1[], int);

#include<stdio.h>

void main()
{
    int arr[]={4, 454, 454 , 65,123,321,4,21,31,3214,4};
    int size= sizeof(arr)/ sizeof(int);

    rev(arr,size);
}

void rev(int arr1[], int size)
{
    int rev[size];
    for ( int i=0; i<size ; i++)
    rev[i]=arr1[(size-1-i)];
    
    for(int i=0; i< size;i++)
    printf("%d\n",rev[i]);
}