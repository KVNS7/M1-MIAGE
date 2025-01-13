package Exo2;

import java.util.concurrent.Semaphore;

public class ReineTaches {

    // Nombre de tâches
    private static final int N = 10;
    private static Semaphore[] semaphores = new Semaphore[N];
    private static final char[] characters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

    public static void main(String[] args) {
        // Initialiser les sémaphores
        for (int i = 0; i < N; i++) {
            semaphores[i] = new Semaphore(0); // Toutes les tâches sont initialement bloquées
        }
        semaphores[0].release(); // Lancer la première tâche (T1 commence)

        // Créer et démarrer les threads pour chaque tâche
        for (int i = 0; i < N; i++) {
            final int id = i;
            new Thread(() -> {
                try {
                    while (true) {
                        semaphores[id].acquire();  // Attendre que ce sémaphore soit libéré
                        System.out.print(characters[id]);  // Afficher le caractère
                        // Passer le témoin à la tâche suivante
                        semaphores[(id + 1) % N].release();  // Libérer le sémaphore de la tâche suivante
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }
}
