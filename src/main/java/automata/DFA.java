package automata;

import java.util.*;
import java.io.*;

public class DFA {
    /* Finite set of states */
    public StateSet states = new StateSet();
    /* Input alphabet */
    public Alphabet alpha = new Alphabet();
    /* Set of transition functions */
    public TransSet transitions = new TransSet();
    /* Start state */
    public String start;
    /* Set of final states */
    public StateSet finalStates = new StateSet();

    public DFA() {}

    public DFA (String filename) {
        readFile(filename);
    }

    public DFA intersection(DFA d2) {
        if (!alpha.equals(d2.alpha)) {
            return null;
        }
        DFA ins = new DFA();
        ins.states = states.product(d2.states);
        ins.alpha = alpha;
        ins.start = start + d2.start;
        ins.transitions = transitions.product(alpha, d2.transitions);
        ins.finalStates = finalStates.product(d2.finalStates);
        return ins;
    }

    public DFA complement() {
        DFA comp = new DFA();
        comp.states = states;
        comp.alpha = alpha;
        comp.start = start + start;
        comp.transitions = transitions;
        comp.finalStates = states.difference(finalStates);
        return comp;
    }

    public DFA union(DFA d2) {
        if (!alpha.equals(d2.alpha)) {
            return null;
        }
        DFA cd1 = this.complement();
        DFA cd2 = d2.complement();
        DFA union = (cd1.intersection(cd2)).complement();
        return union;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void readFunction(String data, Scanner scanner) {
        String state = data;
        char symbol = scanner.next().charAt(0);
        String newstate = scanner.next();
        transitions.addFunction(state, symbol, newstate);
    }

    public void readFile(String filename) {
        File file = new File(filename);
        try(Scanner scanner = new Scanner(file)) {
            int status = 0;
            while(scanner.hasNext()){
                String data = scanner.next();
                switch (data){
                    case "Q:": status = 1; break;
                    case "Sigma:": status = 2; break;
                    case "delta:": status = 3; break;
                    case "s:": status = 4; break;
                    case "F:": status = 5; break;
                    default:
                    switch (status) {
                        case 1: states.addState(data); break;
                        case 2: alpha.addSymbol(data.charAt(0)); break;
                        case 3: readFunction(data, scanner); break;
                        case 4: setStart(data); break;
                        case 5: finalStates.addState(data); break;
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
            curstate = transitions.transition(curstate, symbol);
        }

        if (finalStates.contains(curstate)) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        DFA m1 = new DFA("data.txt");
        DFA m2 = new DFA("data2.txt");
        DFA mi = m1.intersection(m2);
        DFA mu = m1.union(m2);
        DFA m1c = m1.complement();
        String word1 = "aab";
        String word2 = "abb";

        System.out.println(m1.automaton(word1));
        System.out.println(m1c.automaton(word1));
        System.out.println(m2.automaton(word1));
        System.out.println(mi.automaton(word1));
        System.out.println(mu.automaton(word1));
        System.out.println("\n");
        System.out.println(m1.automaton(word2));
        System.out.println(m1c.automaton(word2));
        System.out.println(m2.automaton(word2));
        System.out.println(mi.automaton(word2));
        System.out.println(mu.automaton(word2));
    }
}
