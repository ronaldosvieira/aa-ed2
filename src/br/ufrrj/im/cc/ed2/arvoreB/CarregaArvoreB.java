package br.ufrrj.im.cc.ed2.arvoreB;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import br.ufrrj.im.cc.ed2.classes.Registro;
import br.ufrrj.im.cc.ed2.arquivos.Arquivo;
import br.ufrrj.im.cc.ed2.arvoreB.No;

public class CarregaArvoreB extends Arquivo implements Serializable {
	
	private Registro registro = new Registro();
	private ArvoreB arvore = new ArvoreB<>();
	private long seek;
	private static final long serialVersionUID = 1L;

	public CarregaArvoreB(String nomeRelacao) throws FileNotFoundException,	IOException {
		
		super(nomeRelacao);
		open();
		No<String, Long> no;
		seek = retornaPosicao();

		while ((registro = (Registro) super.next()) != null) {

			try {

				arvore.getNo(registro.getValor("aluno_id")+"\t"+registro.getValor("ano")+"\t"+registro.getValor("periodo")).setValor(seek);
				seek = retornaPosicao();
			} catch (Exception e) {

				no = new No<String, Long>(registro.getValor("aluno_id")+"\t"+registro.getValor("ano")+"\t"+registro.getValor("periodo"), seek);
				arvore.add(no);
				seek = retornaPosicao();

			}

		}
		close();

		// arvore.imprime();

		arvore.salvarArvore("arvoreBDisciplinaHistorico.dat");

		ArvoreB avb = (ArvoreB) arvore.CarregaArvore("arvoreBDisciplinaHistorico.dat");
		avb.imprime();

	}
	
	

}
