public class App {
    public static void main(String[] args) {
        // cas qui va bien
        Ampoule a = new Ampoule();
        try {
            a.allumer();
            a.eteindre();
        } catch (EtatIllegal e){
            System.out.println(e.getMessage());
        }
        // cas qui pose problème 1
        a = new Ampoule();
        try {
            a.allumer();
            a.allumer();
        } catch (EtatIllegal e){
            System.out.println(e.getMessage());
        }
        // cas qui pose problème 2
        a = new Ampoule();
        try {
            a.allumer();
            a.eteindre();
            a.eteindre();
        } catch (EtatIllegal e){
            System.out.println(e.getMessage());
        }

        a = new Ampoule();
        try{
            a.allumer();
            a.eteindre();

            a.allumer();
            a.eteindre();

            a.allumer();
            a.eteindre();

            a.allumer();
            a.allumer();
        }catch(EtatIllegal e){
            System.out.println(e.getMessage());
        }
    }
}
