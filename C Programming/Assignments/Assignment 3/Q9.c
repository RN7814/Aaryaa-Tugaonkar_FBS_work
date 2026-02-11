void main(){
    int a;
    printf("Enter a number: ");
    scanf("%d",&a);
    int t1,t2=0,t3=a;
    while(a!=0) {
        t1= a%10;
        t2=t2*10+t1;
        a=a/10;   
    }
    printf("Reversed Number: %d",t2);
    if(t2==t3){
        printf("\nThe number is a palindrome");
    } else {
        printf("The number is not a palindrome");
}
}