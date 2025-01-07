public class IncrementeDecremente {

    private static final int LOOPS = 100000;
    private static int x = 0;

    
    public static class IncrementeThread extends Thread {   // fonction d'incrémentation
        @Override
        public void run() {
            for (int i = 0; i < LOOPS; i++) {
                x++;
            }
        }
    }

    // Classe pour décrémenter
    public static class DecrementeThread extends Thread {   // fonction de décrémentation
        @Override
        public void run() {
            for (int i = 0; i < LOOPS; i++) {
                x--;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("\n\nDébut : x = " + x);

        IncrementeThread threadIncr = new IncrementeThread();   // crée le thread d'incrémentation
        DecrementeThread threadDecr = new DecrementeThread();   // crée le thread de décrémentation

        threadIncr.start();     // lance le thread d'incrémentation
        threadDecr.start();     // lance le thread de décrémentation

        threadIncr.join();  // attendre la fin des threads
        threadDecr.join();

        System.out.println("Final : x = " + x + "\n\n");
    }
}
