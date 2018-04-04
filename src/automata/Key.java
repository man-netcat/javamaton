package automata;

public class Key {

    private final int state;
    private final char symbol;

    public Key(int state, char symbol) {
        this.state = state;
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Key))
            return false;
        Key key = (Key) o;
        return state == key.state && symbol == key.symbol;
    }

    @Override
    public int hashCode() {
        int result = state;
        result = 17 * result + symbol;
        return result;
    }
}
