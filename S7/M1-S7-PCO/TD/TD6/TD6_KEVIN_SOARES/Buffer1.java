package TD6_KEVIN_SOARES;
import java.util.concurrent.LinkedBlockingQueue;

public class Buffer1 {
    private LinkedBlockingQueue<Character> queue = new LinkedBlockingQueue<>();

    public void write(char c) throws InterruptedException {
        queue.put(c);
    }

    public char read() throws InterruptedException {
        return queue.take();
    }
}
