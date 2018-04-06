package automata;

import java.util.Objects;

public class Key {

    private final String state;
    private final Character symbol;

    public Key(String state, Character symbol) {
        this.state = state;
        this.symbol = symbol;
    }

    public String getState() {
        return state;
    }

    public Character getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Key))
            return false;
        Key key = (Key) o;
        return state.equals(key.state) && symbol.equals(key.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, symbol);
    }

    @Override
    public String toString() {
        return "(" + state + ", " + symbol + ")";
    }
}
