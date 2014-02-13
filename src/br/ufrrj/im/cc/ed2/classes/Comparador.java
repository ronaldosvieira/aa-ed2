package br.ufrrj.im.cc.ed2.classes;

import java.util.Comparator;

public class Comparador implements Comparator<Registro>{		
	
	int quantidadeTokens , tipoComparacao;
	
	 public Comparador(int tipoComparacao) {  
        this.tipoComparacao = tipoComparacao;  
    }  
	public int compare(Registro o1, Registro o2) {		
		
		return (o1.tokens[tipoComparacao].compareTo(o2.tokens[tipoComparacao]));
	}

}
