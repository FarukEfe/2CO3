package A2._2_5_20;

public class Tuple<A, B> {
    
    private A first;
    private B second;

    public Tuple(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(A first) {
        this.first = first;
    }

    public A getFirst() {
        return first;
    }

    public void setSecond(B second) {
        this.second = second;
    }

    public B getSecond() {
        return second;
    }

    @Override
    public String toString() {
        String str = "( " + this.first + " , " + this.second + " )";
        return str;
    }

}
