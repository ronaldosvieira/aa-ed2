package br.ufrrj.im.cc.ed2.main;
import java.io.IOException;







import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import br.ufrrj.im.cc.ed2.arquivos.Arquivo;
import br.ufrrj.im.cc.ed2.arquivos.HashTable;
import br.ufrrj.im.cc.ed2.arvoreB.ArvoreB;
import br.ufrrj.im.cc.ed2.arvoreB.No;
import br.ufrrj.im.cc.ed2.classes.ColunaRegistro;
import br.ufrrj.im.cc.ed2.classes.Comparador;
import br.ufrrj.im.cc.ed2.classes.ComparadorDisciplinaHistorico;
import br.ufrrj.im.cc.ed2.classes.Registro;
import br.ufrrj.im.cc.ed2.classes.Selecao;
import br.ufrrj.im.cc.ed2.join.HashJoin;
import br.ufrrj.im.cc.ed2.join.NestedLoopJoin;
import br.ufrrj.im.cc.ed2.join.NestedMergeJoin;
import br.ufrrj.im.cc.ed2.projecoes.ProjecaoFeitaComSelecao;

public class Main {
	public static <T> void main(String[] args) throws IOException {
		long tempo = System.currentTimeMillis();
		
		//hashJoin funcionando
		/*HashJoin hj = new HashJoin("DisciplinaHistorico", "aluno_id", "Aluno", "id");
		hj.open();
		Registro saida;
		while((saida = (Registro) hj.next()) != null) System.out.println(saida);*/

		//nestedloopFuncionando
//		NestedLoopJoin nlj = new NestedLoopJoin("Disciplina", "curso_id", "Curso", "id");
//		nlj.open();
//		Registro saida;
//		while((saida = (Registro) nlj.next())!=null) System.out.println(saida);

		//nestedMerge funcionando
//		NestedMergeJoin nmj = new NestedMergeJoin("Disciplina", "curso_id", "Curso", "id");
//		nmj.open();
//		Registro saida;
//		while((saida = (Registro) nmj.next()) != null) System.out.println(saida.toString());		
//		nmj.print();
		
			//selecao funcionando	
//		Selecao selecao = new Selecao("DisciplinaHistorico", "id", "0d094f4b-4b16-4aec-b87b-85557eaee2f3");
//		selecao.open();
//		Registro saida;
//		while((saida = (Registro) selecao.next()) != null) System.out.println(saida.toString());
		
		
//		ArvoreB arvore = new ArvoreB();	
//		
//		Registro saida2;
//		//carrega o id , ponteiro em uma hashmap
//		Arquivo arquivo = new Arquivo("Disciplina");
//		arquivo.open();
//		HashMap<String,Long> hash = new HashMap<String,Long>();
//		
//		while((saida2=(Registro)arquivo.next())!=null){
//			Registro iddisciplina = new Registro(saida2.toString(),"Disciplina",arquivo.retornaPosicao());	
//			hash.put(iddisciplina.getValor("id").toString(),arquivo.retornaPosicao());
//			
//		}
//		
//		
//
//		
//		Arquivo arq = new Arquivo("DisciplinaHistorico");
//		arq.open();
//		Registro saida;	
//		List<Registro> aluno = new ArrayList<Registro>();
//
//		
//		Registro alunoid=null;
//		while((saida = (Registro) arq.next()) != null){ 
//			alunoid = new Registro(saida.toString(),"DisciplinaHistorico",arq.retornaPosicao());
//			aluno.add(alunoid);
//					
//		}
//		
//		Collections.sort((List)aluno,new ComparadorDisciplinaHistorico());
//		
//		No no=null;
//		Registro novo = aluno.get(0);
//		String id =novo.getValor("aluno_id").toString();
//		String concatenada = novo.getValor("aluno_id").toString()+"\t"+novo.getValor("ano")+"\t"+novo.getValor("periodo");
//		no = new No(concatenada, hash.get(novo.getValor("disciplina_id")));
//		no.setKey(concatenada);
//		no.setValor(hash.get(novo.getValor("disciplina_id")));
//		arvore.add(no);
//		for(int i=1;i<aluno.size();i++){
//			novo = aluno.get(i);
//			String novaConcatenada=novo.getValor("aluno_id").toString()+"\t"+novo.getValor("ano")+"\t"+novo.getValor("periodo");
//			if(!concatenada.equals(novaConcatenada)){
//				arvore.add(no);
//				concatenada = novo.getValor("aluno_id").toString()+"\t"+novo.getValor("ano")+"\t"+novo.getValor("periodo");
//				id =novo.getValor("aluno_id").toString();
//				no = new No(concatenada, hash.get(novo.getValor("disciplina_id")));
//				no.setKey(concatenada);
//				no.setValor(hash.get(novo.getValor("disciplina_id")));
//			}else{				
//				concatenada = novo.getValor("aluno_id").toString()+"\t"+novo.getValor("ano")+"\t"+novo.getValor("periodo");
//				no.setValor(hash.get(novo.getValor("disciplina_id")));				
//			}
//			if(i==aluno.size()){
//				concatenada = novo.getValor("aluno_id").toString()+"\t"+novo.getValor("ano")+"\t"+novo.getValor("periodo");
//				no.setKey(concatenada);
//				no.setValor(hash.get(novo.getValor("disciplina_id")));
//				arvore.add(no);
//				
//			}
//		}
//		
//		arvore.salvarArvore("arvoreb");
//		arvore.imprime();
		
		ArvoreB arv = new ArvoreB();
		arv = (ArvoreB) arv.CarregaArvore("arvoreb");
		arv.imprime();
		Arquivo disciplina = new Arquivo("Disciplina");
		disciplina.open();
		ArrayList<Long> ar = new ArrayList<Long>();
		ar=arv.getNo("2f4cc4d7-9862-4947-8208-8178f2eda2ca	2000	2").getValue();
		for(int i=0;i<ar.size();i++){
			
			System.out.println(disciplina.buscarRegistro((Long)ar.get(i)));
			
		}
		
		
		
		System.out.println(arv.getNo("2f4cc4d7-9862-4947-8208-8178f2eda2ca	2000	2").getValue());

//					nomeAluno.close();
//					if(no!=null){
//					arvore.add(no);
//					}
//			}
//			
//			arvore.salvarArvore("arvoreb");
		
//		
//		int numeroColuna,numeroColuna1,numeroColuna2;
//		for(ColunaRegistro abc: alunoid.colunas){
//			if(abc.nomeColuna.equals("aluno_id")){
//				numeroColuna=abc.posicao;
//			}			
//		}
//		
//		for(ColunaRegistro abc: ano.colunas){
//			if(abc.nomeColuna.equals("ano")){
//				numeroColuna2=abc.posicao;
//			}			
//		}
//		for(ColunaRegistro abc: periodo.colunas){
//			if(abc.nomeColuna.equals("periodo")){
//				numeroColuna2=abc.posicao;
//			}			
//		}
//		
//		Collections.sort(array1, new ComparadorDisciplinaHistorico(numeroColuna,numeroColuna1,numeroColuna2));
//		
//		for(int i=0;i<20;i++){
//		System.out.println(array1.get(i));
//		}
		
//		ArvoreB arvore = new ArvoreB();
//		arvore = (ArvoreB) arvore.CarregaArvore("arvoreb");
//		//arvore.imprime();
//		ArrayList<T> array ;
//		array = arvore.getNo("001282c6-502b-4612-ba6c-a99672830b05	2008	1").getValue();	
		
		//imprime as materias que o aluno cursou , falta organizar pelo ano e periodo
//		for(int i=0;i<array.size();i++){
//			System.out.println(array.get(i).toString());
//			System.out.println(arquivo.buscarRegistro(Long.parseLong(array.get(i).toString())));
//			arquivo.seek(0);
//		}
//		System.out.println("key "+arvore.getNo("001282c6-502b-4612-ba6c-a99672830b05	2008	1").getKey() +" valores fp arquivo disciplina "+ arvore.getNo("001282c6-502b-4612-ba6c-a99672830b05	2008	1").getValue());
		
		
		
			//funcionando		
//		ProjecaoFeitaComSelecao prog = new ProjecaoFeitaComSelecao("BACHARELADO EM CIÊNCIA DA COMPUTAÇÃO");
//		prog.open();
//		Registro reg;
//		while((reg=(Registro)prog.next())!=null){
//			//System.out.println(reg.toString());
//			}
	
		tempo = System.currentTimeMillis() - tempo;
		System.out.println("Tempo "+tempo+" ms");
	}
}