package BufferJava;

public class Buffer {

    public final int taille_max;
    public final char[] buffer;

    public int readIndex = 0;
    public int writeIndex = 0;
    public int count = 0;

    public Buffer(int n) {
        this.taille_max = n;
        this.buffer = new char[n];
    }

    public void put(char item) {
        synchronized (this) {
            while (count == taille_max) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            buffer[writeIndex] = item;
            writeIndex = (writeIndex + 1) % taille_max;
            count++;

            notifyAll();
        }
    }

    public char get() {
        synchronized (this) {
            while (count == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            char item = buffer[readIndex];
            readIndex = (readIndex + 1) % taille_max;
            count--;

            notifyAll();

            return item;
        }
    }

}
