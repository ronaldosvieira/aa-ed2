package br.ufrrj.im.cc.ed2.main;
import java.io.IOException;



import br.ufrrj.im.cc.ed2.classes.Registro;
import br.ufrrj.im.cc.ed2.classes.Selecao;
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
		
//		NestedLoopJoin nlj = new NestedLoopJoin("Disciplina", "curso_id", "Curso", "id");
//		nlj.open();
//		Registro saida;
//		while((saida = (Registro) nlj.next())!=null) System.out.println(saida);
		
		NestedMergeJoin nmj = new NestedMergeJoin("Disciplina", "curso_id", "Curso", "id");
		nmj.open();
		Registro saida;
		while((saida = (Registro) nmj.next()) != null) System.out.println(saida.toString());		
		nmj.print();
		
//		Selecao selecao = new Selecao("DisciplinaHistorico", "id", "0d094f4b-4b16-4aec-b87b-85557eaee2f3");
//		selecao.open();
//		Registro saida;
//		while((saida = (Registro) selecao.next()) != null) System.out.println(saida.toString());
		
		tempo = System.currentTimeMillis() - tempo;
		System.out.println(tempo);
	}
}