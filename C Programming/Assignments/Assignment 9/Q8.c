//8. Merge two arrays
void merge(int arr[],int arr2[], int , int );
#include<stdio.h>

void main()
{
    int arr[]={4, 454, 454 , 65,123,321,4,21,31,3214,4};
    int arr2[]={321,54622,431,2,352,854,58};
    int size1= sizeof(arr) / sizeof(int);
    int size2= sizeof(arr2) / sizeof(int);
    merge(arr, arr2, size1,size2);
}

void merge(int arr1[],int arr2[], int size1, int size2)
{
    int mix[size1+size2];

    for(int i=0; i<size1 ; i++)
    {
        mix[i]=arr1[i];
    }
    for(int i=0 ; i<size2 ; i++)
    {
                mix[i+size1]=arr2[i];

    }
    for(int i=0 ; i<size1+size2 ;i++)
    printf("%d \n",mix[i]);
}