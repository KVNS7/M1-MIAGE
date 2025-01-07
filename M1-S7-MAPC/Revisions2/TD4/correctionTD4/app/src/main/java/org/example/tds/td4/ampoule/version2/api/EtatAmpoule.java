package org.example.tds.td4.ampoule.version2.api;

import io.vavr.control.Try;

public interface EtatAmpoule {
    Try<Integer> allumer(Ampoule a);
    Try<Void> eteindre(Ampoule a);
}
