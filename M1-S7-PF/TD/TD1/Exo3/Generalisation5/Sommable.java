package Exo3.Generalisation5;

public interface Sommable<T> extends Comparable<T> {
    T sommer(T autre);
    int compareTo(T autre);
}