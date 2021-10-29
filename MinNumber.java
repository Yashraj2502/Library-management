import java.util.Scanner;

public class MinNumber {
    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");  
        float a;
        float b;

        Scanner in = new Scanner(System.in);
        System.out.println("Enter two numbers:-");
        a = in.nextFloat();
        b = in.nextFloat();

        if (a > b) {
            System.out.println(a + " is the bigger number.");
        }

        else if (a == b) {
            System.out.println("Both are equal.");
        }

        else{
            System.out.println(b + " is the bigger number.");
        }

        in.close();
    }
}
