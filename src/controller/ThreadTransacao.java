package controller;

import java.util.concurrent.Semaphore;

public class ThreadTransacao extends Thread {
	
	private int idTransacao;
	private Semaphore semas;
	
	public ThreadTransacao(int idTransacao, Semaphore semas) {
		this.semas = semas;
		this.idTransacao = idTransacao;
	}
	
	@Override
	public void run() {
		calculo();
//		------------------Start---------------
		try {
			semas.acquire();
			transacaoBD();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semas.release();
		}
//		-------------------End----------------
	}

	private void calculo() {
		int tempoTotal = 0;
		if(idTransacao % 3 == 1) {
			tempoTotal = (int)((Math.random() * 801) + 200);
		}if(idTransacao % 3 == 2){
			tempoTotal = (int)((Math.random() * 1001) + 500);
		}if(idTransacao % 3 == 0) {
			tempoTotal = (int)((Math.random() * 1001) + 1000);
		}
		System.out.println("#"+idTransacao+" iniciou o cálculo");
		
		int tempoCalculo = 0;
		int tempo = 100;
		int processamento = 100;
		while(tempoCalculo < tempoTotal) {
			tempoCalculo += processamento;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("#"+idTransacao+" ja cálculou por "+tempoCalculo+"s.");
		}
		System.out.println("#"+idTransacao+" terminou de calcular");
	}

	private void transacaoBD() {
		System.out.println("#"+idTransacao+" iniciou a transação");
		int tempo = 0;
		if(idTransacao % 3 == 1) {
			tempo = 1000;
		}else {
			tempo = 1500;
		}
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("#"+idTransacao+" terminou a transação");
		
	}
}
