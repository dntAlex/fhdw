package insertionSort;

import util.MyComparable;
import buffer.*;

public class InsertionSorter implements Runnable {

	private final Buffer<MyComparable> sorted;
	private final Buffer<MyComparable> output;
	
	private final MyComparable myComparable;
	
	private boolean isAlive;
	
	private InsertionSorter(MyComparable myComparable, final Buffer<MyComparable> input, final Buffer<MyComparable> output) {
		this.sorted = input;
		this.output = output;
		this.myComparable = myComparable;
		this.setAlive(true);
		
		new Thread(this).start();
	}

	public static InsertionSorter create(MyComparable myComparable, Buffer<MyComparable> input, Buffer<MyComparable> output) {
		return new InsertionSorter(myComparable, input, output);
	}
	
	/**
	 * First element must be a Comparable. Need to compare StopSignals with
	 * Comparables.
	 */
	@Override
	public void run() {
		while(this.isAlive) {
			this.sort();
		}
		this.placeInOutput();
	}
	
	private void placeInOutput() {
		MyComparable comp = this.getSorted().get();
		while (!comp.isStopSignal()) {
			this.getOutput().put(comp);
			comp = this.getSorted().get();
		}
		this.getOutput().put(comp);
	}

	private void sort() {
		MyComparable comp = this.getSorted().get();
		if(this.getMyComparable().lessEquals(comp)) {
			this.getOutput().put(this.getMyComparable());
			this.setAlive(false);
		}
		this.getOutput().put(comp);
	}

	public Buffer<MyComparable> getSorted() {
		return sorted;
	}
	public Buffer<MyComparable> getOutput() {
		return output;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	/**
	 * @return the myComparable
	 */
	private MyComparable getMyComparable() {
		return this.myComparable;
	}
	
}
