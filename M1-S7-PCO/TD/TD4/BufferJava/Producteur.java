package BufferJava;

public class Producteur extends Thread {

    Buffer my_buffer;

    public Producteur(Buffer b) {
        this.my_buffer = b;
    }

    public void run() {
        try {
            for (int k = 0; k < 10; k++) {
                for (int i = 0; i < 10; i++) {
                    my_buffer.put((char) (i + '0'));
                    // sleep(40);
                }
                System.out.println("\n");
            }
        } catch (Exception e) {

        }
    }
}