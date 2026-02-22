#include<stdio.h>

float peri(float l,float w);   // Type 4

void main(){
float l=12,w=6,p;
p=peri(l,w);
printf("Perimeter = %f",p);
}

float peri(float l,float w){
return 2*(l+w);
}