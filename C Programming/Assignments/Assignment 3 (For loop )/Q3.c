//Sum of numbers in given range.
//Find sum of numbers from start to end.
//Input: start = 1, end = 5
//Output: 15//

void main () {
    int tot=0,end=5;
    int start=1;
    for (;start<=end;start++){
        tot=start+tot;
    }
    printf("%d",tot);
}
