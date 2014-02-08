package br.ufrrj.im.cc.ed2.join;


import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import br.ufrrj.im.cc.ed2.arquivos.Arquivo;
import br.ufrrj.im.cc.ed2.arquivos.ArquivoOrdenado;
import br.ufrrj.im.cc.ed2.arquivos.Iterator;
import br.ufrrj.im.cc.ed2.classes.ColunaRegistro;
import br.ufrrj.im.cc.ed2.classes.Registro;
public class NestedMerge implements Iterator {

	public Arquivo relacaoA;
	public Arquivo relacaoB;
	public ArquivoOrdenado relacaoAOrdenado;
	public ArquivoOrdenado relacaoBOrdenado;
	public String colunaRelacaoA;
	public String colunaRelacaoB;
	public int contadorLinha1;
	int contadorlinha2=0;
	String linha , linha2;
	RandomAccessFile arquivo = null,arquivo2=null;
	
	List<Registro> array = new ArrayList<Registro>();
	List<Registro> array2 = new ArrayList<Registro>();
	
	
	public NestedMerge (String relacaoA, String colunaRelacaoA, String relacaoB, String colunaRelacaoB){
			
			this.relacaoA = new Arquivo(relacaoA);
			this.colunaRelacaoA = colunaRelacaoA;
			relacaoAOrdenado= new ArquivoOrdenado(relacaoA,colunaRelacaoA);
			
			this.relacaoB = new Arquivo(relacaoB);			
			this.colunaRelacaoB = colunaRelacaoB;
			relacaoBOrdenado= new ArquivoOrdenado(relacaoB,colunaRelacaoB);
	}
	@Override
	public void open() {	
		relacaoA.open();
		relacaoB.open();
		relacaoAOrdenado.open();
		relacaoBOrdenado.open();
		
		Registro registroRelacao=null, registroRelacao2=null;		
			
		while((linha = relacaoA.next())!=null){
			registroRelacao = new Registro(linha, relacaoA.nomeRelacao, relacaoA.retornaPosicao());
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
		
		
		
		for(int i=0;i<array.size();i++){
			System.out.println(array.get(i));
			relacaoAOrdenado.escreve(array.get(i).toString());
			
		}
		
		
		while((linha2 = relacaoB.next())!=null){
			registroRelacao2 = new Registro(linha2, relacaoB.nomeRelacao, relacaoB.retornaPosicao());
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
		
		
		for(int i=0;i<array2.size();i++){
			System.out.println(array2.get(i));
			relacaoBOrdenado.escreve(array2.get(i).toString());
		}
		
		
		
		relacaoA.close();
		relacaoB.close();
		relacaoBOrdenado.seek(0);
		relacaoAOrdenado.seek(0);
		
		linha = relacaoAOrdenado.next();
		linha2 = relacaoBOrdenado.next();
		
	}
	@Override
	public String next() {
		Registro registroRelacao;
		Registro registroRelacao2;
		String retorno = null;

		
		if(linha==null) {
			return null;
		}
		
		registroRelacao = new Registro(linha, relacaoAOrdenado.nomeRelacao, relacaoAOrdenado.retornaPosicao());
		registroRelacao2 = new Registro(linha2, relacaoBOrdenado.nomeRelacao, relacaoBOrdenado.retornaPosicao());
		boolean igual = registroRelacao.getValor(colunaRelacaoA).equals(registroRelacao2.getValor(colunaRelacaoB));
		

				while(igual==false){
						if(igual=registroRelacao.getValor(colunaRelacaoA).compareTo(registroRelacao2.getValor(colunaRelacaoB))<0){
							linha2 = relacaoBOrdenado.next(); 
							registroRelacao2 = new Registro(linha2, relacaoBOrdenado.nomeRelacao, relacaoBOrdenado.retornaPosicao());
							retorno= registroRelacao.join(registroRelacao2);
							return retorno;
						}
						if(igual=registroRelacao.getValor(colunaRelacaoA).compareTo(registroRelacao2.getValor(colunaRelacaoB))>0){
							linha = relacaoAOrdenado.next();
							registroRelacao = new Registro(linha, relacaoAOrdenado.nomeRelacao, relacaoAOrdenado.retornaPosicao());
							retorno= registroRelacao.join(registroRelacao2);
							return retorno;
						}						
						
							
				}
				
				if(igual){
					retorno= registroRelacao.join(registroRelacao2);
				}
									
		
		return retorno;
	}

	@Override
	public void close() {
		
		
	}
	


	
}
