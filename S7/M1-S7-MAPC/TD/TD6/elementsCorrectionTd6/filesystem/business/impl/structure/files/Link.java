package org.example.tds.td6.filesystem.business.impl.structure.files;

import java.util.List;

import org.example.tds.td6.filesystem.business.api.structure.files.File;
import org.example.tds.td6.filesystem.business.api.structure.identification.FileId;
import org.example.tds.td6.filesystem.business.api.structure.identification.UserId;
import org.example.tds.td6.filesystem.business.impl.visitors.FileVisitor;

public class Link extends AbstractFile {

    private File cible;

    Link(FileId parent, FileId id, String name, UserId owner, File target) {
        super(parent, id, name, owner);
        this.cible = target;
    }

    @Override
    public String toString() {
        return String.format("link %s:%s (%s) -> %s", id(), name(), owner(), cible.name());
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public List<File> contents() {
        return List.of(this.cible);
    }

    @Override
    public <T> void accept(FileVisitor<T> v) {
        v.visit(this);
        // attention aux cycles si on rajoute :
        // this.cible.accept(v);
    }

}
