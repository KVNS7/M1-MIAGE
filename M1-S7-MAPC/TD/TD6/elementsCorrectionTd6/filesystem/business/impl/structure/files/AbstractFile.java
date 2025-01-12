package org.example.tds.td6.filesystem.business.impl.structure.files;

import org.example.tds.td6.filesystem.business.api.structure.files.File;
import org.example.tds.td6.filesystem.business.api.structure.identification.FileId;
import org.example.tds.td6.filesystem.business.api.structure.identification.UserId;

public abstract class AbstractFile implements File {

    private FileId id;
    private String name;
    private UserId owner;
    private FileId parent;

    AbstractFile(FileId parent, FileId id, String name, UserId owner) {
        assert parent != null;
        assert id != null;
        assert name != null && !name.isEmpty();
        assert owner != null;
        this.parent = parent;
        this.id = id;
        this.name = name;
        this.owner = owner;
    }

    @Override
    public FileId parent() {
        return parent;
    }

    @Override
    public FileId id() {
        return this.id;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public UserId owner() {
        return this.owner;
    }
    
}
