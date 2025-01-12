package org.example.tds.td1.memoires;

import java.util.List;
import java.util.ArrayList;

public class Chapitre implements Document {

    private String titre;
    private List<String> pages;

    public Chapitre(String titre, List<String> pages) {
        this.pages = new ArrayList<>(pages); // recopie
    }

    @Override
    public String titre() {
        return this.titre;
    }
    
    @Override
    public int taille() {
        return this.pages.size();
    }
    
}
