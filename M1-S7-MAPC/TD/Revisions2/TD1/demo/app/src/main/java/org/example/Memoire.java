package org.example;

import java.util.List;

public class Memoire implements Document{

    private String titre;
    private List<Chapitre> chapitres;

    public Memoire(String titre, List<Chapitre> chapitres){
        this.titre = titre;
        this.chapitres = chapitres;
    }

    public String titre(){
        return this.titre;
    }

    public int taille(){
        int taille = 0;
        for (Chapitre chapitre : chapitres) {
            taille += chapitre.taille();
        }
        return taille;
    }

}