package automata;

import java.util.Objects;

public class Key {

    private final String state;
    private final Character symbol;

    public Key(String state, Character symbol) {
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
        return Objects.hash(state, symbol);
    }
}
