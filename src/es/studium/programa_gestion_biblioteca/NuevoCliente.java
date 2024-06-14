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

public class NuevoCliente implements WindowListener, ActionListener {
	Frame ventana = new Frame("Nuevo Cliente");
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);

	Label lblTitulo = new Label("------- Alta de Cliente -------");
	Label lblNombre = new Label("Nombre:");
	Label lblPrimerApellido = new Label("Primer Apellido:");
	Label lblSegundoApellido = new Label("Segundo Apellido:");
	Label lblFechaNacimiento = new Label("Fecha Nacimiento:");
	Label lblDni = new Label("DNI:");
	Label lblTelefono = new Label("Telefono:");
	Label lblDireccion = new Label("Direccion:");
	Label lblCorreo = new Label("Correo");
	Label lblMensaje = new Label("Alta de Cliente Correcta");

	TextField txtNombre = new TextField(10);
	TextField txtPrimerApellido = new TextField(10);
	TextField txtSegundoApellido = new TextField(10);
	TextField txtFechaNacimiento = new TextField(10);
	TextField txtDni = new TextField(10);
	TextField txtTelefono = new TextField(10);
	TextField txtDireccion = new TextField(10);
	TextField txtCorreo = new TextField(10);

	Button btnAceptar = new Button("Aceptar");
	Button btnCancelar = new Button("Cancelar");

	Conexion conexion = new Conexion();

	NuevoCliente() {
		ventana.setLayout(new FlowLayout());
		ventana.setSize(150, 570);
		ventana.addWindowListener(this);

		ventana.add(lblTitulo);
		ventana.add(lblNombre);
		ventana.add(txtNombre);
		ventana.add(lblPrimerApellido);
		ventana.add(txtPrimerApellido);
		ventana.add(lblSegundoApellido);
		ventana.add(txtSegundoApellido);
		ventana.add(lblFechaNacimiento);
		ventana.add(txtFechaNacimiento);
		ventana.add(lblDni);
		ventana.add(txtDni);
		ventana.add(lblTelefono);
		ventana.add(txtTelefono);
		ventana.add(lblDireccion);
		ventana.add(txtDireccion);
		ventana.add(lblCorreo);
		ventana.add(txtCorreo);

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
		// Nuevo Cliente
		if (e.getSource().equals(btnAceptar)) {
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(170, 100);
			dlgMensaje.addWindowListener(this);

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
				// Dar de alta
				String sentencia = "INSERT INTO clientes VALUES (null, '" + txtNombre.getText() + "', '"
						+ txtPrimerApellido.getText() + "', '" + txtSegundoApellido.getText() + "', '"
						+ txtFechaNacimiento.getText() + "', '" + txtDni.getText() + "', '" + txtTelefono.getText()
						+ "', '" + txtDireccion.getText() + "', '" + txtCorreo.getText() + "');";
				int respuesta = conexion.altaCliente(sentencia);
				if (respuesta != 0) {
					// Mostrar Mensaje Error
					lblMensaje.setText("Error en Alta");
				} else {
					lblMensaje.setText("Alta de Cliente Correcta");
				}
			}

			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		} else if (e.getSource().equals(btnCancelar)) {
			txtNombre.setText("");
			txtPrimerApellido.setText("");
			txtSegundoApellido.setText("");
			txtFechaNacimiento.setText("");
			txtDni.setText("");
			txtTelefono.setText("");
			txtDireccion.setText("");
			txtCorreo.setText("");
			txtNombre.requestFocus();
		}
	}
}
