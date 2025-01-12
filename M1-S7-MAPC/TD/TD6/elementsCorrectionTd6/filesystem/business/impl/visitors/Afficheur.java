package org.example.tds.td6.filesystem.business.impl.visitors;

import org.example.tds.td6.filesystem.business.impl.structure.files.Directory;
import org.example.tds.td6.filesystem.business.impl.structure.files.Link;
import org.example.tds.td6.filesystem.business.impl.structure.files.SimpleFile;

public class Afficheur implements FileVisitor<String> {

    private StringBuilder sb;

    public Afficheur() {
        this.sb = new StringBuilder();
    }

    @Override
    public String value() {
        return sb.toString();
    }

    @Override
    public void visit(SimpleFile f) {
        sb.append(f.toString());
    }

    @Override
    public void visit(Link l) {
        sb.append(l.toString());
    }

    @Override
    public void visit(Directory d) {
        sb.append(d.toString());
    }
    
}
