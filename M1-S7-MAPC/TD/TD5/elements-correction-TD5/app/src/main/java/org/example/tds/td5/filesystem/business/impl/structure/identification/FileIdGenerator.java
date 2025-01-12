package org.example.tds.td5.filesystem.business.impl.structure.identification;

import java.util.Optional;

import org.example.tds.td5.filesystem.business.api.structure.identification.FileId;
import org.example.tds.td5.filesystem.business.api.structure.identification.IdGenerator;

public class FileIdGenerator implements IdGenerator<FileId> {

    private final int maxfiles;
    private FileId nextId;
    
    public FileIdGenerator(int maxfiles) {
        this.maxfiles = maxfiles;
        this.nextId = new FileIdImpl(0);
    }

    @Override
    public Optional<FileId> createId() {
        FileId result = nextId;
        if (result.id() < this.maxfiles) {
            nextId = new FileIdImpl(result.id() + 1);
            return Optional.of(result);
        } else {
            return Optional.empty();
        }
    }
}
