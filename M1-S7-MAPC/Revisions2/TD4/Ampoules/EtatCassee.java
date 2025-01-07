public class EtatCassee implements EtatAmpoule{
    
    public EtatCassee(){
        System.out.println("L'ampoule s'est casée !");
    }

    @Override
    public EtatAmpoule allumer() throws EtatIllegal {
        throw new EtatIllegal("L'ampoule est cassée");
    }

    @Override
    public EtatAmpoule eteindre() throws EtatIllegal {
        throw new EtatIllegal("L'ampoule est déjà éteinte");
    }
}
