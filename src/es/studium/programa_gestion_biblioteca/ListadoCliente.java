package es.studium.programa_gestion_biblioteca;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ListadoCliente implements WindowListener, ActionListener {
	Frame ventana = new Frame("Listado de Clientes");

	TextArea txtListado = new TextArea(10, 40);
	Conexion conexion = new Conexion();

	ListadoCliente() {
		ventana.setLayout(new FlowLayout());
		ventana.setSize(340, 260);
		ventana.addWindowListener(this);

		txtListado.append("id\tNombre\t\tCorreo\n");
		// Sacar de la tabla clientes todos los registros seleccionados
		txtListado.append(conexion.obtenerClientes());
		ventana.add(txtListado);


		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
	

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		ventana.setVisible(false);
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	        }

}
