
void main(){
    int n=28,fno=0;
    for(int i=1;i<n;i++){
        if(n%i==0){
            fno=fno+i;
        }
    }if(fno==n){
            printf("Perrfect");
        }else
            printf("not perfect");
    
}