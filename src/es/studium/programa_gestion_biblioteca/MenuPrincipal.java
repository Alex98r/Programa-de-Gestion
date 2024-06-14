package es.studium.programa_gestion_biblioteca;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MenuPrincipal

		implements WindowListener, ActionListener {
	Frame ventana = new Frame("Menú Principal");

	MenuBar menuBar = new MenuBar();

	Menu mnuClientes = new Menu("Clientes");
	Menu mnLibro = new Menu("Libros");
	Menu mnuPrestamo = new Menu("Prestamos");
	

	MenuItem mniNuevoCliente = new MenuItem("Nuevo");
	MenuItem mniBajaCliente = new MenuItem("Baja");
	MenuItem mniListadoCliente = new MenuItem("Listado");
	MenuItem mniModificarCliente = new MenuItem("Modificar");

	MenuItem mniNuevoLibro = new MenuItem("Nuevo");
	MenuItem mniBajaLibro = new MenuItem("Baja");
	MenuItem mniListadoLibro = new MenuItem("Listado");
	MenuItem mniModificarLibro = new MenuItem("Modificar");

	MenuItem mniNuevoPrestamo = new MenuItem("Nuevo");
	MenuItem mniBajaPrestamo = new MenuItem("Baja");
	MenuItem mniListadoPrestamo = new MenuItem("Listado");
	MenuItem mniModificarPrestamo = new MenuItem("Modificar");

	int tipoUsuario;

	MenuPrincipal(int t) {
		tipoUsuario = t;
		ventana.setLayout(new FlowLayout());
		ventana.addWindowListener(this);

		mniNuevoCliente.addActionListener(this);
		mniBajaCliente.addActionListener(this);
		mniListadoCliente.addActionListener(this);
		mniModificarCliente.addActionListener(this);

		mniNuevoLibro.addActionListener(this);
		mniBajaLibro.addActionListener(this);
		mniListadoLibro.addActionListener(this);
		mniModificarLibro.addActionListener(this);

		mniNuevoPrestamo.addActionListener(this);
		mniBajaPrestamo.addActionListener(this);
		mniListadoPrestamo.addActionListener(this);
		mniModificarPrestamo.addActionListener(this);

		mnuClientes.add(mniNuevoCliente);
		mnuPrestamo.add(mniNuevoLibro);
		mnLibro.add(mniNuevoPrestamo);
		if (tipoUsuario != 0) {
			mnuClientes.add(mniBajaCliente);
			mnuClientes.add(mniListadoCliente);
			mnuClientes.add(mniModificarCliente);
			mnuPrestamo.add(mniListadoLibro);
			mnuPrestamo.add(mniModificarLibro);
			mnLibro.add(mniBajaPrestamo);
			mnLibro.add(mniListadoPrestamo);
			mnLibro.add(mniModificarPrestamo);

		}

		menuBar.add(mnuClientes);
		menuBar.add(mnLibro);
		menuBar.add(mnuPrestamo);

		ventana.setMenuBar(menuBar);

		ventana.setSize(400, 400);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
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
		if (e.getSource().equals(mniNuevoCliente)) {
			new NuevoCliente();
		} else if (e.getSource().equals(mniBajaCliente)) {
			new BajaCliente();
		} else if (e.getSource().equals(mniListadoCliente)) {
			new ListadoCliente();
		} else if (e.getSource().equals(mniModificarCliente)) {
			new ModificarCliente();
		} else if (e.getSource().equals(mniNuevoLibro)) {
			new NuevoPrestamo();
		} else if (e.getSource().equals(mniListadoLibro)) {
			new ListadoPrestamo();
		} else if (e.getSource().equals(mniModificarLibro)) {
			new ModificarPrestamo();
		} else if (e.getSource().equals(mniNuevoPrestamo)) {
			new NuevoLibro();
		} else if (e.getSource().equals(mniBajaPrestamo)) {
			new BajaLibro();
		} else if (e.getSource().equals(mniListadoPrestamo)) {
			new ListadoLibro();
		} else if (e.getSource().equals(mniModificarPrestamo)) {
			new ModificarLibro();
		}
	}

}
