
class Q6{

public static void main (String args[]){
    int n=28,i=1;
    int fno=0;
    while(i<n){
        if(n%i==0){
            fno=fno+i;
        }
        i++;
    }if(fno==n){
        System.out.printf("Perrfect");

    }else
        System.out.printf("not perfect");
}
}