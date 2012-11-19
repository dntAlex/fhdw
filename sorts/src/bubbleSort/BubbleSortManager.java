package bubbleSort;

import java.util.Stack;

import lock.AbstractLock;
import lock.Lock;
import util.ComparableCollection;
import util.MyComparable;
import util.StopSignal;
import buffer.Buffer;

public class BubbleSortManager {

	private static BubbleSortManager instance = null;
	private final Stack<BubbleSorter> sorters;
	private final AbstractLock lock;
	
	/**
	 * Constructor.
	 */
	private BubbleSortManager() {
		this.sorters = new Stack<BubbleSorter>();
		this.lock = new Lock(true);
	}
	
	/**
	 * Factory method.
	 */
	public static BubbleSortManager getInstance() {
		if(instance == null) instance = new BubbleSortManager();
		return instance;
	}
	
	/**
	 * Sorts the Buffer <source> and returns the sorted result as a ComparableCollection.
	 */
	public ComparableCollection startSort(Buffer<MyComparable> source) {
		source.put(StopSignal.getInstance());
		this.nextSorter(source);
		this.getLock().lock();
		
		ComparableCollection result = new ComparableCollection();
		BubbleSorter lastSorter = getSorters().peek();
		while(!lastSorter.getTarget().isEmpty()) {
			MyComparable comp = lastSorter.getTarget().get();
			if(!comp.isStopSignal()) {
				result.add(comp);
			}
		}
		return result;
	}

	/**
	 * Creates a new BubbleSorter with the Buffer <source> as input.
	 */
	public void nextSorter(Buffer<MyComparable> source) {
		this.getSorters().push(new BubbleSorter(source, new Buffer<MyComparable>()));
	}
	
	/* Getter and Setter */
	
	public Stack<BubbleSorter> getSorters() {
		return sorters;
	}

	public AbstractLock getLock() {
		return lock;
	}
	
}
