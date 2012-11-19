package test;

import static org.junit.Assert.*;

import model.Automaton;
import model.ConfigurationManager;
import model.Configuration;
import model.State;
import model.Transition;

import org.junit.Test;

public class AutomatonTest {

	ConfigurationManager mng = ConfigurationManager.getInstance();
	
//	@Test
	public void testNextStates() {
		Automaton a = Automaton.create();
		State s0 = State.create("s0", a);
		State s1 = State.create("s1", a);
		
		Transition t0 = Transition.create(a.getStartState(), 'a', s0, '0');
		Transition t1 = Transition.create(s0, 'b', s1, '1');
		Transition tx = Transition.create(s0, 'b', a.getEndState(), 'y');
		Transition t2 = Transition.create(s1, 'c', a.getEndState(), '2');

		a.getTransitions().add(t0);
		a.getTransitions().add(t1);
		a.getTransitions().add(t2);
		a.getTransitions().add(tx);
		
		Configuration c = Configuration.create(a);
		c.start("abc");
	}
	
//	@Test
	public void testSimple() {
		Automaton a = Automaton.create();
		State s0 = State.create("s0", a);

		Transition t2 = Transition.create(s0, 'y', a.getEndState(), ';');
		Transition t1 = Transition.create(a.getStartState(), 'x', a.getEndState(), '!');
		Transition t0 = Transition.create(a.getStartState(), 'x', s0, '?');
		
		a.getTransitions().add(t0);
		a.getTransitions().add(t1);
		a.getTransitions().add(t2);
		
		assertEquals(true, true);
	}
	
	@Test
	public void testSimple2() {
		Automaton a = Automaton.create();
		State s0 = State.create("s0", a);
		State s1 = State.create("s1", a);

		Transition t0 = Transition.create(a.getStartState(), 'x', s0, '!');
		Transition t1 = Transition.create(a.getStartState(), 'x', s1, '?');
		
		Transition t2 = Transition.create(s0, 'y', a.getEndState(), ';');
		Transition t3 = Transition.create(s1, 'y', a.getEndState(), '.');
		
		a.getTransitions().add(t0);
		a.getTransitions().add(t1);
		a.getTransitions().add(t2);
		a.getTransitions().add(t3);
		
		Configuration c = Configuration.create(a);
		c.start("xy");
		
		assertEquals(true, true);
	}
	
//	@Test
	public void test() {
		Automaton a = Automaton.create();
		
		State s0 = State.create("s0", a);
		State s1 = State.create("s1", a);
		State s2 = State.create("s2", a);
		State s3 = State.create("s3", a);
		State s4 = State.create("s4", a);
		
		Transition t1 = Transition.create(a.getStartState(), 'a', s0, '1');
		Transition t2 = Transition.create(a.getStartState(), 'a', s1, '!');
		Transition t3 = Transition.create(s0, 'b', s2, '2');
		Transition t4 = Transition.create(s0, 'b', s3, '<');
		Transition t5 = Transition.create(s1, 'c', s4, '3');
		Transition t6 = Transition.create(s1, 'c', a.getEndState(), ';');
		Transition t7 = Transition.create(s2, 'd', s4, '-');
		Transition t8 = Transition.create(s2, 'd', a.getEndState(), 'x');
		Transition t9 = Transition.create(s4, 'a', a.getEndState(), 'w');
		Transition t10 = Transition.create(s3, 'c', a.getEndState(), 'C');
		
		a.getTransitions().add(t1);
		a.getTransitions().add(t2);
		a.getTransitions().add(t3);
		a.getTransitions().add(t4);
		a.getTransitions().add(t5);
		a.getTransitions().add(t6);
		a.getTransitions().add(t7);
		a.getTransitions().add(t8);
		a.getTransitions().add(t9);
		a.getTransitions().add(t10);
	}

}
