package br.ufrrj.im.cc.ed2.arquivos;

import java.util.ArrayList;
import java.util.List;

public class HashTable<T> {
	public List<T> table = new ArrayList<T>();
	
	public HashTable (int size) {
		for(int i=0; i<size; i++) table.add(null);
	}
	
	public void insert(T element) {
		int index = element.hashCode();
		table.add(index, element);
	}
	
	public T get(int index) {
		return table.get(index);		
	}

	public void remove(T element) {
		if (table.contains(element)) {
			table.remove(element);
		}
	}
}
