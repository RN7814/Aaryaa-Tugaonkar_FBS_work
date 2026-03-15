//6. Accept array and print only prime numbers of array
#include<stdio.h>

void prime(int *, int);

int main()
{
    int num[10];
    int size = sizeof(num)/sizeof(int);

    for(int i=0;i<size;i++)
    {
        printf("Enter a NO : ");
        scanf("%d",&num[i]);
    }

    prime(num,size);

    return 0;
}

void prime(int* ptr,int size)
{
    for(int j=0;j<size;j++)
    {
        int n = *ptr;
        int flag = 1;

        if(n < 2)
            flag = 0;

        for(int i=2;i<=n/2;i++)
        {
            if(n % i == 0)
            {
                flag = 0;
                break;
            }
        }

        if(flag == 1)
            printf("%d is prime\n",n);

        ptr++;
    }
}