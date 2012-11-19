package mergeSort;

import util.MyComparable;
import buffer.Buffer;

public class MergeSorter implements Runnable {

	private final Buffer<MyComparable> input;
	private final Buffer<MyComparable> output;

	private final Buffer<MyComparable> left;
	private final Buffer<MyComparable> right;
	
	private Buffer<MyComparable> current;
	
	private boolean isAlive;

	private MergeSorter(Buffer<MyComparable> input) {
		this.input = input;
		this.output = new Buffer<MyComparable>();
		this.left = new Buffer<MyComparable>();
		this.right = new Buffer<MyComparable>();
		this.setAlive(true);
		
		new Thread(this).start();
	}
	
	public static MergeSorter create(Buffer<MyComparable> input) {
		return new MergeSorter(input);
	}
	
	@Override
	public void run() {
		while(this.isAlive()) {
			this.sort();
		}
	}

	private void sort() {

	}
	
	public MyComparable getSmallestElement() {
		return this.getOutput().get();
	}
	

	/* Getter and Setter */
	
	public Buffer<MyComparable> getInput() {
		return input;
	}

	public Buffer<MyComparable> getOutput() {
		return output;
	}

	private boolean isAlive() {
		return isAlive;
	}

	private void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	/**
	 * @return the left
	 */
	public Buffer<MyComparable> getLeft() {
		return left;
	}

	/**
	 * @return the current
	 */
	public Buffer<MyComparable> getCurrent() {
		return current;
	}

	/**
	 * @param current the current to set
	 */
	public void setCurrent(Buffer<MyComparable> current) {
		this.current = current;
	}

	/**
	 * @return the right
	 */
	public Buffer<MyComparable> getRight() {
		return right;
	}

}
