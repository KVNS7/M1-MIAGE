package org.example.ampoule.version1.api;

public class EtatAllumee implements EtatAmpoule {

    public int usages;

    public EtatAllumee(int usages) {
        this.usages = usages;
    }

    @Override
    public EtatAmpoule allumer() throws ActionIllegale {
        throw new ActionIllegale("Ampoule déjà allumée");
    }

    @Override
    public EtatAmpoule eteindre() throws ActionIllegale {
        System.out.println("L'ampoule s'éteint");
        return new EtatEteinte(usages);
    }

}
