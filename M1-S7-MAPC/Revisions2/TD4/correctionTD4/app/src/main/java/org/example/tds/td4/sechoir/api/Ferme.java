package org.example.tds.td4.sechoir.api;

public class Ferme implements EtatSechoir {

    private Ferme() {}

    private static final EtatSechoir instance = new Ferme();

    public static EtatSechoir instance() { return instance; }

    @Override
    public EtatSechoir fermer() throws ActionIllegale {
        throw new ActionIllegale("DEJA OUVERT");
    }
    
    @Override
    public EtatSechoir ouvrir() throws ActionIllegale {
        return Ouvert.instance();
    }

    @Override
    public EtatSechoir payer() throws ActionIllegale {
        return PayeFerme.instance();
    }

    @Override
    public EtatSechoir secher() throws ActionIllegale {
        return this;
    }

    @Override 
    public EtatSechoir timeout() throws ActionIllegale {
        throw new ActionIllegale("PAS EN MARCHE");
    }

}
