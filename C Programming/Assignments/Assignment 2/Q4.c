void main (){
    int tma=32;
    if(tma>75&&tma<=100&&tma!=0){
        printf("Distinvtion");
    } else if(tma<65&&tma>75){
        printf("Firstclass");
    } else if(tma<55&&tma>65){
        printf("Second Class");
    }else if(tma<=40&&tma>55){
        printf("PassClass");
    }else if(tma<40){
        printf("fail");
    }else{
        printf("the Output is wromng");
    }
}