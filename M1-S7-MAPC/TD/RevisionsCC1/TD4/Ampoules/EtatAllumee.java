public class EtatAllumee implements EtatAmpoule {
    
    public int usages;

    public EtatAllumee(int uses){
        this.usages = uses;
    }

    @Override
    public EtatAmpoule allumer() throws EtatIllegal {
        throw new EtatIllegal("Ampoule deja allumée");
    }

    @Override
    public EtatAmpoule eteindre() {
        System.out.println("L'ampoule s'allume");
        return new EtatAllumee(usages);
    }
}
