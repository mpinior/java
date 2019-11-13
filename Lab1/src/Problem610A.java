import java.util.Scanner;

public class Problem610A {
    public static void main(String[] arg){
        System.out.print("Podaj dlugosc patyka:");
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        int polowa = number/2;
        int j=1;
        for (int i=polowa; i>polowa/2 + 1; i--){
            int opc = polowa-j;
            System.out.println("Opcja:"+opc+", "+opc+", "+j+", "+j+", ");
            j++;
        }
    }
}
