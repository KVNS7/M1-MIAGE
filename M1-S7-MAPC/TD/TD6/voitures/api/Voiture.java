package code.voitures.api;

import code.voitures.api.moteurs.Moteur;
import code.voitures.api.batteries.Batterie;
import code.voitures.api.batteries.Puissance;
import code.voitures.api.equipements.EquipementNumerique;
import code.voitures.api.equipements.LecteurCD;
import code.voitures.api.equipements.LecteurDVD;
import io.vavr.collection.List;

public class Voiture {
    private Moteur moteur;
    private Batterie batterie;
    private List<EquipementNumerique> equipements;

    public static class VoitureBuilder {

        private Moteur moteur;
        private Batterie batterie;
        private List<EquipementNumerique> equipements;

        //#green parties obligatoires
        public VoitureBuilder(Moteur moteur, Batterie batterie) {
            this.moteur = moteur;
            this.batterie = batterie;
            this.equipements = List.empty();
        }
        //#

        // BERK pas optimal si les équipements évoluent

        //#blue parties facultatives
        public VoitureBuilder avecCD() {
            if (equipements.length() == 0 || batterie.puissance() != Puissance.LOW) {
                if (!equipements.exists(LecteurCD.class::isInstance)) // BERK
                    this.equipements = equipements.append(new LecteurCD());
            }
            return this;
        }

        public VoitureBuilder avecDVD() {
            if (equipements.length() == 0 || batterie.puissance() != Puissance.LOW) {
                if (!equipements.exists(LecteurDVD.class::isInstance)) // BERK
                    this.equipements = equipements.append(new LecteurDVD());
            }
            return this;
        }
        //#

        //#red creation de l'objet
        public Voiture commander() {
            return new Voiture(moteur, batterie, equipements);
        }
        //#
    }

    private Voiture(Moteur moteur, Batterie batterie, List<EquipementNumerique> equipements) {
        this.moteur = moteur;
        this.batterie = batterie;
        this.equipements = List.ofAll(equipements);
    }

    @Override
    public String toString() {
        return String.format("Voiture\nmoteur: %s\nbatterie: %s\nequipements: %s",
            this.moteur,
            this.batterie,
            this.equipements
        );
    }
}
