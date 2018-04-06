package automata;
import java.util.*;
 
public class StateSet {
    private Set<String> set = new HashSet<>();

    public StateSet product(StateSet s2){
        StateSet product = new StateSet();
        for (String state1 : set) {
            for (String state2 : s2.set) {
                product.addState(state1 + state2);
            }
        }        
        return product;
    }

    /**
     * Add a state to the set of states.
     * @param state The state
     */
    public void addState(String state) {
        set.add(state);
    }

    public boolean contains(String state) {
        return set.contains(state);
    }

    public String toString() {
        String result = "(";
        for (String state : set) {
            result += state + ", ";
        }
        result += ")";
        return result;
    }
}
