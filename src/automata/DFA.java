package automata;

import java.util.*;
import java.io.*;

public class DFA {
    // Finite set of states
    private Set <Character> states = new HashSet<>();
    // Input alphabet
    private Set <Character> alphabet = new HashSet<>();
    // Set of transition functions
    private Map <Key, Character> functions = new HashMap<>();
    // Start state
    private char start;
    // Set of final states
    private Set <Character> finalStates = new HashSet<>();

    public void addState(char state) {
        states.add(state);
    }

    public void addStates(char amount) {
        for (char state: states) {
            addState(state);
        }
    }

    public void addFinalState(char state) {
        finalStates.add(state);
    }

    public void addFinalStates(char[] states) {
        for (char state: states) {
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

    public void addFunction(char state, char symbol, char newstate) {
        Key key = new Key(state, symbol);
        functions.put(key, newstate);
    }

    public char transition(char state, char symbol) {
        Key key = new Key(state, symbol);
        return functions.get(key);
    }

    public void setStart(char start) {
        this.start = start;
    }

    public char getStart() {
        return start;
    }

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

    void readData(String filename)
    {
        File file = new File(filename);
        try {
            Scanner scanner = new Scanner(file);
            String data;
            char status = 0;
            while(scanner.hasNext()){
                data = scanner.next();
                switch (data){
                    case "States:": status = 1; break;
                    case "Symbols:": status = 2; break;
                    case "Start:": status = 3; break;
                    case "FinalStates:": status = 4; break;
                    case "Functions:": status = 5; break;
                    case "EndFunctions.": status = 0; break;
                    default:
                    switch (status) {
                        case 1: addStates(data.charAt(0)); break;
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

    public static void main(String[] args) {
        DFA m1 = new DFA();
        m1.readData("data.txt");
        System.out.println(m1.automaton("babaa"));
        System.out.println(m1.automaton("aabbb"));
    }
}
