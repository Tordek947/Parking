package ua.hpopov.parking.presentation.paginationwrappers;

import java.util.Iterator;

public class BeanSet<T> {
	private Iterator<T> it;
	
	public BeanSet(Iterator<T> it) {
		this.it = it;
	}
	
	public T getNext() {
		if (it.hasNext()) {
			return it.next();
		}
		return null;		
	}
	
}
