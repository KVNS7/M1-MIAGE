package Exo2;
public class Paire <A, B> {

    private final A fst;
    private final B snd;

    public Paire(A fst, B snd){
        this.fst = fst;
        this.snd = snd;
    }

    public A fst() {
        return fst;
    }

    public B snd() {
        return snd;
    }
    
    public <C> Paire<C,B> changeFst(C value){
        return new Paire<>( value, this.snd);
    }

    public <C> Paire<A,C> changeSnd(C value){
        return new Paire<>(this.fst, value);
    }

    @Override
    public String toString(){
        return "(" + fst + ", " + snd + ") :: Paire[" + fst.getClass().getSimpleName() + ", " + snd.getClass().getSimpleName() + "]";
    }

}

