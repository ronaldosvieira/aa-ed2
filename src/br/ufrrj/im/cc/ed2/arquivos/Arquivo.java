package br.ufrrj.im.cc.ed2.arquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Arquivo implements Iterator {
	public File file;
		
	/* tamanho de cada arquivo */
	public int tamanhoArquivo1;

	/* leitura do arquivo de entrada */
	public FileReader fr;
	public BufferedReader br;

	
	/* ponteiro para linha do arquivo */
	public String line = "";

	@Override
	public void open(String arquivo ){
		file = new File(arquivo);
		
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.err.println("Operação malsucedida!");
			}
		}
		
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			System.err.println("Operação malsucedida!");
		}
		br = new BufferedReader(fr);	
		
		
	}

	@Override
	public String next() {
		try {
			if(line == null) {
				throw new IOException("Não há mais registros!");
			} else {
				line = br.readLine();
			}
			return line;
		} catch (IOException e) {
			return null;
		}		
	}

	@Override
	public void close() {
		try {
			br.close();
					} catch (IOException e) {
			System.err.println("Operação malsucedida!");
		}
		
	}	
}
