class Q6
{


public static void main(String args[]) {
    int a = 30;

    if (a % 3 == 0 && a % 5 == 0) {
        System.out.printf("The number is divisible by both");
    }
    else if (a % 3 == 0) {
        System.out.printf("The number is divisible by 3");
    }
    else if (a % 5 == 0) {
        System.out.printf("The number is divisible by 5");
    }
    else {
        System.out.printf("The number is not divisible by either");
    }

   
}


}