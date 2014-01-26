package br.ufrrj.im.cc.ed2.arquivos;

public interface Iterator {
	void open(String string);
	String next();
	void close();
}