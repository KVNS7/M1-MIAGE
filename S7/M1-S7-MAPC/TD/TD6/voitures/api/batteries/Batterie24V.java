package code.voitures.api.batteries;

public class Batterie24V implements Batterie {

    @Override
    public Puissance puissance() {
        return Puissance.MEDIUM;
    }
    
}
