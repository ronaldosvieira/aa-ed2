package br.ufrrj.im.cc.ed2.classes;

import java.util.ArrayList;
import java.util.List;

import br.ufrrj.im.cc.ed2.arquivos.Arquivo;
import br.ufrrj.im.cc.ed2.arquivos.Iterator;



public class Selecao implements Iterator{
	
	String tipoRelacao;//tipoRelacao -> nomeRelacao
	String coluna;//coluna ->campo
	String valor;
	Iterator relacao;
	
		
	public Selecao(String tipoRelacao, String coluna, String valor) {
		this.tipoRelacao = tipoRelacao; 
		this.coluna = coluna;
		this.valor = valor;
		relacao = new Arquivo(tipoRelacao);
	}
	
	public Selecao(Iterator relacao, String coluna, String valor) {
		this.relacao = relacao;
		this.coluna = coluna;
		this.valor = valor;
		
	}

	@Override
	public Iterator open() {
		
		relacao.open();
		return this;
	}

	@Override
	public Iterator next() {
		Registro registro = new Registro();
		
		while((registro = (Registro) relacao.next()) != null) {
			if(registro.getValor(coluna).equals(valor)){
				return registro;
			}
		}
		return registro;
	}

	@Override
	public Iterator close() {
		relacao.close();
		return null;
	}


	public int calculacusto() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
