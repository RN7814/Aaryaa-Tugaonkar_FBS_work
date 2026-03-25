import java.util.Scanner;

class Q10 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int a = sc.nextInt();

        int fno, lno;

        lno = a % 10;   // last digit

        while (a > 9) {
            a = a / 10;
        }

        fno = a;   // first digit

        System.out.println("First digit: " + fno);
        System.out.println("Last digit: " + lno);
        System.out.println("Sum of First and Last digit: " + (fno + lno));

        sc.close();
    }
}
