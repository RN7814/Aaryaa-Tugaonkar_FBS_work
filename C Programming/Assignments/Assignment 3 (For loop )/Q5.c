//armstrong
void main() {
    int no=;
    int tno=no,q=0;
    for(int t1;tno>0;tno=tno/10){
        t1=tno%10;
        t1=t1*t1*t1;
        q=q+t1;
    }
    if(q==no){
        printf("The no is armstrong\n");
    }else
    {
        printf("not armstrong");
    }
}