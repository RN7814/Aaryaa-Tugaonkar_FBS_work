#include <stdio.h>
int main() {
    int arr[] = {10, 12, 14, 158, 147, 168, 15, 52};
    int size = sizeof(arr) / sizeof(int);
    int sum = 0;

    for (int i = 0; i < size; i++)
        sum += arr[i];

    printf("Sum: %d\n", sum);
    return 0;
}
