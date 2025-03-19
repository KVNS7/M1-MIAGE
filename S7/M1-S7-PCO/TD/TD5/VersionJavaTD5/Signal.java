package VersionJavaTD5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Signal {
    private final Lock[] locks;
    private final Condition[] conditions;
    
    public Signal(int n) {
        locks = new ReentrantLock[n];
        conditions = new Condition[n];
        for (int i = 0; i < n; i++) {
            locks[i] = new ReentrantLock();
            conditions[i] = locks[i].newCondition();
        }
    }

    public void envoyerSignal(int id) {
        locks[id].lock();
        try {
            conditions[id].signal();
        } finally {
            locks[id].unlock();
        }
    }

    public void attendre(int id) throws InterruptedException {
        locks[id].lock();
        try {
            conditions[id].await();
        } finally {
            locks[id].unlock();
        }
    }
}
