#include <stdio.h>
int main() {
    int arr[] = {30, 25, 42, 68, 31, 58, 75, 77, 98, 18};
    int size = sizeof(arr) / sizeof(int);

    printf("Even numbers:\n");
    for (int i = 0; i < size; i++)
        if (arr[i] % 2 == 0) printf("%d\n", arr[i]);

    printf("Odd numbers:\n");
    for (int i = 0; i < size; i++)
        if (arr[i] % 2 != 0) printf("%d\n", arr[i]);

    return 0;
}
