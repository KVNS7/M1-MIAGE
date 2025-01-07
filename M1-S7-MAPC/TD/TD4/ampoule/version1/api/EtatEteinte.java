package org.example.ampoule.version1.api;

public class EtatEteinte implements EtatAmpoule {

    public int usages;

    public EtatEteinte(int usages){
        this.usages = usages;
    }

    @Override
    public EtatAmpoule allumer() throws ActionIllegale {
        if(usages < 3 ){
            usages++;
            System.out.println("\nL'ampoule s'allume -> usages : " + usages);
            return new EtatAllumee(usages);
        }else{
            return new EtatCassee();
        }
    }

    @Override
    public EtatAmpoule eteindre() throws ActionIllegale {
        throw new ActionIllegale("\nAmpoule déjà éteinte");
    }
    
}
