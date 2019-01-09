package ua.hpopov.parking.presentation.paginationwrappers;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import ua.hpopov.parking.beans.Bean;

public class PaginationBeanSet implements Iterable<BeanWrapper> {

	private BeanWrapper header;
	private Iterator<BeanWrapper> it;
	
	private PaginationBeanSet(Iterator<BeanWrapper> it, BeanWrapper header) {
		this.it = it;
		this.header = header;
	}
	
	/**
	 * 
	 * @param originBeanList a list of some Bean-type elements.
	 * @return PaginationBeanSet<T> instance upon a specifies originBeanList
	 */
	public static <T extends Bean> PaginationBeanSet makeSet(List<T> originBeanList, T header) {
		List<BeanWrapper> list = new LinkedList<>();
		Function<Bean, BeanWrapper> packer = BeanWrapperUtil.makeBeanPacker(header);
		for(T bean : originBeanList) {
			list.add(packer.apply(bean));
		}
		return new PaginationBeanSet(list.iterator(), packer.apply(header));		
	}

	public BeanWrapper getHeader() {
		return header;
	}
	
	@Override
	public Iterator<BeanWrapper> iterator() {
		return it;
	}

}
