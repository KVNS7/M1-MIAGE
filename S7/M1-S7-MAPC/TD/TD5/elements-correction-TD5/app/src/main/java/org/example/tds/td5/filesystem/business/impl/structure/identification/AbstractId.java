package org.example.tds.td5.filesystem.business.impl.structure.identification;

import org.example.tds.td5.filesystem.business.api.structure.identification.Id;

/**
 * Une implémentation simple et triviale des identifications métier.
 */
public class AbstractId<T> implements Id<T> {

    private T id;

    protected AbstractId(T id) {
        this.id = id;
    }

    @Override
    public T id() {
        return this.id;
    }

    @Override
    public String toString() {
        return id().toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractId<T> other = (AbstractId) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}
