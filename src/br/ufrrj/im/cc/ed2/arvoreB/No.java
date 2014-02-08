package arvoreB;

import java.io.Serializable;
import java.util.ArrayList;

public class No <K extends Comparable<? super K>, V> implements Comparable<K>, Serializable {
	
	private K chave;
	private V valor;
	private ArrayList<V> listaLivros;

	
	
	public No(K chave, V valor) {

		this.chave = chave;
		this.valor = valor;
		listaLivros = new ArrayList<V>();
		listaLivros.add(this.valor);
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
		 return listaLivros;
	}

	public void setValor(V valor) {
		listaLivros.add(valor);
	}

	@Override
	public int compareTo(K arg0) {
		// TODO Auto-generated method stub
		return this.chave.compareTo(arg0);
	}
}
