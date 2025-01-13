package CorrectionJava;

public class ReineOuvriers {
    static int N = 10;
    static MyChar V = new MyChar();

    public static void main(String[] args) throws InterruptedException{
        Signal tabSignal[] = new Signal[N];
        Signal sigReine = new Signal();

        for (int i = 0; i < N; i++) {
            tabSignal[i] = new Signal();
        }

        // création et démarrage des ouvriers
        Ouvrier tabOuvrier[] = new Ouvrier[N];
        for (int i = 0; i < N; i++) {
            tabOuvrier[i] = new Ouvrier(tabSignal[i], V, sigReine);
            tabOuvrier[i].start();
        }
        java.util.Scanner entree = new java.util.Scanner(System.in);

        while(true){
            System.out.println("\nEntrez un caractère");
            String S = entree.next();
            V.set(S.charAt(0));
            for (int i = 0; i < N; i++) {
                tabSignal[i].sendSig();
            }

            System.out.println("\nSignaux envoyés");
            Thread.sleep(100);
            for (int i = 0; i < N; i++) {
                sigReine.waitSig();
            }
            if (V.get() == '0') {
                break;
            }
        }
        entree.close();

        System.out.println("\nAttente des ouvriers");
        for (int i = 0; i < N; i++) {
            tabOuvrier[i].join();
        }
    }
}
