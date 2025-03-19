public class JavaMulti {

    static int xMax, yMax = 1;
    static double max = 0;
    static int N = 40000;
    static final int NB_THREADS = 10;

    static double f(int x, int y) {
        return (x * x * x) * Math.cos((double) x) - (y * y * y) * Math.sin((double) y) - (x * x * x) / (y * y);
    }

    static class MaxTask extends Thread {
        int start, end;
        double localMax = 0;
        int localXMax = 1, localYMax = 1;

        public MaxTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int x = start; x <= end; x++) {
                for (int y = 1; y <= N; y++) {
                    double maxLoc = f(x, y);
                    if (maxLoc < 0) {
                        maxLoc = -maxLoc;
                    }
                    if (maxLoc > localMax) {
                        localMax = maxLoc;
                        localXMax = x;
                        localYMax = y;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.nanoTime();

        int range = N / NB_THREADS;

        MaxTask[] tasks = new MaxTask[NB_THREADS];

        for (int i = 0; i < NB_THREADS; i++) {
            int start = i * range + 1;
            int end = (i == NB_THREADS - 1) ? N : (i + 1) * range;
            tasks[i] = new MaxTask(start, end);
            tasks[i].start();
        }

        for (int i = 0; i < NB_THREADS; i++) {
            tasks[i].join();
        }

        synchronized (JavaMulti.class) {

            for (int i = 0; i < NB_THREADS; i++) {
                if (tasks[i].localMax > max) {
                    max = tasks[i].localMax;
                    xMax = tasks[i].localXMax;
                    yMax = tasks[i].localYMax;
                }
            }
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000000; // Temps d'exécution en secondes
        // Afficher le résultat
        System.out.println("xMax=" + xMax + " yMax=" + yMax + " max=" + max);
        System.out.println("Temps d'execution " + duration + " secondes");

    }
}