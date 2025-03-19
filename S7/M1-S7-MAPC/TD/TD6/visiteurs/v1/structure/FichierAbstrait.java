package code.visiteurs.v1.structure;

public abstract class FichierAbstrait implements Fichier {

    private String nom;
    private String proprietaire;

    public FichierAbstrait(String nom, String proprietaire) {
        this.nom = nom;
        this.proprietaire = proprietaire;
    }

    @Override
    public String proprietaire() {
        return proprietaire;
    }

    @Override
    public String nom() {
        return nom;
    }

}
