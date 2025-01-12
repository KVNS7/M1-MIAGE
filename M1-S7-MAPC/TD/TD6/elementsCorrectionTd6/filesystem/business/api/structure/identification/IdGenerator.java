package org.example.tds.td6.filesystem.business.api.structure.identification;

import java.util.Optional;

/**
 * Interface des générateurs d'identificateurs.
 */
public interface IdGenerator<T extends Id<?>> {
    
    /**
     * Crée un identifiant.
     * @return un Optional contenant l'identifiant créé, ou vide si le générateur ne peut pas créer d'identifiant.
     */
    Optional<T> createId();

}
