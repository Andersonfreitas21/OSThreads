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
			Servidor server = new Servidor();
			
			Scanner teclado = new Scanner(System.in);
			
			
			while (true) {

				String tempo = teclado.nextLine();

				if (!tempo.isEmpty()) {
					System.out.println("RECEPCAO: Cliente " + ++id + 
							" chegou e deseja pedido de " + tempo + " segundos.");
					cliente = new Cliente(id, tempo);
					socketCliente = new Socket("localhost", 12345);
					
					ObjectOutputStream output = new ObjectOutputStream(socketCliente.getOutputStream());
					output.writeObject(cliente);
					output.flush();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
