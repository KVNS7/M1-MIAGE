package org.example.tds.td5.filesystem.business.api.structure.files;

import java.util.List;

import org.example.tds.td5.filesystem.business.api.structure.identification.FileId;
import org.example.tds.td5.filesystem.business.api.structure.identification.UserId;

/**
 * Interface pour les fichiers.
 */
public interface File {

    /**
     * Parent du fichier.
     * 
     * @return le parent du fichier.
     */
    FileId parent();

    /**
     * Identification du fichier.
     * 
     * @return l'identification du fichier.
     */
    FileId id();

    /**
     * Nom du fichier
     * 
     * @return le nom du fichier.
     */
    String name();

    /**
     * Propriétaire du fichier.
     * 
     * @return le propriétaire du fichier.
     */
    UserId owner();

    /**
     * Taille du fichier.
     * 
     * @return la taille du fichier.
     */
    int size();

    /**
     * Contenu du fichier.
     * 
     * @return le contenu du fichier.
     */
    List<File> contents();

    /**
     * Indique si le fichier est un répertoire ou non.
     * @return true si le fichier est un répertoire, false sinon.
     */
    default boolean isDirectory() {
        return false;
    }
}
