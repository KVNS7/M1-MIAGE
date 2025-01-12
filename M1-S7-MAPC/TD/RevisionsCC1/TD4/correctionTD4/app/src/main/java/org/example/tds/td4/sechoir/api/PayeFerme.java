package org.example.tds.td4.sechoir.api;

public class PayeFerme implements EtatSechoir {

    private PayeFerme() {}

    private static final EtatSechoir instance = new PayeFerme();

    public static EtatSechoir instance() { return instance; }

    @Override
    public EtatSechoir fermer() throws ActionIllegale {
        throw new ActionIllegale("DEJA FERME");
    }
    
    @Override
    public EtatSechoir ouvrir() throws ActionIllegale {
        return PayeOuvert.instance();
    }
    
    @Override
    public EtatSechoir payer() throws ActionIllegale {
        throw new ActionIllegale("DEJA PAYE");
    }

    @Override
    public EtatSechoir secher() throws ActionIllegale {
        return Sechage.instance();
    }

    @Override 
    public EtatSechoir timeout() throws ActionIllegale {
        throw new ActionIllegale("PAS EN MARCHE");
    }

}
