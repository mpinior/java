import java.util.Scanner;

public class Fibo {

    public static void main(String [] args){
        System.out.print("Podaj liczbe od 1-45:");
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        int[] tab = new int[number];
        tab[0] = 1;
        tab[1] = 1;
        for (int i=2; i<number; i++){
            tab[i] = tab[i-1] + tab[i-2];
        }
        for (int j=0; j<number; j++){
            System.out.print(tab[j] + ",");
        }

    };
}
