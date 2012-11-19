package model;

import i_o.CharElement;
import i_o.EndElement;
import i_o.IO_Element;

import java.util.Iterator;

/**
 * Represents a Configuration of a non deterministic Automaton.
 * @author Admin
 *
 */
public class Configuration implements Runnable {
	
	private StringSequencer input;
	private State currentState;
	private final Automaton automaton;
	private IO_Element output;
	private boolean isAlive;
	
	private Configuration(Automaton automaton) {
		this.input = StringSequencer.create("");
		this.currentState = automaton.getStartState();
		this.automaton = automaton;
		this.output = new EndElement();
		this.isAlive = true;
		
		ConfigurationManager.getInstance().register(this);
	}
	
	/**
	 * Constructor for Configuration.
	 * @param output 
	 */
	private Configuration(StringSequencer input, State currentState, Automaton master, IO_Element output) {
		this.input = input;
		this.currentState = currentState;
		this.automaton = master;
		this.output = output;
		this.setAlive(true);
		
		ConfigurationManager.getInstance().register(this);
	}
	
	public static Configuration create(Automaton automaton) {
		return new Configuration(automaton);
	}
	
	public void start(String input) {
		this.setInput(StringSequencer.create(input));
		new Thread(this).start();
	}
	
	public void start() {
		new Thread(this).start();
	}
	
	/**
	 *  If the input of this Configuration is not empty, this method will
	 *  process this Configuration into it's next Configuration(s).
	 */
	private void process(){
		if(input.hastNextChar()) this.step();
		else if(this.isEndconfiguration()) {
			ConfigurationManager.getInstance().finished(this.getOutput());
		}
		else {
			ConfigurationManager.getInstance().deregister(this);
			this.setAlive(false);
		}
	}
	
	@Override
	public void run() {
		while(this.isAlive()) {
			this.process();
		}
	}
	
	/**
	 * Creates all new Configurations which can be reached when processing
	 * all actual States with input. All new Configurations will be processed.
	 * @param input char
	 */
	public void step() {
		Transitions transitions = this.getCurrentState().getNextStates(this.getInput().getNextChar());
		int transitionCount = transitions.size();
		if(transitionCount == 0) this.setAlive(false);
		else {
			Iterator<Transition> i = transitions.iterator(); 
			while(i.hasNext()) {
				if(!this.isAlive()) return;
				Transition current = i.next();
				IO_Element nextOutput = new CharElement(this.getOutput(), current.getOutput());
				if(transitionCount != 1) {
					Configuration nextConfig = new Configuration(this.getInput().copy(), current.getTo(), automaton, nextOutput);
					nextConfig.start();
					transitionCount--;
				}
				else {
					this.setCurrentState(current.getTo());
					this.setOutput(nextOutput);
				}
			}
			this.process();
		}
	}
	
	/**
	 * Returns true if this Configuration contains an endState.
	 */
	public boolean isEndconfiguration() {
		return this.getCurrentState().equals(this.getAutomaton().getEndState());
	}
	
	/* Getters and Setters */

	/**
	 * Returns a StateSet with all States in which the Automaton is a the moment.
	 * @return
	 * 		StateSet states
	 */
	public State getCurrentState() {
		return currentState;
	}
	
	public void setCurrentState(State state) {
		this.currentState = state;
	}

	/**
	 * Returns the Input of this Configuration.
	 */
	public StringSequencer getInput() {
		return this.input;
	}

	public void setInput(StringSequencer input) {
		this.input = input;
	}

	/**
	 * @return the master
	 */
	public Automaton getAutomaton() {
		return automaton;
	}
	
	public IO_Element getOutput() {
		return output;
	}
	
	private void setOutput(IO_Element output) {
		this.output = output;
	}

	/**
	 * @return the isAlive
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * @param isAlive the isAlive to set
	 */
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
}
