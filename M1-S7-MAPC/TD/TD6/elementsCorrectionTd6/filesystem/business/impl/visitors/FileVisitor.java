package org.example.tds.td6.filesystem.business.impl.visitors;

import org.example.tds.td6.filesystem.business.impl.structure.files.Directory;
import org.example.tds.td6.filesystem.business.impl.structure.files.Link;
import org.example.tds.td6.filesystem.business.impl.structure.files.SimpleFile;

public interface FileVisitor<T> {

    T value();

    void visit(SimpleFile f);

    void visit(Link l);

    void visit(Directory d);
}
