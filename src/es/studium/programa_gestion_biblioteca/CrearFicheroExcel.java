package es.studium.programa_gestion_biblioteca;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CrearFicheroExcel
{

	public void FicheroConsulta(String r, String n, String[] c, String[][] d) {
		// TODO Auto-generated method stub
String rutaArchivo= r;
String hoja=n;
XSSFWorkbook libro = new XSSFWorkbook();
XSSFSheet hoja1 = libro.createSheet(hoja);
// Cabeceras de la hoja de excel
String [] header= c;
// Contenido de la hoja de excel
String [][] document= d;

// Poner en negrita la cabecera
XSSFCellStyle style = libro.createCellStyle();
XSSFFont font = libro.createFont();
font.setBold(true);
style.setFont(font);
// Generar los datos para el fichero
for (int i = 0; i <= document.length; i++)
{
XSSFRow row = hoja1.createRow(i); // Se crean las filas
for (int j = 0; j <header.length; j++)
{
if (i==0)
{ // Para la cabecera
XSSFCell cell= row.createCell(j); // Se crean las celdas para lacabecera, junto con la posición
cell.setCellStyle(style); // Se añade el style creadoanteriormente
cell.setCellValue(header[j]); // Se añade el contenido
}
else
{ // Para el contenido
XSSFCell cell= row.createCell(j); // Se crean las celdas para lacabecera, junto con la posición
cell.setCellValue(document[i-1][j]); // Se añade el contenido
}
}
}
File file;
file = new File(rutaArchivo);
try (FileOutputStream fileOuS = new FileOutputStream(file))
{
if (file.exists())
{ // Si el archivo ya existe, se elimina
file.delete();
System.out.println("Archivo eliminado");
}
// Se guarda la información en el fichero
libro.write(fileOuS);
fileOuS.flush();

fileOuS.close();
System.out.println("Archivo Creado");
libro.close();
}
catch (FileNotFoundException e)
{
System.out.println("El archivo no se encuentra o está en uso...");
}
catch (IOException e)
{
e.printStackTrace();
}
	}
}