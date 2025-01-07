package org.example.tds.td4.ampoule.version2.api;

import io.vavr.control.Try;

public class EtatEteinte implements EtatAmpoule {

    private EtatEteinte() {}

    public static final EtatAmpoule INSTANCE = new EtatEteinte();

    @Override
    public Try<Integer> allumer(Ampoule a) {
        if (a.usages() < 3) {
            a._allumer();
            a.changeEtat(EtatAllumee.INSTANCE);
            return Try.success(a.lumen());
        } else {
            a._casser();
            a.changeEtat(EtatCassee.INSTANCE);
            return Try.success(0);
        }
    }

    @Override
    public Try<Void> eteindre(Ampoule a) {
        return Try.failure(new ActionIllegale("ampoule déjà éteinte"));
    }
    
}
