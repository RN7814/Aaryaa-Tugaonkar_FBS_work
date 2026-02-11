void main() {
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
        printf("Prime");
    } else {
        printf("Not Prime");
    }

}
