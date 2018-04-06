package src.main.java.automata;

import java.util.*;
import java.io.*;

public class DFA {
    /* Finite set of states */
    private StateSet states = new StateSet();
    /* Input alphabet */
    private Alphabet aleph = new Alphabet();
    /* Set of transition functions */
    private TransitionSet tf = new TransitionSet();
    /* Start state */
    private String start;
    /* Set of final states */
    private StateSet finalStates = new StateSet();

    /**
     * Constructor for the DFA. Reads data from aleph file and initialises the
     * class data with that data.
     * @param filename Name of the file containing the data.
     */

     public DFA() {

     }

    public DFA (String filename) {
        readFile(filename);
    }

    public DFA intersection(DFA d2) {
        if (!aleph.equals(d2.aleph)) {
            return null;
        }
        DFA intersection = new DFA();
        intersection.states = states.product(d2.states);
        intersection.aleph = aleph;
        intersection.start = start + d2.start;
        intersection.tf = tf.product(aleph, d2.tf);
        intersection.finalStates = finalStates.product(d2.finalStates);
        return intersection;
    }

    /**
     * Sets the starting state.
     * @param start The starting states
     */
    public void setStart(String start) {
        this.start = start;
    }

    public void readFile(String filename) {
        File file = new File(filename);
        try(Scanner scanner = new Scanner(file)) {
            int status = 0;
            while(scanner.hasNext()){
                String data = scanner.next();
                switch (data){
                    case "States:": status = 1; break;
                    case "Symbols:": status = 2; break;
                    case "Start:": status = 3; break;
                    case "FinalStates:": status = 4; break;
                    case "Functions:": status = 5; break;
                    default:
                    switch (status) {
                        case 1: states.addState(data); break;
                        case 2: aleph.addSymbol(data.charAt(0)); break;
                        case 3: setStart(data); break;
                        case 4: finalStates.addState(data); break;
                        case 5:
                        String state = data;
                        char symbol = scanner.next().charAt(0);
                        String newstate = scanner.next();
                        tf.addFunction(state, symbol, newstate);
                        break;
                    }
                }
            }
        } catch(FileNotFoundException ex) {
            ex.printStackTrace();
        } catch(NoSuchElementException ex) {
            ex.printStackTrace();
        }
    }

    public boolean automaton(String word) {
        String curstate = start;

        for (int i = 0; i < word.length(); i++) {
            char symbol = word.charAt(i);
            curstate = tf.transition(curstate, symbol);
        }

        if (finalStates.contains(curstate)) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        StateSet set1 = new StateSet();
        set1.addState("1");
        set1.addState("2");

        StateSet set2 = new StateSet();
        set2.addState("1");
        set2.addState("2");

        StateSet product = set1.product(set2);
        System.out.println(product);

        DFA m1 = new DFA("data.txt");
        DFA m2 = new DFA("data2.txt");
        DFA mi = m1.intersection(m2);
        System.out.println(m1.automaton("babaa"));
        System.out.println(m2.automaton("babaa"));
        System.out.println(mi.automaton("babaa"));
    }
}
