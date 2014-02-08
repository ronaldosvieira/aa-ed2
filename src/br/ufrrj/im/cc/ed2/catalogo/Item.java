package br.ufrrj.im.cc.ed2.catalogo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Item {
	
	public String nome; //nome do arquivo
	public String relacao; //classe representada pelo arquivo
	public int size;
	public List<Coluna> colunas;
	
	public Item(String nome, String relacao, List<Coluna> colunas, int size) {
		this.nome = nome;
		this.relacao = relacao;
		this.colunas = colunas;
		this.size = size;
	}
	
	public Item(String nome, String classe, List<Coluna> colunas) {
		this.nome = nome;
		this.relacao = classe;
		this.colunas = colunas;
		try {
			this.size = getSizeOfRelation();
		} catch (IOException e) {
			System.err.println("Impossible to get size!");
		}
	}
	
	public Item(String nome, String classe) {
		this.nome = nome;
		this.relacao = classe;
	}
	
	private int getSizeOfRelation() throws IOException{
		File file = new File(nome);
		FileReader fr = new FileReader(file);
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(fr);
		size = 0;
		while(br.readLine()!=null) {
			size++;
		}
		return size;
	}
	
	public List<Coluna> getColunas() {
		return colunas;
	}
	
	public String getRelacao() {
		return relacao;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getSize() {
		return size;
	}
	
	@Override
	public String toString(){
		return nome;
	}
}
