#include <stdio.h>
int main() {
    int arr[10];
    int size = sizeof(arr) / sizeof(int);

    for (int i = 0; i < size; i++) {
        printf("Enter number: ");
        scanf("%d", &arr[i]);
    }

    for (int j = 0; j < size; j++) {
        int n = arr[j];
        int flag = 1;

        if (n < 2) flag = 0;

        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) { flag = 0; break; }
        }

        if (flag) printf("%d is prime\n", n);
    }

    return 0;
}
