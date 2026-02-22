#include<stdio.h>

void power();   // Type 1

void main(){
power();
}

void power(){
int n=5,sq,cu;
sq=n*n;
cu=n*n*n;
printf("Square = %d\n",sq);
printf("Cube = %d",cu);
}