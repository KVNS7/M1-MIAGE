package VersionJavaTD5;

import java.util.Scanner;

public class ReineOuvriers {

    public static final int N = 10;
    public static char val;
    private static Signal signal;

    public static void main(String[] args) {
        Thread[] workers = new Thread[N];
        signal = new Signal(N);

        // Création des ouvriers
        for (int i = 0; i < N; i++) {
            workers[i] = new Thread(new Ouvrier(i, signal));
            workers[i].start();
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Entrer un caractère : ");
            val = scanner.next().charAt(0);

            // Envoi du signal à tous les ouvriers
            for (int i = 0; i < N; i++) {
                signal.envoyerSignal(i);
            }

            if (val == '0') {
                break;
            }

            // Attente que tous les ouvriers aient fini leur travail
            for (Thread worker : workers) {
                try {
                    worker.join(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            System.out.println();
        }

        System.out.println("Programme terminé.");
        scanner.close();
    }
}
