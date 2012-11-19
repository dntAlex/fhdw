package quickSort;

import util.MyComparable;
import util.StopSignal;
import buffer.Buffer;

public class Merger implements Runnable {

	private final Buffer<MyComparable> leftSource;
	private final Buffer<MyComparable> rightSource;
	private final Buffer<MyComparable> output;
	
	private final MyComparable pivot;
	
	private boolean isAlive;
	
	public Merger(Buffer<MyComparable> left, Buffer<MyComparable> right, Buffer<MyComparable> output, MyComparable pivot) {
		this.leftSource = left;
		this.rightSource = right;
		this.output = output;
		this.pivot = pivot;
		this.setAlive(true);
		new Thread(this).start();
	}

	public static Merger create(Buffer<MyComparable> left, Buffer<MyComparable> right, Buffer<MyComparable> output, MyComparable pivot) {
		return new Merger(left, right, output, pivot);
	}

	@Override
	public void run() {
		while(this.isAlive) {
			this.merge();
		}
	}
	
	private void merge() {
		this.out(this.getLeftSource());
		this.getOutput().put(this.getPivot());
		this.out(this.getRightSource());
		this.getOutput().put(StopSignal.getInstance());
	}
	
	private void out(Buffer<MyComparable> buffer) {
		MyComparable comp = buffer.get();
		if(!comp.isStopSignal()) {
			this.getOutput().put(comp);
			this.out(buffer);
		}
	}
	
	/* Getter and Setter */

	/**
	 * @return the leftSource
	 */
	public Buffer<MyComparable> getLeftSource() {
		return leftSource;
	}

	/**
	 * @return the rightSource
	 */
	public Buffer<MyComparable> getRightSource() {
		return rightSource;
	}

	/**
	 * @return the output
	 */
	public Buffer<MyComparable> getOutput() {
		return output;
	}

	/**
	 * @return the pivot
	 */
	public MyComparable getPivot() {
		return pivot;
	}

	/**
	 * @return the isAlive
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * @param isAlive the isAlive to set
	 */
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

}
