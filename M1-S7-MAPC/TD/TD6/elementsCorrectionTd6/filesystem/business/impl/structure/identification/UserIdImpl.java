package org.example.tds.td6.filesystem.business.impl.structure.identification;

import org.example.tds.td6.filesystem.business.api.structure.identification.UserId;

/**
 * Implémentation simple et triviale d'identification d'utilisateur.
 */
public class UserIdImpl extends AbstractId<Integer> implements UserId {

    UserIdImpl(int id) {
        super(id);
    }

}