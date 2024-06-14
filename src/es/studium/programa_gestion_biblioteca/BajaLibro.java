package es.studium.programa_gestion_biblioteca;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class BajaLibro implements WindowListener, ActionListener {
	Frame ventana = new Frame("Baja Libro");
	Dialog dlgConfirmacion = new Dialog(ventana, "¿Segur@?", true);
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);

	Label lblElegir = new Label("Elegir libro a borrar:");
	Label lblConfirmar = new Label("¿Seguro de borrar el libro ");
	Label lblMensaje = new Label("Eliminación Correcta");

	Choice choLibro = new Choice();

	Button btnEliminar = new Button("Eliminar");
	Button btnCancelar = new Button("Cancelar");
	Button btnSi = new Button("Sí");
	Button btnNo = new Button("No");

	Conexion conexion = new Conexion();

	BajaLibro() {
		ventana.setLayout(new FlowLayout());
		ventana.setSize(220, 150);
		ventana.addWindowListener(this);

		ventana.add(lblElegir);
		// Rellenar el Choice con los elementos de la tabla libros
		conexion.rellenarChoiceLibros(choLibro);
		ventana.add(choLibro);
		btnEliminar.addActionListener(this);
		ventana.add(btnEliminar);
		btnCancelar.addActionListener(this);
		ventana.add(btnCancelar);

		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (dlgConfirmacion.isActive()) {
			dlgConfirmacion.setVisible(false);
		} else if (dlgMensaje.isActive()) {
			dlgConfirmacion.setVisible(false);
			dlgMensaje.setVisible(false);
		} else {
			ventana.setVisible(false);
		}
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
		if (e.getSource().equals(btnCancelar)) {
			ventana.setVisible(false);
		} else if (e.getSource().equals(btnEliminar)) {
			// Si NO está seleccionada la primera opción
			if (choLibro.getSelectedIndex() != 0) {
				dlgConfirmacion.setLayout(new FlowLayout());
				dlgConfirmacion.setSize(250, 100);
				dlgConfirmacion.addWindowListener(this);

				lblConfirmar.setText("¿Seguro de borrar el libro '" + choLibro.getSelectedItem() + "'?");
				dlgConfirmacion.add(lblConfirmar);
				btnSi.addActionListener(this);
				dlgConfirmacion.add(btnSi);
				btnNo.addActionListener(this);
				dlgConfirmacion.add(btnNo);

				dlgConfirmacion.setResizable(false);
				dlgConfirmacion.setLocationRelativeTo(null);
				dlgConfirmacion.setVisible(true);
			}
		} else if (e.getSource().equals(btnNo)) {
			dlgConfirmacion.setVisible(false);
		} else if (e.getSource().equals(btnSi)) {
			String tabla[] = choLibro.getSelectedItem().split("-");
			int resultado = conexion.eliminarLibro(tabla[0]); // idLibro --> DELETE

			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(250, 100);
			dlgMensaje.addWindowListener(this);

			// Baja correcta
			if (resultado == 0) {
				// Mostramos el Diálogo con el OK
				lblMensaje.setText("Eliminación Correcta");
			}
			// Error en Baja
			else {
				// Mostramos el Diálogo con el ERROR
				lblMensaje.setText("ERROR en Eliminación");
			}

			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		}
	}

}
