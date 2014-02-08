package br.ufrrj.im.cc.ed2.classes;

import java.util.LinkedList;
import java.util.List;

import br.ufrrj.im.cc.ed2.catalogo.Catalogo;
import br.ufrrj.im.cc.ed2.catalogo.Coluna;
import br.ufrrj.im.cc.ed2.catalogo.Item;

public class Registro {
	public List<ColunaRegistro> colunas = new LinkedList<ColunaRegistro>();
	public String [] tokens;
	public String relacao;
	public long posicao;
	
	public Registro (String registro, String relacao, long posicao) {
		this.relacao = relacao;
		this.posicao = posicao;
		
		tokens = registro.split("\t");
		Item item = Catalogo.getInstance().getItem(relacao);
		
		if(item.colunas.size()!=tokens.length) {
			throw new RuntimeException ("Registro incompatível com a relação!");
		}
		
		int i = 0;
		for(Coluna c : item.colunas) {
			ColunaRegistro colunaRegistro = new ColunaRegistro(c.getNome(), tokens[i], i);
			colunas.add(colunaRegistro);
			i++;
		}		
	}
	
	public String getValor(String nomeColuna){
		String retorno = null;
		for(ColunaRegistro cr : colunas) {
			if(cr.nomeColuna.equals(nomeColuna)) {
				retorno = cr.getValor();
			}
		}
		if(retorno==null) {
			System.err.println("Coluna inexistente!");
		}
		return retorno;
	}
	
	public boolean contains(Object obj) {
		if(obj instanceof String) {
			String valor = (String) obj;
			for(ColunaRegistro cr : colunas) {
				if(cr.equals(valor)) return true;
			}
		}
		return false;
	}

	public String toString() {
		String retorno = tokens[0];
		
		for(int i=1;i<tokens.length;i++){			
			retorno=retorno+"\t"+tokens[i];
			
		}
		return retorno;
	}
	
	public String join (Registro reg) {
		String retorno = this.toString() + "	" + reg.toString();		
		return retorno;		
	}
}
