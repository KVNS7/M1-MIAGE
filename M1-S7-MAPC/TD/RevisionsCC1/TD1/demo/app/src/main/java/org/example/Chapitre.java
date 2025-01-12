package org.example;

import java.util.List;

public class Chapitre implements Document {
    private String titre;
    private List<String> pages;

    public Chapitre(String titre, List<String> pages) {
        this.titre = titre;
        this.pages = pages;
    }

    public String titre(){
        return this.titre;
    }

    public int taille(){
        return this.pages.size();
    }
}