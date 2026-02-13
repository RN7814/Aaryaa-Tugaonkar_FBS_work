void main(){
    int r;
    printf("Enter the range from 1 to: ");
    scanf("%d",&r);
    for(int i=1; i<=r; i++){
        int no = i;
        int sum = 0;
        while(no > 0){
            int digit = no % 10;
            int fact = 1;
            for(int j=1; j<=digit; j++){
                fact = fact * j;
            }
            sum = sum + fact;
            no = no / 10;
        }
        if(sum == i){
            printf("%d ", i);
        }
    }
}
