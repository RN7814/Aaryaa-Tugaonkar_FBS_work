//Check the given number is Strong number or not.
#include<stdio.h>
int strong(int);
void main()
{
    int no;
    printf("Enter a no : ");
    scanf("%d",&no);

    int res=strong(no);
    if(res==1)
    {
        printf("Strong");
    }else if(res==0)
    {
        printf("Not Strong");
    }
}

int strong(int no){
int t1=no,tot=0;

for(;no!=0;){
    int temp=no%10;
        no=no/10;

    int fact=1;

    for(int i=1;i<=temp;i++){
        fact=fact*i;
    }

tot=tot+fact;
}

if(tot==t1)
return 1;
else
return 0;
}