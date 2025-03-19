package org.example.tds.td4.ampoule.version1.api;

public class EtatEteinte implements EtatAmpoule {

    // ne peut pas être singleton

    private int usages;

    public EtatEteinte(int usages) {
        this.usages = usages;
    }

    @Override
    public EtatAmpoule allumer() throws ActionIllegale {
        if (usages < 3) {
            System.out.println("l'ampoule s'allume");
            return new EtatAllumee(usages + 1);
        } else {
            System.out.println("l'ampoule se casse");
            return new EtatCassee();
        
        }
    }

    @Override
    public EtatAmpoule eteindre() throws ActionIllegale {
        throw new ActionIllegale("ampoule déjà éteinte");
    }
    
}
