public class Pair<T, U> {
    private T first;
    private U second;
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }
    public Pair() {
    }
    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }
    public void setFirst(T f){
        this.first = f;
    }
    public void setSecond(U s){
        this.second = s;
    }
    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
