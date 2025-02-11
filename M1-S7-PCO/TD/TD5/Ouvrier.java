

import VersionJavaTD5.ReineOuvriers;
import VersionJavaTD5.Signal;

public class Ouvrier implements Runnable {
    private final int id;
    private final Signal signal;
    
    public Ouvrier(int id, Signal signal) {
        this.id = id;
        this.signal = signal;
    }

    @Override
    public void run() {
        try {
            System.out.println("La tâche " + id + " démarre.");
            while (true) {
                signal.attendre(id);
                if (ReineOuvriers.val == '0') {
                    break;
                }
                System.out.print(ReineOuvriers.val);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
