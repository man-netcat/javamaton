package automata;

import java.util.*;
import java.io.*;

public class DFA {
    /* Finite set of states */
    private Set <Character> states = new HashSet<>();
    /* Input alphabet */
    private Set <Character> alphabet = new HashSet<>();
    /* Set of transition functions */
    private Map <Key, Character> transfuncs = new HashMap<>();
    /* Start state */
    private char start;
    /* Set of final states */
    private Set <Character> finalStates = new HashSet<>();

    /**
     * Constructor for the DFA. Reads data from a file and initialises the
     * class data with that data.
     * @param filename Name of the file containing the data.
     */
    public DFA (String filename) {
        File file = new File(filename);
        try {
            Scanner scanner = new Scanner(file);
            char status = 0;
            while(scanner.hasNext()){
                String data = scanner.next();
                switch (data){
                    case "States:": status = 1; break;
                    case "Symbols:": status = 2; break;
                    case "Start:": status = 3; break;
                    case "FinalStates:": status = 4; break;
                    case "Functions:": status = 5; break;
                    case "EndFunctions.": status = 0; break;
                    default:
                    switch (status) {
                        case 1: addState(data.charAt(0)); break;
                        case 2: addSymbol(data.charAt(0)); break;
                        case 3: this.start = data.charAt(0); break;
                        case 4: addFinalState(data.charAt(0)); break;
                        case 5:
                        char state = data.charAt(0);
                        char symbol = scanner.next().charAt(0);
                        char newstate = scanner.next().charAt(0);
                        addFunction(state, symbol, newstate);
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

    /**
     * Add a state to the set of states.
     * @param state The state
     */
    public void addState(char state) {
        states.add(state);
    }

    /**
     * Add symbol to the alphabet.
     * @param symbol The symbol
     */
    public void addSymbol(char symbol) {
        alphabet.add(symbol);
    }

    /**
     * Add transition function to the set of transition functions.
     * @param state    Primary state
     * @param symbol   Associated Symbol
     * @param newstate Succeeding state
     */
    public void addFunction(char state, char symbol, char newstate) {
        Key key = new Key(state, symbol);
        transfuncs.put(key, newstate);
    }

    /**
     * Sets the starting state.
     * @param start The starting states
     */
    public void setStart(char start) {
        this.start = start;
    }

    /**
     * Add final state to the set of final states.
     * @param state The final state
     */
    public void addFinalState(char state) {
        finalStates.add(state);
    }

    /**
     * Return state associated with the given transition function.
     * @param  state  Current state
     * @param  symbol Associated symbol
     * @return        Next state
     */
    public char transition(char state, char symbol) {
        Key key = new Key(state, symbol);
        return transfuncs.get(key);
    }

    /**
     * Execute automaton with a given word. A word must exclusively contain
     * symbols from the current alphabet.
     * @param  word A word containing symbols present in the current alphabet
     * @return      True if accepted, false if rejected.
     */
    public boolean automaton(String word) {
        char curstate = start;

        for (int i = 0; i < word.length(); i++) {
            char symbol = word.charAt(i);
            curstate = transition(curstate, symbol);
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
