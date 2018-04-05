package automata;

import java.util.*;

public class StateSet {
    private Set<String> set = new HashSet<>();

    // public Set<String> product(Set<String> s2){
    //     Set<String> product = new HashSet<>();
    //
    // }

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
}
