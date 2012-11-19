package util;


public class StopSignal extends MyComparable {

	private static StopSignal instance = null;
	
	private StopSignal() {}

	public static StopSignal getInstance() {
		if(instance == null) instance = new StopSignal();
		return instance;
	}

	@Override
	public boolean isStopSignal() {
		return true;
	}

	@Override
	public boolean lessEquals(MyComparable comp) {
		return false;
	}

}
