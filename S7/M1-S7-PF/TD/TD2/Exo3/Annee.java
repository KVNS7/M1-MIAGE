package Exo3;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Annee {
    private Set<UE> ues;
    private Set<Etudiant> etudiants;

    public Annee(Set<UE> ues) {
        this.ues = ues;
        this.etudiants = new HashSet<>();
    }

    public Set<UE> ues() {
        return ues;
    }

    public Set<Etudiant> etudiants() {
        return etudiants;
    }

    void inscrire(Etudiant e) {
        etudiants.add(e);
    }


    public void afficheSi(String entete, Predicate<Etudiant> condition) {
        System.out.println(entete);

        for (Etudiant etudiant : etudiants) {
            if (condition.test(etudiant)) {
                System.out.println(etudiant);
            }
        }
    }

    public void afficheSi(String entete, Predicate<Etudiant> condition, Consumer<Etudiant> consumer) {
        System.out.println(entete);
        
        etudiants.stream().filter(condition).forEach(consumer);
    }

    public void afficheSiv2(String header, Predicate<Etudiant> predicat, Function<Etudiant, String> representant) {
        System.out.println(header);
        for (Etudiant etudiant : etudiants) {
            if (predicat.test(etudiant)) {
                System.out.println(representant.apply(etudiant));
            }
        }
    }

}
