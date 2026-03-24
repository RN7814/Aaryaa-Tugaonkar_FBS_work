class Q1
{
	public static void main(String args[])
{
   int a=100,b=15;
   char c='+';
   int ans;
   if(a!=0&&b!=0)
   {
    if(c=='+')
    {
        ans=a+b;
        System.out.printf("%d is the Answer",ans);
    }else if(c=='-')
    {
        ans=a-b;
        System.out.printf("%d is the Answer",ans);

    }else if(c=='/')
    {
        ans=a/b;
            System.out.printf("%d is the Answer",ans);
    }else if(c=='%')
    {
        ans=a%b;
        System.out.printf("%d is the Answer",ans);

    }else if(c=='*')
    {
        ans=a*b;
        System.out.printf("%d is the Answer",ans);

    }
    
   }else{
    System.out.printf("the Input is Wrong");
   }
}
}