package automata;

import java.util.*;

public class DFA {
  // Finite set of states
  private Set<Integer> states = new HashSet<>();
  // Input alphabet
  private Set<Character> alphabet = new HashSet<>();
  // Set of transition functions
  private Map<Key, Integer> functions = new HashMap<>();
  // Start state
  private int start;
  // Set of final states
  private Set<Integer> acceptedStates = new HashSet<>();

  public DFA(int start) {
    addStates(start);
    this.start = start;
  }

  public void addState() { states.add(states.size() + 1); }

  public void addStates(int amount) {
    for (int i = 0; i < amount; i++) {
      addState();
    }
  }

  public void addAcceptedState(int state) { acceptedStates.add(state); }

  public void addAcceptedStates(int[] states) {
    for (int state : states) {
      addAcceptedState(state);
    }
  }

  public void addSymbol(char symbol) { alphabet.add(symbol); }

  public void addSymbols(char[] symbols) {
    for (char symbol : symbols) {
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

  public void setStart(int start) { this.start = start; }

  public int getStart() { return start; }

  public boolean automaton(String word) {
	int curstate = start;
	
    for (int i = 0; i < word.length() - 1; i++) {
      char symbol = word.charAt(i);
      System.out.println(symbol);
      curstate = transition(curstate, symbol);
	}
	
    if (acceptedStates.contains(curstate)) {
      return true;
	}
	
    return false;
  }

  public static void main(String[] args) {
    DFA machine = new DFA(1);
    machine.addStates(4);
	machine.addSymbols(new char[] {'a', 'b'});
	machine.addAcceptedState(4);
    machine.addFunction(1, 'a', 2);
    machine.addFunction(2, 'a', 2);
    machine.addFunction(3, 'a', 4);
    machine.addFunction(4, 'a', 5);
    machine.addFunction(1, 'b', 1);
    machine.addFunction(2, 'b', 3);
    machine.addFunction(3, 'b', 1);
    machine.addFunction(4, 'b', 4);
    System.out.println(machine.automaton("aabbabaa"));
  }
}
