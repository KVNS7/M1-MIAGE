package org.example.tds.td4.sechoir.api;

public class Ouvert implements EtatSechoir {

    private Ouvert() {
    }

    private static final EtatSechoir instance = new Ouvert();

    public static EtatSechoir instance() {
        return instance;
    }

    @Override
    public EtatSechoir fermer() throws ActionIllegale {
        return Ferme.instance();
    }

    @Override
    public EtatSechoir ouvrir() throws ActionIllegale {
        throw new ActionIllegale("DEJA OUVERT");
    }

    @Override
    public EtatSechoir payer() throws ActionIllegale {
        return PayeOuvert.instance();
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