public class Ampoule {
    private EtatAmpoule etat;

    public Ampoule(){
        this.etat = new EtatEteinte(0);
    }

    public void allumer() throws EtatIllegal{
        this.etat = this.etat.allumer();
    }

    public void eteindre() throws EtatIllegal{
        this.etat = this.etat.eteindre();
    }
}
