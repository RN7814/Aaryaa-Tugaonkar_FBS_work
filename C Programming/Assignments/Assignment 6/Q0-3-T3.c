#include<stdio.h>

float convert();

void main(){
float f;
f=convert();
printf("Fahrenheit = %f",f);
}

float convert(){
float c=37,f;
f=(c*9/5)+32;
return f;
}