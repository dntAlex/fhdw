package util;

import java.util.Iterator;
import java.util.Vector;


public class ComparableCollection {
	
	private final Vector<MyComparable> comparables;
	
	public ComparableCollection() {
		this.comparables = new Vector<MyComparable>();
	}
	
	public void add(MyComparable comp) {
		this.getComparables().add(comp);
	}

	public boolean equals(ComparableCollection cCollection) {
		if(this.size() == cCollection.size()) {
			for(int i = 0; i < this.size(); i++) {
				if(this.get(i) != cCollection.get(i)) return false;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String toString = "[";
		Iterator<MyComparable> i = this.getComparables().iterator();
		while(i.hasNext()) {
			MyComparable current = i.next();
			toString  += current + ", ";
		}
		int commaSpace = toString.length();
		if(toString.length() > 2) commaSpace = toString.length() - 2;
		
		return toString.substring(0, commaSpace) + "]";
	}
	
	public MyComparable get(int index) {
		return this.get(index);
	}
	
	public int size() {
		return this.getComparables().size();
	}
	
	public Vector<MyComparable> getComparables() {
		return comparables;
	}
	
}
