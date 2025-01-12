public class EtatEteinte implements EtatAmpoule {

    public int usages;

    public EtatEteinte(int uses) {
        this.usages = uses;
    }

    @Override
    public EtatAmpoule allumer() throws EtatIllegal {
        if (usages < 3) {
            System.out.println("L'ampoule s'allume");
            return new EtatAllumee(usages++);
        }
        return new EtatCassee();
    }

    @Override
    public EtatAmpoule eteindre() throws EtatIllegal {
        throw new EtatIllegal("L'ampoule est déjà éteinte");
    }
}
