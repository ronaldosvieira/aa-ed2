package ed2;

public class Disciplina {
	
	public String id;
	public String nome;
	public String curso_id;
	
	public Disciplina(String id,String nome,String curso_id){
		this.id = id;
		this.nome = nome;
		this.curso_id = curso_id;
	}
	
	
	public String toString(){
		return id + "\t" + nome + "\t" + curso_id;
	}

}
