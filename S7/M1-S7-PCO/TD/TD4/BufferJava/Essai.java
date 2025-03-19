package BufferJava;
// import java.util.logging.ConsoleHandler;

public class Essai{
    public static void main(String[] args) throws InterruptedException{
        Buffer b = new Buffer(10);

        Consommateur TConso = new Consommateur(b);
        Producteur TProduct = new Producteur(b);

        TConso.start();
        TProduct.start();

        TConso.join();
        TProduct.join();
    }
}