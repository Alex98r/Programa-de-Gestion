package es.studium.programa_gestion_biblioteca;

import java.awt.Choice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

	String driver = "com.mysql.cj.jdbc.Driver"; // Driver Nativo Tipo 4 de MySQL
	String url = "jdbc:mysql://localhost:3306/biblioteca_grud"; // Ubicación y Nombre de la base de datos
	String user = "root"; // Usuario para conectar
	String password = "Qwertyqwerty1*"; // Clave del usuario

	Connection connection = null; // Objeto para conectar
	Statement statement = null; // Objeto para lanzar sentencias SQL
	ResultSet rs = null;

	Conexion() {
		connection = this.conexion();
	}

	public Connection conexion() {
		try {
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD Empresa
			return (DriverManager.getConnection(url, user, password));
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	public int comprobarCredenciales(String u, String c) {
		String cadena = "SELECT * FROM usuarios WHERE nombreUsuario = '" + u + "' AND claveUsuario = SHA2('" + c
				+ "',256);";
		try {

			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(cadena);
			if (rs.next()) {
				return rs.getInt("tipoUsuario");
			} else {
				return -1;
			}
		} catch (SQLException sqle) {
			System.out.println("Error 3-" + sqle.getMessage());
		}
		return -1;
	}

	public int altaCliente(String sentencia) {
		// TODO Auto-generated method stub
		try {
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia
			statement.executeUpdate(sentencia);
			return 0;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return 1;
	}

	public int altaPrestamo(String sentencia) {
		// TODO Auto-generated method stub
		try {
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia
			statement.executeUpdate(sentencia);
			return 0;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return 1;
	}
	public int altaDetallesPrestamo(String sentencia1) {
		// TODO Auto-generated method stub
		try {
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia
			statement.executeUpdate(sentencia1);
			return 0;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return 1;
	}
	public int altaLibro(String sentencia) {
		// TODO Auto-generated method stub
		try {
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia
			statement.executeUpdate(sentencia);
			return 0;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return 1;
	}

	public String obtenerClientes() {
		// TODO Auto-generated method stub
		String sentencia = "SELECT idCliente, nombreCliente, correoElectronicoCliente FROM clientes";
		String resultado = "";
		try {
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia
			rs = statement.executeQuery(sentencia);
			while (rs.next()) {
				resultado = resultado + rs.getInt("idCliente") + "\t" + rs.getString("nombreCliente") + "\t\t"
						+ rs.getString("correoElectronicoCliente") + "\n";
			}
			return resultado;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return "";
	}

	public String obtenerPrestamo() {
	    // TODO Auto-generated method stub
	    String sentencia = "SELECT p.idPrestamo, p.idClienteFK1, CONCAT(c.nombreCliente, ' ', c.primerApellidoCliente, ' ', c.segundoApellidoCliente) AS nombreCompleto, p.cantidadLibroPrestamo, MIN(dp.fechaPrestamo) AS fechaPrestamo, MAX(dp.fechaDevolucion) AS fechaDevolucion FROM prestamo p JOIN clientes c ON p.idClienteFK1 = c.idCliente JOIN detallesprestamo dp ON p.idPrestamo = dp.idPrestamoFK2 GROUP BY p.idPrestamo, p.idClienteFK1";
	    String resultado = "";
	    try {
	        // Crear una sentencia
	        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        // Ejecutar la sentencia
	        rs = statement.executeQuery(sentencia);
	        while (rs.next()) {
	            resultado = resultado + rs.getInt("idPrestamo") + "\t" 
	                        + rs.getInt("idClienteFK1") + "\t" 
	                        + rs.getString("nombreCompleto") + "\t" 
	                        + rs.getInt("cantidadLibroPrestamo") + "\t" 
	                        + rs.getDate("fechaPrestamo") + "\t" 
	                        + rs.getDate("fechaDevolucion") + "\n";
	        }
	        return resultado;
	    } catch (Exception e) {
	        System.out.println("Error: " + e.getMessage());
	    }
	    return "";
	}
	public int obtenerUltimoPrestamo() {
		// TODO Auto-generated method stub
		String sentenciauP = "SELECT idPrestamo FROM prestamo ORDER BY idPrestamo DESC LIMIT 1";
		int resultado = 0;
		try {
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia
			rs = statement.executeQuery(sentenciauP);
			while (rs.next()) {
				resultado = rs.getInt("idPrestamo");
			}
			return resultado;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return resultado;
	}

	public String obtenerLibro() {
		// TODO Auto-generated method stub
		String sentencia = "SELECT idLibro, Titulo, Autor, Genero, Editorial, anio_Publicacion, Precio FROM libros";
		String resultado = "";
		try {
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia
			rs = statement.executeQuery(sentencia);
			while (rs.next()) {
				resultado = resultado + rs.getInt("idLibro") + "\t" + rs.getString("Titulo") + "\t\t"
						+ rs.getString("Autor")+ "\t\t" +rs.getString("Genero")+ "\t\t"+ rs.getString("Editorial")+ "\t\t"+rs.getString("anio_Publicacion")+ "\t\t" +rs.getString("Precio")  + "\n";
			}
			return resultado;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return "";
	}

	public void rellenarChoiceClientes(Choice choClientes) {
		// TODO Auto-generated method stub
		choClientes.removeAll();
		choClientes.add("Elegir el cliente ...");
		String sentencia = "SELECT idCliente, nombreCliente FROM clientes ORDER BY idCliente;";
		try {
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia
			rs = statement.executeQuery(sentencia);
			while (rs.next()) {
				choClientes.add(rs.getInt("idCliente") + "-" + rs.getString("nombreCliente"));
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public String editarCliente(String idCliente) {
		// TODO Auto-generated method stub
		String sentencia = "SELECT * FROM clientes WHERE idCliente = " + idCliente + ";";
		String resultado = "";
		try {
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia
			rs = statement.executeQuery(sentencia);
			if (rs.next()) {
				resultado = rs.getString("nombreCliente") + "-" + rs.getString("primerApellidoCliente") + "-"
						+ rs.getString("segundoApellidoCliente") + "-" + rs.getString("fechaNacimientoCliente") + "-"
						+ rs.getString("dniCliente") + "-" + rs.getString("telefonoCliente") + "-"
						+ rs.getString("direccionCliente") + "-" + rs.getString("correoElectronicoCliente");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return resultado;
	}

	public int modificarCliente(String sentencia) {
		// TODO Auto-generated method stub
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			return 0;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return 1;
	}

	public void rellenarChoiceLibros(Choice choLibros) {
		// TODO Auto-generated method stub
		choLibros.removeAll();
		choLibros.add("Elegir el libro ...");
		String sentencia = "SELECT idLibro, Titulo FROM libros ORDER BY idLibro;";
		try {
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia
			rs = statement.executeQuery(sentencia);
			while (rs.next()) {
				choLibros.add(rs.getInt("idLibro") + "-" + rs.getString("Titulo"));
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public String editarLibro(String idLibro) {
		// TODO Auto-generated method stub
		String sentencia = "SELECT * FROM libros WHERE idLibro = " + idLibro + ";";
		String resultado = "";
		try {
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia
			rs = statement.executeQuery(sentencia);
			if (rs.next()) {
				resultado = rs.getString("Titulo") + "-" + rs.getString("Autor") + "-"
						+ rs.getString("Genero") + "-" + rs.getString("Editorial")
						+ "-" + rs.getString("Anio_Publicacion")+ "-" + rs.getString("Precio");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return resultado;
	}

	public int modificarLibro(String sentencia) {
		// TODO Auto-generated method stub
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			return 0;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return 1;
	}

	public void rellenarChoicePrestamos(Choice choPrestamos) {
		// TODO Auto-generated method stub
		choPrestamos.removeAll();
		choPrestamos.add("Elegir el prestamo ...");
		String sentencia = "SELECT idPrestamoFK2, idLibroFK3, fechaPrestamo, fechaDevolucion FROM detallesprestamo ORDER BY idPrestamoFK2;";
		try {
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia
			rs = statement.executeQuery(sentencia);
			while (rs.next()) {
				choPrestamos.add(rs.getInt("idPrestamoFK2") + "-" + rs.getInt("idLibroFK3")+ rs.getString("fechaPrestamo") + "-" + rs.getString("fechaDevolucion"));
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public String editarPrestamo(String idPrestamo) {
		// TODO Auto-generated method stub
		String sentencia = "SELECT * FROM detallesprestamo WHERE idPrestamoFK2 = " + idPrestamo + ";";
		String resultado = "";
		try {
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia
			rs = statement.executeQuery(sentencia);
			if (rs.next()) {
				resultado = rs.getString("fechaPrestamo") + "-" + rs.getString("fechaDevolucion");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return resultado;
	}

	public int modificarPrestamo(String sentencia) {
		// TODO Auto-generated method stub
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			return 0;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return 1;
	}

	public int eliminarCliente(String idCliente) {
		// TODO Auto-generated method stub
		String sentencia = "DELETE FROM clientes WHERE idCliente = " + idCliente + ";";
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			return 0;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return 1;
	}

	public int eliminarLibro(String idLibro) {
		// TODO Auto-generated method stub
		String sentencia = "DELETE FROM libros WHERE idLibro = " + idLibro + ";";
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			return 0;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return 1;
	}

	public int eliminarPrestamo(String idPrestamo) {
		// TODO Auto-generated method stub
		String sentencia = "DELETE FROM prestamos WHERE idPrestamo = " + idPrestamo + ";";
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			return 0;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return 1;
	}
	

}
