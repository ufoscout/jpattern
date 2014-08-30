package com.jpattern.jobexecutor.console;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 
 * @author Francesco Cina'
 *
 * 28/set/2010
 */
public class ExecutorWrappedList<E> implements List<E> {

	private List<E> _list;
	private IExecutor<E> _executor;

	public ExecutorWrappedList(List<E> aList, IExecutor<E> executor) {
		_list = aList;
		_executor = executor;
	}

	public boolean add(E e) {
		_executor.exec(e);
		return _list.add(e);
	}

	public void add(int index, E element) {
		_executor.exec(element);
		_list.add(index, element);
	}

	public boolean addAll(Collection<? extends E> c) {
		return addAll( _list.size(), c );
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		Iterator<? extends E> iterator = c.iterator();
		while (iterator.hasNext()) {
			add(index++, iterator.next());
		}
		return true;
	}

	public void clear() {
		_list.clear();
	}

	public boolean contains(Object o) {
		return _list.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return _list.containsAll(c);
	}

	public boolean equals(Object o) {
		return _list.equals(o);
	}

	public E get(int index) {
		return _list.get(index);
	}

	public int hashCode() {
		return _list.hashCode();
	}

	public int indexOf(Object o) {
		return _list.indexOf(o);
	}

	public boolean isEmpty() {
		return _list.isEmpty();
	}

	public Iterator<E> iterator() {
		return _list.iterator();
	}

	public int lastIndexOf(Object o) {
		return _list.lastIndexOf(o);
	}

	public ListIterator<E> listIterator() {
		return _list.listIterator();
	}

	public ListIterator<E> listIterator(int index) {
		return _list.listIterator(index);
	}

	public E remove(int index) {
		return _list.remove(index);
	}

	public boolean remove(Object o) {
		return _list.remove(o);
	}

	public boolean removeAll(Collection<?> c) {
		return _list.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return _list.retainAll(c);
	}

	public E set(int index, E element) {
		return _list.set(index, element);
	}

	public int size() {
		return _list.size();
	}

	public List<E> subList(int fromIndex, int toIndex) {
		return _list.subList(fromIndex, toIndex);
	}

	public Object[] toArray() {
		return _list.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return _list.toArray(a);
	}

}
