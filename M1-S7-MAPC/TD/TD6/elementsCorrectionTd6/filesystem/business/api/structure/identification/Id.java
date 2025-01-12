package org.example.tds.td6.filesystem.business.api.structure.identification;

/**
 * Interface générique pour l'indentification métier.
 */
public interface Id<T> {

    /**
     * Retourne l'id de l'objet métier.
     * @return l'id de l'objet métier.
     */
    T id();

}
