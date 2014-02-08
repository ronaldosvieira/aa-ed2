package arvoreB;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import base.Relacao;
import base.Tupla;
import catalogo.Catalogo;
import arvoreB.No;

public class CarregaArvoreB extends Relacao implements Serializable {

	private Tupla tupla = new Tupla();
	private ArvoreB arvore = new ArvoreB<>();
	private long seek;
	private static final long serialVersionUID = 1L;

	public CarregaArvoreB(String nomeRelacao) throws FileNotFoundException,	IOException {
		
		super(nomeRelacao);
		open();

		No<String, Long> no;
		seek = pegaLinha();

		while ((tupla = (Tupla) super.next()) != null) {

			try {

				arvore.getNo(tupla.getValorCampo("editora")).setValor(seek);
				seek = pegaLinha();
			} catch (Exception e) {

				no = new No<String, Long>(tupla.getValorCampo("editora"), seek);
				arvore.add(no);
				seek = pegaLinha();

			}

		}
		close();

		// arvore.imprime();

		arvore.salvarArvore("avbEditora.dat");

		ArvoreB avb = (ArvoreB) arvore.CarregaArvore("avbEditora.dat");
		avb.imprime();

	}
	
	

}
