//2. Search the given number in array
#include<stdio.h>
void pfarray(int arr[]);
void fno( int arr[],int,int , int* );
void main()
{

    int arr[1000];  
    for (int i=0; i<1000 ; i++)
        arr[i]=2*i;

    int findno,exact=0;

    printf("Enter a No to find : ");
    scanf("%d",&findno);


    int size=(sizeof(arr)/sizeof(int));
    fno(arr,findno,size ,&exact);

   // pfarray(arr);   
}

void fno( int arr[],int findno,int size, int* ptr )
{
    for( int i=0; i < size ; i++)
    {
        if (arr[i]== findno){
        *ptr=i;
        printf("%d found on %d position of array\n\n",findno,*ptr);
        break;
        }
    }
}
void pfarray(int arr[])
{

    for( int i=0 ; i<1000; i++)
    printf("%d  ",arr[i]);
}