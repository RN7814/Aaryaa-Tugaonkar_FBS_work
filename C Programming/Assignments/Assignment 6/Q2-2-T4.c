#include<stdio.h>
int aot (int,int,int);

int main()
{
    int s1,s2,s3,res;
    printf("Enter side 1 : ");
    scanf("%d",&s1);
    printf("Enter side 2 : ");
    scanf("%d",&s2);
    printf("Enter side 3 : ");
    scanf("%d",&s3);
    res= aot(s1,s2,s3);
    if(res==3)
    printf("The Traingle is Equilateral");
    else if(res==2)
    printf("The Traingle is Isoscalen");
    else if(res==1)
    printf("The Traingle is scalen");
    return 0;
}
int aot(int a,int b,int c)
{
       if(a==b&&a==c){
        return 3;  
    }else if(a==b||b==c||a==c){
        return 2;
    }else if (a!=b && b!=c && a!=c)
        return 1;
    
}