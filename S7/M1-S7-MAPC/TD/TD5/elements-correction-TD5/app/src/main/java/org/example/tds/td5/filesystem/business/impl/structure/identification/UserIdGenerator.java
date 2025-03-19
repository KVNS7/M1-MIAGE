package org.example.tds.td5.filesystem.business.impl.structure.identification;

import java.util.Optional;

import org.example.tds.td5.filesystem.business.api.structure.identification.IdGenerator;
import org.example.tds.td5.filesystem.business.api.structure.identification.UserId;

public class UserIdGenerator implements IdGenerator<UserId> {

    private UserId nextId;
    
    public UserIdGenerator() {
        this.nextId = new UserIdImpl(0);
    }

    @Override
    public Optional<UserId> createId() {
        UserId result = nextId;
        nextId = new UserIdImpl(result.id() + 1);
        return Optional.of(result);
    }
}
