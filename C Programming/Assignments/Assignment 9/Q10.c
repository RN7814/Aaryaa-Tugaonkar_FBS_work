#include<stdio.h>
void sort(int arr[], int);
void main ()
{
	int arr[10];
	
	for( int i=0; i<10 ; i++)
	{
		printf("Enter a No:");
		scanf("%d",&arr[i]);
	}
	printf("FOLLOWING IS THE SORTED ARRAY :");
	sort(arr,10);
	
}

void sort(int arr1[], int size)
{
	int temp;
	
	for ( int j=0;j<size;j++)
	{
		
		for( int i=j+1; i<size ;i++)
		{
		if(arr1[j]>arr1[i])
		{
            temp=arr1[j];
			arr1[j]=arr1[i];
			arr1[i]=temp;
		}
		
		}
	
	}
	for ( int i =0; i<size ; i++)
	printf("%d \n",arr1[i]);
	
	
}