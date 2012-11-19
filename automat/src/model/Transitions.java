package model;

import java.util.Iterator;
import java.util.LinkedList;

public class Transitions extends LinkedList<Transition> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Factory method for Transition.
	 * @return
	 * 			Transition
	 */
	public static Transitions create() {
		return new Transitions();
	}
	
	public Transitions getStates(State from, char input) {
		Transitions result = Transitions.create();
		Iterator<Transition> i = this.iterator();
		while (i.hasNext()) {
			Transition current = i.next();
			if(current.getFrom() == from && current.getInput() == input) {
				result.add(current);
			}
		}
		return result;
	}
	
	@Override
	public String toString() {
		String result = "{";
		Iterator<Transition> i = this.iterator();
		while (i.hasNext()) {
			Transition current = i.next();
			result += current.toString() + " ";
		}
		return result += "}";
	}
	
}