package org.example.ampoule.version1.api;

public class EtatAllumee implements EtatAmpoule {

    @Override
    public EtatAmpoule allumer() throws ActionIllegale {
        throw new ActionIllegale("ampoule déjà allumée");
    }
    
    @Override
    public EtatAmpoule eteindre() throws ActionIllegale {
        System.out.println("l'ampoule s'éteint");
        return new EtatEteinte();
    }
    
}
