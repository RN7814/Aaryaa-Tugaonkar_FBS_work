#include<stdio.h>

void category(int age);

void main(){
int age=81;
category(age);
}

void category(int age){
if(age<12){
printf("Child");
}
else if(age<=19){
printf("Teenager");
}
else if(age<=59){
printf("Adult");
}
else{
printf("Senior");
}
}