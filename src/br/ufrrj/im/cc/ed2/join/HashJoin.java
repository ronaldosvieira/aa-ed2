package br.ufrrj.im.cc.ed2.join;

import br.ufrrj.im.cc.ed2.arquivos.Arquivo;
import br.ufrrj.im.cc.ed2.arquivos.Iterator;
import br.ufrrj.im.cc.ed2.catalogo.Catalogo;
import br.ufrrj.im.cc.ed2.catalogo.Item;
import br.ufrrj.im.cc.ed2.classes.Registro;

public class HashJoin implements Iterator{
	
	public Arquivo relacaoMaior;
	public Arquivo relacaoMenor;
	public String colunaRelacaoMaior;
	public String colunaRelacaoMenor;
	public TabelaDispersao tabelaHash;
	
	public HashJoin (String relacaoA, String colunaRelacaoA, String relacaoB, String colunaRelacaoB) {		
		Item itemA = Catalogo.getInstance().getItem(relacaoA);
		Item itemB = Catalogo.getInstance().getItem(relacaoB);
		
		
		/* Escolhe automaticamente qual das duas rela��es ser� armazana na tabela de dispers�o */
		if(itemA.getSize()>itemB.getSize()) {
			relacaoMaior = new Arquivo(relacaoA);
			colunaRelacaoMaior = colunaRelacaoA;
			
			relacaoMenor = new Arquivo(relacaoB);			
			colunaRelacaoMenor = colunaRelacaoB;
		} else {
			relacaoMaior = new Arquivo(relacaoB);
			colunaRelacaoMaior = colunaRelacaoB;
			
			relacaoMenor = new Arquivo(relacaoA);			
			colunaRelacaoMenor = colunaRelacaoA;
		}		
	}

	@Override
	public Iterator open() {
		relacaoMenor.open();
		tabelaHash = new TabelaDispersao ();
		
		Registro registro;
		while((registro= (Registro) relacaoMenor.next())!=null) {
			tabelaHash.inserir(registro);
		}
		relacaoMaior.open();
		return null;
	}
	
	@Override
	public Iterator next() {
		Registro registroRelacao = (Registro) relacaoMaior.next();
		Registro registroTabela;
		
		if(registroRelacao==null) {
			return null;
		}
		String chave = registroRelacao.getValor(colunaRelacaoMaior);
		registroTabela = tabelaHash.recuperar(chave);
		Registro retorno = new Registro();
		retorno.join(registroRelacao);
		retorno.join(registroTabela);
		return retorno;
	}

	@Override
	public Iterator close() {
		relacaoMenor.close();
		relacaoMaior.close();
		return null;
	}	
}
