//5. Print alternate elements in array. 
#include<stdio.h>
void main ()
{
    int arr[]={56,45,77,16,54,10,80,74,15,84,51,84};
    int size= sizeof(arr)/sizeof(int);
    for ( int i=-1 ; i<size ;i++)
    {
        printf("%d-",arr[i++]);

    }






}