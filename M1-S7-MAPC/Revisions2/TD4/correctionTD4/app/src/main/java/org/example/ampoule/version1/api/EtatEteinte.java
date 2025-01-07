package org.example.ampoule.version1.api;

public class EtatEteinte implements EtatAmpoule {

    @Override
    public EtatAmpoule allumer() throws ActionIllegale {
        System.out.println("l'ampoule s'allume");
        return new EtatAllumee();
    }

    @Override
    public EtatAmpoule eteindre() throws ActionIllegale {
        throw new ActionIllegale("ampoule déjà éteinte");
    }
    
}
