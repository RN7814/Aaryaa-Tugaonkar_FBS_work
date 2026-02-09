void main(){
    int no1=250,no2=456,no3=678;
    if(no1>no2){
        if(no1>no3){
            printf("%d is greatest no",no1);
        }
    }else if(no1<no2){
        if(no1<no3){
            printf("%d is greatest no",no2);
        }else{
                        printf("%d is greatest no",no3);

        }
    }
}