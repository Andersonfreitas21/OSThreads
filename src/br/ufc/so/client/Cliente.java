package br.ufc.so.client;

import java.io.Serializable;

public class Cliente implements Serializable, Runnable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String tempo;

	public Cliente(int id, String tempo) {
		this.id = id;
		this.tempo = tempo;
		Thread cliente = new Thread(this);
		cliente.start();
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	public int getId() {
		return id;
	}
	
	public void msgCliente() {
		System.out.println("CLIENTE " + getId() + " : Esperando servidor me atender.");
	}

	@Override
	public void run() {
		
	}

}
