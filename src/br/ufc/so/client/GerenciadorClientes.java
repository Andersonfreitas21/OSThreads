package br.ufc.so.client;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class GerenciadorClientes implements Runnable {

	private Cliente cliente;
	private static Integer NUM_MAX_VAGAS = 2;
	private static BlockingQueue<Cliente> filaClientes = new ArrayBlockingQueue<Cliente>(NUM_MAX_VAGAS);

	public GerenciadorClientes(Cliente cliente) {
		this.cliente = cliente;
		Thread gerenciadorClientes = new Thread(this);
		gerenciadorClientes.start();
	}

	@Override
	public void run() {
		try {

			System.out.println("CLIENTE : " + cliente.getId() + " Esperando servidor me atender.");

			// Adicionando cliente no fila
			if (filaClientes.offer(cliente)) {
				// Aguardando servidor processar
				Thread.sleep(Long.parseLong(cliente.getTempo()) * 1000);
				System.out.println("CLIENTE : " + filaClientes.poll().getId() + ": Saindo.");
			} else {
				System.out.println("CLIENTE : " + cliente.getId() + " Erro: não há vagas.");
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
