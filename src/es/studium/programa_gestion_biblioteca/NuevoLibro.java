package es.studium.programa_gestion_biblioteca;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class NuevoLibro implements WindowListener, ActionListener {
	Frame ventana = new Frame("Nuevo Libro");
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);

	Label lblTituloMenu = new Label("Alta de Libro");
	Label lblTitulo = new Label("Título");
	Label lblAutor = new Label("Autor");
	Label lblGenero = new Label("Género");
	Label lblEditorial = new Label("Editorial");
	Label lblAnio = new Label("Año");
	Label lblPrecio = new Label("Precio");
	Label lblMensaje = new Label("Alta de Libro Correcta");

	TextField txtTitulo = new TextField(10);
	TextField txtAutor = new TextField(10);
	TextField txtGenero = new TextField(10);
	TextField txtEditorial = new TextField(10);
	TextField txtAnio = new TextField(10);
	TextField txtPrecio = new TextField(10);

	Button btnAceptar = new Button("Aceptar");
	Button btnCancelar = new Button("Cancelar");

	Conexion conexion = new Conexion();

	NuevoLibro() {
		
		ventana.setLayout(new FlowLayout());
		ventana.setSize(150, 570);
		ventana.addWindowListener(this);

		ventana.add(lblTituloMenu);
		ventana.add(lblTitulo);
		ventana.add(txtTitulo);
		ventana.add(lblAutor);
		ventana.add(txtAutor);
		ventana.add(lblGenero);
		ventana.add(txtGenero);
		ventana.add(lblEditorial);
		ventana.add(txtEditorial);
		ventana.add(lblAnio);
		ventana.add(txtAnio);
		ventana.add(lblPrecio);
		ventana.add(txtPrecio);
		
		
		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);

		ventana.add(btnAceptar);
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
		if (dlgMensaje.isActive()) {
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
		// Nuevo Departamento
		if (e.getSource().equals(btnAceptar)) {
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(250, 300);
			dlgMensaje.addWindowListener(this);

			if (txtTitulo.getText().length() == 0 || txtAutor.getText().length() == 0
					|| txtGenero.getText().length() == 0 || txtEditorial.getText().length() == 0
					|| txtAnio.getText().length() == 0 || txtPrecio.getText().length() == 0)
					 {
				lblMensaje.setText("Los campos están vacíos");
			} else {
				// Dar de alta
				String sentencia = "INSERT INTO libros VALUES (null, '" + txtTitulo.getText() + "', '"
						+ txtAutor.getText() + "', '" + txtGenero.getText() + "', '"
						+ txtEditorial.getText() + "', '" + txtAnio.getText() + "', '" + txtPrecio.getText()+ "');";
				int respuesta = conexion.altaLibro(sentencia);
				if (respuesta != 0) {
					// Mostrar Mensaje Error
					lblMensaje.setText("Error en Alta");
				} else {
					lblMensaje.setText("Alta de libro Correcta");
				}
			}

			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		} else if (e.getSource().equals(btnCancelar)) {
			txtTitulo.setText("");
			txtAutor.setText("");
			txtGenero.setText("");
			txtEditorial.setText("");
			txtTitulo.requestFocus();
		}
	}

}
