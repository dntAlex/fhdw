package insertionSort;

import java.util.Collection;

import lock.AbstractLock;
import lock.Lock;
import util.MyComparable;
import util.StopSignal;
import buffer.Buffer;

public class InsertionManager implements Runnable {

	private final AbstractLock lock;
	private  Buffer<MyComparable> inputOfNextSorter;
	private final Buffer<MyComparable> input;
	
	private InsertionManager(Buffer<MyComparable> input) {
		this.lock = new Lock(true);
		this.input = input;
		
		Buffer<MyComparable> initialInput = new Buffer<MyComparable>();
		initialInput.put(StopSignal.getInstance());
		this.inputOfNextSorter = initialInput;
		
		new Thread(this).start();
	}
	
	public static InsertionManager create(Buffer<MyComparable> buffer) {
		buffer.put(StopSignal.getInstance());
		return new InsertionManager(buffer);
	}
	
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

	public void sort() {
		MyComparable comp = this.getInput().get();
		while(!comp.isStopSignal()) {
			Buffer<MyComparable> nextOutput = new Buffer<MyComparable>();
			InsertionSorter.create(comp, this.getInputOfNextSorter(), nextOutput);
			this.setInputOfNextSorter(nextOutput);
			comp = this.getInput().get();
		}
	}
	
	public MyComparable getSmallestRemaining() {
		this.getLock().lock();
		MyComparable smallest = this.getInputOfNextSorter().get();
		this.getLock().unlock();
		return smallest;
	}
	
	/* Getter and Setter */

	public AbstractLock getLock() {
		return lock;
	}
	
	public Buffer<MyComparable> getInputOfNextSorter() {
		return inputOfNextSorter;
	}
	
	private void setInputOfNextSorter(Buffer<MyComparable> inputOfNextSorter) {
		this.inputOfNextSorter = inputOfNextSorter;
	}

	public Buffer<MyComparable> getInput() {
		return input;
	}
	
}
