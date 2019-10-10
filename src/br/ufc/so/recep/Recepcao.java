package br.ufc.so.recep;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import br.ufc.so.client.Cliente;
import br.ufc.so.server.Servidor;

public class Recepcao implements Runnable {

	private Cliente cliente;
	private Socket socketCliente;
	private ObjectOutputStream output;
	private Scanner teclado;
	private int id = 0;

	public Recepcao() {
		Thread recepcao = new Thread(this);
		recepcao.start();
	}

	@Override
	public void run() {
		try {
			// Instancia uma Thread Servidor
			Servidor server = new Servidor();

			// Método scanner para ler entrada do usuário
			teclado = new Scanner(System.in);

			while (true) {
				// Lendo tempo
				String tempo = teclado.nextLine();

				// Se o tempo não é vazio
				if (!tempo.isEmpty()) {
					System.out.println(
							"RECEPCAO: Cliente " + ++id + " chegou e deseja pedido de " + tempo + " segundos.");

					// Instanciando oum cliente com tempo passado na entrada
					cliente = new Cliente(id, tempo);
					cliente.msgCliente();

					// Conectando ao ServerSocket do Servidor
					socketCliente = new Socket("localhost", 12345);

					output = new ObjectOutputStream(socketCliente.getOutputStream());

					// Enviando o tempo do cliente ao Servidor
					output.writeObject(cliente);
					output.flush();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				// fechar conexões para gerenciar melhor a memória.
				teclado.close();
				output.close();
				socketCliente.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
