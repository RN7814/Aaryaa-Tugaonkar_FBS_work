//7. Take two array and add sum in third array
#include<stdio.h>
void addi ( int arr[], int arr1[],int);
void main()
{
    int arr1[]={58,56,2,5,48,53,547,68,2,45};
    int arr2[]={56,2,3,4,5,6,78,965,24,28};
    int size = sizeof(arr1)/sizeof(int);
    int add[size];
    addi(arr1 ,arr2,size);
}
void addi ( int arr[], int arr1[] ,int size)
{
    int add[size];
    for ( int i=0; i <= size ; i++)
    {
        add[i]=arr[i] + arr1[i];
    }
    for (int i=0; i<size ; i++)
    printf("%d \n",add[i]);

}