package i_o;

public class CharElement extends IO_Element {

	private final IO_Element master;
	private final Character myChar;
	
	public CharElement(IO_Element master, Character myChar) {
		this.master = master;
		this.myChar = myChar;
	}

	public IO_Element getMaster() {
		return this.master;
	}

	public Character getMyChar() {
		return this.myChar;
	}
	
	@Override
	public String resolve() {
		return getMaster().resolve() + this.getMyChar(); 
	}
	
}
