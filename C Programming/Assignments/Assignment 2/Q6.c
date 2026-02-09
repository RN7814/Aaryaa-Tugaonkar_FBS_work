

void main() {
    int a = 30;

    if (a % 3 == 0 && a % 5 == 0) {
        printf("The number is divisible by both");
    }
    else if (a % 3 == 0) {
        printf("The number is divisible by 3");
    }
    else if (a % 5 == 0) {
        printf("The number is divisible by 5");
    }
    else {
        printf("The number is not divisible by either");
    }

    return 0;
}
