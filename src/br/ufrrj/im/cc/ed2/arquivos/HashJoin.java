package br.ufrrj.im.cc.ed2.arquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HashJoin implements Iterator{

	public File file;
	public FileReader fr;
	public BufferedReader br;
	public HashTable<?> tabelaHash;
	
	public HashJoin (File file) throws IOException {
		
	}

	@Override
	public void open(String arquivo) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
}
