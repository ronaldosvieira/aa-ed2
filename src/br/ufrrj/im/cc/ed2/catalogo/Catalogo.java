package br.ufrrj.im.cc.ed2.catalogo;

import java.util.LinkedList;
import java.util.List;

public class Catalogo {
	private static Catalogo instance;
	private List<Coluna> colunasAluno = new LinkedList<Coluna>();
	private List<Coluna> colunasCurso = new LinkedList<Coluna>();
	private List<Coluna> colunasDisciplina = new LinkedList<Coluna>();
	private List<Coluna> colunasDisciplinaHistorico = new LinkedList<Coluna>();
	private List<Item> itensCatalogo = new LinkedList<Item>();
	
	private Catalogo () {
		createColuna("Aluno", "id", 0);
		createColuna("Aluno", "curso_id", 1);
		createColuna("Aluno", "matricula", 2);
		createColuna("Aluno", "nome", 3);
		Item arquivoAluno = new Item("Alunos.txt", "Aluno", colunasAluno);
		itensCatalogo.add(arquivoAluno);
		
		createColuna("Curso", "id", 0);
		createColuna("Curso", "nome", 1);
		Item arquivoCurso = new Item("Cursos.txt", "Curso", colunasCurso);
		itensCatalogo.add(arquivoCurso);
		
		createColuna("Disciplina", "id", 0);
		createColuna("Disciplina", "nome", 1);
		createColuna("Disciplina", "curso_id", 2);
		Item arquivoDisciplina = new Item("Disciplinas.txt", "Disciplina", colunasDisciplina);
		itensCatalogo.add(arquivoDisciplina);
		
		createColuna("DisciplinaHistorico", "id", 0);
		createColuna("DisciplinaHistorico", "aluno_id", 1);
		createColuna("DisciplinaHistorico", "disciplina_id", 2);
		createColuna("DisciplinaHistorico", "nota", 3);
		createColuna("DisciplinaHistorico", "ano", 4);
		createColuna("DisciplinaHistorico", "periodo", 5);
		createColuna("DisciplinaHistorico", "situacao", 6);
		Item arquivoDisciplinaHistorico = new Item("DisciplinaHistorico.txt", "DisciplinaHistorico", colunasDisciplinaHistorico);
		itensCatalogo.add(arquivoDisciplinaHistorico);
	}
	
	
	private void createColuna(String nomeRelacao, String nome, int ordem) {
		Coluna coluna = new Coluna(nome, "String", ordem);
		if(nomeRelacao.equals("Aluno")){
			colunasAluno.add(coluna);
		} else if(nomeRelacao.equals("Curso")){
			colunasCurso.add(coluna);
		} else if(nomeRelacao.equals("Disciplina")){
			colunasDisciplina.add(coluna);
		} else {
			colunasDisciplinaHistorico.add(coluna);
		}
	}
	
	public static Catalogo getInstance(){
		if(instance == null){
			instance = new Catalogo();
		}
		return instance;		
	}
	
	public Item getMenor() {
		Item retorno = itensCatalogo.get(0);
		for(Item i : itensCatalogo) {
			if(retorno.size>i.size)
				retorno = i;
		}
		return null;
	}
	
	public Item getMaior() {
		Item retorno = itensCatalogo.get(0);
		for(Item i : itensCatalogo) {
			if(retorno.size<i.size)
				retorno = i;
		}
		return null;
	}
	
	public Item getItem(String relacao) {
		for(Item i : itensCatalogo) {
			if(i.relacao.equals(relacao))
				return i;
		}
		return null;
	}
	
	public String getFileName(String relacao) {
		for(Item i : itensCatalogo) {
			if(i.relacao.equals(relacao))
				return i.getNome();
		}
		return null;
	}
}