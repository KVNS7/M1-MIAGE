package org.example.tds.td6.filesystem.business.impl.structure.files;

import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

import org.example.tds.td6.filesystem.business.api.structure.files.File;
import org.example.tds.td6.filesystem.business.api.structure.identification.FileId;
import org.example.tds.td6.filesystem.business.api.structure.identification.IdGenerator;
import org.example.tds.td6.filesystem.business.api.structure.identification.UserId;
import org.example.tds.td6.filesystem.business.impl.structure.identification.FileIdGenerator;
import org.example.tds.td6.filesystem.business.impl.structure.identification.UserIdGenerator;

public class FileSystem {

    private IdGenerator<FileId> fGen = new FileIdGenerator(MAXFILES);
    private IdGenerator<UserId> uGen = new UserIdGenerator();

    private Map<FileId, File> files = new HashMap<>();

    public final UserId ADMIN = uGen.createId().get();
    public final FileId ROOT = createRoot();
    public static final int MAXFILES = 16;

    private FileId createRoot() {
        Optional<FileId> rootId = fGen.createId();
        if (rootId.isPresent()) {
            Directory root = new Directory(null, rootId.get(), "root", ADMIN);
            files.put(root.id(), root);
            return root.id();
        } else {
            return null;
        }
    }

    public Optional<UserId> createUser() {
        return uGen.createId();
    }

    public Optional<FileId> createDirectory(String name, UserId owner, FileId parent) {
        assert name != null && !name.isEmpty();
        assert owner != null;
        assert parent != null;
        Optional<File> p = getFileFromId(parent);
        if (p.isPresent() && p.get().isDirectory()) {
            Directory r = (Directory)(p.get()); // on verra comment améliorer cela
            Optional<FileId> newId = fGen.createId();
            if (newId.isPresent()) {
                File newDirectory = new Directory(parent, newId.get(), name, owner);
                if (r.add(newDirectory)) {
                    files.put(newId.get(), newDirectory);
                    return newId;
                }
            }
        }
        return Optional.empty();
    }

    public Optional<FileId> createLink(String name, UserId owner, FileId parent, FileId target) {
        assert name != null && !name.isEmpty();
        assert owner != null;
        assert parent != null;
        assert target != null;
        Optional<File> p = getFileFromId(parent);
        Optional<File> c = getFileFromId(target);
        if (p.isPresent() && p.get().isDirectory() && c.isPresent()) {
            Directory r = (Directory)(p.get()); // on verra comment améliorer cela
            Optional<FileId> newId = fGen.createId();
            if (newId.isPresent()) {
                File newLink = new Link(parent, newId.get(), name, owner, c.get());
                if (r.add(newLink)) {
                    files.put(newId.get(), newLink);
                    return newId;
                }
            }
        }
        return Optional.empty();
    }

    public Optional<FileId> createFile(String name, UserId owner, FileId parent, String contents) {
        assert name != null && !name.isEmpty();
        assert owner != null;
        assert parent != null;
        assert contents != null;
        Optional<File> p = getFileFromId(parent);
        if (p.isPresent() && p.get().isDirectory()) {
            Directory r = (Directory)(p.get()); // on verra comment améliorer cela
            Optional<FileId> newId = fGen.createId();
            if (newId.isPresent()) {
                File newFile = new SimpleFile(parent, newId.get(), name, owner, contents);
                if (r.add(newFile)) {
                    files.put(newId.get(), newFile);
                    return newId;
                }
            }
        }
        return Optional.empty();
    }

    public Optional<FileId> createDirectory(String name, FileId parent) {
        return getFileFromId(parent).flatMap(p -> createDirectory(name, p.owner(), parent));
    }

    public Optional<FileId> createFile(String name, FileId parent, String contents) {
        return getFileFromId(parent).flatMap(p -> createFile(name, p.owner(), parent, contents));
    }

    public Optional<FileId> createLink(String name, FileId parent, FileId target) {
        return getFileFromId(parent).flatMap(p -> createLink(name, p.owner(), parent, target));
    }

    public Optional<File> getFileFromId(FileId id) {
        assert id != null;
        return Optional.ofNullable(files.get(id));
    }
}
