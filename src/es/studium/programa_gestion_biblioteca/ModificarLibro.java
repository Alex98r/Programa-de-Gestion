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

public class ModificarLibro implements WindowListener, ActionListener {
	Frame ventana = new Frame("Edición Libro");
	Dialog dlgEdicion = new Dialog(ventana, "Editar Libro", true);
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);

	Label lblElegir = new Label("Elegir el Libro a editar:");

	Choice choLibros = new Choice();

	Button btnEditar = new Button("Editar");
	Button btnCancelar = new Button("Cancelar");

	Label lblTituloMenu = new Label("Edición de Libro");
	Label lblTitulo = new Label("Título");
	Label lblAutor = new Label("Autor");
	Label lblGenero = new Label("Género");
	Label lblEditorial = new Label("Editorial");
	Label lblAnio = new Label("Año");
	Label lblPrecio = new Label("Precio");
	Label lblMensaje = new Label("Edición de Libro Correcta");

	TextField txtTitulo = new TextField(10);
	TextField txtAutor = new TextField(10);
	TextField txtGenero = new TextField(10);
	TextField txtEditorial = new TextField(10);
	TextField txtAnio = new TextField(10);
	TextField txtPrecio = new TextField(10);
	

	Button btnModificar = new Button("Modificar");
	Button btnVolver = new Button("Volver");

	Conexion conexion = new Conexion();
	String idLibro = "";

	ModificarLibro() {
		ventana.setLayout(new FlowLayout());
		ventana.setSize(220, 200);
		ventana.addWindowListener(this);

		ventana.add(lblElegir);
		// Rellenar el Choice con los elementos de la tabla departamentos
		conexion.rellenarChoiceLibros(choLibros);
		ventana.add(choLibros);
		btnEditar.addActionListener(this);
		ventana.add(btnEditar);
		btnCancelar.addActionListener(this);
		ventana.add(btnCancelar);

		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);

		dlgMensaje.setLayout(new FlowLayout());
		dlgMensaje.setSize(250, 300);
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

			if (choLibros.getSelectedIndex() != 0) {
				String tabla[] = choLibros.getSelectedItem().split("-");
				idLibro = tabla[0];
				String datosObtenidos = conexion.editarLibro(idLibro);

				String[] tablaDatos = datosObtenidos.split("-");
				dlgEdicion.setLayout(new FlowLayout());
				dlgEdicion.setSize(150, 550);
				dlgEdicion.addWindowListener(this);

				dlgEdicion.add(lblTituloMenu);
				dlgEdicion.add(lblTitulo);
				txtTitulo.setText(tablaDatos[0]);
				dlgEdicion.add(txtTitulo);
				dlgEdicion.add(lblAutor);
				txtAutor.setText(tablaDatos[1]);
				dlgEdicion.add(txtAutor);
				dlgEdicion.add(lblGenero);
				txtGenero.setText(tablaDatos[2]);
				dlgEdicion.add(txtGenero);
				dlgEdicion.add(lblEditorial);
				txtEditorial.setText(tablaDatos[3]);
				dlgEdicion.add(txtEditorial);
				dlgEdicion.add(lblAnio);
				txtAnio.setText(tablaDatos[4]);
				dlgEdicion.add(txtAnio);
				dlgEdicion.add(lblPrecio);
				txtPrecio.setText(tablaDatos[5]);
				dlgEdicion.add(txtPrecio);

				btnModificar.addActionListener(this);
				btnVolver.addActionListener(this);

				dlgEdicion.add(btnModificar);
				dlgEdicion.add(btnVolver);

				dlgEdicion.setResizable(false);
				dlgEdicion.setLocationRelativeTo(null);
				dlgEdicion.setVisible(true);
			}
		} else if (e.getSource().equals(btnModificar)) {
			if (txtTitulo.getText().length() == 0 || txtAutor.getText().length() == 0
					|| txtPrecio.getText().length() == 0 || txtGenero.getText().length() == 0) {
				lblMensaje.setText("Los campos están vacíos");
			} else {
				String sentencia = "UPDATE libros SET Titulo = '" + txtTitulo.getText()
						+ "', Autor = '" + txtAutor.getText() + "', Genero = '"
						+ txtGenero.getText() + "', Editorial = '" + txtEditorial.getText()
						+ "', Anio_Publicacion = '" + txtAnio.getText() + "', Precio = '"
						+ txtPrecio.getText()
						+ "' WHERE idLibro = " + idLibro + ";";
				int respuesta = conexion.modificarLibro(sentencia);
				if (respuesta != 0) {
					// Mostrar Mensaje Error
					lblMensaje.setText("Error en Modificación");
				} else {
					lblMensaje.setText("Modificación de libro Correcta");
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
