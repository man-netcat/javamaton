package automata;
import java.util.*;

class TransitionSet {
    private Map<TransFunc, String> set = new HashMap<>();

    /**
     * Add transition function to the set of transition functions.
     * @param state    Primary state
     * @param symbol   Associated Symbol
     * @param newstate Succeeding state
     */
    public void addFunction(String state, char symbol, String newstate) {
        set.put(new TransFunc(state, symbol), newstate);
    }

    /**
     * Return state associated with the given transition function.
     * @param  state  Current state
     * @param  symbol Associated symbol
     * @return        Next state
     */
    public String transition(String state, char symbol) {
        return set.get(new TransFunc(state, symbol));
    }

    public TransitionSet product(Alphabet alphabet, TransitionSet t2) {
        TransitionSet product = new TransitionSet();
        for (TransFunc key1 : set.keySet()) {
            for (TransFunc key2 : t2.set.keySet()) {
                for (char symbol : alphabet.getSet()) {
                    String state1 = key1.getState();
                    String state2 = key2.getState();
                    String newstate1 = transition(state1, symbol);
                    String newstate2 = transition(state2, symbol);
                    String res = state1+state2;
                    String newres = newstate1+newstate2;
                    product.addFunction(res, symbol, newres);
                }
            }
        }
        return product;
    }

    public String toString() {
        String result = "(";
        for (TransFunc func : set.keySet()) {
            result += func + ", ";
        }
        result += ")\n(";
        for (String state : set.values()) {
            result += state + ", ";
        }
        result += ")";
        return result;
    }
}
