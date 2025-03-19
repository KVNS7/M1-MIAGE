package BufferJava;

public class Consommateur extends Thread {

    Buffer my_buffer;

    public Consommateur(Buffer b) {
        this.my_buffer = b;
    }

    public void run() {
        try {
            for (int i = 0; i < 100; i++) {

                char c = my_buffer.get();
                System.out.print(c);

                sleep(80);
            }
        } catch (Exception e) {

        }
    }
}
