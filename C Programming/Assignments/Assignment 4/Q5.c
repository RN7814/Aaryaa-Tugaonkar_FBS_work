void main (){
    int x;
    printf("1 -TO Check the no the no is Even or Odd\n");
    printf("2 -TO Check the no the no is Prime or Not\n");
    printf("3 -TO Check the no the no is Palindrome or Not\n");
    printf("4 -TO Check the no the no is Postive,Negative or Zero\n");
    printf("5 -To Reverse the no\n");
    printf("6 -To find the sum of digits\n");
    printf("enter the choice: ");
    scanf("%d",&x);
    
    if(x==1){
        int no;
        printf("Enter the no: ");
        scanf("%d",&no);
        if(no%2==0){
            printf("The no is Even");
        }else{
            printf("The no is Odd");
        }
    }
    else if(x==2){
        int no,flag=0;
        printf("Enter the no: ");
        scanf("%d",&no);
        for(int i=2;i<=no/2;i++){
            if(no%i==0){
                flag=1;
                break;
            }
        }
        if(flag==0){
            printf("The no is Prime");
        }else{
            printf("The no is not Prime");
        }
    }
    else if (x==3) {
        int a;
        printf("Enter a number: ");
        scanf("%d",&a);
        int t1,t2=0,t3=a;
        while(a!=0) {
        t1= a%10;
        t2=t2*10+t1;
        a=a/10;   
        }
        if(t2==t3){
        printf("\nThe number is a palindrome");
        } else {
        printf("The number is not a palindrome");
    }
    }else if(x==4){
        int n;
        printf("Enter a NO : ");
        scanf("%d",&n);
            if(n<0){
                printf("NO is Negative");
            }else if(n>0){
                printf("No is Positive");
            }else{
                printf("No is 0");
            }
    }
    else if(x==5){
        int n;
        printf("Enter a NO : ");
        scanf("%d",&n);

        int t1,t2=0,t3=n;
        while(n!=0) {
        t1= n%10;
        t2=t2*10+t1;
        n=n/10;    
        }
        printf("%d is Reversed no",t2);
    }
    else if(x==6){
        int n,no;
        printf("Enter a NO : ");
        scanf("%d",&n);
        int sum=0;
       while(n!=0){
         no=n%10;
        sum=no+sum;
        n=n/10;
       }
        printf("The Sum of Digits = %d",sum);
        }}
