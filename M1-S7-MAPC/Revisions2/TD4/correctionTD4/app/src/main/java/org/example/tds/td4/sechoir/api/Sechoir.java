package org.example.tds.td4.sechoir.api;

public class Sechoir {
    private EtatSechoir etat;

    public Sechoir() {
        etat = Ouvert.instance();
    }

    public void ouvrir() throws ActionIllegale {
        etat = etat.ouvrir();
    }

    public void fermer() throws ActionIllegale {
        etat = etat.fermer();
    }

    public void payer() throws ActionIllegale {
        etat = etat.payer();
    }

    public void secher() throws ActionIllegale {
        etat = etat.secher();
    }

    public void timeout() throws ActionIllegale {
        etat = etat.timeout();
    }
}