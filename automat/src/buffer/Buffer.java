package buffer;

import java.util.List;
import java.util.Vector;

import lock.AbstractLock;
import lock.Lock;


public class Buffer {

	private List<BufferEntry> entries;
	private AbstractLock lock;
	private AbstractLock criticalSection;
	
	public Buffer(){
		this.entries = new Vector<BufferEntry>();
		this.lock = new Lock(true);
		this.criticalSection = new Lock(false);
	}

	public void put(BufferEntry entry) {
		this.criticalSection.lock();
		this.entries.add(entry);
		this.lock.unlock();
		this.criticalSection.unlock();
	}

	public BufferEntry get() {
		this.lock.lock();
		this.criticalSection.lock();
		BufferEntry result = this.entries.get(0);
		this.entries.remove(0);
		if (!entries.isEmpty()) this.lock.unlock();
		this.criticalSection.unlock();
		return result;
	}
	
	public boolean isEmpty() {
		return entries.isEmpty();
	}

}
