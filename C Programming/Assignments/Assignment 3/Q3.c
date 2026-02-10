//Sum of numbers in given range.
//Find sum of numbers from start to end.
//Input: start = 1, end = 5
//Output: 15//
void main(){
    int start=1,end=5;
    int tot=0;

    while(start<=end){
    
        tot=start+tot;
        start++;
    }
    printf("%d",tot);
    


    }
