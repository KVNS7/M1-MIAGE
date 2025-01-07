package Exo3.Arbres1;

import java.util.ArrayList;

public class Main {

    public static Arbre arbre0() {
        return new Noeud(new ArrayList<Arbre>());
    }

    public static void main(String[] args) {
        System.out.println(arbre0().taille());
    }
}
