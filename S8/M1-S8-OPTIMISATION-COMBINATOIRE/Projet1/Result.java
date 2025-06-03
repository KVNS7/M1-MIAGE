import java.util.List;

public class Result {
    public double valeur;
    public List<Element> choix;

    public Result(double valeur, List<Element> choix) {
        this.valeur = valeur;
        this.choix = choix;
    }
}