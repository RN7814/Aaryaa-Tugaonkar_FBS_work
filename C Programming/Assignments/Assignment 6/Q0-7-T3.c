#include<stdio.h>

int convert();   // Type 3

void main(){
int h;
h=convert();
}

int convert(){
int min=130,h,r;
h=min/60;
r=min%60;
printf("Hours = %d\n",h);
printf("Remaining minutes = %d",r);
return h;
}