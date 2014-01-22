package ed2;

public class Curso {
	String id;
	String nome;
	
	public Curso(String id, String nome){
		this.id = id;
		this.nome = nome;
		
	}
	
	public String toString(){
		return id + "\t" + nome;
	}

}
