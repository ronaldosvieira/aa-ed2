package br.ufrrj.im.cc.ed2.main;
import java.io.IOException;

import br.ufrrj.im.cc.ed2.arquivos.Arquivo;
import br.ufrrj.im.cc.ed2.classes.Registro;
import br.ufrrj.im.cc.ed2.join.HashJoin;
import br.ufrrj.im.cc.ed2.join.NestedLoopJoin;
import br.ufrrj.im.cc.ed2.join.NestedMergeJoin;

public class Main {
	public static void main(String[] args) throws IOException {
		long tempo = System.currentTimeMillis();
		/*HashJoin hj = new HashJoin("DisciplinaHistorico", "aluno_id", "Aluno", "id");
		hj.open();
		Registro saida;
		while((saida = (Registro) hj.next()) != null) System.out.println(saida);*/
		/*NestedLoopJoin nlj = new NestedLoopJoin("Disciplina", "curso_id", "Curso", "id");
		nlj.open();
		Registro saida;
		while((saida = (Registro) nlj.next())!=null) System.out.println(saida);*/
		
		NestedMergeJoin nmj = new NestedMergeJoin("DisciplinaHistorico", "aluno_id", "Aluno", "id");
		nmj.open();
		Registro saida;
		while((saida = (Registro) nmj.next()) != null) System.out.println(saida.toString());
		
		nmj.print();
		tempo = System.currentTimeMillis() - tempo;
		System.out.println(tempo);
	}
}