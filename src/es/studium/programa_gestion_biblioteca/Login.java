package es.studium.programa_gestion_biblioteca;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Login implements WindowListener, ActionListener, KeyListener {
	Frame ventanaLogin = new Frame("Login");
	Dialog dlgMensaje = new Dialog(ventanaLogin, "Error", true);

	Label lblUsuario = new Label("Usuario:");
	Label lblClave = new Label("Clave:    ");
	Label lblMensaje = new Label("Credenciales Incorrectas");

	TextField txtUsuario = new TextField(10);
	TextField txtClave = new TextField(10);

	Button btnAcceder = new Button("Acceder");
	int tipoUsuario;

	Conexion conexion = new Conexion();

	Login() {
		ventanaLogin.setLayout(new FlowLayout());
		ventanaLogin.addWindowListener(this);

		ventanaLogin.add(lblUsuario);
		ventanaLogin.add(txtUsuario);
		ventanaLogin.add(lblClave);
		txtClave.setEchoChar('*');
		ventanaLogin.add(txtClave);
		btnAcceder.addActionListener(this);
		ventanaLogin.add(btnAcceder);

		ventanaLogin.setSize(220, 135);
		ventanaLogin.setResizable(false);
		ventanaLogin.setLocationRelativeTo(null);

		ventanaLogin.setVisible(true);
	}

	public static void main(String[] args) {
		new Login();
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (dlgMensaje.isActive()) {
			dlgMensaje.setVisible(false);
		} else {
			System.exit(0);
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
		if (e.getSource().equals(btnAcceder)) {
			comprobarCredenciales();
		}
	}

	private void comprobarCredenciales() {
		// TODO Auto-generated method stub
		String usuario = txtUsuario.getText();
		String clave = txtClave.getText();

		tipoUsuario = conexion.comprobarCredenciales(usuario, clave);
		if (tipoUsuario != -1) {
			new MenuPrincipal(tipoUsuario);
			ventanaLogin.setVisible(false);
		}
		// Credenciales incorrectas
		else {
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.addWindowListener(this);

			dlgMensaje.add(lblMensaje);
			dlgMensaje.setSize(210, 80);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			comprobarCredenciales();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
