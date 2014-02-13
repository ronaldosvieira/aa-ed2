package br.ufrrj.im.cc.ed2.classes;

public class Aluno implements Comparable<Object> {
	public String id;
	public String curso_id;
	public String matricula;
	public String nome;
	
	public Aluno (String id, String curso_id, String matricula, String nome) {
		this.id = id;
		this.curso_id = curso_id;
		this.matricula = matricula;
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "["+matricula+"]" + "	" + nome;
	}
	
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
