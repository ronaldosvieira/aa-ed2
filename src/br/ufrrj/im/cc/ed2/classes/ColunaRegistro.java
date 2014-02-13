package br.ufrrj.im.cc.ed2.classes;

import br.ufrrj.im.cc.ed2.arquivos.Iterator;

public class ColunaRegistro implements Iterator{
	public String nomeColuna;
	public String valor;
	public int posicao;
	
	public ColunaRegistro (String nomeColuna, String valor, int posicao) {
		this.nomeColuna = nomeColuna;
		this.valor = valor;
		this.posicao = posicao;
	}
	
	public String toString () {
		return nomeColuna+"	"+valor;
	}

	@Override
	public Iterator open() {
		return null;		
	}

	@Override
	public Iterator next() {
		return null;
	}

	@Override
	public Iterator close() {
		return null;
	}
	
	public String getNome() {
		return nomeColuna;
	}
	
	public String getValor() {
		return valor;
	}
	
	public int getPosicao() {
		return posicao;
	}
	
	public boolean equals(String valor) {
		if(this.valor.equals(valor))
			return true;
		return false;
	}
	
	
}
