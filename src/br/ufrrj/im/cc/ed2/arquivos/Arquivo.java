package br.ufrrj.im.cc.ed2.arquivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import br.ufrrj.im.cc.ed2.catalogo.Catalogo;
import br.ufrrj.im.cc.ed2.classes.Registro;

public class Arquivo implements Iterator {
	RandomAccessFile file;
	public String nomeArquivo;
	public String nomeRelacao;
	public long pointer;
	public FileWriter writer;
	
	public Arquivo (String nomeRelacao) {
		this.nomeRelacao = nomeRelacao;
		nomeArquivo = Catalogo.getInstance().getFileName(nomeRelacao);
	}
	
	@Override
	public Iterator open() {
		try {
			file = new RandomAccessFile(nomeArquivo, "rw");
		} catch (FileNotFoundException e) {
			System.err.println("Operação malsucedida!");
		}
		return null;
	}

	@Override
	public Iterator next() {
		Registro saida;
		String linha = "";
		
		try {
			pointer = file.getFilePointer();
			linha = file.readLine();
			if(linha==null) {
				return null;
			}
			saida = new Registro(linha, this.nomeRelacao, pointer);
			return saida;
		} catch (IOException e) {
			System.err.println("Não há mais registros!");
			return null;
		}
	}

	@Override
	public Iterator close() {
		try {
			file.close();
		} catch (IOException e) {
			System.err.println("Impossível fechar arquivo!");
		}
		return null;
	}
	
	public String buscarRegistro (long chave) {
		String retorno = null;
		try {
			file.seek(chave);
		} catch (IOException e1) {
			System.err.println("Posicao nao encontrada!");
		}
		try {
			retorno = file.readLine();
		} catch (IOException e) {
			System.err.println("Registro nao encontrado!");
		}
		return retorno;
	}
	
	public long retornaPosicao() {
		return pointer;
	}
	
	public String retornaRelacao() {
		return nomeRelacao;
	}
	
	public String toString() {
		return nomeArquivo+"	"+nomeRelacao;
	}
	
	public void escrever(String texto){
		try {
			writer.write(texto);
			writer.write("\n");
			} catch (IOException e) {
			System.err.println("Erro ao escrever");
		}
	}
	
	public void resetaArquivo(){
		try {
			writer = new FileWriter(new File(nomeArquivo),false);  
		} catch (IOException e) {
			System.err.println("Erro ao sobrescrever");
		}
	}
	
	public void fecharEscrever(){
		try {
			writer.close();
		} catch (IOException e) {
			System.err.println("Erro ao fechar o writer");
		}
	}
	
	public void seek(long position) {
		try {
			file.seek(position);
		} catch (IOException e) {
			System.err.println("Posição não encontrada!");
		}
	}
}