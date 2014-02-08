package br.ufrrj.im.cc.ed2.classes;

public class ColunaRegistro {
	public String nomeColuna;
	public String valor;
	public int posicao;
	
	public ColunaRegistro (String nomeColuna, String valor, int posicao) {
		this.nomeColuna = nomeColuna;
		this.valor = valor;
		this.posicao = posicao;
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
	
	public String toString () {
		return nomeColuna+"	"+valor;
	}

}
