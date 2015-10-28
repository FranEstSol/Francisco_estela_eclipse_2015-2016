import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class Almacen {


	//Metodo para guardar un libro de forma individual en un fichero
	public void Guardar(Libro l, String f){

		System.out.println("++Guardando libros de forma individual en ficheros individuales:  "+f);
		System.out.println("");
		//Objeto ObjectOutputStream generado e inicializado
		ObjectOutputStream out = null;

		try {
			//Le metemos un nuevo objeto FileOutputStream que recibe el "fichero f"
			out = new ObjectOutputStream(new FileOutputStream(f));
			//Escribimos el objeto Libro "l"
			out.writeObject(l);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			intentarCerrar(out);
		}
	}

	//Metodo para guardar todos los libros en un solo fichero
	public void GuardarTodoJunto(Libro l, String f){

		System.out.println("++Guardando un nuevo libro en el fichero general:  "+f);
		System.out.println("");
		//Objeto ObjectOutputStream generado e inicializado
		ObjectOutputStream out = null;

		try {
			//Le metemos un nuevo objeto FileOutputStream que recibe el "fichero f"
			//El "true" permite que en lugar de sustituir el archivo, se añada despues del contenido
			out = new ObjectOutputStream(new FileOutputStream(f, true));
			//Escribimos el objeto Libro "l"
			out.writeObject(l);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			intentarCerrar(out);
		}
	}

	//Metodo para guardar la lista de libros
	public void GuardarLista(ArrayList<Libro> l, String f){
		
		System.out.println("++Guardando la lista de libros en un fichero:  " +f);
		System.out.println("");
		//Objeto ObjectOutputStream generado e inicializado
		ObjectOutputStream out = null;

		try {
			//Le metemos un nuevo objeto FileOutputStream que recibe el "fichero f"
			out = new ObjectOutputStream(new FileOutputStream(f));
			//Escribimos el contenido de la ArrayList libros, que contiene objetos del tipo libro
			out.writeObject(l);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			intentarCerrar(out);
		}
	}

	//Metodo para recuperar un solo libro
	public Libro Recuperar (Libro l, String f){
		System.out.println("");
		System.out.println("++Recuperando el libro: " + l.getTitulo() + "  desde el archivo:  "+f);
		
		//Objeto ObjectInputStream y objeto Libro generados e inicializados
		ObjectInputStream in=null;
		
		try{
			//Le metemos un nuevo objeto ObjectInputStream que recibe el "fichero f"
			in= new ObjectInputStream (new FileInputStream(f));
			//Lee el objeto l que esta dentro del fichero meriante el in (haciendo cast a libro)
			l= (Libro) in.readObject();
		}
		catch (ClassNotFoundException ex){
			System.err.println("Error ClassNotFound");
		}
		catch (IOException ex){
			System.err.println("Error IOException");
		}finally {
			intentarCerrar(in);
		}
		return l;
	}

	//Metodo para recuperar todos los libros de la lista
	public void RecuperarLista(ArrayList<Libro> libros, String f) {

		System.out.println("++Recuperando todos los libros de la lista guardada en:  " +f);
		System.out.println("");
		//Creacion e inicializacion del objeto ObjectInputStream
		ObjectInputStream ois = null;

		try {
			//Le asignamos al objeto ois el FileInputStream apuntando al archivo donde se guarda la lista de libros
			ois = new ObjectInputStream(new FileInputStream(f));
			//Leemos la array del fichero y se la asignamos a "libros"
			libros = (ArrayList<Libro>) ois.readObject();			
			//Mediante un iterator, recorremos toda la ArrayList para recuperarlos uno a uno y los vamos imprimiendo
			Iterator it = libros.iterator();
			while (it.hasNext()) {
				Libro l = (Libro) it.next();
				l.print();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			intentarCerrar(ois);
		}
	}

	//Metodo que recibe un objeto "cerrable" y lo intenta cerrar, de forma que si un objeto falla y no puede cerrarse, seguira intentando cerrar los demas

	private void intentarCerrar(Closeable aCerrar) {
		try{
			if (aCerrar != null){
				aCerrar.close();
			}			
		}catch (IOException ex){
			ex.printStackTrace(System.err);
		}
	}
}
