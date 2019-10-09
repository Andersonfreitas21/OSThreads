package br.ufc.so.client;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class GerenciadorClientes implements Runnable {

	private Cliente cliente;
	private BlockingQueue<Cliente> listaClientes = new ArrayBlockingQueue<Cliente>(2);

	public GerenciadorClientes(Cliente cliente) {
		this.cliente = cliente;
		Thread gerenciadorClientes = new Thread(this);
		gerenciadorClientes.start();
	}

	@Override
	public void run() {
		try {
			
			System.out.println("CLIENTE : " + cliente.getId() + " Esperando servidor me atender.");
			
			//Adicionando cliente no fila
			listaClientes.put(cliente);
			
			//Aguardando servidor processar 
			Thread.sleep(Long.parseLong(cliente.getTempo())*1000);
			
			System.out.println("CLIENTE " + cliente.getId() + ": Saindo.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
