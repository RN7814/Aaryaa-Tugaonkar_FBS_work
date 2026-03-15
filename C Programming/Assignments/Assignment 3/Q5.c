//armstrong
void main(){
    int no=123;
    int tno=no;
    int t1,q=0;
    while(no>0){
        t1=no%10;
        no=no/10;
        t1=t1*t1*t1;
        q=q+t1;
       
    }
    if(q==tno){
        printf("The no is armstrong\n");
      
    }else{
        printf("not armstrong");
    }
}