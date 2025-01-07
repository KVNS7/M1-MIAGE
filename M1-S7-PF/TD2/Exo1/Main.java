package Exo1;

import java.nio.channels.Pipe.SourceChannel;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        /* -------------------------------- Question 1 -------------------------------- */

        // Somme entiers
        Somme<Integer> sommeInt = (a, b) -> a + b;
        System.out.println("\nSomme d'entiers : " + sommeInt.somme(4, 7));

        // Somme doubles
        Somme<Double> sommeDouble = (a, b) -> a + b;
        System.out.println("\nSomme de doubles :" + sommeDouble.somme(4.32, 2.34));
        // Somme longs
        Somme<Long> sommeLong = (a, b) -> a + b;
        System.out.println("\nSomme de longs : " + sommeLong.somme(376L, 290L));

        // Somme string
        Somme<String> sommeStr = (a, b) -> a + b;
        System.out.println("\nSomme de String : " + sommeStr.somme("Bien", " joué !"));

        /* -------------------------------- Question 2 -------------------------------- */

        // ToString liste
        ToString<List<String>> l2s = list -> String.join(", ", list);
        List<String> liste = List.of("MacOS", "Windows", "Linux", "iOS", "Android");
        System.out.println("\nListe en String : " + l2s.toStr(liste));

        // ToString map
        ToString<Map<String, Integer>> m2s = map -> {
            return map.entrySet()
                      .stream()
                      .map(entry -> entry.getKey() + ": " + entry.getValue())
                      .collect(Collectors.joining(", "));
        };

        Map<String, Integer> map = Map.of("MacOs", 1, "Windows", 2, "Linux", 3, "iOS", 4, "Android", 5);
        System.out.println("\nMap en String : " + m2s.toStr(map));
        

        /* -------------------------------- Question 3 -------------------------------- */

        // VOIR Q3.md
    }
}
