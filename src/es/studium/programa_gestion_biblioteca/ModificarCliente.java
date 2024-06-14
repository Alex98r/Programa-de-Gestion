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

public class ModificarCliente implements WindowListener, ActionListener {
	Frame ventana = new Frame("Edición Cliente");
	Dialog dlgEdicion = new Dialog(ventana, "Editar Cliente", true);
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);

	Label lblElegir = new Label("Elegir el cliente a editar:");

	Choice choClientes = new Choice();

	Button btnEditar = new Button("Editar");
	Button btnCancelar = new Button("Cancelar");

	Label lblTitulo = new Label("------- Edición de Cliente -------");
	Label lblNombre = new Label("Nombre:");
	Label lblPrimerApellido = new Label("Primer Apellido:");
	Label lblSegundoApellido = new Label("Segundo Apellido:");
	Label lblFechaNacimiento = new Label("Fecha Nacimiento:");
	Label lblDni = new Label("DNI:");
	Label lblTelefono = new Label("Telefono:");
	Label lblDireccion = new Label("Direccion:");
	Label lblCorreo = new Label("Correo");
	Label lblMensaje = new Label("Edición de Cliente Correcta");

	TextField txtNombre = new TextField(10);
	TextField txtPrimerApellido = new TextField(10);
	TextField txtSegundoApellido = new TextField(10);
	TextField txtFechaNacimiento = new TextField(10);
	TextField txtDni = new TextField(10);
	TextField txtTelefono = new TextField(10);
	TextField txtDireccion = new TextField(10);
	TextField txtCorreo = new TextField(10);

	Button btnModificar = new Button("Modificar");
	Button btnVolver = new Button("Volver");

	Conexion conexion = new Conexion();
	String idCliente = "";

	ModificarCliente() {
		ventana.setLayout(new FlowLayout());
		ventana.setSize(220, 150);
		ventana.addWindowListener(this);

		ventana.add(lblElegir);
		// Rellenar el Choice con los elementos de la tabla clientes
		conexion.rellenarChoiceClientes(choClientes);
		ventana.add(choClientes);
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

			if (choClientes.getSelectedIndex() != 0) {
				String tabla[] = choClientes.getSelectedItem().split("-");
				idCliente = tabla[0];
				String datosObtenidos = conexion.editarCliente(idCliente);

				String[] tablaDatos = datosObtenidos.split("-");
				dlgEdicion.setLayout(new FlowLayout());
				dlgEdicion.setSize(150, 570);
				dlgEdicion.addWindowListener(this);

				dlgEdicion.add(lblTitulo);
				dlgEdicion.add(lblNombre);
				txtNombre.setText(tablaDatos[0]);
				dlgEdicion.add(txtNombre);
				dlgEdicion.add(lblPrimerApellido);
				txtPrimerApellido.setText(tablaDatos[1]);
				dlgEdicion.add(txtPrimerApellido);
				dlgEdicion.add(lblSegundoApellido);
				txtSegundoApellido.setText(tablaDatos[2]);
				dlgEdicion.add(txtSegundoApellido);
				dlgEdicion.add(lblFechaNacimiento);
				txtFechaNacimiento.setText(tablaDatos[3]);
				dlgEdicion.add(txtFechaNacimiento);
				dlgEdicion.add(lblDni);
				txtDni.setText(tablaDatos[4]);
				dlgEdicion.add(txtDni);
				dlgEdicion.add(lblTelefono);
				txtTelefono.setText(tablaDatos[5]);
				dlgEdicion.add(txtTelefono);
				dlgEdicion.add(lblDireccion);
				txtDireccion.setText(tablaDatos[6]);
				dlgEdicion.add(txtDireccion);
				dlgEdicion.add(lblCorreo);
				txtCorreo.setText(tablaDatos[7]);
				dlgEdicion.add(txtCorreo);

				btnModificar.addActionListener(this);
				btnVolver.addActionListener(this);

				dlgEdicion.add(btnModificar);
				dlgEdicion.add(btnVolver);

				dlgEdicion.setResizable(false);
				dlgEdicion.setLocationRelativeTo(null);
				dlgEdicion.setVisible(true);

			}
		} else if (e.getSource().equals(btnModificar)) {
			if (txtNombre.getText().length() == 0 || txtPrimerApellido.getText().length() == 0
					|| txtSegundoApellido.getText().length() == 0 || txtFechaNacimiento.getText().length() == 0
					|| txtDni.getText().length() == 0 || txtTelefono.getText().length() == 0
					|| txtDireccion.getText().length() == 0 || txtCorreo.getText().length() == 0) {
				lblMensaje.setText("Los campos están vacíos");
			}
			// Comprobar restricciones
			else if (txtDni.getText().length() != 9) {
				lblMensaje.setText("DNI no correcto");
			} else if (txtTelefono.getText().length() != 9) {
				lblMensaje.setText("El telefóno tiene que tener 9 dígitos");
			} else {
				String sentencia = "UPDATE clientes SET nombreCliente = '" + txtNombre.getText()
						+ "', primerApellidoCliente = '" + txtPrimerApellido.getText() + "', segundoApellidoCliente = '"
						+ txtSegundoApellido.getText() + "', fechaNacimientoCliente = '" + txtFechaNacimiento.getText()
						+ "', dniCliente = '" + txtDni.getText() + "', telefonoCliente = '" + txtTelefono.getText()
						+ "', direccionCliente = '" + txtDireccion.getText() + "', correoElectronicoCliente = '"
						+ txtCorreo.getText() + "' WHERE idCliente = " + idCliente + ";";
				int respuesta = conexion.modificarCliente(sentencia);
				if (respuesta != 0) {
					// Mostrar Mensaje Error
					lblMensaje.setText("Error en Modificación");
				} else {
					lblMensaje.setText("Modificación de Cliente Correcta");
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
