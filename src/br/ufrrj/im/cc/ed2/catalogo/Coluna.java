package br.ufrrj.im.cc.ed2.catalogo;

import java.util.TreeMap;

public class Coluna implements Comparable<Object>{
	
	public String nome; //name of column
	public String tipo; //data type
	public int ordem; //position at the file
	public int registrosDistintos; //number of distinct registers
	public TreeMap<String, Integer> histograma = new TreeMap<String, Integer>(); //frequency of the occurrence of each register
	
	public Coluna (String nome, String tipo, int ordem) {
		this.nome = nome;
		this.tipo = tipo;
		this.ordem = ordem;
	}
	
	@Override
	public int compareTo(Object obj) {
		if(obj instanceof Coluna){
			Coluna column = (Coluna) obj;
			if(column.ordem < ordem)
				return 1;
			else if(column.ordem > ordem)
				return -1;
		}
		return 0;
	}
	
	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		return "Coluna: ["+nome+", "+tipo+", "+ordem+"]";
	}
	
	public int registrosDistintos() {
		return registrosDistintos;
	}
}
