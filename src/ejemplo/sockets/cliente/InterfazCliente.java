package ejemplo.sockets.cliente;

import javax.swing.JFrame;

public class InterfazCliente extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InterfazCliente() {
		
		setBounds(600,300,280,350);
		
		PanelCliente panel = new PanelCliente();
		
		add(panel);
		
		setVisible(true);
		
		
	}
	
	

}
