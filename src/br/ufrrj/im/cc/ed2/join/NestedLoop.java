package br.ufrrj.im.cc.ed2.join;

import br.ufrrj.im.cc.ed2.arquivos.Arquivo;
import br.ufrrj.im.cc.ed2.arquivos.Iterator;
import br.ufrrj.im.cc.ed2.catalogo.Catalogo;
import br.ufrrj.im.cc.ed2.catalogo.Item;
import br.ufrrj.im.cc.ed2.classes.Registro;




public class NestedLoop implements Iterator {
	public Arquivo relacaoMaior;
	public Arquivo relacaoMenor;
	public String colunaRelacaoMaior;
	public String colunaRelacaoMenor;
	public int contadorLinha1;
	int contadorlinha2=0;
	
	
	
	public NestedLoop (String relacaoA, String colunaRelacaoA, String relacaoB, String colunaRelacaoB){
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
		
		relacaoMaior.open();
		relacaoMenor.open();
	}

	@Override
	public String next() {
		Registro registroRelacao;
		Registro registroRelacao2;
		String retorno = null;
		String linha2;
		String chave;
		String chave2;
		String linha = relacaoMaior.next();
		
		if(linha==null) {
			return null;
		}
		
		registroRelacao = new Registro(linha, relacaoMaior.nomeRelacao, relacaoMaior.retornaPosicao());
		contadorLinha1++;				
		chave = registroRelacao.getValor(colunaRelacaoMaior);		
		
		while((linha2 = relacaoMenor.next())!=null){
			registroRelacao2 = new Registro(linha2, relacaoMenor.nomeRelacao, relacaoMenor.retornaPosicao());
			chave2 = registroRelacao2.getValor(colunaRelacaoMenor);
			
			if (chave.equals(chave2)){
				retorno = registroRelacao.join(registroRelacao2);
				break;
			}
		}		
		relacaoMenor.seek(0);
		
		return retorno;	
		
		
	}

	@Override
	public void close() {
		relacaoMaior.close();
		relacaoMenor.close();
				
	}

	
	public int getTamanho (){
		return contadorLinha1;
		
	}
	
	


}
