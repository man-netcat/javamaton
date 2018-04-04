package automata;

import java.util.*;
import java.io.*;

public class DFA {
    // Finite set of states
    private Set <Integer> states = new HashSet<>();
    // Input alphabet
    private Set <Character> alphabet = new HashSet<>();
    // Set of transition functions
    private Map <Key, Integer> functions = new HashMap<>();
    // Start state
    private int start;
    // Set of final states
    private Set <Integer> finalStates = new HashSet<>();

    public DFA(int start) {
        addStates(start);
        this.start = start;
    }

    public void addState() {
        states.add(states.size());
    }

    public void addStates(int amount) {
        for (int i = 0; i < amount; i++) {
            addState();
        }
    }

    public void addFinalState(int state) {
        finalStates.add(state);
    }

    public void addFinalStates(int[] states) {
        for (int state: states) {
            addFinalState(state);
        }
    }

    public void addSymbol(char symbol) {
        alphabet.add(symbol);
    }

    public void addSymbols(char[] symbols) {
        for (char symbol: symbols) {
            addSymbol(symbol);
        }
    }

    public void addFunction(int state, char symbol, int newstate) {
        if (!states.contains(state)) {
            System.out.println("State not present in set of states");
            System.exit(1);
        }
        if (!alphabet.contains(symbol)) {
            System.out.println("Symbol not present in alphabet");
            System.exit(1);
        }
        Key key = new Key(state, symbol);
        functions.put(key, newstate);
    }

    public int transition(int state, char symbol) {
        Key key = new Key(state, symbol);
        return functions.get(key);
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStart() {
        return start;
    }

    public boolean automaton(String word) {
        int curstate = start;

        for (int i = 0; i < word.length(); i++) {
            char symbol = word.charAt(i);
            curstate = transition(curstate, symbol);
        }

        if (finalStates.contains(curstate)) {
            return true;
        }

        return false;
    }

    void readFunctions(String filename)
    {
        File file = new File(filename);
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()){
                int state = scanner.next().charAt(0) - '0';
                char symbol = scanner.next().charAt(0);
                int newstate = scanner.next().charAt(0) - '0';
                addFunction(state, symbol, newstate);
            }
        } catch(FileNotFoundException ex) {
            ex.printStackTrace();
        } catch(NoSuchElementException ex) {
            ex.printStackTrace();
        }
    }

    void readData(String filename)
    {
        File file = new File(filename);
        try {
            Scanner scanner = new Scanner(file);
            String data;
            int status = 0;
            while(scanner.hasNext()){
                switch (data = scanner.next()){
                    case "States:": status = 1; break;
                    case "Symbols:": status = 2; break;
                    case "FinalStates:": status = 3; break;
                    default:
                    switch (status) {
                        case 1: addStates(data.charAt(0) - '0'); break;
                        case 2: addSymbol(data.charAt(0)); break;
                        case 3: addFinalState(data.charAt(0) - '0'); break;
                    }
                }
            }
        } catch(FileNotFoundException ex) {
            ex.printStackTrace();
        } catch(NoSuchElementException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DFA machine = new DFA(1);
        machine.readData("data.txt");
        machine.readFunctions("funcs.txt");
        System.out.println(machine.automaton("babaa"));
        System.out.println(machine.automaton("aabbb"));
    }
}
