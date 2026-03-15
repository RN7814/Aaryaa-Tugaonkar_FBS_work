#include <stdio.h>
int main() {
    int arr[] = {56, 45, 77, 16, 54, 10, 80, 74, 15, 84, 51, 84};
    int size = sizeof(arr) / sizeof(int);

    for (int i = 0; i < size; i += 2)
        printf("%d\n", arr[i]);

    return 0;
}
