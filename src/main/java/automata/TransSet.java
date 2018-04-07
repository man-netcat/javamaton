package automata;
import java.util.*;

class TransSet {
    private Map<TransFunc, String> set = new HashMap<>();

    public void addFunction(String state, char symbol, String newstate) {
        set.put(new TransFunc(state, symbol), newstate);
    }

    public String transition(String state, char symbol) {
        return set.get(new TransFunc(state, symbol));
    }

    public TransSet product(Alphabet alphabet, TransSet t2) {
        TransSet product = new TransSet();
        for (TransFunc key1 : set.keySet()) {
            for (TransFunc key2 : t2.set.keySet()) {
                for (char symbol : alphabet.getSet()) {
                    String state1 = key1.getState();            
                    String state2 = key2.getState();                
                    String statei = state1 + state2;
                    String newstate1 = transition(state1, symbol);
                    String newstate2 = t2.transition(state2, symbol);
                    String newstatei = newstate1 + newstate2;
                    product.addFunction(statei, symbol, newstatei);
                }
            }
        }
        return product;
    }

    public String toString() {
        String result = "{";
        for (TransFunc func : set.keySet()) {
            result += func + ", ";
        }
        result += "}\n{";
        for (String state : set.values()) {
            result += state + ", ";
        }
        result += "}";
        return result;
    }
}
