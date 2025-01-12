package org.example.tds.td5.filesystem.business.api.structure.files;

import java.util.Iterator;

public interface CompositeFile extends Iterable<File> {

    boolean add(File f);

    public Iterator<File> iterator();
}
