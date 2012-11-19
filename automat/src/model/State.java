package model;

/**
 * State represents a state of an Automaton.
 * @author Admin
 *
 */
public class State {
	
	final private String name;
	private Automaton master;
	
	/**
	 * Constructor for State.
	 */
	private State(final String name, final Automaton master) {
		this.name = name;
		this.master = master;
	}
	
	/**
	 * Factory method for State.
	 */
	public static State create(String name, Automaton master) {
		return new State(name, master);
	}
	
	/**
	 * Adds a new Transition to the Transitions of this States master.
	 * @param input The char which process this State the new State.
	 * @param state The state which will be reached after processing this state with the input.
	 */
	public void add(char input, State state, char output) {
		Transition d = Transition.create(this, input, state, output);
		this.getMaster().getTransitions().add(d);
	}
	
	public Transitions getNextStates(char input) {
		return this.getMaster().getTransitions().getStates(this, input);
	}
	
	public String toString() {
		return Constants.BRACKET_OPEN + this.getName() + Constants.BRACKET_CLOSE;
	}
	
	/* Getters */
	
	/**
	 * Returns the name of this State.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the Automaton which owns this State.
	 */
	public Automaton getMaster() {
		return master;
	}
	
}
