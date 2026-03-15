#include <stdio.h>
int main() {
    int arr[] = {4, 5, 6, 8, 4, 2, 10};
    int size = sizeof(arr) / sizeof(int);
    int min = arr[0], max = arr[0];

    for (int i = 1; i < size; i++) {
        if (arr[i] < min) min = arr[i];
        if (arr[i] > max) max = arr[i];
    }

    printf("Minimum: %d\n", min);
    printf("Maximum: %d\n", max);
    return 0;
}
