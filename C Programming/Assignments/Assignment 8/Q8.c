//8.Merge two arrays
#include<stdio.h>

void main ()
{
    int arr1[]={46547,165,962,48,38,32647,328,45,30};
    int arr2[]={545,34654,16547,21,84324,896564,51};
    int size1 = sizeof(arr1)/sizeof(int);
    int size2 = sizeof(arr2)/sizeof(int);
    int mix[size2+size1];
    
    for( int i=0;i<size1; i++)
    {
        mix[i]=arr1[i];
        
    }
    for ( int i=0 ; i<size2 ; i++ )
    {
        mix[i + size1]=arr2[i];
    }
    
    for( int i=0;i<size1+size2; i++)
    printf("%d \n",mix[i]);
}