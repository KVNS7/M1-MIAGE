public class TD5_Reine_Ouvriers {

    static int N = 10;
    public static myChar V = new myChar();

    public static void main(String[] args) throws InterruptedException{
        // création des signaux
        signal tabSignal[] = new signal[N];
        signal sigReine = new signal();

        for (int i = 0; i < N; i++) {
            tabSignal[i] = new signal();
        }
        // création et démarrage des ouvriers
        ouvrier tabOuvrier[] = new ouvrier[N];
        for (int i = 0; i < N; i++) {
            tabOuvrier[i] = new ouvrier(tabSignal[i], V, sigReine);
            tabOuvrier[i].start();
        }
        java.util.Scanner entree = new java.util.Scanner(System.in);

        while (true) {
            System.out.print("\nEntrez un charactere :");
            String S = entree.next();
            V.set(S.charAt(0));
            for (int i = 0; i < N; i++) {
                tabSignal[i].sendSig();
            }
            System.out.println("Signaux envoyés");
            Thread.sleep(100);
            for (int i = 0; i < N; i++) {
                sigReine.waitSig();
            }
            if (V.get() == '0'){
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