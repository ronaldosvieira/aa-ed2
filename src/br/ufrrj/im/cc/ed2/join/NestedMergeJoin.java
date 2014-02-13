package br.ufrrj.im.cc.ed2.join;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import br.ufrrj.im.cc.ed2.arquivos.Arquivo;
import br.ufrrj.im.cc.ed2.arquivos.Iterator;
import br.ufrrj.im.cc.ed2.classes.ColunaRegistro;
import br.ufrrj.im.cc.ed2.classes.Comparador;
import br.ufrrj.im.cc.ed2.classes.Registro;

public class NestedMergeJoin implements Iterator {
	
	public Arquivo relacaoA;
	public Arquivo relacaoB;
	public String colunaRelacaoA;
	public String colunaRelacaoB;
	private Registro regAux,regAux2;
	String linha , linha2;
	RandomAccessFile arquivo = null,arquivo2=null;
	
	List<Registro> array = new ArrayList<Registro>();
	List<Registro> array2 = new ArrayList<Registro>();
	

	public NestedMergeJoin (String relacaoA, String colunaRelacaoA, String relacaoB, String colunaRelacaoB){
		this.relacaoA = new Arquivo(relacaoA);
		this.colunaRelacaoA = colunaRelacaoA;
		this.relacaoB = new Arquivo(relacaoB);			
		this.colunaRelacaoB = colunaRelacaoB;
		
	}	

	@Override
	public Iterator open() {
		relacaoA.open();
		relacaoB.open();
				
		Registro registroRelacao = null, registroRelacao2 = null ,registroAuxiliar=null;		
		
		while((registroAuxiliar = (Registro) relacaoA.next())!=null){
			registroRelacao = new Registro(registroAuxiliar.toString(),relacaoA.nomeRelacao, relacaoA.retornaPosicao());
			array.add(registroRelacao);				
		}		
		
		int numeroColuna=0;
		for(ColunaRegistro abc: registroRelacao.colunas){
			if(abc.nomeColuna.equals(colunaRelacaoA)){
				numeroColuna=abc.posicao;
			}			
		}
		
		//ordena o arquivo de acordo com a coluna da tabela
		Collections.sort(array,new Comparador(numeroColuna));
		relacaoA.close();
		relacaoA.resetaArquivo();
		
		for(int i=0;i<array.size();i++){
			System.out.println(array.get(i));
			relacaoA.escrever(array.get(i).toString());			
		}
		
		relacaoA.fecharEscrever();
		
		
		while((registroAuxiliar = (Registro) relacaoB.next())!=null){
			registroRelacao2 = new Registro(registroAuxiliar.toString(),relacaoB.nomeRelacao, relacaoB.retornaPosicao());
			array2.add(registroRelacao2);				
		}			
		
		int numeroColuna2=0;
		for(ColunaRegistro nome: registroRelacao2.colunas){
			if(nome.nomeColuna.equals(colunaRelacaoB)){
				numeroColuna=nome.posicao;
			}			
		}
		
		//ordena o arquivo de acordo com a coluna da tabela
		Collections.sort(array2,new Comparador(numeroColuna2));
		
		relacaoB.close();
		relacaoB.resetaArquivo();
		for(int i=0;i<array2.size();i++){
			System.out.println(array2.get(i));
			relacaoB.escrever(array2.get(i).toString());		
		}
		
		relacaoB.fecharEscrever();
		
		
		
		relacaoA.open();
		relacaoB.open();
				
		regAux = (Registro) relacaoA.next();
		regAux2 = (Registro) relacaoB.next();
		
		
		return this;
	}

	@Override
	public Iterator next() {
		Registro registroRelacao;
		Registro registroRelacao2;
		Registro retorno = new Registro();
		boolean igual=false;
		
		if(regAux==null ) {
			return null;
		}
		
		registroRelacao = new Registro(regAux.toString(), relacaoA.nomeRelacao, relacaoA.retornaPosicao());
		registroRelacao2 = new Registro(regAux2.toString(), relacaoB.nomeRelacao, relacaoB.retornaPosicao());
		igual = registroRelacao.getValor(colunaRelacaoA).equals(registroRelacao2.getValor(colunaRelacaoB));
		

				while(igual==false){
					if(registroRelacao.getValor(colunaRelacaoA).compareTo(registroRelacao2.getValor(colunaRelacaoB))>0){
							igual=true;
							registroRelacao2 = new Registro(regAux2.toString(), relacaoB.nomeRelacao, relacaoB.retornaPosicao());
							retorno.join(registroRelacao);
							retorno.join(registroRelacao2);
							
							 regAux2 = (Registro) relacaoB.next(); 
							return retorno;
					}
					if(registroRelacao.getValor(colunaRelacaoA).compareTo(registroRelacao2.getValor(colunaRelacaoB))<0){
						igual=true;
						regAux = new Registro(regAux.toString(), relacaoA.nomeRelacao, relacaoA.retornaPosicao());
						retorno.join(registroRelacao);
						retorno.join(registroRelacao2);
						regAux = (Registro) relacaoA.next();
						return retorno;
					}								
				}
				
				if(igual){
					retorno.join(registroRelacao);
					retorno.join(registroRelacao2);
					regAux = (Registro) relacaoA.next();
					return retorno;
				}
									
		
		return retorno;
	}

	@Override
	public Iterator close() {
		return null;
	}
	
	public void print() {
		System.out.println(array.size());
	}

}
