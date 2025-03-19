public class ouvrier extends Thread {
    signal sig, sigReine;
    myChar c = new myChar();

    public ouvrier(signal sig, myChar c, signal sigReine) {
        this.sig = sig;
        this.c = c;
        this.sigReine = sigReine;
    }

    public void run() {
        while (true) {
            sig.waitSig();
            System.out.print(c.get());
            sigReine.sendSig();
            if (c.get() == '0')
                break;
        }
    }
}