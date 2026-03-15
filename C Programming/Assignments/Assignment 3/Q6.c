void main(){
    int n=28,i=1;
    int fno=0;
    while(i<n){
        if(n%i==0){
            fno=fno+i;
        }
        i++;
    }if(fno==n){
        printf("Perrfect");

    }else
        printf("not perfect");
}