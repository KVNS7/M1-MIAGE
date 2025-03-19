import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ReineOuvriers {

    private static final int N = 10;
    private static char val;
    private static final Lock[] locks = new ReentrantLock[N];
    private static final Condition[] conditions = new Condition[N];

    public static void main(String[] args) {
        Thread[] workers = new Thread[N];
        for (int i = 0; i < N; i++) {
            locks[i] = new ReentrantLock();
            conditions[i] = locks[i].newCondition();
            workers[i] = new Thread(new Worker(i));
            workers[i].start();
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Entrer un caractère : ");
            val = scanner.next().charAt(0);

            for (int i = 0; i < N; i++) {
                locks[i].lock();
                try {
                    conditions[i].signal();
                } finally {
                    locks[i].unlock();
                }
            }

            if (val == '0') {
                break;
            }

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

    static class Worker implements Runnable {
        private final int id;

        Worker(int id) {
            this.id = id;
            // Ajouter le message indiquant que la tâche démarre
            System.out.println("La tâche " + id + " démarre.");
        }

        @Override
        public void run() {
            while (true) {
                locks[id].lock();
                try {
                    conditions[id].await();
                    if (val == '0') {
                        break;
                    }
                    System.out.print(val);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                } finally {
                    locks[id].unlock();
                }
            }
        }
    }
}
