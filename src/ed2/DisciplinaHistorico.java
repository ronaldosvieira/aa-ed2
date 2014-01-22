package ed2;

public class DisciplinaHistorico {
	String id;
	String aluno_id;
	String disciplina_id;
	String nota;
	String ano;
	String periodo;
	String situacao;
	
	public DisciplinaHistorico(String id , String aluno_id, String disciplina_id, String nota, String ano, String periodo, String situacao){
		this.id = id;
		this.aluno_id = aluno_id;
		this.disciplina_id = disciplina_id;
		this.nota = nota;
		this.ano = ano;
		this.periodo = periodo;
		this.situacao = situacao;
	}
	
	
	public String toString(){
		return id + "\t" + aluno_id + "\t" + disciplina_id + "\t" + nota + "\t" + ano + "\t" + periodo + "\t" + situacao;
	}

}
