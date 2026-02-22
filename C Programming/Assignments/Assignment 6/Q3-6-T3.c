#include<stdio.h>
int perfect();
void main(){
    int res=perfect();
    if(res==1){
    printf("Perfect");
    }
    else{
    printf("Not Perfect");
    }
}

int perfect(){
    int n=28,fno=0;
    for(int i=1;i<n;i++){
        if(n%i==0){
            fno=fno+i;
        }
    }if(fno==n){
            return 1;
        }else
            return 0;
    
}