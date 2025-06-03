import java.util.List;

public class State {
    int idx;
    int poidsRem;
    int volRem;
    double valCourante;
    List<Element> choix;

    State(int idx, int poidsRem, int volRem, double valCourante, List<Element> choix) {
        this.idx = idx;
        this.poidsRem = poidsRem;
        this.volRem = volRem;
        this.valCourante = valCourante;
        this.choix = choix;
    }
}