void main (){
    int n=5,i=n,o=1;
    while(i!=0&&i<=n){
        if(n!=0){ 
            o=o*n;
        }
        n--;
        i--;  
    }
    printf("%d",o);
}
