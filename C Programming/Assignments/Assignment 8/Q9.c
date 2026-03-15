#include <stdio.h>
int main() {
    int arr[] = {4, 10, 520, 14, 283, 246, 85, 55};
    int size = sizeof(arr) / sizeof(int);
    int rev[size];

    for (int i = 0; i < size; i++)
        rev[i] = arr[size - 1 - i];

    for (int i = 0; i < size; i++)
        printf("%d\n", rev[i]);

    return 0;
}
