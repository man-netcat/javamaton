package automata;

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
    public DFA (String filename) {
        readFile(filename);
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
        DFA m1 = new DFA("data.txt");
        System.out.println(m1.automaton("babaa"));
        System.out.println(m1.automaton("aabbb"));

        DFA m2 = new DFA("data2.txt");
        System.out.println(m2.automaton("1001010"));
        System.out.println(m2.automaton("100101"));
    }
}
