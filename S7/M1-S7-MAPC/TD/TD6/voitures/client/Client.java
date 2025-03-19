package code.voitures.client;

import code.voitures.api.Voiture;
import code.voitures.api.batteries.Batterie;
import code.voitures.api.batteries.Batterie12V;
import code.voitures.api.batteries.Batterie24V;
import code.voitures.api.moteurs.Moteur;
import code.voitures.api.moteurs.MoteurSP95;

public class Client {
    public static void main(String[] args) {
        Moteur m = new MoteurSP95();
        Batterie b = new Batterie24V();
        Voiture v = (new Voiture.VoitureBuilder(m, b))
                        .avecCD()
                        .avecCD()
                        .avecDVD()
                        .avecDVD()
                        .commander();
        System.out.println(v);
    }
}
