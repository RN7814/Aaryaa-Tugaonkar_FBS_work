void num();
void main()
{
    num();

}

void num(){
    int n;
    printf("Enter a Age of Voter :");
    scanf("%d",&n);
    if(n>18){
    printf("Eligibile");
    }else if(n<=18){
        printf("Not eligible");
    }

}