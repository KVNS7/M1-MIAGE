package org.example.tds.td4.sechoir.api;

public class PayeOuvert implements EtatSechoir {

    private PayeOuvert() {}

    private static final EtatSechoir instance = new PayeOuvert();

    public static EtatSechoir instance() { return instance; }

    @Override
    public EtatSechoir fermer() throws ActionIllegale {
        return PayeFerme.instance();
    }

    @Override
    public EtatSechoir ouvrir() throws ActionIllegale {
        throw new ActionIllegale("DEJA OUVERT");
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
        throw new ActionIllegale("PAS EN MARCHE");
    }

}
