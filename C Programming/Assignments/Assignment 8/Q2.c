#include <stdio.h>
int main() {
    int arr[] = {10, 25, 38, 47, 56, 63, 72, 84, 91, 100};
    int size = sizeof(arr) / sizeof(int);
    int target, found = 0;

    printf("Enter number to search: ");
    scanf("%d", &target);

    for (int i = 0; i < size; i++) {
        if (arr[i] == target) {
            printf("%d found at index %d\n", target, i);
            found = 1;
            break;
        }
    }

    if (!found) printf("%d not found in array\n", target);
    return 0;
}
