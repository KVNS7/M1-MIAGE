package TD6_KEVIN_SOARES;
public class Filtre extends Thread {
    private Buffer1 buffGauche;
    private Buffer1 buffDroit;
    private String pattern;
    private char remplacement;

    public Filtre(Buffer1 buffEntree, Buffer1 buffSortie, String pattern, char remplacement) {
        this.buffGauche = buffEntree;
        this.buffDroit = buffSortie;
        this.pattern = pattern;
        this.remplacement = remplacement;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c = buffGauche.read(); 

                if (c == '\0') {
                    break; 
                }
                
                if (Character.toString(c).matches(pattern)) {
                    c = remplacement;
                }
                
                buffDroit.write(c);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
