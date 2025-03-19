package org.example.tds.td6.filesystem.business.impl.structure.identification;

import org.example.tds.td6.filesystem.business.api.structure.identification.FileId;

/**
 * Implémentation simple et triviale d'identification de fichier.
 */
public class FileIdImpl extends AbstractId<Integer> implements FileId {

    FileIdImpl(int id) {
        super(id);
    }

}