package br.ufrrj.im.cc.ed2.classes;

public class Curso implements Comparable<Object>{
	public String id;
	public String nome;
	
	public Curso (String id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
