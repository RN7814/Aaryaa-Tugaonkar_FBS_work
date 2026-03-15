#include<stdio.h>

int add(int a,int b);

void main(){
int x=10,y=20;
 int sum=add(x,y);
printf("Sum = %d",sum);
}

int add(int a,int b){
return a+b;
}