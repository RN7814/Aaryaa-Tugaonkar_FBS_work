void main (){
    int n=5,i=n,op=1;
    while(i!=0&&i<=n){
        if(n!=0){ 
            op=op*n;
        }
        n--;
        i--;  
    }
    printf("%d",op);
}