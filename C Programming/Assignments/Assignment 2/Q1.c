void main()
{
   int a=015,b=015;
   char c='-';
   int ans;
   if(a!=0&&b!=0)
   {
    if(c=='+')
    {
        ans=a+b;
        printf("%d is the Answer");
    }else if(c=='-')
    {
        ans=a-b;
        printf("%d is the Answer");

    }else if(c=='/')
    {
        ans=a/b;
            printf("%d is the Answer");
    }else if(c=='%')
    {
        ans=a%b;
        printf("%d is the Answer");

    }else if(c=='*')
    {
        ans=a*b;
        printf("%d is the Answer");

    }
    
   }else{
    printf("the Input is Wrong");
   }
}