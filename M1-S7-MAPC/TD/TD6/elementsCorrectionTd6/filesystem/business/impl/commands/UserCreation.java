package org.example.tds.td6.filesystem.business.impl.commands;

import java.util.Optional;

import org.example.tds.td6.filesystem.business.api.commands.Command;
import org.example.tds.td6.filesystem.business.api.structure.identification.UserId;
import org.example.tds.td6.filesystem.business.impl.structure.files.FileSystem;

/**
 * Commande de création d'utilisateur.
 */
public class UserCreation implements Command<Optional<UserId>> {

    private FileSystem s; // le contexte de la commande.

    public UserCreation(FileSystem s) {
        assert s != null;
        this.s = s;
    }

    /**
     * Exécute la commande et renvoie le résultat.
     * Ici on délègue au contexte.
     */
    @Override
    public Optional<UserId> execute() {
        return s.createUser();
    }
    
}
