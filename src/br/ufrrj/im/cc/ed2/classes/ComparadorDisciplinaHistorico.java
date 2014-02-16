package br.ufrrj.im.cc.ed2.classes;

import java.util.Comparator;

public class ComparadorDisciplinaHistorico implements Comparator<Registro> {
	 
		
		
		
		 public void ComparadorDisciplinaHistorico() {  
	      	    }  
		public int compare(Registro o1, Registro o2) {
			
						
			return (o1.tokens[1].toString()+"\t"+o1.tokens[4].toString()+"\t"+o1.tokens[5].toString()).compareTo(o2.tokens[1].toString()+"\t"+o2.tokens[4].toString()+"\t"+o2.tokens[5].toString());
		}

	

}
