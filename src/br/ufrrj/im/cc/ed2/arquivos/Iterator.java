package br.ufrrj.im.cc.ed2.arquivos;

public interface Iterator {
	Iterator open();
	Iterator next();
	Iterator close();
}