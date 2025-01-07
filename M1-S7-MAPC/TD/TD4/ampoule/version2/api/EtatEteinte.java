package org.example.ampoule.version2.api;

import io.vavr.control.Try;

public class EtatEteinte implements EtatAmpoule {

    private EtatEteinte() {}

    public static final EtatAmpoule INSTANCE = new EtatEteinte();

    @Override
    public Try<Integer> allumer(Ampoule a) {
        a._allumer();
        a.changeEtat(EtatAllumee.INSTANCE);
        return Try.success(a.lumen());
    }

    @Override
    public Try<Void> eteindre(Ampoule a) {
        return Try.failure(new ActionIllegale("ampoule déjà éteinte"));
    }
    
}
