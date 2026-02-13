void main () {
    int n;
    printf("Enter a number: ");
    scanf("%d", &n);
    int temp = n;
    int sum = 0;
    for(; n!=0; n=n/10) {
        int digi=n%10;
        int t1=0;
        t1=t1*10+digi;
        int fact=1;
        for(; t1!=0; t1--) {
            fact=fact*t1;
        }
        sum=sum+fact;
    }   if (sum==temp) {
        printf("Strong number");
    } else {
        printf("Not a Strong number");
    }
}