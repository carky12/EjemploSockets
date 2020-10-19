package ejemplo.sockets.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PanelCliente extends JPanel {
	
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
			
				try {
					
					//Creamos el socket
					Socket socket = new Socket("127.0.0.1", 55);
					
					//A�adimos los datos del socket al stream
					DataOutputStream data_salida = new DataOutputStream(socket.getOutputStream());
					
					//A�adimos el texto al stream
					data_salida.writeUTF(txtTexto.getText());
					
					//Cerramos el stream
					data_salida.close();
					
					
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
		
	}
}