void table(int);               //Type 2
void main()
{
    int n=5;
    table(n);
}
void table(int n)
{
    for (int i;i<=10;i++)
    {
    int t;
    t=n*i;
    printf("%d\n",t);
    }
}