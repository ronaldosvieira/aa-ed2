package br.ufrrj.im.cc.ed2.arvoreB;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;




public class ArvoreB<K extends Comparable<? super K>, V> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pagina<K, V> raiz;
	private int ordem;

	public ArvoreB(int ordem) {
		this.ordem = ordem;
		criaArvoreB();
	}

	public ArvoreB() {
		this.ordem = 3;
		criaArvoreB();
	}

	private void criaArvoreB() {

		this.raiz = new Pagina<K, V>();
		this.raiz.setN(0);
	}

	// Retornando o nó buscado
	public No<K, V> getNo(K k) {
		return this.get(this.raiz, k);
	}

	// Buscando nas Paginas o nó requisitado
	public No<K, V> get(Pagina<K, V> p, K k) {
		int i = 0;

		// Buscando o indice menor ou igual ao requisitado
		while ((i < p.getN()) && (k.compareTo(p.getNos().get(i).getKey()) > 0)) {
			i++;
		}
		// Retornando o nó requisitado
		if ((i < p.getN()) && (k.compareTo(p.getNos().get(i).getKey()) == 0)) {
			return p.getNos().get(i);
		}
		// Verificando se é folha para terminar a busca
		else if (p.ehFolha()) {
			return null;
		}
		// Chamando nova busca, modo recursivo, até encontrar o nó
		else {
			return this.get(p.getFilhos().get(i), k);
		}
	}

	public void add(No<K, V> no) {

		Pagina<K, V> r = this.raiz;
		Pagina<K, V> s;

		if (r.getN() == (2 * ordem - 1)) {
			s = new Pagina<K, V>();

			this.raiz = s;
			s.setFolha(false);
			s.setN(0);
			s.getFilhos().add(0, r);

			cisaoFilho(s, 0);
			inserirNaoCheia(s, no);
		} else
			inserirNaoCheia(r, no);
	}

	private void inserirNaoCheia(Pagina<K, V> p, No<K, V> no) {

		int i = p.getN() - 1;

		if (p.ehFolha()) {

			while ((i >= 0) && (no.compareTo(p.getNos().get(i).getKey()) < 0)) {
				try {
					p.getNos().set(i + 1, p.getNos().get(i));
				} catch (Exception e) {
					p.getNos().add(i + 1, p.getNos().get(i));
				}

				i--;
			}
			try {
				p.getNos().set(i + 1, no);
			} catch (Exception e) {
				p.getNos().add(i + 1, no);
			}

			p.setN(p.getN() + 1);
		} else {
			while ((i >= 0) && (no.compareTo(p.getNos().get(i).getKey()) < 0)) {
				i--;
			}

			i++;

			if (p.getFilhos().get(i).getN() == (2 * ordem - 1)) {
				cisaoFilho(p, i);

				if (no.compareTo(p.getNos().get(i).getKey()) > 0)
					i++;
			}

			inserirNaoCheia(p.getFilhos().get(i), no);
		}
	}

	private void cisaoFilho(Pagina<K, V> p, int i) {

		Pagina<K, V> z = new Pagina<K, V>();
		Pagina<K, V> y = p.getFilhos().get(i);

		z.setFolha(y.ehFolha());

		z.setN(this.ordem - 1);

		for (int j = 0; j < ordem - 1; j++) {
			z.getNos().add(j, y.getNos().get(ordem));

			// Atualizando os nos de y
			y.getNos().remove(ordem);
		}

		if (!y.ehFolha()) {
			for (int j = 0; j < ordem; j++) {
				z.getFilhos().add(j, y.getFilhos().get(ordem));

				// Atualizando filhos de y
				y.getFilhos().remove(ordem);
			}
		}

		y.setN(ordem - 1);

		for (int j = p.getN(); j >= i + 1; j--) {
			try {
				p.getFilhos().set(j + 1, p.getFilhos().get(j));
			} catch (Exception e) {
				p.getFilhos().add(j + 1, p.getFilhos().get(j));
			}
		}

		try {
			p.getFilhos().set(i + 1, z);
		} catch (Exception e) {
			p.getFilhos().add(i + 1, z);
		}

		for (int j = p.getN() - 1; j >= i; j--) {
			try {
				p.getNos().set(j + 1, p.getNos().get(j));
			} catch (Exception e) {
				p.getNos().add(j + 1, p.getNos().get(j));
			}
		}

		try {
			p.getNos().set(i, y.getNos().get(ordem - 1));
		} catch (Exception e) {
			p.getNos().add(i, y.getNos().get(ordem - 1));
		}

		p.setN(p.getN() + 1);
	}

	public void imprime() {
		raiz.imprimeNo();
		imprimeArvoreB(raiz, raiz.getFilhos().size());
	}

	private void imprimeArvoreB(Pagina<K, V> pag, int num) {

		if (pag.ehFolha()) {
			return;
		} else {
			if (num > 0) {
				pag.getFilhos().get(num - 1).imprimeNo();
				imprimeArvoreB(pag, num - 1);
				imprimeArvoreB(pag.getFilhos().get(num - 1), pag.getFilhos()
						.get(num - 1).getFilhos().size());
			}
		}
	}

	public void salvarArvore(String nome) throws FileNotFoundException,
			IOException {

		ObjectOutputStream objectOut;
		FileOutputStream arquivo = new FileOutputStream(nome);
		objectOut = new ObjectOutputStream(arquivo);
		objectOut.writeObject(this);
		objectOut.flush();
		objectOut.close();
		arquivo.flush();
		arquivo.close();

	}

	public Object CarregaArvore(String nome)  {
		try{
			
			FileInputStream arquivoLeitura = new FileInputStream(nome);

			// Classe responsavel por recuperar os objetos do arquivo

			ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);

			// System.out.println(objLeitura.readObject());
			ArvoreB<K, V> avb = (ArvoreB<K, V>) objLeitura.readObject();

			objLeitura.close();

			arquivoLeitura.close();

			return avb;
		}
		catch(IOException e){
			e.printStackTrace();
			return null;
		}
		catch(ClassNotFoundException cfe){
			cfe.printStackTrace();
			return null;
		}

	}

}
