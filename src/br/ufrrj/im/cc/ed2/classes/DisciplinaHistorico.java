package br.ufrrj.im.cc.ed2.classes;

public class DisciplinaHistorico implements Comparable<Object> {
	public String id;
	public String aluno_id;
	public String disciplina_id;
	public String nota;
	public String ano;
	public String periodo;
	public String situacao;
	
	public DisciplinaHistorico (String id, String nota, String periodo, String situacao) {
		
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
