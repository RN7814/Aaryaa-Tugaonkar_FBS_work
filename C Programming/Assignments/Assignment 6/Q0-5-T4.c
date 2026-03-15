#include<stdio.h>

float avg(int a,int b,int c,int d,int e);   

void main(){
int n1=10,n2=20,n3=30,n4=40,n5=50;
float a;
a=avg(n1,n2,n3,n4,n5);
printf("Average = %f",a);
}

float avg(int a,int b,int c,int d,int e){
float av;
av=(a+b+c+d+e)/5.0;
return av;
}