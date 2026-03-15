void main() {
    int n=7;
    int i;
    int isprime=0;
     for(i=2;i<n;i++){
        if(n % i==0){
            isprime=1;
            break;
        }
     }if(isprime==0){
    printf("no is prime");
     }else{
        printf("not prime");
     }
}