package ed2;

public class Aluno {
	
	public String id;
	public String curso_id;
	public String matricula;
	public String nome;
	
	public Aluno(String id, String curso_id, String matricula, String nome){
		this.id = id;
		this.curso_id = curso_id;
		this.matricula = matricula;
		this.nome = nome;
	}
	
	public int hashCode(int length) {
		return Integer.parseInt(matricula)%length;
	}
	
	public String toString(){
		return id + "\t" + curso_id + "\t" + matricula + "\t" + nome;
	}
	
	
	
}
