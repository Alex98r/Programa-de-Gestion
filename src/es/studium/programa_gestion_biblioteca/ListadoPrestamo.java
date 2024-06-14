package es.studium.programa_gestion_biblioteca;


import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;


public class ListadoPrestamo implements WindowListener, ActionListener {
	Frame ventana = new Frame("Listado de Prestamos");

	TextArea txtListado = new TextArea(20, 70);

	Button btnExcel = new Button("Excel");

	Conexion conexion = new Conexion();

	ListadoPrestamo() {
	    ventana.setLayout(new FlowLayout());
	    ventana.setSize(540, 460);
	    ventana.addWindowListener(this);

	    // Actualizar el encabezado para incluir todos los datos que se obtendrán
	    txtListado.append("ID Préstamo\tID Cliente\tNombre Completo\tCantidad Libros\tFecha Préstamo\tFecha Devolución\n");
	    // Sacar de la tabla prestamos todos los registros seleccionados
	    txtListado.append(conexion.obtenerPrestamo());
	    ventana.add(txtListado);

	    
	    btnExcel.addActionListener(this);

	    ventana.add(btnExcel);

	    ventana.setResizable(false);
	    ventana.setLocationRelativeTo(null);
	    ventana.setVisible(true);
	    
	}
	 private String[][] obtenerDatosParaExcel() {
	        String textoCompleto = txtListado.getText();
	        String[] lineas = textoCompleto.split("\n");
	        // La primera línea contiene las cabeceras, por lo que la omitimos
	        List<String[]> datosList = new ArrayList<>();
	        for (int i = 1; i < lineas.length; i++) {
	            String[] datosLinea = lineas[i].split("\t");
	            datosList.add(datosLinea);
	        }
	        // Convertir la lista a un array bidimensional
	        String[][] datos = new String[datosList.size()][];
	        datos = datosList.toArray(datos);
	        return datos;
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
        // Verificar si el evento proviene del botón Excel
        if (e.getSource() == btnExcel) {
            // Aquí llamas al método que crea el archivo Excel
            // Suponiendo que tienes una clase llamada CrearFicheroExcel con un método crearArchivoExcel
        	
            CrearFicheroExcel excelCreator = new CrearFicheroExcel();
            String r = "C:\\FicherosExcel\\Inventario.xlsx";
            String n = "Hoja1";
            String[] c = {"ID Préstamo", "ID Cliente", "Nombre Completo", "Cantidad Libros", "Fecha Préstamo", "Fecha Devolución"};
            String[][] d = obtenerDatosParaExcel(); // Debes implementar este método para obtener los datos de la tabla

            excelCreator.FicheroConsulta(r, n, c, d);
        }
    }

}
