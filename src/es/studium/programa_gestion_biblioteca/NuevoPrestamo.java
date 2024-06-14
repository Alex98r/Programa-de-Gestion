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

public class NuevoPrestamo implements WindowListener, ActionListener {
	Frame ventana = new Frame("Nuevo prestamo");
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);
	Choice choCantLibro = new Choice();
	Choice choClientes = new Choice();
	Choice choLibro1 = new Choice();
	Choice choLibro2 = new Choice();
	Choice choLibro3 = new Choice();
	
	Dialog dlgPrestamo = new Dialog(ventana, "Alta Préstamo", true);
	
    
	Label lblTitulo = new Label("------- Alta de Prestamo -------");
	Label lblCliente = new Label("Número Cliente:");
	Label lblCantLibro = new Label("Cantidad Libros:");
	Label lblLibro1 = new Label("Número libro 1:");
	Label lblLibro2 = new Label("Número libro 2:");
	Label lblLibro3 = new Label("Número libro 3:");
	Label lblFechaPrestamo = new Label("Fecha Prestamo:");
	Label lblFechaDevolucion = new Label("Fecha Devolución:");
	Label lblMensaje = new Label("Alta de Prestamo Correcta");

	TextField txtCliente = new TextField(10);
	TextField txtLibro1 = new TextField(10);
	TextField txtLibro2 = new TextField(10);
	TextField txtLibro3 = new TextField(10);
	TextField txtFechaPrestamo = new TextField(10);
	TextField txtFechaDevolucion = new TextField(10);
	
	
	Button btnAceptar = new Button("Aceptar");
	Button btnCancelar = new Button("Cancelar");
	Button btnLibros = new Button("Añadir Libro, Libros");

	Conexion conexion = new Conexion();

	NuevoPrestamo() {
		ventana.setLayout(new FlowLayout());
		ventana.setSize(150, 450);
		ventana.addWindowListener(this);
		dlgPrestamo.setLayout(new FlowLayout());
		dlgPrestamo.setSize(150, 500);
		ventana.add(lblTitulo);
		ventana.add(lblCantLibro);
		ventana.add(choCantLibro);
		choCantLibro.add("1");
		choCantLibro.add("2");
		choCantLibro.add("3");
		ventana.add(btnLibros);
		

		
		btnCancelar.addActionListener(this);
		btnLibros.addActionListener(this);


		

		ventana.setResizable(true);
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
		
		// Nuevo Prestamo
		if (e.getSource().equals(btnAceptar)) {
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(200, 100);
			dlgMensaje.addWindowListener(this);

      
        	
			String opcionSeleccionada = choCantLibro.getSelectedItem();
			
			
			
			if (opcionSeleccionada.equals("1")&&choClientes.getSelectedIndex()==0 || choLibro1.getSelectedIndex()==0
					||txtFechaPrestamo.getText().length() == 0 || txtFechaDevolucion.getText().length() == 0
			) {
		lblMensaje.setText("Los campos están vacíos");
	}
			else{
				// Dar de alta Prestamo 1 libro
					String tabla[] = choClientes.getSelectedItem().split("-");
					String obtenerClienteSeleccionado = tabla[0];
					int clienteSeleccionado = Integer.parseInt(obtenerClienteSeleccionado);
					String tablaLibros1[] = choLibro1.getSelectedItem().split("-");
					String obtenerLibroSeleccionado = tablaLibros1[0];
					int LibroeSeleccionado1 = Integer.parseInt(obtenerLibroSeleccionado);
					
				
				String sentencia = "INSERT INTO prestamo VALUES (null, '" + clienteSeleccionado + "', '"
						+ choCantLibro.getSelectedItem() 
						+ "');";
				
				int respuesta = conexion.altaPrestamo(sentencia);
				
				
				conexion.obtenerUltimoPrestamo();
				
				int ultimoPrestamo = conexion.obtenerUltimoPrestamo();
				
				String sentencia1 = "INSERT INTO detallesprestamo VALUES ("+ultimoPrestamo+",'"+LibroeSeleccionado1+ "', '"+ txtFechaPrestamo.getText() + "', '"
						+ txtFechaDevolucion.getText() 
						+ "');";
				
				int respuesta1 = conexion.altaDetallesPrestamo(sentencia1);
				
				int repestafinal= respuesta+respuesta1;
				
				
				if (repestafinal != 0) {
					// Mostrar Mensaje Error
					lblMensaje.setText("Error en Alta");
				} else {
					lblMensaje.setText("Alta de Prestamo Correcta");
				}
			}
			
			
			if (opcionSeleccionada.equals("2")&&choClientes.getSelectedIndex()==0 || choLibro1.getSelectedIndex()==0
					|| choLibro2.getSelectedIndex()==0
							||txtFechaPrestamo.getText().length() == 0 || txtFechaDevolucion.getText().length() == 0
					) {
				lblMensaje.setText("Los campos están vacíos");
			}
			if  (choLibro2.getSelectedItem() != null&&opcionSeleccionada.equals("2")) {
				// Dar de alta Prestamo 2 libros
				String tabla[] = choClientes.getSelectedItem().split("-");
				String obtenerClienteSeleccionado = tabla[0];
				int clienteSeleccionado = Integer.parseInt(obtenerClienteSeleccionado);
				String tablaLibros1[] = choLibro1.getSelectedItem().split("-");
				String obtenerLibroSeleccionado = tablaLibros1[0];
				int LibroeSeleccionado1 = Integer.parseInt(obtenerLibroSeleccionado);
				String tablaLibros2[] = choLibro2.getSelectedItem().split("-");
				String obtenerLibroSeleccionado2 = tablaLibros2[0];
				int LibroeSeleccionado2 = Integer.parseInt(obtenerLibroSeleccionado2);
				String sentencia = "INSERT INTO prestamo VALUES (null, '" + clienteSeleccionado + "', '"
						+ choCantLibro.getSelectedItem() 
						+ "');";
				
				int respuesta = conexion.altaPrestamo(sentencia);
				
				
				conexion.obtenerUltimoPrestamo();
				
				int ultimoPrestamo = conexion.obtenerUltimoPrestamo();
				
				String sentencia1 = "INSERT INTO detallesprestamo VALUES ("+ultimoPrestamo+",'"+LibroeSeleccionado1+ "', '"+ txtFechaPrestamo.getText() + "', '"
						+ txtFechaDevolucion.getText() 
						+ "');";
				
				String sentencia2 = "INSERT INTO detallesprestamo VALUES ("+ultimoPrestamo+",'"+LibroeSeleccionado2+ "', '"+ txtFechaPrestamo.getText() + "', '"
						+ txtFechaDevolucion.getText() 
						+ "');";
				int respuesta1 = conexion.altaDetallesPrestamo(sentencia1);
				
				int respuesta2 = conexion.altaDetallesPrestamo(sentencia2);
				
				int repestafinal= respuesta+respuesta1+respuesta2;
				
				
				if (repestafinal != 0) {
					// Mostrar Mensaje Error
					lblMensaje.setText("Error en Alta");
				} else {
					lblMensaje.setText("Alta de Prestamo Correcta");
				}
			}

			
			
			if ( opcionSeleccionada.equals("3")&&choClientes.getSelectedIndex()==0 || choLibro1.getSelectedIndex()==0
					|| choLibro2.getSelectedIndex()==0|| choLibro3.getSelectedIndex()==0
					||txtFechaPrestamo.getText().length() == 0 || txtFechaDevolucion.getText().length() == 0
			) {
		lblMensaje.setText("Los campos están vacíos");
	}
			
			if  (choLibro3.getSelectedItem() != null&&opcionSeleccionada.equals("3")) {
				// Dar de alta Prestamo 3 libros
				String tabla[] = choClientes.getSelectedItem().split("-");
				String obtenerClienteSeleccionado = tabla[0];
				int clienteSeleccionado = Integer.parseInt(obtenerClienteSeleccionado);
				String tablaLibros1[] = choLibro1.getSelectedItem().split("-");
				String obtenerLibroSeleccionado = tablaLibros1[0];
				int LibroeSeleccionado1 = Integer.parseInt(obtenerLibroSeleccionado);
				String tablaLibros2[] = choLibro2.getSelectedItem().split("-");
				String obtenerLibroSeleccionado2 = tablaLibros2[0];
				int LibroeSeleccionado2 = Integer.parseInt(obtenerLibroSeleccionado2);
				String tablaLibros3[] = choLibro3.getSelectedItem().split("-");
				String obtenerLibroSeleccionado3 = tablaLibros3[0];
				int LibroeSeleccionado3 = Integer.parseInt(obtenerLibroSeleccionado3);
				
				String sentencia = "INSERT INTO prestamo VALUES (null, '" + clienteSeleccionado + "', '"
						+ choCantLibro.getSelectedItem() 
						+ "');";
				
				int respuesta = conexion.altaPrestamo(sentencia);
				
				
				conexion.obtenerUltimoPrestamo();
				
				int ultimoPrestamo = conexion.obtenerUltimoPrestamo();
				
				String sentencia1 = "INSERT INTO detallesprestamo VALUES ("+ultimoPrestamo+",'"+LibroeSeleccionado1+ "', '"+ txtFechaPrestamo.getText() + "', '"
						+ txtFechaDevolucion.getText() 
						+ "');";
				
				String sentencia2 = "INSERT INTO detallesprestamo VALUES ("+ultimoPrestamo+",'"+LibroeSeleccionado2+ "', '"+ txtFechaPrestamo.getText() + "', '"
						+ txtFechaDevolucion.getText() 
						+ "');";
				String sentencia3 = "INSERT INTO detallesprestamo VALUES ("+ultimoPrestamo+",'"+LibroeSeleccionado3+ "', '"+ txtFechaPrestamo.getText() + "', '"
						+ txtFechaDevolucion.getText() 
						+ "');";
				int respuesta1 = conexion.altaDetallesPrestamo(sentencia1);
				
				int respuesta2 = conexion.altaDetallesPrestamo(sentencia2);
				
				int respuesta3 = conexion.altaDetallesPrestamo(sentencia3);
				
				int repestafinal= respuesta+respuesta1+respuesta2+respuesta3;
				
				
				if (repestafinal != 0) {
					// Mostrar Mensaje Error
					lblMensaje.setText("Error en Alta");
				} else {
					lblMensaje.setText("Alta de Prestamo Correcta");
				}
			}
			
			

			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		} else if (e.getSource().equals(btnCancelar)) {
			txtCliente.setText("");
			txtLibro1.setText("");
			txtFechaPrestamo.setText("");
			txtFechaDevolucion.setText("");
			txtCliente.requestFocus();
		}
		else if (e.getSource().equals(btnLibros)) {
			String opcionSeleccionada = choCantLibro.getSelectedItem();
            if (opcionSeleccionada.equals("1")) {
            	
            	dlgPrestamo.addWindowListener(this);
            	
            	conexion.rellenarChoiceClientes(choClientes);
            	conexion.rellenarChoiceLibros(choLibro1);
            	
            	dlgPrestamo.add(lblCliente);
            	dlgPrestamo.add(choClientes);
            	dlgPrestamo.add(lblLibro1);
            	dlgPrestamo.add(choLibro1);
        		dlgPrestamo.add(lblFechaPrestamo);
        		dlgPrestamo.add(txtFechaPrestamo);
        		dlgPrestamo.add(lblFechaDevolucion);
        		dlgPrestamo.add(txtFechaDevolucion);

        		btnAceptar.addActionListener(this);
        		btnCancelar.addActionListener(this);
        		btnLibros.addActionListener(this);

        		dlgPrestamo.add(btnAceptar);
        		dlgPrestamo.add(btnCancelar);

        		dlgPrestamo.setResizable(true);
        		dlgPrestamo.setLocationRelativeTo(null);
        		dlgPrestamo.setVisible(true);
            	
            	
            	
            } else if (opcionSeleccionada.equals("2")) {

            	dlgPrestamo.addWindowListener(this);
            	
            	conexion.rellenarChoiceClientes(choClientes);
            	conexion.rellenarChoiceLibros(choLibro1);
            	conexion.rellenarChoiceLibros(choLibro2);
            
            	
            	dlgPrestamo.add(lblCliente);
            	dlgPrestamo.add(choClientes);
            	dlgPrestamo.add(lblLibro1);
            	dlgPrestamo.add(choLibro1);
            	dlgPrestamo.add(lblLibro2);
            	dlgPrestamo.add(choLibro2);
        		dlgPrestamo.add(lblFechaPrestamo);
        		dlgPrestamo.add(txtFechaPrestamo);
        		dlgPrestamo.add(lblFechaDevolucion);
        		dlgPrestamo.add(txtFechaDevolucion);

        		btnAceptar.addActionListener(this);
        		btnCancelar.addActionListener(this);
        		btnLibros.addActionListener(this);

        		dlgPrestamo.add(btnAceptar);
        		dlgPrestamo.add(btnCancelar);

        		dlgPrestamo.setResizable(true);
        		dlgPrestamo.setLocationRelativeTo(null);
        		dlgPrestamo.setVisible(true);
            	
            		
            	}
		
            	
                // ...
             else if (opcionSeleccionada.equals("3")) {
            	 dlgPrestamo.addWindowListener(this);
             	
            	conexion.rellenarChoiceClientes(choClientes);
            	conexion.rellenarChoiceLibros(choLibro1);
            	conexion.rellenarChoiceLibros(choLibro2);
            	conexion.rellenarChoiceLibros(choLibro3);
             	
             	dlgPrestamo.add(lblCliente);
             	dlgPrestamo.add(choClientes);
            	dlgPrestamo.add(lblLibro1);
            	dlgPrestamo.add(choLibro1);
            	dlgPrestamo.add(lblLibro2);
            	dlgPrestamo.add(choLibro2);
            	dlgPrestamo.add(lblLibro3);
            	dlgPrestamo.add(choLibro3);
         		dlgPrestamo.add(lblFechaPrestamo);
         		dlgPrestamo.add(txtFechaPrestamo);
         		dlgPrestamo.add(lblFechaDevolucion);
         		dlgPrestamo.add(txtFechaDevolucion);

         		btnAceptar.addActionListener(this);
         		btnCancelar.addActionListener(this);
         		btnLibros.addActionListener(this);

         		dlgPrestamo.add(btnAceptar);
         		dlgPrestamo.add(btnCancelar);

         		dlgPrestamo.setResizable(true);
         		dlgPrestamo.setLocationRelativeTo(null);
         		dlgPrestamo.setVisible(true);
            	
            	
            	
            	
            }
        }
		}

	}