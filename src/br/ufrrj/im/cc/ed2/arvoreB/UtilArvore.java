package br.ufrrj.im.cc.ed2.arvoreB;

import java.io.FileNotFoundException;
import java.io.IOException;

import br.ufrrj.im.cc.ed2.arquivos.Arquivo;
import br.ufrrj.im.cc.ed2.classes.Registro;




public class UtilArvore extends Arquivo {
	private Registro registro;
	private Arquivo arquivo;
	private ArvoreB arvore = new ArvoreB<>();
	private long seek;
	private static final long serialVersionUID = 1L;

	public UtilArvore(String nomeRelacao) {
		// TODO Auto-generated constructor stub
		super(nomeRelacao);
	}

	public ArvoreB criaArvoreB(String nomeRelacao, String nomeCampo) {
		// TODO Auto-generated method stub
		UtilArvore u = new UtilArvore(nomeRelacao);

		open();

		No<String, Long> no;
		seek = retornaPosicao();

		while ((registro = (Registro) super.next()) != null) {

			try {

				arvore.getNo(registro.getValor(nomeCampo)).setValor(seek);
				seek = retornaPosicao();
			} catch (Exception e) {

				no = new No<String, Long>(registro.getValor(nomeCampo), seek);
				arvore.add(no);
				seek = retornaPosicao();
			}

		}

		//arvore.imprime();
		close();
		return arvore;
		

	}

	public void salvaArvore(ArvoreB arv, String nomeArquivo) {

		try {
			arv.salvarArvore(nomeArquivo);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public ArvoreB carregaArvore(String nomeArq){
		return (ArvoreB) arvore.CarregaArvore(nomeArq);
	}

}
