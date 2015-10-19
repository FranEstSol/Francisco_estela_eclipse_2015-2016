import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws IOException {

		//String para los nombres de los ficheros
		String ficheroLectura = null;
		String ficheroEscritura =null;

		//Variable para definir el orden. 
		int orden=0;
		
		//Objeto Scanner
		Scanner in = new Scanner(System.in);

		//Objeto para el directorio de la carpeta de trabajo
		File carpetaDeTrabajo = File.listRoots()[0];

		//Iniciamos el sistema para definir el orden
		System.out.println("Hola! bienvenido al sistema de ordenar y desordenar palabras");
		System.out.println("Para empezar, necesito saber si quieres las palabras en orden logico o inverso");
		System.out.println("Por favor, escribe un 0 para orden normal o un 1 para orden inverso");
		try {
			orden=Integer.parseInt(in.next());
		}
		catch (Exception e) {
			throw new IOException("Pon un numero! no pongas otras cosas raras" );
		}
	
		if(orden == 1 || orden == 0){
			//Inicio del programa en consola para la creacion del archivo de lectura
			System.out.println("Escribe el fichero que quieres leer");
			//lee la linea de consola
			ficheroLectura = in.next();
			//crea un nuevo objeto tipo File en la carpeta de trabajo con el nombre del ficheroLectura
			File read = new File (carpetaDeTrabajo, ficheroLectura);
			//control de permisos
			if(carpetaDeTrabajo.canWrite()){
				//controlamos que el fichero no exista con anterioridad
				if(read.exists()){
					System.out.println("Fichero '" + read +"' leido con exito en: " + carpetaDeTrabajo );
				}
				else {
					throw new IOException("Error. " + read.getAbsolutePath() + " no existe");
				}
			}
			else {
				throw new IOException("Error. Permisos insuficientes");
			}

			//Inicio del programa en consola para la creacion del archivo de escritura
			System.out.println("Escribe el nombre del fichero donde quieres escribir");
			//lee la linea de consola
			ficheroEscritura = in.next();
			//crea un nuevo objeto tipo File en la carpeta de trabajo con el nombre del ficheroEscritura
			File write = new File (carpetaDeTrabajo, ficheroEscritura);

			//control de permisos
			if(carpetaDeTrabajo.canWrite()){
				//controlamos la existencia del archivo. Si no existe, lo creamos.
				if(!write.exists()){
					try {
						write.createNewFile();
						System.out.println("Fichero '" + write +"' creado con exito en " + carpetaDeTrabajo  );
						ordenarFichero(read, write, orden);
					} catch (IOException e) {
						throw new IOException("Error al crear el archivo " );
					}
				}
				else {
					System.out.println("Fichero '" + write +"' leido con exito en: " + carpetaDeTrabajo );
					ordenarFichero(read, write, orden);
				}
			}
			else {
				//En caso de error, eliminamos el anterior archivo para no liarnos
				read.delete();
				throw new IOException("Error. Permisos insuficientes");
			} 
		}
		else {
			System.out.println("Te dije que pusieras un 1 o un 0... ahora el programa se ha roto");
		}
		
		
		 
	}

	static void ordenarFichero (File origen, File destino, int tipo_orden) throws IOException{

		//Variables String	
		String linea;
		String word;

		//Variables int		
		int numPalabras = 0;
		
		//arraylist
		ArrayList<String> listaPalabras = new ArrayList<String>();
		
		//Creacion de un objeto tipo BufferedReader
		BufferedReader br = new BufferedReader (new FileReader (origen));
		
		//Creacion de un objeto tipo PrintWriter
		PrintWriter pw = new PrintWriter(new FileWriter(destino));
		
		//Orden normal. Si la orden dada por el metodo main es 0
		if(tipo_orden == 0){
			
			//ciclo while que lee las lineas
			while ((linea = br.readLine()) != null) {
				//Creacion de un objeto tipo StringTokenizer
				StringTokenizer st = new StringTokenizer (linea);
				//Ciclo while que funciona mientras se encuentren mas palabras en la linea
				while (st.hasMoreTokens()) {
					//Sustituye el valor de la variable word por el siguiente token
					word = st.nextToken();	
					//inserta la palabra en el archivo de salida con un cambio de linea incluido
					pw.println(word);
					/*System.out.println (word);*/
				}
			}
			
			//Cerramos el BufferedReader y el PrintWriter
			br.close();
			pw.close();
		}
		
		//Orden normal. Si la orden desde el metodo main no es valida
		else if(tipo_orden == 1){
			
			//Variable que usaremos de puntero
			int pointer=0;
			
			//ciclo while que lee las lineas
			while ((linea = br.readLine()) != null) {
				//Creacion de un objeto tipo StringTokenizer
				StringTokenizer st = new StringTokenizer (linea);
				//Ciclo while que funciona mientras se encuentren mas palabras en la linea
				while (st.hasMoreTokens()) {
					//añade 1 al numero de palabras existentes
					numPalabras++;
					//Sustituye el valor de la variable word por el siguiente token 
					word = st.nextToken();
					//Añade el valor de "word" a la ArrayList "listaPalabras"
					listaPalabras.add(word);					
				}
			}
			
			//Ciclo for invertido, que empieza por un numero igual al numero de palabras totales y va descontando.
			for(int i=numPalabras; i>=1;i--){
				//Resta 1 al puntero
				pointer--;
				//lee la arrayList "listaPalabras", lee el tamaño y resta 1 al puntero para despues ir grabando las palabras en el archivo de salida
				//(al final de la linea, pone "+pointer" ya que al pointer ya le estamos descontando arriba y si aqui restasemos, se convertiria en una suma)				
				pw.println(listaPalabras.get(listaPalabras.size()+pointer));
				/*System.out.println(listaPalabras.get(listaPalabras.size()+pointer));*/			
			}
			
			//Cerramos el BufferedReader y el PrintWriter
			br.close();
			pw.close();
		}
		//Orden normal. Si la orden recibida desde el metodo main no es valida.
		//Esto en teoria no tendria que saltar nunca
		else {
			System.out.println("Ha ocurrido un error inesperado con la declaracion de orden");
			System.out.println("Esto no tendria que haber pasado");
		}
	}
}
