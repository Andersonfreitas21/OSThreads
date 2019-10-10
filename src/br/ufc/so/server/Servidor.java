package br.ufc.so.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import br.ufc.so.client.Cliente;
import br.ufc.so.client.GerenciadorClientes;

public class Servidor implements Runnable {

	private ServerSocket serverSocket;
	private Socket socketCliente;
	private ObjectInputStream input;
	private Cliente cliente;

	public Servidor() throws Exception {
		Thread servidor = new Thread(this);
		servidor.start();
	}

	@Override
	public void run() {
		try {
			iniciaServidor();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void iniciaServidor() throws ClassNotFoundException {

		try {
			// Escutando na porta 12345
			serverSocket = new ServerSocket(12345);

			System.out.println("SERVIDOR: Esperando pedidos.");

			// Mantem o servidor para diversas conexões
			while (true) {
				
				//Aceitando a conexão do cliente
				socketCliente = serverSocket.accept();
				
				//Ler o tempo do cliente via InputStream
				input = new ObjectInputStream(socketCliente.getInputStream());
				
				cliente = (Cliente) input.readObject();
				System.out.println("SERVIDOR: Esperando dados do cliente.");
				System.out.println("SERVIDOR: Atendendo cliente " + cliente.getId() + " .");

				GerenciadorClientes gerenciadorClientes = new GerenciadorClientes(cliente);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
