package org.example.tds.td5.filesystem.business.api.commands;

/**
 * Interface pour commandes.
 */
public interface Command<T> {

    /**
     * Exécute la commande et renvoie le résultat.
     * @return le résultat de l'exécution de la commande.
     */
    T execute();
}
