void main (){
    int r;
    printf("Enter the from range 1 to :");
    scanf("%d",&r);
    
    for(int i=1;i<=r;i++){
    int no=i;
    int tno=no,t1,q=0;
       for(;tno>0;tno=tno/10){
        t1=tno%10;
     
        q=q+(t1*t1*t1);
    }
    if(q==no){
        printf("%d ",no);
    }
}
}