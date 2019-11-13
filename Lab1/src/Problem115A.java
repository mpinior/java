import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Problem115A {
    public static void main(String [] args){
        System.out.print("Liczba pracownikow:");
        Scanner word = new Scanner(System.in);
        int number = word.nextInt();
        int [] workers = new int[number];
        System.out.println("Kto kim rzadzi:");
        for (int i=0; i<number; i++){
            System.out.print(i+1);
            Scanner scan = new Scanner(System.in);
            workers[i] = scan.nextInt();
        }

//wypisanie pracowników
//        for (int i=0; i<number; i++){
//            System.out.print(workers[i] +", ");
//        }

    }
}

//COŚ CO DLA MNIE JEST LOGICZNE, ALE JAVA MA PROBLEM EGZYSTENCJONALNY.
//        ArrayList<ArrayList<Integer>> groups = new ArrayList<ArrayList<Integer>>();
//        for (int i=0; i<number; i++){
//            for (int j=i+1; j<number; j++){
//                for (int k=j+1; k<number; k++){
//                    if (workers[i] == -1){
//                        if (!groups.contains(group1)){
//                            ArrayList<Integer> group1 = new ArrayList<Integer>();
//                            group1.add(workers[i]);
//                        }
//                        else{
//                            group1.add(workers[i]);
//                        }
//
//                    }
//                    if (workers[i] == k || workers[i] == j){
//                        if(!groups.contains(group2)){
//                            ArrayList<Integer> group2 = new ArrayList<Integer>();
//                            group2.add(workers[i]);
//                        }
//                        else{
//                            group2.add(workers[k])
//                        }
//                    }
//                }
//
//            }
//        }
