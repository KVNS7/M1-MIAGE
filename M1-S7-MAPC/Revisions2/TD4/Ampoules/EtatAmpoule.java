public interface EtatAmpoule {
    public EtatAmpoule allumer() throws EtatIllegal;
    public EtatAmpoule eteindre() throws EtatIllegal;
}
