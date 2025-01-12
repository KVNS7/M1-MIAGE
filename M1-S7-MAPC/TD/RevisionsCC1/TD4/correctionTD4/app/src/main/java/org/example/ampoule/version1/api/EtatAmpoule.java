package org.example.ampoule.version1.api;

public interface EtatAmpoule {
    EtatAmpoule allumer() throws ActionIllegale;
    EtatAmpoule eteindre() throws ActionIllegale;
}
