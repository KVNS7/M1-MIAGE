import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import TD3.paires.*;
import TD3.universite.*;

public class AppTd3 {
    public static void main(String[] args) {
        Matiere m1 = new Matiere("MAT1");
        Matiere m2 = new Matiere("MAT2");
        UE ue1 = new UE("UE1", Map.of(m1, 2, m2, 2));
        Matiere m3 = new Matiere("MAT3");
        UE ue2 = new UE("UE2", Map.of(m3, 1));
        Annee a1 = new Annee(Set.of(ue1, ue2));
        Etudiant e1 = new Etudiant("39001", "Alice", "Merveille", a1);
        e1.noter(m1, 12.0);
        e1.noter(m2, 14.0);
        e1.noter(m3, 10.0);
        System.out.println(e1);
        Etudiant e2 = new Etudiant("39002", "Bob", "Eponge", a1);
        e2.noter(m1, 4.0);
        e2.noter(m3, 4.0);
        Etudiant e3 = new Etudiant("39003", "Charles", "Chaplin", a1);
        e3.noter(m1, 18.0);
        e3.noter(m2, 5.0);
        e3.noter(m3, 14.0);


        Predicate<Etudiant> aDEF = etudiant -> {
            for (UE ue : etudiant.annee().ues()) {
                for (Matiere matiere : ue.ects().keySet()) {
                    if (!etudiant.notes().containsKey(matiere)) {
                        return true; 
                    }
                }
            }
            return false; 
        };

        Predicate<Etudiant> aNoteEliminatoire = etudiant -> {
            for (UE ue : etudiant.annee().ues()){
                for (Matiere matiere : ue.ects().keySet()){
                    if (etudiant.notes().containsKey(matiere) && etudiant.notes().get(matiere) < 6){
                        return true;
                    }
                }
            }
            return false;
        };

        Function<Etudiant,Double> moyenne = etudiant -> {
            if (aDEF.test(etudiant)) {
                return null; 
            }
        
            double somme = 0.0;
            int count = 0;
        
            for (UE ue : etudiant.annee().ues()) {
                for (Matiere matiere : ue.ects().keySet()) {
                    if (etudiant.notes().containsKey(matiere)) {
                        somme += etudiant.notes().get(matiere);
                        count++;
                    }
                }
            }
        
            if (count == 0) {
                return null;
            }
        
            return somme / count;
        };

        Predicate<Etudiant> naPasLaMoyennev1 = etudiant -> {
            if(aDEF.test(etudiant)){
                return false;
            }
            Optional<Double> moyenneEtudiant = Optional.ofNullable(moyenne.apply(etudiant));

            if (moyenneEtudiant.isEmpty()) {
                return false; 
            }

            return moyenneEtudiant.get() < 10;
        };

        Predicate<Etudiant> naPasLaMoyennev2 = etudiant -> {
            if(aDEF.test(etudiant))
                return true;
            Optional<Double> moyenneEtudiant = Optional.ofNullable(moyenne.apply(etudiant));

            if (moyenneEtudiant.isEmpty()) {
                return false; 
            }

            return moyenneEtudiant.get() < 10;
            
        };

        Predicate<Etudiant> session2v1 = etudiant -> 
        naPasLaMoyennev1.test(etudiant) || naPasLaMoyennev2.test(etudiant);


        // matières d'une année
        Function<Annee, Stream<Matiere>> matieresA = annee ->
            annee.ues().stream()
                .flatMap(ue -> ue.ects().keySet().stream());

        // matières d'un étudiant
        Function<Etudiant, Stream<Matiere>> matieresE = etudiant ->
            matieresA.apply(etudiant.annee());

        // matières coefficientées d'un étudiant (version Entry)
        Function<Etudiant, Stream<Map.Entry<Matiere, Integer>>> matieresCoefE_ = etudiant ->
            etudiant.annee().ues().stream()
                .flatMap(ue -> ue.ects().entrySet().stream());

        // transformation d'une Entry en une Paire
        Function<Map.Entry<Matiere, Integer>, Paire<Matiere, Integer>> entry2paire = entry ->
            new Paire<>(entry.getKey(), entry.getValue());

        // matières coefficientées d'un étudiant (version Paire)
        Function<Etudiant, Stream<Paire<Matiere, Integer>>> matieresCoefE = etudiant ->
            matieresCoefE_.apply(etudiant).map(entry2paire);

        // accumulateur pour calcul de la moyenne
        BinaryOperator<Paire<Double, Integer>> accumulateurMoyenne =
            (acc, pair) -> new Paire<>(acc.fst() + pair.fst() * pair.snd(),
                                      acc.snd() + pair.snd());

        // zero (valeur initiale pour l'accumulateur)
        Paire<Double, Integer> zero = new Paire<>(0.0, 0);

        // obtention de la liste de (note, coef) pour les matières d'un étudiant
        Function<Etudiant, List<Paire<Double, Integer>>> notesPonderees = etudiant ->
            matieresCoefE.apply(etudiant)
                .map(matiereCoef -> new Paire<>(etudiant.notes().getOrDefault(matiereCoef.fst(), null), matiereCoef.snd()))
                .collect(Collectors.toList());

        // obtention de la liste de (note, coef) pour les matières d'un étudiant (version indicative)
        Function<Etudiant, List<Paire<Double, Integer>>> notesPondereesIndicatives = etudiant ->
            matieresCoefE.apply(etudiant)
                .map(matiereCoef -> new Paire<>(etudiant.notes().getOrDefault(matiereCoef.fst(), 0.0), matiereCoef.snd()))
                .collect(Collectors.toList());

        // replie avec l'accumulateur spécifique
        Function<List<Paire<Double, Integer>>, Paire<Double, Integer>> reduit = list ->
            list.stream().reduce(zero, accumulateurMoyenne);

        // calcule la moyenne à partir d'un couple (somme pondérée, somme coefs)
        Function<Paire<Double, Integer>, Double> divise = paire ->
            paire.snd() == 0 ? null : paire.fst() / paire.snd();

        // calcul de moyenne fonctionnel
        Function<Etudiant, Double> computeMoyenne = etudiant -> {
            if (aDEF.test(etudiant)) {
                return null; // Si l'étudiant est défaillant
            }
            return divise.apply(reduit.apply(notesPonderees.apply(etudiant)));
        };

        // calcul de moyenne fonctionnel (indicative)
        Function<Etudiant, Double> computeMoyenneIndicative = etudiant ->
            divise.apply(reduit.apply(notesPondereesIndicatives.apply(etudiant)));

        // calcul de moyenne indicative
        Function<Etudiant, Double> moyenneIndicative = computeMoyenneIndicative;

    }
}
