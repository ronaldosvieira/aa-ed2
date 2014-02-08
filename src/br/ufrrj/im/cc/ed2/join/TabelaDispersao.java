package br.ufrrj.im.cc.ed2.join;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.ufrrj.im.cc.ed2.classes.Registro;

public class TabelaDispersao {
	public List<LinkedList<Registro>> tabela;
	public int size;
	
	public TabelaDispersao () {
		this.size = 50;
		tabela = new ArrayList<LinkedList<Registro>>();
		
		for(int i=0; i<50; i++)
			tabela.add(new LinkedList<Registro>());
	}
	
	public void inserir (Registro reg) {
		int index = hashCode(reg);
		LinkedList<Registro> list = tabela.get(index);
		list.addLast(reg);
	}
	
	public Registro recuperar (String chave) {
		for(LinkedList<Registro> list : tabela) {
			for(Registro registro : list) {
				if(registro.contains(chave)) {
					return registro;
				}
			}
		}
		return null;
		
	}
	
	public int hashCode(Object obj) {
		return obj.hashCode() % size;
	}

}
