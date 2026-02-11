//Check the given number is Strong number or not.
//Input: n = 145
//Output: Strong
void main () {
    int n;
    printf("Enter a number: ");
    scanf("%d", &n);
    int temp = n;
    int sum = 0;
    while (n!=0) {
        int digi=n%10;
        int t1=0;
        t1=t1*10+digi;
        int fact=1;
        while (t1!=0) {
            fact=fact*t1;
            t1--;
        }
        sum=sum+fact;
        n=n/10;
    }
    if (sum==temp) {
        printf("Strong number");
    } else {
        printf("Not a Strong number");
    }
}