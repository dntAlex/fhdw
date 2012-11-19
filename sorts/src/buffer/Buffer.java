package buffer;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import lock.AbstractLock;
import lock.Lock;

public class Buffer<X> {

	private List<X> entries;
	private AbstractLock lock;
	private AbstractLock criticalSection;
	
	public Buffer(){
		this.entries = new Vector<X>();
		this.lock = new Lock(true);
		this.criticalSection = new Lock(false);
	}

	public void put(X entry) {
		this.criticalSection.lock();
		this.entries.add(entry);
		this.lock.unlock();
		this.criticalSection.unlock();
	}

	public X get() {
		this.lock.lock();
		this.criticalSection.lock();
		X result = this.entries.get(0);
		this.entries.remove(0);
		if (!entries.isEmpty()) this.lock.unlock();
		this.criticalSection.unlock();
		return result;
	}
	
	public boolean isEmpty() {
		return entries.isEmpty();
	}

	public void putAll(Collection<X> collection) {
		Iterator<X> i = collection.iterator();
		while(i.hasNext()) {
			X current = i.next();
			this.put(current);
		}
	}

}
