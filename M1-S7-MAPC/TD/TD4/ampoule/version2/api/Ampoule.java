package org.example.ampoule.version2.api;

import java.util.function.Consumer;

import io.vavr.control.Option;

public class Ampoule {
    private EtatAmpoule e;
    private int lumen;
    private Consumer<String> informateur;

    public Ampoule(Consumer<String> informateur, int lumen) {
        this.lumen = lumen;
        this.informateur = informateur;
        this.e = EtatEteinte.INSTANCE;
    }

    // visibilité package
    void _allumer() {
        informateur.accept("l'ampoule s'allume");
    }

    // visibilité package
    void _eteindre() {
        informateur.accept("l'ampoule s'éteint");
    }

    // visibilité package
    int lumen() {
        return lumen;
    }

    // visibilité package
    void changeEtat(EtatAmpoule e) {
        this.e = e;
    }

    private Consumer<? super Throwable> informateurErreur = 
        exception -> informateur.accept(exception.getMessage());

    public Option<Integer> allumer() {
        return this.e.allumer(this).onFailure(informateurErreur).toOption();
    }

    public void eteindre() {
        this.e.eteindre(this).onFailure(informateurErreur);
    }
}
