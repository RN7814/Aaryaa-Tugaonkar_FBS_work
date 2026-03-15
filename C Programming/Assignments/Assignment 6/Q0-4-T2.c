#include<stdio.h>

void swap(int a,int b);

void main(){
int x=10,y=20;
printf(" before swap x=%d y=%d",x,y);
swap(x,y);
}

void swap(int a,int b){
int t;
t=a;
a=b;
b=t;
printf("\n After swap a=%d b=%d",a,b);
}