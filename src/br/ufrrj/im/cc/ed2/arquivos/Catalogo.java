package br.ufrrj.im.cc.ed2.arquivos;

import java.io.File;

public class Catalogo {
	
	private File alunos = new File("pessoas.txt");
	
	public Catalogo(){}
	
	public String path(){
		return alunos.getPath();
	}
	
	public String size(){
		return String.valueOf(alunos.length());
	}
}
