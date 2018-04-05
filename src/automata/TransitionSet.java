package automata;
import java.util.*;

class TransitionSet {
    private Map<Key, String> set = new HashMap<>();

    /**
     * Add transition function to the set of transition functions.
     * @param state    Primary state
     * @param symbol   Associated Symbol
     * @param newstate Succeeding state
     */
    public void addFunction(String state, char symbol, String newstate) {
        set.put(new Key(state, symbol), newstate);
    }

    /**
     * Return state associated with the given transition function.
     * @param  state  Current state
     * @param  symbol Associated symbol
     * @return        Next state
     */
    public String transition(String state, char symbol) {
        return set.get(new Key(state, symbol));
    }
}
