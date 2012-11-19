package quickSort;

import util.MyComparable;
import buffer.Buffer;

public class QuickSort implements Runnable {

	private final Buffer<MyComparable> input;
	private final Buffer<MyComparable> output;
	private final Buffer<MyComparable> left;
	private final Buffer<MyComparable> right;
	
	private boolean isAlive;
	
	/**
	 * Constructor.
	 */
	private QuickSort(Buffer<MyComparable> input) {
		this.input = input;
		this.output = new Buffer<MyComparable>();
		this.left = new Buffer<MyComparable>();
		this.right = new Buffer<MyComparable>();
		this.setAlive(true);
		
		new Thread(this).start();
	}
	
	/**
	 * Factory method.
	 */
	public static QuickSort create(Buffer<MyComparable> input) {
		return new QuickSort(input);
	}
	
	@Override
	public void run() {
		final MyComparable pivot = this.getInput().get();
		if(pivot.isStopSignal()) {
			this.setAlive(false);
			this.getOutput().put(pivot);
		} else {
			Buffer<MyComparable> leftOut = QuickSort.create(this.getLeft()).getOutput();
			Buffer<MyComparable> rightOut = QuickSort.create(this.getRight()).getOutput();
			Merger.create(leftOut, rightOut, this.getOutput(), pivot);
			
			while(this.isAlive()) {
				this.split(pivot);
			}
		}
	}
	
	/**
	 * If the element returned by this <input> is an StopSignal, it will be put into this
	 * <left> and <right> buffer and this Thread will be killed. Otherwise the element is
	 * compared with <pivot> and put to <left> if it is smaller or equals <pivot>. Otherwise
	 * it will be placed into <right>.
	 * @param pivot
	 */
	private void split(MyComparable pivot) {
		final MyComparable comp = this.getInput().get();
	
		if(!comp.isStopSignal()) {
			if(comp.lessEquals(pivot)) QuickSort.this.getLeft().put(comp);
			else QuickSort.this.getRight().put(comp);
		} else {
			QuickSort.this.getLeft().put(comp);
			QuickSort.this.getRight().put(comp);
			this.setAlive(false);
		}
		
	}
	
	/**
	 * Returns the first element returned by this output.
	 */
	public MyComparable getFirstElement() {
		return this.getOutput().get();
	}

	/* Getter and Setter */
	
	/**
	 * @return the input
	 */
	public Buffer<MyComparable> getInput() {
		return input;
	}

	/**
	 * @return the output
	 */
	public Buffer<MyComparable> getOutput() {
		return output;
	}

	/**
	 * @return the isAlive
	 */
	private boolean isAlive() {
		return isAlive;
	}

	/**
	 * @param isAlive the isAlive to set
	 */
	private void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	/**
	 * @return the left
	 */
	private Buffer<MyComparable> getLeft() {
		return left;
	}

	/**
	 * @return the right
	 */
	private Buffer<MyComparable> getRight() {
		return right;
	}
	
}
