public class IncrementeDecrementeSynchro {

    private static final int LOOPS = 100000;
    private static int x = 0;

    public static class IncrementeThread extends Thread { // Classe pour incrémenter
        @Override
        public void run() {
            for (int i = 0; i < LOOPS; i++) {
                synchronized (IncrementeDecrementeSynchro.class) {
                    x++;
                }
            }
        }
    }

    public static class DecrementeThread extends Thread { // Classe pour décrémenter
        @Override
        public void run() {
            for (int i = 0; i < LOOPS; i++) {
                synchronized (IncrementeDecrementeSynchro.class) {x--;}
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
