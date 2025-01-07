package Cartes;

public enum Suit { 
    PIQUES, TREFLES, COEURS, CARREAUX, JOKER;

    public Color color() {
        switch (this) {
            case PIQUES:
            case TREFLES:
                return Color.NOIR;
            case COEURS:
            case CARREAUX:
                return Color.ROUGE;
            case JOKER:
                return null;
            default:
                throw new IllegalStateException("Couleur inconnue pour la suite : " + this);
        }
    }
}
