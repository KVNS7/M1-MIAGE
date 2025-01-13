package CorrectionJava;
public class Signal {
    private int present = 0;

    public synchronized void sendSig(){
        present++;
        notify();
    }

    public synchronized void waitSig(){
        try{
            while (present==0){
                wait();
            } 
        }catch(Exception e){}

        present--;
        notify();
    }
}
