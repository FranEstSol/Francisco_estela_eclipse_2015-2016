import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

	static String fileToRead = null;
	static String fileToCreate = null;
	static String dirToCreate = null;
	
	static Scanner in = new Scanner(System.in);
	
	static byte [] readBuffer = null;
	
	//metodo Main
	public static void main(String[] args) throws IOException {		
		abrirFichero();
	}

	//metodo que lee el fichero que queremos copiar
	public static void abrirFichero() throws IOException {
		System.out.println("Vamos a proceder a realizar la copia de un fichero byte a byte");
		System.out.println("Para empezar, dime el directorio del archivo que quieres copiar.");
		System.out.println("No te olvides de poner la ruta completa, incluyendo el nombre del archivo y su extension");
		
		//leemos el siguiente mensaje por consola y lo convertimos en String
		fileToRead = in.next().toString();	
		
		//Creamos un objeto File para poder hacer los controles
		File rFile = new File (fileToRead);
		
		//Creamos un objeto path para poder hacer la lectura por bytes.
		Path path = Paths.get(fileToRead);					
		
		//control de la existencia del archivo
		if(rFile.exists()){
			//control de permiso de lectura sobre el archivo
			if(rFile.canRead() && rFile.isFile()){
				readBuffer = Files.readAllBytes(path);
				crearFichero();
			}
			else {
				System.out.println("Error. Permisos insuficientes");
				abrirFichero();
			}
		}
		else {	
			System.out.println("El fichero no se pudo leer o no existe, prueba de nuevo");
			System.out.println("");
			abrirFichero();		
		} 
	}

	public static void crearFichero() throws IOException {
		
		System.out.println("Dime la ruta donde quieres crear el archivo incluyendo nombre del fichero y su extension");
		fileToCreate = in.next().toString();	
		
		File rFile = new File(fileToCreate);			
		File carpetaDeTrabajo = new File (rFile.getParent());
		
		if (carpetaDeTrabajo.exists() && carpetaDeTrabajo.canWrite() && carpetaDeTrabajo.canRead()){			
			if (!rFile.exists()){
				rFile.createNewFile();
				copiaBytes();
			}
			else {
				System.out.println("Ya existe un archivo con este nombre, prueba con otro");
				System.out.println("");
				crearFichero();
			}
		}
		else {
			System.out.println("Ha ocurrido un error, la carpeta no existe o no tienes suficientes permisos. Prueba de nuevo.");
			System.out.println("");
			crearFichero();
		}
	}
	
	//Metodo que copia la informacion
	public static void copiaBytes()  {
		FileOutputStream fo = null;
		byte[] buffer = null;
		
		Path path = Paths.get(fileToRead);		
		
		try {
			fo = new FileOutputStream(fileToCreate);
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}		
		try {
			buffer = Files.readAllBytes(path);
			fo.write(buffer);
			fo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
