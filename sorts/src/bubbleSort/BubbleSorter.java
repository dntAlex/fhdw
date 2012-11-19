package bubbleSort;

import util.MyComparable;
import util.StopSignal;
import buffer.Buffer;

public class BubbleSorter implements Runnable {
	
	private final Buffer<MyComparable> source;
	private final Buffer<MyComparable> target;
	private MyComparable left;
	private MyComparable right;
	private boolean alive;
	private boolean swapped;
	
	/**
	 * Constructor.
	 */
	public BubbleSorter(Buffer<MyComparable> source, Buffer<MyComparable> target) {
		this.source = source;
		this.target = target;
		this.setAlive(true);
		this.setSwapped(false);
		new Thread(this, "").start();
	}
	
	@Override
	public void run() {
		this.setRight((MyComparable) this.getSource().get());
		while(this.isAlive()) {
			this.sort();
		}
	}
	
	/**
	 * Compares the two MyComparables <left> and <right> if <left> is not a StopSignal. 
	 * Puts <right> to the target buffer if it is smaller or equals <left>, otherwise 
	 * <left> will be given to the <target> buffer. If <left> is a StopSignal, <right> 
	 * and a StopSignal will be given to the <target> buffer and this instance will
	 * kill it's Thread.
	 */
	private void sort() {
		MyComparable comp = this.getSource().get();
		
		if(!comp.isStopSignal()) {
			this.setLeft((MyComparable) comp);
			if(this.getRight().lessEquals(this.getLeft()))	{
				this.getTarget().put(getRight());
				this.setRight(getLeft());
			} else {
				this.setSwapped(true);
				this.getTarget().put(this.getLeft());
			}
		} else {
			this.getTarget().put(this.getRight());
			this.getTarget().put(StopSignal.getInstance());
			if(!this.isSwapped()) BubbleSortManager.getInstance().getLock().unlock();
			this.setAlive(false);
		}
	}
	
	/**
	 * Set this swapped to swapped.
	 * If a true swap occurs for the first time, a new BubbleSorter will be created with this
	 * target as input. 
	 */
	public void setSwapped(boolean swapped) {
		if(!isSwapped() && swapped) BubbleSortManager.getInstance().nextSorter(this.getTarget());
		this.swapped = swapped;
	}
	
	/* Getter and Setter */

	public Buffer<MyComparable> getSource() {
		return source;
	}

	public MyComparable getLeft() {
		return left;
	}

	public void setLeft(MyComparable left) {
		this.left = left;
	}

	public MyComparable getRight() {
		return right;
	}

	public void setRight(MyComparable right) {
		this.right = right;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public Buffer<MyComparable> getTarget() {
		return target;
	}

	public boolean isSwapped() {
		return swapped;
	}
	
}
