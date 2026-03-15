#include <stdio.h>
int main() {
    int arr1[] = {46547, 165, 962, 48, 38, 32647, 328, 45, 30};
    int arr2[] = {545, 34654, 16547, 21, 84324, 896564, 51};
    int size1 = sizeof(arr1) / sizeof(int);
    int size2 = sizeof(arr2) / sizeof(int);
    int merged[size1 + size2];

    for (int i = 0; i < size1; i++)
        merged[i] = arr1[i];

    for (int i = 0; i < size2; i++)
        merged[size1 + i] = arr2[i];

    for (int i = 0; i < size1 + size2; i++)
        printf("%d\n", merged[i]);

    return 0;
}
