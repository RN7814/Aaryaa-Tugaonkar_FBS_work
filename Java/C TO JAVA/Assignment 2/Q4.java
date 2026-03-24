class Q4
{
public static void main (String args[]){
    int tma=32;
    if(tma>75&&tma<=100&&tma!=0){
        System.out.printf("Distinvtion");
    } else if(tma<65&&tma>75){
        System.out.printf("Firstclass");
    } else if(tma<55&&tma>65){
        System.out.printf("Second Class");
    }else if(tma<=40&&tma>55){
        System.out.printf("PassClass");
    }else if(tma<40){
        System.out.printf("fail");
    }else{
        System.out.printf("the Output is wromng");
    }
}
}