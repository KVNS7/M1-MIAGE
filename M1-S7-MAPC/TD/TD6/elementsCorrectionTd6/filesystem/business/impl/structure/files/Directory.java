package org.example.tds.td6.filesystem.business.impl.structure.files;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.example.tds.td6.filesystem.business.api.structure.files.File;
import org.example.tds.td6.filesystem.business.api.structure.files.CompositeFile;
import org.example.tds.td6.filesystem.business.api.structure.identification.FileId;
import org.example.tds.td6.filesystem.business.api.structure.identification.UserId;
import org.example.tds.td6.filesystem.business.impl.visitors.FileVisitor;

import java.util.HashMap;
import java.util.Iterator;

public class Directory extends AbstractFile implements CompositeFile {

    private Map<FileId, File> elements;

    Directory(FileId parent, FileId id, String name, UserId owner) {
        super(parent, id, name, owner);
        this.elements = new HashMap<>();
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("directory %s:%s (%s)", id(), name(), owner());
    }

    @Override
    public int size() {
        return 1 + elements.values().stream().mapToInt(File::size).sum();
    }

    @Override
    public List<File> contents() {
        return new ArrayList<>(this.elements.values());
    }

    private boolean containsFile(String fileName) {
        assert fileName != null;
        return this.contents().stream().anyMatch(f -> f.name().equals(fileName));
    }

    // gestion d'erreurs par booleen, pas terrible (passer par Validation par exemple)
    @Override
    public boolean add(File f) {
        assert f != null;
        if (f.parent() == this.id() && !this.containsFile(f.name())) {
            this.elements.put(f.id(), f);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Iterator<File> iterator() {
        return this.elements.values().iterator();
    }

    @Override
    public <T> void accept(FileVisitor<T> v) {
        v.visit(this);
        for (File f : this) {
            f.accept(v);
        }
    }
    
}
