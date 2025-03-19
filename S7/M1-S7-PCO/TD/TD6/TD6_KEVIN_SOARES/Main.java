package TD6_KEVIN_SOARES;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) { 
            
            Buffer1 buffer1 = new Buffer1();
            Buffer1 buffer2 = new Buffer1();
            Buffer1 buffer3 = new Buffer1();
            Buffer1 buffer4 = new Buffer1();
            Buffer1 buffer5 = new Buffer1();
            Buffer1 buffer6 = new Buffer1();
            
            Thread filtre1 = new Filtre(buffer1, buffer2, ".*[êéèêë].*", 'e');
            Thread filtre2 = new Filtre(buffer2, buffer3, ".*[ôòö].*", 'o');
            Thread filtre3 = new Filtre(buffer3, buffer4, ".*[îï].*", 'i');
            Thread filtre4 = new Filtre(buffer4, buffer5, ".*[àâ].*", 'a');
            Thread filtre5 = new Filtre(buffer5, buffer6, ".*[àâêéèêëîïôòö].*", ' ');

            filtre2.start();
            filtre1.start();
            filtre3.start();
            filtre4.start();
            filtre5.start();

            System.out.print("\n\nEntrez une chaîne de caractères : \n");
            String input = scanner.nextLine();
            
            try {
                for (char c : input.toCharArray()) {
                    buffer1.write(c);
                }
            
                buffer1.write('\0');
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Thread filtre6 = new Thread(() -> {
                try {
                    char c;
                    while ((c = buffer6.read()) != '\0') {
                        System.out.print(c);
                    }
                    System.out.println();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            filtre6.start();
            
            try {    
                Thread.sleep(100); // laisse aux threads le temps de finir avant de demander une nouvelle entrée
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
