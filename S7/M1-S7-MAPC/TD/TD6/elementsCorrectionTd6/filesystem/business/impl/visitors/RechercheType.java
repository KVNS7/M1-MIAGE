package org.example.tds.td6.filesystem.business.impl.visitors;

import java.util.ArrayList;
import java.util.List;

import org.example.tds.td6.filesystem.business.impl.structure.files.Directory;
import org.example.tds.td6.filesystem.business.impl.structure.files.Link;
import org.example.tds.td6.filesystem.business.impl.structure.files.SimpleFile;

public class RechercheType implements FileVisitor<List<String>> {

    private List<String> res;
    private String suffixe;

    public RechercheType(String suffixe) {
        this.suffixe = suffixe;
        this.res = new ArrayList<>();
    }

    @Override
    public List<String> value() {
        return res;
    }

    @Override
    public void visit(SimpleFile f) {
        if (f.name().endsWith(suffixe)) {
            res.add(f.name());
        }
    }

    @Override
    public void visit(Link l) {
        // rien
    }

    @Override
    public void visit(Directory d) {
        // rien
    }

}
