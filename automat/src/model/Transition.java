package model;

/**
 * Transition represents a transition inside an Automaton.
 * @author Admin
 *
 */
public class Transition {
	
	private final State from;
	private final State to;
	private final char input;
	private final char output;
	
	/**
	 * Constructor for Transition.
	 */
	private Transition(State from, char input, State to, char output) {
		this.from = from;
		this.input = input;
		this.to = to;
		this.output = output;
	}
	
	public String toString() {
		return "d[" + this.getFrom() + ", " + this.getInput() + ", " + this.getTo() + ", " + this.getOutput() + "]";
	}
	
	/**
	 * Factory method for Transition.
	 * @param from is the State which will be transfered.
	 * @param input is the char which transfers the State from to the State to.
	 * @param to is the new State of the Automaton after the State from was processed with the char input. 
	 */
	public static Transition create(State from, char input, State to, char output) {
		return new Transition(from, input, to, output); 
	}
	
	/* Getters */
	
	/**
	 * Returns the State from.
	 * @return
	 * 			State from
	 */
	public State getFrom() {
		return this.from;
	}
	
	/**
	 * Returns the State to. 
	 * @return
	 * 		State
	 */
	public State getTo() {
		return this.to;
	}

	/**
	 * Returns the input which processes the State from to the State to.
	 * @return
	 * 			
	 */
	public char getInput() {
		return input;
	}

	/**
	 * @return the output
	 */
	public char getOutput() {
		return output;
	}
	
	
}
