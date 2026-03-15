void main(){
    int a,fno,lno;
    printf("Enter a number: ");
    scanf("%d",&a);
    lno=a%10;
    for(; a > 9; a = a/10);

        fno=a;
   
    printf("Sum of digits: %d\n",fno+lno);
}
