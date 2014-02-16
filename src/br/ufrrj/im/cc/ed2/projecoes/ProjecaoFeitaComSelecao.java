package br.ufrrj.im.cc.ed2.projecoes;



import br.ufrrj.im.cc.ed2.arquivos.Arquivo;
import br.ufrrj.im.cc.ed2.arquivos.Iterator;
import br.ufrrj.im.cc.ed2.classes.Registro;
import br.ufrrj.im.cc.ed2.classes.Selecao;
import br.ufrrj.im.cc.ed2.join.HashJoin;

public class ProjecaoFeitaComSelecao implements Iterator {
	private String nomeRelacao,cursoid;
	private Selecao selecao ,selecao2;
	Registro tuplaResultante;
	
	public ProjecaoFeitaComSelecao(String nomeCurso){
		this.selecao = new Selecao("Curso", "nome", nomeCurso);
		this.selecao2 = new Selecao("Aluno","curso_id",cursoid);
		
	}

	@Override
	public Iterator open() {
		selecao.open();
		tuplaResultante = new Registro(selecao.next().toString(),"Curso",0);;
		cursoid=tuplaResultante.getValor("id").toString();
		selecao2 = new Selecao("Aluno","curso_id",cursoid);
		selecao2.open();
		return this;
	}


	@Override
	public Iterator next() {
		Registro registro = (Registro) selecao2.next();
		if(registro!=null){
		tuplaResultante=new Registro(registro.toString(),"Aluno",0);
		System.out.println(tuplaResultante.getValor("nome") +" "+tuplaResultante.getValor("matricula"));
		}else{
			return null;
		}
		return tuplaResultante;
	}


	@Override
	public Iterator close() {
		selecao.close();
		selecao2.close();
		return null;
	}

}
