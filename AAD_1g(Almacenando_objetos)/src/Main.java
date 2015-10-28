import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main (String[] args) throws ClassNotFoundException, IOException{
		
		//Variables para los nombres de los ficheros
		String fichero1 = "fichero1.dat";
		String fichero2 = "fichero2.dat";
		String fichero3 = "fichero3.dat";
		String fichero4 = "fichero4.dat";
		String ficheroGeneral = "ficheroGeneral.dat";
		String ficheroLista = "ficheroLista.dat";
		
		//Creacion de objetos varios
		Almacen almacen = new Almacen();
		ArrayList<Libro> libros = new ArrayList();
		
		//Libros creados inicialmente
		Libro l1 = new Libro("TituloLibro1", "AutorLibro1", "FechaLibro1", "EditorLibro1", 1);
		Libro l2 = new Libro("TituloLibro2", "AutorLibro2", "FechaLibro2", "EditorLibro2", 2);
		Libro l3 = new Libro("TituloLibro3", "AutorLibro3", "FechaLibro3", "EditorLibro3", 3);
		Libro l4 = new Libro("TituloLibro4", "AutorLibro4", "FechaLibro4", "EditorLibro4", 4);
		
		//Libro vacio que usaremos para usar de conejito de indias.
		//Este libro sera donde recibiremos la informacion de los anterior libros desde los archivos.
		Libro lComodin = new Libro(null, null, null, null, 0);
		
		//Imprimimos por pantalla los libros creados inicialmente
		System.out.println("");
		System.out.println("Impresion de los 5 libros iniciales. 4 normales y el comodin");
		System.out.println("");
		l1.print();
		l2.print();
		l3.print();
		l4.print();
		lComodin.print();
		System.out.println("");
		//Guardamos en 4 ficheros distintos los 4 libros creados inicialmente
		almacen.Guardar(l1, fichero1);
		almacen.Guardar(l2, fichero2);
		almacen.Guardar(l3, fichero3);
		almacen.Guardar(l4, fichero4);
		
		//Guardamos los libros todos juntos uno a uno sin borrar los anteriores en un mismo archivo
		almacen.GuardarTodoJunto(l1, ficheroGeneral);
		almacen.GuardarTodoJunto(l2, ficheroGeneral);
		almacen.GuardarTodoJunto(l3, ficheroGeneral);
		almacen.GuardarTodoJunto(l4, ficheroGeneral);
		
		System.out.println("");
		System.out.println("Impresion del libro comodin que toma la forma de los 4 libros iniciales, leidos desde sus respectivos archivos");
		System.out.println("");
		
		//Llamamos al metodo Recuperar, el cual lee la informacion del fichero de datos 1 (perteneciente al libro 1) y se lo aplicamos al libro comodin		
		lComodin = almacen.Recuperar(lComodin, fichero1);
		//Imprimimos el libro comodin
		lComodin.print();
		//Repetimos el proceso con el resto de los libros, siempre sustituyendo el libro comodin.
		lComodin = almacen.Recuperar(lComodin,fichero2);
		lComodin.print();
		lComodin = almacen.Recuperar(lComodin,fichero3);
		lComodin.print();
		lComodin = almacen.Recuperar(lComodin,fichero4);
		lComodin.print();
		
		//Añadimos los 4 objetos de tipo Libro al arraylist libros
		libros.add(l1);
		libros.add(l2);
		libros.add(l3);
		libros.add(l4);
		
		
		//LLamamos al metodo GuardarLista que se encargara de guardar los libros en 1 archivo de datos
		almacen.GuardarLista(libros, ficheroLista);
		
		System.out.println("");
		System.out.println("Impresion de la lista de libros desde el archivo creado anteriormente con una lista de libros");
		System.out.println("");
		
		//LLamamos al metodo RecuperarLista que se encargara de recuperar los libros
		almacen.RecuperarLista(libros,ficheroLista);
		
		System.out.println("");
		System.out.println("Modificacion del libro comodin, modificando el titulo y el editor.");
		System.out.println("");
		
		System.out.println("lComodin antes de ser modificado:");
		lComodin.print();
		System.out.println("lComodin despues de ser modificado:");
		//modificamos el libro comodin
		lComodin.getTitulo();
		lComodin.setTitulo("Titulo modificado de prueba");
		lComodin.getAutor();
		lComodin.setAutor("Autor modificado de prueba");
		lComodin.print();		
	}	
}
