void main() {
    int a=7;
    int isPrime=1;
    for(int i=2;i<a;i++){
        if(a%i==0){
            isPrime=0;
            break;
        }
    }if(isPrime==1){
        printf("Prime");
}else{
        printf("Not Prime");
    }
}