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
		
		
		/* Escolhe automaticamente qual das duas relações será armazana na tabela de dispersão */
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
	public void open() {
		relacaoMenor.open();
		tabelaHash = new TabelaDispersao ();
		
		Registro registro;
		
		String linha = "";
		while((linha=relacaoMenor.next())!=null) {
			registro = new Registro(linha, relacaoMenor.retornaRelacao(), relacaoMenor.retornaPosicao());
			tabelaHash.inserir(registro);
		}
		relacaoMaior.open();
	}
	
	@Override
	public String next() {
		Registro registroRelacao;
		Registro registroTabela;		
		String linha = relacaoMaior.next();
		
		if(linha==null) {
			return null;
		}
		registroRelacao = new Registro(linha, relacaoMaior.nomeRelacao, relacaoMaior.retornaPosicao());
		String chave = registroRelacao.getValor(colunaRelacaoMaior);
		registroTabela = tabelaHash.recuperar(chave);		
		String retorno = registroRelacao.join(registroTabela);
		return retorno;
	}

	@Override
	public void close() {
		relacaoMenor.close();
		relacaoMaior.close();		
	}	
}
