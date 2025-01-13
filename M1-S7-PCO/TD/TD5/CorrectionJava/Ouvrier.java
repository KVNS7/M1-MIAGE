package CorrectionJava;

public class Ouvrier extends Thread{
    Signal sig, sigReine;
    MyChar c = new MyChar();

    public Ouvrier(Signal sig, MyChar c, Signal sigReine){
        this.sig = sig;
        this.c = c;
        this.sigReine = sigReine;
    }

    public void run(){
        while(true){
            sig.waitSig();
            System.out.print(c.get());
            sigReine.sendSig();
            if(c.get() == '0') break;
        }
    }
}
