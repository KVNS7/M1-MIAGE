package code.voitures.api.batteries;

public class Batterie12V implements Batterie {

    @Override
    public Puissance puissance() {
        return Puissance.LOW;
    }
    
}
