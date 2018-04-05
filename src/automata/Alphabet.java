package automata;
import java.util.*;

class Alphabet {
    private Set<Character> alphabet = new HashSet<>();

    /**
     * Add symbol to the alphabet.
     * @param symbol The symbol
     */
    public void addSymbol(char symbol) {
        alphabet.add(symbol);
    }
}
