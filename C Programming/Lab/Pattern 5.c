void main(){
    int n=23;
  
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            if(i<=n&&j==n ){
                printf("+ ");
              
            }
            else{
                printf("8 ");
            }
        }
        printf("\n");
    }
}