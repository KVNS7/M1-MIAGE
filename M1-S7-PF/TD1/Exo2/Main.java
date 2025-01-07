package Exo2;
public class Main {
    public static void main(String[] args){

        // Exercice 2 :

        Paire<Integer, String> paire1 = new Paire<>(1, "un");

        System.out.println(paire1);

        Paire<Double, String> paire2 = new Paire<>(1.0, "un");

        System.out.println(paire2);

        Paire<Double, Paire<Integer, String>> paire3 = new Paire<>(1.0, paire1);

        System.out.println(paire3);

    }
}
