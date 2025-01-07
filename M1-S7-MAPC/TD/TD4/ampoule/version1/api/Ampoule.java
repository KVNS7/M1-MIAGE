package org.example.ampoule.version1.api;

public class Ampoule {
    private EtatAmpoule e;

    public Ampoule() {
        this.e = new EtatEteinte(0);
    }

    public void allumer() throws ActionIllegale {
        this.e = this.e.allumer();
    }

    public void eteindre() throws ActionIllegale {
        this.e = this.e.eteindre();
    }
}
