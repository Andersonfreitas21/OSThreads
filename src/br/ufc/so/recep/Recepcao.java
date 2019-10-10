package br.ufc.so.recep;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import br.ufc.so.client.Cliente;
import br.ufc.so.server.Servidor;

public class Recepcao implements Runnable {

	private Cliente cliente;
	private int id = 0;
	private Socket socketCliente;

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
			Scanner teclado = new Scanner(System.in);

			while (true) {
				// Lendo tempo
				String tempo = teclado.nextLine().trim();

				// Se o tempo não é vazio
				if (!tempo.isEmpty()) {
					System.out.println(
							"RECEPCAO: Cliente " + ++id + " chegou e deseja pedido de " + tempo + " segundos.");

					// Instanciando oum cliente com tempo passado na entrada
					cliente = new Cliente(id, tempo);

					// Conectando ao ServerSocket do Servidor
					socketCliente = new Socket("localhost", 12345);

					ObjectOutputStream output = new ObjectOutputStream(socketCliente.getOutputStream());

					// Enviando o cliente criado ao Servidor
					output.writeObject(cliente);
					output.flush();

					// Fechando conexões
					output.close();
					teclado.close();
					socketCliente.close();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
