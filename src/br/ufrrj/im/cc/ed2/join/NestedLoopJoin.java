package br.ufrrj.im.cc.ed2.join;

import br.ufrrj.im.cc.ed2.arquivos.Arquivo;
import br.ufrrj.im.cc.ed2.arquivos.Iterator;
import br.ufrrj.im.cc.ed2.catalogo.Catalogo;
import br.ufrrj.im.cc.ed2.catalogo.Item;
import br.ufrrj.im.cc.ed2.classes.Registro;

public class NestedLoopJoin implements Iterator {
	public Arquivo relacaoMaior;
	public Arquivo relacaoMenor;
	public String colunaRelacaoMaior;
	public String colunaRelacaoMenor;
	
	public NestedLoopJoin (String relacaoA, String colunaRelacaoA, String relacaoB, String colunaRelacaoB){
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
	public Iterator open() {		
		relacaoMaior.open();
		relacaoMenor.open();
		return null;
	}

	@Override
	public Iterator next() {
		Registro registroRelacaoMaior = (Registro) relacaoMaior.next();
		Registro registroRelacaoMenor;
		Registro retorno = null;
		String chave;
		String comparador;
		
		if(registroRelacaoMaior==null) {
			return null;
		}
		
		chave = registroRelacaoMaior.getValor(colunaRelacaoMaior);
		
		while((registroRelacaoMenor = (Registro) relacaoMenor.next())!=null) {
			retorno = new Registro();			
			comparador = registroRelacaoMenor.getValor(colunaRelacaoMenor);
			
			if(comparador.equals(chave)) {
				retorno.join(registroRelacaoMaior);
				retorno.join(registroRelacaoMenor);
				break;
			}
		}
		relacaoMenor.seek(0);
		return retorno;
	}

	@Override
	public Iterator close() {
		relacaoMaior.close();
		relacaoMenor.close();
		return null;
	}
}
