#include<stdio.h>

void discount(int price,int stu);

void main(){
int price=5600;
int stu=1;
discount(price,stu);
}

void discount(int price,int stu){
double dis=0;

if(stu==1){
    if(price>=600){
        dis=price*0.15;
    }
    else if(price>=500){
        dis=price*0.20;
}
    else{
        dis=price*0.10;
}
}

printf("%f is the discount",dis);
}