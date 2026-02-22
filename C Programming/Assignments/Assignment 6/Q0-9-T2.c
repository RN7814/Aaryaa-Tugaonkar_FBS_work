#include<stdio.h>

void area(float b,float h);   // Type 2

void main(){
float b=10,h=5;
area(b,h);
}

void area(float b,float h){
float ar;
ar=0.5*b*h;
printf("Area of triangle = %f",ar);
}