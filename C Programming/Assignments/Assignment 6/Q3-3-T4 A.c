int range(int,int);
void main()
{
    int start=1,end=5;
    int tot= range(start,end);
    printf("%d is the sum of no from start to end",tot);
}
int range(int start,int end)
{
    int s=0;
    for (;start<=end;start++){
        s=s+start;   
    }
    return s;
}