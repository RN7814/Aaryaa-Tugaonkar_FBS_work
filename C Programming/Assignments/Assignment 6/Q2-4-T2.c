#include<stdio.h>

void result(int tma);

void main(){
int tma=32;
result(tma);
}

void result(int tma){
    if(tma>75&&tma<=100){
        printf("Distinction");
}
    else if(tma>65&&tma<=75){
        printf("First Class");
}
    else if(tma>55&&tma<=65){
        printf("Second Class");
}
    else if(tma>=40&&tma<=55){
        printf("Pass Class");
}
    else if(tma<40){
        printf("Fail");
}
    else{
        printf("The output is wrong");
}
}