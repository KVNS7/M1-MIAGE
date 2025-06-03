public class Element {
    private int id;
    private double poids;
    private double volume;
    private double valeur;

    public Element(int id, double poids, double volume, double valeur) {
        this.id = id;
        this.poids = poids;
        this.volume = volume;
        this.valeur = valeur;
    }

    public int getId() {
        return id;
    }
    public double getPoids() {
        return poids;
    }
    public double getValeur() {
        return valeur;
    }
    public double getVolume() {
        return volume;
    }

    public void afficherElement(){
        System.out.println("\nid: "+ this.id+"\npoids: "+ this.poids+ "\nvolume: "+ this.volume+ "\nvaleur: "+ this.valeur);
    }

}
