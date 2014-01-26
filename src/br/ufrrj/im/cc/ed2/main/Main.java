package br.ufrrj.im.cc.ed2.main;
import java.io.IOException;

import br.ufrrj.im.cc.ed2.arquivos.Arquivo;

public class Main {
	public static void main(String[] args) throws IOException {
		Arquivo archive = new Arquivo();
		archive.open("pessoas.txt");
	}
}
