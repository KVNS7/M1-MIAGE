package org.example.tds.td4.ampoule.version2.client;

import org.example.tds.td4.ampoule.version2.api.Ampoule;

import java.util.function.Consumer;

import io.vavr.control.Option;

public class App {

    private static final Consumer<String> afficheur = System.out::println;

    public static void main(String[] args) {
        final int LUMEN = 2500;
        Ampoule a;
        Option<Integer> lumens;

        // cas qui va bien
        a = new Ampoule(afficheur, LUMEN);
        lumens = a.allumer();
        System.out.println(lumens + " lumens");
        a.eteindre();

        // cas qui pose problème 1
        a = new Ampoule(afficheur, LUMEN);
        lumens = a.allumer();
        System.out.println(lumens + " lumens");
        a.allumer();

        // cas qui pose problème 2
        a = new Ampoule(afficheur, LUMEN);
        lumens = a.allumer();
        System.out.println(lumens + " lumens");
        a.eteindre();
        a.eteindre();

        // cas qui casse
        a = new Ampoule(afficheur, LUMEN);
        a.allumer(); // 0 -> 1
        a.eteindre();
        a.allumer(); // 1 -> 2
        a.eteindre();
        a.allumer(); // 2 -> 3
        a.eteindre();
        a.allumer(); // casse
        a.eteindre();
    }
}
