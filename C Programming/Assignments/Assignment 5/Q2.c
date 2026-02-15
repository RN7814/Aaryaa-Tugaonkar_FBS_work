void main(){
    int n=5,x=1;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            if(i>=x&&j==x){
            printf("* ");
        }
        x++;
        printf("\n");
    }
        
    }
