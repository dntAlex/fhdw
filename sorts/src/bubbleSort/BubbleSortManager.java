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
	
	private BubbleSortManager() {
		this.sorters = new Stack<BubbleSorter>();
		this.lock = new Lock(true);
	}
	
	public static BubbleSortManager getInstance() {
		if(instance == null) instance = new BubbleSortManager();
		return instance;
	}
	
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

	public Stack<BubbleSorter> getSorters() {
		return sorters;
	}

	public AbstractLock getLock() {
		return lock;
	}

	public void nextSorter(Buffer<MyComparable> source) {
		this.getSorters().push(new BubbleSorter(source, new Buffer<MyComparable>()));
	}
	
}
