package br.ufrrj.im.cc.ed2.classes;

public class Disciplina implements Comparable<Object>{
	public String id;
	public String nome;
	public String curso_id;
	
	public Disciplina (String id, String nome, String curso_id) {
		this.id = id;
		this.nome = nome;
		this.curso_id = curso_id;
	}
	
	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
