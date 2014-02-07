package br.ufrrj.im.cc.ed2.main;
import java.io.IOException;

import br.ufrrj.im.cc.ed2.catalogo.Catalogo;
import br.ufrrj.im.cc.ed2.join.HashJoin;
import br.ufrrj.im.cc.ed2.join.NestedLoop;
import br.ufrrj.im.cc.ed2.join.NestedMerge;

public class Main {
	public static void main(String[] args) throws IOException {
		long tempo = System.currentTimeMillis();
//		HashJoin hj = new HashJoin("DisciplinaHistorico", "aluno_id", "Aluno", "id");
//		hj.open();
//		String saida;
//		while((saida=hj.next()) != null) System.out.println(saida);
		
//		NestedLoop hj = new NestedLoop("DisciplinaHistorico", "aluno_id", "Aluno", "id");
//		hj.open();
//		String saida;
//		while((saida=hj.next()) != null ) System.out.println(saida);
		
		NestedMerge hj = new NestedMerge("DisciplinaHistorico", "aluno_id", "Aluno", "id");
		hj.open();		
		String saida;
		while((saida=hj.next()) != null ) System.out.println(saida);
		
		tempo = System.currentTimeMillis() - tempo;
		System.out.println(tempo +"ms");
		/*Arquivo file = new Arquivo("Aluno");
		file.open();
		long posicao;
		String linha = file.next();
		posicao = file.retornaPosicao();
		System.out.println(posicao + "	" + linha);
		System.out.println(file.buscarRegistro(posicao));
		linha = file.next();
		posicao = file.retornaPosicao();
		System.out.println(posicao + "	" + linha);
		System.out.println(file.buscarRegistro(posicao));*/		
	}
}