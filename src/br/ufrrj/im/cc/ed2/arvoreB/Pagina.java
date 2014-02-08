package arvoreB;

import java.io.Serializable;
import java.util.ArrayList;


public class Pagina<K extends Comparable<? super K>, V> implements Serializable{

	ArrayList<No<K, V>> nos;
	boolean folha;
	ArrayList<Pagina<K, V>> filhos;
	int n;

	public Pagina() {

		this.nos = new ArrayList<No<K, V>>();
		this.filhos = new ArrayList<Pagina<K, V>>();
		folha = true;
		n = 0;
	}

	/**
	 * Verifica se a página é uma folha
	 * 
	 * @return falso ou verdadeiro
	 */
	public boolean ehFolha() {
		return this.folha;
	}

	public void setFolha(boolean folha) {
		this.folha = folha;
	}

	public boolean getFolha() {
		return this.folha;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getN() {
		return this.n;
	}

	public void setFilhos(ArrayList<Pagina<K, V>> filhos) {
		this.filhos = filhos;
	}

	public ArrayList<Pagina<K, V>> getFilhos() {
		return this.filhos;
	}

	public void setNos(ArrayList<No<K, V>> nos) {
		this.nos = nos;
	}

	public ArrayList<No<K, V>> getNos() {
		return nos;
	}

	// Imprime nos indice
	public void imprimeNo() {
		for (No<K, V> no : nos) {
			System.out.println(no.getKey() + ": " + no.getValue().size());
		}
	}

}
