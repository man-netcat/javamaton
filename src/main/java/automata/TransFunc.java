package automata;

import java.util.Objects;

public class TransFunc {

    private final String state;
    private final Character symbol;

    public TransFunc(String state, Character symbol) {
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
        if (!(o instanceof TransFunc))
            return false;
        TransFunc key = (TransFunc) o;
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
