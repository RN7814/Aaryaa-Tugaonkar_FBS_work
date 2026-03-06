//1. Find minimum and maximum number in array.  
#include<stdio.h>
int mini( int arr[] , int ,int*);
int maxi( int arr[], int, int*);
void main ()
{
    int arr[]={4,5,6,8,4,2,10};
    int min=arr[0],max=arr[0];
    int size=sizeof(arr)/sizeof(int);

  //  for(int i = 0; i < size; i++)
  //  {
  //      if( arr[i]<= min)
  //      min=arr[i];
  //  }

  //  for(int i = 0; i < size; i++)
  //  {
  //      if( arr[i]>= max)
  //      max=arr[i];
  // }

    printf("%d,is the minimum NO\n",mini(arr,size , &min));
    printf("%d,is the maximum NO",maxi(arr,size , &max));

}

int mini(int trr[],int size ,int * ptr)
{
for(int i = 0; i < size; i++)
    {
        if( trr[i]<= *ptr)
        *ptr=trr[i];
 
    }
    return *ptr;
}

int maxi(int trr[],int size ,int * ptr)
{
for(int i = 0; i < size; i++)
    {
        if( trr[i]>= *ptr)
        *ptr=trr[i];
 
    }
    return *ptr;
}