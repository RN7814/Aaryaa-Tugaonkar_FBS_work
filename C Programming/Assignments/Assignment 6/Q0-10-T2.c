#include<stdio.h>

void result(int a,int b,int c,int d,int e);   // Type 2

void main(){
int m1=70,m2=65,m3=80,m4=75,m5=60;
result(m1,m2,m3,m4,m5);
}

void result(int a,int b,int c,int d,int e){
int total;
float per;
total=a+b+c+d+e;
per=total/5.0;
printf("Total = %d\n",total);
printf("Percentage = %f %",per);
}