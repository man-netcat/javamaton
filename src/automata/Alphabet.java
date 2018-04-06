package automata;
import java.util.*;

class Alphabet {
    private Set<Character> set = new HashSet<>();

    public Set<Character> getSet() {
        return set;
    }

    public void addSymbol(char symbol) {
        set.add(symbol);
    }

    public boolean equals(Alphabet a2) {
        return set.equals(a2.set);
    }
}
