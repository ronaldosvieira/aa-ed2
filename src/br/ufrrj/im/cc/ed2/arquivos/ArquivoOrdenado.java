package br.ufrrj.im.cc.ed2.arquivos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class ArquivoOrdenado implements Iterator {

	RandomAccessFile file;
	public String nomeArquivo;
	public String nomeRelacao;
	public long pointer;
	
	//escreve um arquivo ordenado de acordo com o nome da relacao +" "+ nome da coluna que vai ser o parâmetro p/ ordenação
	public ArquivoOrdenado (String nomeRelacao , String nomeColuna) {
		this.nomeRelacao=nomeRelacao;
		nomeArquivo = nomeRelacao + " " + nomeColuna+".txt";
	}
		
	@Override
	public void open() {
		try {
			file = new RandomAccessFile(nomeArquivo, "rw");
		} catch (FileNotFoundException e) {
			System.err.println("Operação malsucedida!");
		}
	}

	@Override
	public String next() {
		try {
			pointer = file.getFilePointer();
			return file.readLine();
		} catch (IOException e) {
			System.err.println("Não há mais registros!");
			return null;
		}
	}

	@Override
	public void close() {
		try {
			file.close();
		} catch (IOException e) {
			System.err.println("Impossível fechar arquivo!");
		}
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
	
	public void seek(long numero){
		try {
			file.seek(numero);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Erro no seek"+ e);
		}
	}
	
	public void escreve(String linha){
		try {
			file.writeBytes(linha+"\r\n");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
