import java.io.File;
import java.util.ArrayList;

import javax.xml.transform.TransformerException;

public class Main {

	public static void main (String[] args) {
		ArrayList<Libro> libros;

		// creamos los libros
		libros = new ArrayList<Libro>();
		Libro l1 = new Libro("TituloLibro1", "AutorLibro1", "FechaLibro1", "EditorLibro1", 1);
		Libro l2 = new Libro("TituloLibro2", "AutorLibro2", "FechaLibro2", "EditorLibro2", 2);
		Libro l3 = new Libro("TituloLibro3", "AutorLibro3", "FechaLibro3", "EditorLibro3", 3);
		Libro l4 = new Libro("TituloLibro4", "AutorLibro4", "FechaLibro4", "EditorLibro4", 4);
		
		//Añadimos los 4 objetos de tipo Libro al arraylist libros
		libros.add(l1);
		libros.add(l2);
		libros.add(l3);
		libros.add(l4);
		
		//Llamada al metodo Marshaller en la clase Marshaller pasandole el ArrayList<libros> (que contiene los libros)
		Marshaller marshaller = new Marshaller(libros);
		
		//Llamada al metodo crearDocumento en la clase Marshaller
		marshaller.crearDocumento();
		
		//Llamada al metodo crearArbolDOM en la clase Marshaller
		marshaller.crearArbolDOM();
		
		//Creamos el fichero xml que usaremos
		File file = new File("biblioteca.xml");
		
		//Llamada al metodo escribirDocumentAXml en la clase Marshaller y le enviamos el fichero que hemos creado justo antes
		try {
			marshaller.escribirDocumentAXml(file);
		} catch (TransformerException e) {			
			e.printStackTrace();
		}

	}	
}
