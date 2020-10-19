package ejemplo.sockets.servidor;

import javax.swing.JFrame;

public class Servidor {

	public static void main(String[] args) {
		
		InterfazServidor servidor = new InterfazServidor();
		
		servidor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
