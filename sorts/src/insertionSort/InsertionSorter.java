package insertionSort;

import util.MyComparable;
import buffer.*;

public class InsertionSorter implements Runnable {

	private final Buffer<MyComparable> sorted;
	private final Buffer<MyComparable> output;
	
	private final MyComparable myComparable;
	
	private boolean isAlive;
	
	/**
	 * Constructor.
	 */
	private InsertionSorter(MyComparable myComparable, final Buffer<MyComparable> input, final Buffer<MyComparable> output) {
		this.sorted = input;
		this.output = output;
		this.myComparable = myComparable;
		this.setAlive(true);
		
		new Thread(this).start();
	}

	/**
	 * Factory method.
	 */
	public static InsertionSorter create(MyComparable myComparable, Buffer<MyComparable> input, Buffer<MyComparable> output) {
		return new InsertionSorter(myComparable, input, output);
	}
	
	@Override
	public void run() {
		while(this.isAlive()) {
			this.sort();
		}
		this.placeInOutput();
	}
	
	/**
	 * Puts every element if this <sorted> into this output until a
	 * StopSignal is returned by this <sorted>.
	 */
	private void placeInOutput() {
		MyComparable comp = this.getSorted().get();
		while (!comp.isStopSignal()) {
			this.getOutput().put(comp);
			comp = this.getSorted().get();
		}
		this.getOutput().put(comp);
	}
	
	/**
	 * Puts the element returned by this <sorted> into this <output> if
	 * it is greater than this <comparable>. Otherwise this <comparable> will
	 * be put into this <output> and the Sorter terminates it's Thread. 
	 */
	private void sort() {
		MyComparable comp = this.getSorted().get();
		if(this.getMyComparable().lessEquals(comp)) {
			this.getOutput().put(this.getMyComparable());
			this.setAlive(false);
		}
		this.getOutput().put(comp);
	}
	
	/* Getter and Setter */
	
	public Buffer<MyComparable> getSorted() {
		return this.sorted;
	}
	public Buffer<MyComparable> getOutput() {
		return this.output;
	}

	private boolean isAlive() {
		return this.isAlive;
	}

	private void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	private MyComparable getMyComparable() {
		return this.myComparable;
	}
	
}
