void main() {
    int a;
    printf("Enter a number: "); 
    scanf("%d",&a);
    int fno,lno;
    lno=a%10;
   
    while (a!=0 && a>9){
        a=a/10;
    }
    fno=a;
    printf("First digit: %d\n",fno);
    printf("Last digit: %d\n",lno);
    printf("Sum of First and Last digit: %d",fno+lno);
}