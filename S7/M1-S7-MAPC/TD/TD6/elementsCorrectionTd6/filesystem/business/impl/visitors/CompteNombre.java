package org.example.tds.td6.filesystem.business.impl.visitors;

import org.example.tds.td6.filesystem.business.impl.structure.files.Directory;
import org.example.tds.td6.filesystem.business.impl.structure.files.Link;
import org.example.tds.td6.filesystem.business.impl.structure.files.SimpleFile;

public class CompteNombre implements FileVisitor<Integer> {

    private int n;

    public CompteNombre() {
        this.n = 0;
    }

    @Override
    public Integer value() {
        return n;
    }

    @Override
    public void visit(SimpleFile f) {
        n++;
    }

    @Override
    public void visit(Link l) {
        n++;
    }

    @Override
    public void visit(Directory d) {
        n++;
    }
    
}
