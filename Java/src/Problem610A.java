import java.util.Scanner;

public class Problem610A {
    public static void main(String[] arg){
        System.out.println("Podaj dlugosc patyka:");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int polowa = n/2;
        int j=1;
        for (int i=polowa; i>polowa/2 +1; i--){
            int op1 = polowa-j;
            System.out.println("Ciecie:"+ op1 + "," + op1 +"," + j + "," + j);
            j++;
        }
    }
}
