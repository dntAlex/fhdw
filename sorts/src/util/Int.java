package util;

public class Int extends MyComparable {

	private final int value;
	
	private Int(int value) {
		this.value = value;
	}
	
	public static Int create(int value) {
		return new Int(value);
	}

	@Override
	public boolean lessEquals(MyComparable comp) {
		if (comp instanceof Int) return this.getValue() <= ((Int) comp).getValue();
		else if(comp instanceof StopSignal) return true;
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Int) return this.getValue() == ((Int) obj).getValue();
		return false;
	}

	public int getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.getValue());
	}

	@Override
	public boolean isStopSignal() {
		return false;
	}

}
