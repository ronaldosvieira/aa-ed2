package br.ufrrj.im.cc.ed2.arvoreB;

import java.io.Serializable;
import java.util.ArrayList;

public class No <K extends Comparable<? super K>, V> implements Comparable<K>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private K chave;
	private V valor;
	private ArrayList<V> disciplinas;

	
	
	public No(K chave, V valor) {

		this.chave = chave;
		this.valor = valor;
		disciplinas = new ArrayList<V>();
		disciplinas.add(this.valor);
	}

	public K getKey() {
		return chave;
	}

	public void setKey(K chave) {
		this.chave = chave;
	}

	/**
	 * 
	 * @return Uma lista de valores relacionados a chave
	 */
	public ArrayList<V> getValue() {
		 return disciplinas;
	}

	public void setValor(V valor) {
		disciplinas.add(valor);
	}

	@Override
	public int compareTo(K arg0) {
		// TODO Auto-generated method stub
		return this.chave.compareTo(arg0);
	}
}
