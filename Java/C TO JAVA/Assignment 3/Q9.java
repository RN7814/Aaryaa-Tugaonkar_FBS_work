import java.util.Scanner;

class Q9 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int a = sc.nextInt();

        int t1, t2 = 0, t3 = a;

        while (a != 0) {
            t1 = a % 10;
            t2 = t2 * 10 + t1;
            a = a / 10;
        }

        if (t2 == t3) {
            System.out.println("The number is a palindrome");
        } else {
            System.out.println("The number is not a palindrome");
        }

        sc.close();
    }
}