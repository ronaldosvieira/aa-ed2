package arvoreB;

import java.io.FileNotFoundException;
import java.io.IOException;

import base.Relacao;
import base.Tupla;


public class UtilArvore extends Relacao {

	private Tupla tupla = new Tupla();
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
		seek = pegaLinha();

		while ((tupla = (Tupla) super.next()) != null) {

			try {

				arvore.getNo(tupla.getValorCampo(nomeCampo)).setValor(seek);
				seek = pegaLinha();
			} catch (Exception e) {

				no = new No<String, Long>(tupla.getValorCampo(nomeCampo), seek);
				arvore.add(no);
				seek = pegaLinha();
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
