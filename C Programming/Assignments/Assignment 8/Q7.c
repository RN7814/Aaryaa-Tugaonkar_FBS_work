#include <stdio.h>
int main() {
    int arr1[] = {1, 2, 3, 4, 5};
    int arr2[] = {10, 20, 30, 40, 50};
    int size = sizeof(arr1) / sizeof(int);
    int arr3[size];

    for (int i = 0; i < size; i++)
        arr3[i] = arr1[i] + arr2[i];

    for (int i = 0; i < size; i++)
        printf("%d\n", arr3[i]);

    return 0;
}
