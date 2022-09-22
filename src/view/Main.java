package view;

import java.util.concurrent.Semaphore;

import controller.ThreadTransacao;

public class Main {

	public static void main(String[] args) {

		int permissoes = 1;
		Semaphore semas = new Semaphore(permissoes);
		
		for(int idTransacao = 1 ; idTransacao < 22 ; idTransacao++ ) {
			Thread tTransacao = new ThreadTransacao(idTransacao, semas);
			tTransacao.start();
		}
	}

}
