package es.studium.programa_gestion_biblioteca;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ModificarPrestamo implements WindowListener, ActionListener {
	Frame ventana = new Frame("Edición Prestamo");
	Dialog dlgEdicion = new Dialog(ventana, "Editar Prestamo", true);
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);

	Label lblElegir = new Label("Elegir prestamo a editar:");

	Choice choPrestamos = new Choice();

	Button btnEditar = new Button("Editar");
	Button btnCancelar = new Button("Cancelar");

	Label lblTitulo = new Label("Edición Prestamo");
	Label lblFechaPrestamo = new Label("Fecha Prestamo:");
	Label lblFechaDevolucion = new Label("Fecha Devolucion:");
	Label lblMensaje = new Label("Edición de Prestamo Correcta");

	TextField txtFechaPrestamo = new TextField(10);
	TextField txtFechaDevolucion = new TextField(10);
	

	Button btnModificar = new Button("Modificar");
	Button btnVolver = new Button("Volver");

	Conexion conexion = new Conexion();
	String idPrestamo = "";

	ModificarPrestamo() {
		ventana.setLayout(new FlowLayout());
		ventana.setSize(220, 150);
		ventana.addWindowListener(this);

		ventana.add(lblElegir);
		// Rellenar el Choice con los elementos de la tabla habitaciones
		conexion.rellenarChoicePrestamos(choPrestamos);
		ventana.add(choPrestamos);
		btnEditar.addActionListener(this);
		ventana.add(btnEditar);
		btnCancelar.addActionListener(this);
		ventana.add(btnCancelar);

		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);

		dlgMensaje.setLayout(new FlowLayout());
		dlgMensaje.setSize(250, 100);
		dlgMensaje.addWindowListener(this);
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (dlgEdicion.isActive()) {
			dlgEdicion.setVisible(false);
			dlgMensaje.setVisible(false);
		} else if (dlgMensaje.isActive()) {
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
		} else if (e.getSource().equals(btnEditar)) {
			// Si NO está seleccionada la primera opción

			if (choPrestamos.getSelectedIndex() != 0) {
				String tabla[] = choPrestamos.getSelectedItem().split("-");
				idPrestamo = tabla[0];
				String datosObtenidos = conexion.editarPrestamo(idPrestamo);

				String[] tablaDatos = datosObtenidos.split("-");
				dlgEdicion.setLayout(new FlowLayout());
				dlgEdicion.setSize(150, 350);
				dlgEdicion.addWindowListener(this);

				
				
				
				
				dlgEdicion.add(lblTitulo);
				dlgEdicion.add(lblFechaPrestamo);
				txtFechaPrestamo.setText(tablaDatos[2]);
				dlgEdicion.add(txtFechaPrestamo);
				dlgEdicion.add(lblFechaDevolucion);
				txtFechaDevolucion.setText(tablaDatos[3]);
				dlgEdicion.add(txtFechaDevolucion);

				btnModificar.addActionListener(this);
				btnVolver.addActionListener(this);

				dlgEdicion.add(btnModificar);
				dlgEdicion.add(btnVolver);

				dlgEdicion.setResizable(false);
				dlgEdicion.setLocationRelativeTo(null);
				dlgEdicion.setVisible(true);
			}
		} else if (e.getSource().equals(btnModificar)) {
			if (txtFechaPrestamo.getText().length() == 0 || txtFechaDevolucion.getText().length() == 0
					) {
				lblMensaje.setText("Los campos están vacíos");
			} else {
				String sentencia = "UPDATE detallesprestamo SET fechaPrestamo = '" + txtFechaPrestamo.getText()
			    + "', fechaDevolucion = '" + txtFechaDevolucion.getText()+"' WHERE idPrestamoFK2 = " + idPrestamo;;
				int respuesta = conexion.modificarPrestamo(sentencia);
				if (respuesta != 0) {
					// Mostrar Mensaje Error
					lblMensaje.setText("Error en Modificación");
				} else {
					lblMensaje.setText("Modificación de Prestamo Correcta");
				}
			}
			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		} else if (e.getSource().equals(btnVolver)) {
			dlgEdicion.setVisible(false);
		}
	}

}
