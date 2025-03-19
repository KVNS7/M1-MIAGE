package org.example.ampoule.version1.api;

import java.nio.channels.AcceptPendingException;

public class EtatCassee implements EtatAmpoule {

    public EtatCassee(){
        System.out.println("! Ampoule cassée !");
    }

    @Override
    public EtatAmpoule allumer() throws ActionIllegale {
        throw new ActionIllegale("\nImpossible d'allumer : Ampoule cassée");
    }

    @Override
    public EtatAmpoule eteindre() throws ActionIllegale {
        throw new ActionIllegale("\nAmpoule déjà éteinte (cassée)");
    }

}
