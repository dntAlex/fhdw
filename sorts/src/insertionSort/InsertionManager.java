package insertionSort;

import java.util.Collection;

import lock.AbstractLock;
import lock.Lock;
import util.MyComparable;
import util.StopSignal;
import buffer.Buffer;

public class InsertionManager implements Runnable {

	private final AbstractLock lock;
	private  Buffer<MyComparable> output;
	private final Buffer<MyComparable> input;
	
	/**
	 * Constructor.
	 */
	private InsertionManager(Buffer<MyComparable> input) {
		this.lock = new Lock(true);
		this.input = input;
		
		Buffer<MyComparable> initialOutput = new Buffer<MyComparable>();
		initialOutput.put(StopSignal.getInstance());
		this.output = initialOutput;
		
		new Thread(this).start();
	}
	
	/**
	 * Factory method.
	 */
	public static InsertionManager create(Buffer<MyComparable> buffer) {
		buffer.put(StopSignal.getInstance());
		return new InsertionManager(buffer);
	}
	
	/**
	 * Factory method.
	 */
	public static InsertionManager create(Collection<MyComparable> comparableCollection) {
		Buffer<MyComparable> input = new Buffer<>();
		input.putAll(comparableCollection);
		return new InsertionManager(input);
	}
	
	@Override
	public void run() {
		this.sort();
		this.getLock().unlock();
	}

	/**
	 * Creates InsertionSorters until a StopSignal is returned from <input>. The Sorters will
	 * have the <output> Buffer of the previous created Sorter as <input> Buffer, except the first.
	 */
	public void sort() {
		MyComparable comp = this.getInput().get();
		while(!comp.isStopSignal()) {
			Buffer<MyComparable> nextOutput = new Buffer<MyComparable>();
			InsertionSorter.create(comp, this.getOutput(), nextOutput);
			this.setInputOfNextSorter(nextOutput);
			comp = this.getInput().get();
		}
	}
	
	/**
	 * Returns the first MyComparable of this output.
	 */
	public MyComparable get() {
		this.getLock().lock();
		MyComparable first = this.getOutput().get();
		this.getLock().unlock();
		return first;
	}
	
	/* Getter and Setter */

	public AbstractLock getLock() {
		return lock;
	}
	
	public Buffer<MyComparable> getOutput() {
		return output;
	}
	
	private void setInputOfNextSorter(Buffer<MyComparable> inputOfNextSorter) {
		this.output = inputOfNextSorter;
	}

	public Buffer<MyComparable> getInput() {
		return input;
	}
	
}
