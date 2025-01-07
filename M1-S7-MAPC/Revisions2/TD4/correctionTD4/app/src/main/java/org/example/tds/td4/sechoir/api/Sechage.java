package org.example.tds.td4.sechoir.api;

public class Sechage implements EtatSechoir {

    private Sechage() {}

    private static final EtatSechoir instance = new Sechage();

    public static EtatSechoir instance() { return instance; }

    @Override
    public EtatSechoir fermer() throws ActionIllegale {
        throw new ActionIllegale("DEJA FERME");
    }
    
    @Override
    public EtatSechoir ouvrir() throws ActionIllegale {
        return Ouvert.instance();
    }
    
    @Override
    public EtatSechoir payer() throws ActionIllegale {
        throw new ActionIllegale("DEJA PAYE");
    }

    @Override
    public EtatSechoir secher() throws ActionIllegale {
        return this;
    }

    @Override 
    public EtatSechoir timeout() throws ActionIllegale {
        return Ferme.instance();
    }

}
