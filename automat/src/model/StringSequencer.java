package model;

public class StringSequencer {

	private int position;
	private final StringBuffer string;
	
	private StringSequencer(String string) {
		this.setPosition(0);
		this.string = new StringBuffer(string);
	}

	private StringSequencer(String string, int position) {
		this.setPosition(position);
		this.string = new StringBuffer(string);
	}
	
	public static StringSequencer create(String string) {
		return new StringSequencer(string);
	}
	
	public Character getNextChar() {
		Character res = this.getString().charAt(getPosition());
		this.setPosition(this.getPosition() + 1);
		return res; 
	}
	
	public boolean hastNextChar() {
		if(this.getPosition() == this.getString().length()) return false;
		return true;
	}
	
	public StringSequencer copy() {
		return new StringSequencer(this.getString().toString(), this.getPosition());
	}
	
	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}
	
	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	
	/**
	 * @return the string
	 */
	public StringBuffer getString() {
		return string;
	}
	
}
