class Q4{
public static void main(String args[]) {
    int a = 7;
    int i = 2;
    int isPrime = 1;
    while (i < a) {
        if (a % i == 0) {
            isPrime = 0;
            break;
        }
        i++;
    }
    if (isPrime == 1) {
        System.out.printf("Prime");
    } else {
        System.out.printf("Not Prime");
    }

}

}