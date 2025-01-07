package org.example.tds.td4.ampoule.version1.api;

public class EtatCassee implements EtatAmpoule {

    // peut être singleton

    @Override
    public EtatAmpoule allumer() throws ActionIllegale {
        throw new ActionIllegale("ampoule cassée");
    }

    @Override
    public EtatAmpoule eteindre() throws ActionIllegale {
        throw new ActionIllegale("ampoule cassée");
    }
    
}
