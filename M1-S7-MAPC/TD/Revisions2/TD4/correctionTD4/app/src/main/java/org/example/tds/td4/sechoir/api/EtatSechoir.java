package org.example.tds.td4.sechoir.api;

public interface EtatSechoir {
    EtatSechoir ouvrir() throws ActionIllegale;
    EtatSechoir fermer() throws ActionIllegale;
    EtatSechoir payer() throws ActionIllegale;
    EtatSechoir secher() throws ActionIllegale;
    EtatSechoir timeout() throws ActionIllegale;
}