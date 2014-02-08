package br.ufrrj.im.cc.ed2.join;

import java.util.Comparator;


import br.ufrrj.im.cc.ed2.classes.Registro;

public class Comparador implements Comparator<Registro> {
	int quantidadeTokens , tipoComparacao;
	
	 public Comparador(int tipoComparacao) {  
         this.tipoComparacao = tipoComparacao;  
     }  
	@Override
	public int compare(Registro o1, Registro o2) {
		
		
		return (o1.tokens[tipoComparacao].compareTo(o2.tokens[tipoComparacao]));
	}

	
	

	

}
