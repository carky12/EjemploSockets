package ejemplo.sockets.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ejemplo.sockets.modelo.PaqueteEnvio;

public class PanelCliente extends JPanel implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField txtTexto, txtNombreUsuario, txtIp;
	
	private JButton btnEnviar;
	
	private JTextArea txtAreaTexto;
	
	public PanelCliente(){	
		
		txtNombreUsuario = new JTextField(5);	
		add(txtNombreUsuario);
		
		JLabel texto = new JLabel("- CHAT -");		
		add(texto);
	
		txtIp = new JTextField(8);	
		add(txtIp);	
	
		txtTexto = new JTextField(20);	
		add(txtTexto);
	
		txtAreaTexto = new JTextArea(12, 20);		
		add(txtAreaTexto);
	
		btnEnviar = new JButton("Enviar");		
		btnEnviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
			
				txtAreaTexto.append("\n" + txtTexto.getText());
				
				try {
					
					//Creamos el socket
					Socket socket = new Socket("192.168.0.11", 55);
					
					//Creamos el paquete con los datos que vamos a enviar al servidor
					PaqueteEnvio datos = new PaqueteEnvio();
					datos.setNombre(txtNombreUsuario.getText());
					datos.setIp(txtIp.getText());
					datos.setMensaje(txtAreaTexto.getText());
					
					//Creamos el flujo de datos de salida
					ObjectOutputStream datos_salida = new ObjectOutputStream(socket.getOutputStream());
					
					//Añadimos los datos al stream
					datos_salida.writeObject(datos);
					
					//Cerramos el socket
					socket.close();
					
					/*//Añadimos los datos del socket al stream
					DataOutputStream data_salida = new DataOutputStream(socket.getOutputStream());
					
					//Añadimos el texto al stream
					data_salida.writeUTF(txtTexto.getText());
					
					//Cerramos el stream
					data_salida.close();*/
					
					
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println(e1.getMessage());
				}
				
			}
		});
		
		add(btnEnviar);	
		
		Thread hilo = new Thread(this);
		
		hilo.start();
		
	}

	@Override
	public void run() {
		
		try {
			
			ServerSocket socket_servidor_cliente = new ServerSocket(56);
		
			Socket cliente;
			
			PaqueteEnvio datos_recibidos;
			
			while (true) {
				
				cliente = socket_servidor_cliente.accept();
				
				ObjectInputStream datos_entrada = new ObjectInputStream(cliente.getInputStream());
				
				datos_recibidos = (PaqueteEnvio) datos_entrada.readObject();
				
				txtAreaTexto.append("\n" + datos_recibidos.getNombre() + ": " + datos_recibidos.getMensaje());
				
				
			}
		
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
}
