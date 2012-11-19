package model;

/**
 * Automaton represents an indeterministic Automaton.
 * 
 * @author Admin
 *
 */
public class Automaton {
	
	private final State startState;
	private final State endState;
	private final Transitions transitions;
	
	/**
	 * Returns a new indeterministic Automaton.
	 */
	private Automaton() {
		this.startState = State.create("start", this);
		this.endState = State.create("end", this);
		this.transitions = Transitions.create();
	}
	
	/**
	 * Returns a new indeterministic Automaton.
	 */
	public Automaton(State startState, State endState) {
		this.startState = startState;
		this.endState = endState;
		this.transitions = Transitions.create();
	}

	public static Automaton create() {
		return new Automaton();
	}
	
	public static Automaton create(State startState, State endState) {
		return new Automaton(startState, endState);
	}
	
	/* Getters */

	/**
	 * Returns the startState of this Automaton.
	 */
	public State getStartState() {
		return startState;
	}
	
	/**
	 * Returns the endState of this Automaton.
	 */
	public State getEndState() {
		return endState;
	}
	
	/**
	 * Returns every transition (Delta) which belongs to this Automaton.
	 * @return
	 * 			LinkedList<Delta>
	 */
	public Transitions getTransitions() {
		return transitions;
	}
	
}
