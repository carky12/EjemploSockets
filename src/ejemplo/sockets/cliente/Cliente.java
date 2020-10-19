package ejemplo.sockets.cliente;

import javax.swing.JFrame;

public class Cliente {

	public static void main(String[] args) {
		
		InterfazCliente cliente = new InterfazCliente();
		
		cliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
}
