package ua.hpopov.parking.presentation.beans;

import java.util.Iterator;
import java.util.List;

public class BeanSet<T> {
	private List<T> list;
	
	private Iterator<T> it;
	
	public BeanSet(List<T> list) {
		this.list = list;
		it = list.iterator();
	}
	
	public T getNext() {
		if (it.hasNext()) {
			return it.next();
		}
		return null;		
	}
	
}
