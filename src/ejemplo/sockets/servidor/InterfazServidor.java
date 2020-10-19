package ejemplo.sockets.servidor;

import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ejemplo.sockets.modelo.PaqueteEnvio;

public class InterfazServidor extends JFrame implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private	JTextArea txtAreaTexto;
	
	public InterfazServidor() {
		
		setBounds(1200,300,280,350);				
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		
		txtAreaTexto=new JTextArea();
		
		panel.add(txtAreaTexto,BorderLayout.CENTER);
		
		add(panel);
		
		setVisible(true);
		
		Thread hilo = new Thread(this);				
		
		hilo.start();
	}

	@Override
	public void run() {
		//System.out.println("estoy esperando");
		
		try {
			
			//Abrimos el socket en el puerto esperado (el indicado en el cliente)
			ServerSocket servidor = new ServerSocket(55);
			
			String nombre;
			String ip;
			String mensaje;			
			PaqueteEnvio datos;
			
			while (true) {
			
				//Aceptar las conexiones que vengan del serversocket
				Socket socket = servidor.accept();
				
				//Recibimos los datos del socket
				ObjectInputStream datos_entrada = new ObjectInputStream(socket.getInputStream());
			
				//Casteamos para obtener los datos
				datos = (PaqueteEnvio) datos_entrada.readObject();
				
				//Extraemos los datos
				nombre = datos.getNombre();
				ip = datos.getIp();
				mensaje = datos.getMensaje();
				
				//Mostramos los datos
				txtAreaTexto.append("\n" + nombre + ": " + mensaje + " para " + ip);
		
				socket.close();
				
				/*//Creamos el stream que recibirá los datos
				DataInputStream data_entrada = new DataInputStream(socket.getInputStream());
				
				//Obtenemos los datos
				String mensaje = data_entrada.readUTF();
				
				txtAreaTexto.append("\n" + mensaje);
				
				socket.close();*/
				
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
