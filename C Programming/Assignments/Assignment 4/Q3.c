void main(){
    int r;
    printf("Enter the range from 1 to: ");
    scanf("%d",&r);
    for(int i=1; i<=r; i++){
        int sum = 0;
        for(int j=1; j<i; j++){
            if(i % j == 0){
             sum = sum + j;
            }
        }
    if(sum == i){
        printf("%d ", i);
        }
    }
}
