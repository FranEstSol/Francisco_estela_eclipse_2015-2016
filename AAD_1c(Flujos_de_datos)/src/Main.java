import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	//1º FUNCION (compararContenido)
	
	//Ficheros a comparar
	static String ficheroComp1 = "fichero1.txt";				/* INSERTA AQUI EL FICHERO DONDE QUIERES BUSCAR UNA PALABRA. */
	static String ficheroComp2 = "fichero2.txt";				/* INSERTA AQUI EL FICHERO DONDE QUIERES BUSCAR UNA PALABRA. */

	//objetos tipo file para usarse en la comparacion de ficheros
	static File file1 = new File (ficheroComp1);
	static File file2 = new File (ficheroComp2);

	
	//2º FUNCION (buscarPalabra)
	
	//palabra para buscar en el ficheroBusqueda
	static String buscaPalabra = "cañones";						/* INSERTA AQUI LA PALABRA A BUSCAR */
	
	//Fichero para la busqueda de palabra
	static String ficheroBusqueda = "fichero1.txt";				/* INSERTA AQUI EL FICHERO DONDE QUIERES BUSCAR UNA PALABRA. */

	//objeto tipo file para buscar una palabra
	static File file3 = new File (ficheroBusqueda);

	//seleccion del tipo de busqueda.
	//True = primera aparicion
	//False = ultima aparicion
	static boolean position = true;

	//Funcion Main (llama al resto de las fucniones)
	public static void main(String[] args) throws IOException {
		//Sistema de gestion de errores para la primera funcion.
		if(file1.exists() && file2.exists()){
			if(file1.canRead() && file2.canRead()){
				compararContenido(file1, file2);
			}	
			else {
				System.out.println("Error con los permisos de los archivos. No puedes leer.");
			}
		}
		else {
			System.out.println("Error de existencia de los archivos.");
		}
		
		//Sistema de gestion de errores para la segunda funcion.
		if(file3.exists()){
			if(file3.canRead()){
				if(buscaPalabra == null || buscaPalabra == ""){
					System.out.println("Palabra a buscar vacia o null");
				}
				else {buscarPalabra(file3, buscaPalabra, position);}
			}	
			else {
				System.out.println("Error con los permisos de los archivos. No puedes leer.");
			}
		}
		else {
			System.out.println("Error de existencia de los archivos.");
		}
		
		
	}
	
	//Funcion compararContenido
	static boolean compararContenido(File fichero1, File fichero2) throws IOException{		
		
		//String para las lineas
		String str1 = null;
		String str2 = null;
		
		//Estado de las lineas distintas
		boolean status = true;	
		
		//Declaracion de los objetos FileReader
		FileReader fr1 = new FileReader(fichero1);
		FileReader fr2 = new FileReader(fichero2);
		
		//Declaracion de los objetos BufferedReader
		BufferedReader br1 = new BufferedReader(fr1);
		BufferedReader br2 = new BufferedReader(fr2);
		
		//Ciclo que lee todo el fichero 2 hasta que el fichero 1 devuelve null (cuando se termina)
		while ((str1=br1.readLine()) != null){
			//Lee las lineas del fichero 2 y las mete en la str2
			str2=br2.readLine();
			//comparativa sobre las lineas. Si son distintas, cambia el estado y lo imprime, indicando que lineas son distintas.
			if (!str1.equals(str2)){
				System.out.println("Las siguientes lineas son distintas:");
				System.out.println("Linea repetida del fichero 1: "+str1);
				System.out.println("Linea repetida del fichero 2: "+str2);
				
				status = false;				
			}			
		}
		
		//cierre de los BufferedReader
		br1.close();
		br2.close();
		
		//Imprimimos el estado por pantalla y lo devolvemos
		System.out.println(status);
		return status;
		
	}

	//Funcion buscarPalabra
	static int buscarPalabra(File fichero1, String palabra, boolean primera_aparicion) throws IOException{
	
		//variables numericas para el contador y para la posicion de la ultima palabra
		int count = 0;
		int finalPosition = 0;		
		
		//declaracion de strings
		String str1 = null;
		String selection = null;
		
		//boolean que indica si ha encontrado o no la palabra buscada
		boolean wordFound=false;
		
		//Objeto FileReader
		FileReader fr1 = new FileReader(fichero1);

		//Objeto BufferedReader
		BufferedReader br1 = new BufferedReader(fr1);
		
		//Si queremos buscar la primera aparicion, se ejecuta este bloque
		if(primera_aparicion == true){
			//ciclo que lee el texto hasta que recibe null por terminarse
			while ((str1=br1.readLine()) != null){
				//Si str1 contiene la palabra a buscar, cambia el valor de wordFound a true (Si no la contiene, seguira siendo false)
				wordFound = str1.contains(buscaPalabra);
				
				//Aumenta el contador en 1 para despues poder ver el numero de linea
				count = count +1;
				//Si wordFound es true, significa que ha encontrado coincidencias, asi que lo comunica y rompe el ciclo (ya que buscamos la primera aparicion)
				if (wordFound == true){
					//sustituimos el valor de finalPosition por el contador para imprimir mas adelante el numero de linea
					finalPosition = count;
					System.out.println("La primera coincidencia que se ha encontrado es: " + str1);
					break;
				}								
			}			
		}

		//Si queremos buscar la ultima aparicion, se ejecuta este bloque
		else if(primera_aparicion == false){
			//ciclo que lee el texto hasta que recibe null por terminarse
			while ((str1=br1.readLine()) != null){
				//Si str1 contiene la palabra a buscar, cambia el valor de wordFound a true (Si no la contiene, seguira siendo false)
				wordFound = str1.contains(buscaPalabra);
				//Aumenta el contador en 1 para despues poder ver el numero de linea
				count = count +1;
				//Si wordFound es true, significa que ha encontrado coincidencias, asi que lo comunica y rompe el ciclo (ya que buscamos la primera aparicion)
				if (wordFound == true){
					//sustituye el valor ed la string "selection" por la frase actual, de forma que si vuelve a encontrarlo volvera a sustituirlo y al final tendremos la ultima
					selection = str1;
					//La misma estrategia para el contador de linea
					finalPosition = count;
					//No rompemos el ciclo para poder llegar hasta el final y ver el ultimo resultado
				}								
			}
			System.out.println("La ultima coincidencia que se ha encontrado es: " + selection);				
		}
		
		//Cierre del BufferedReader
		br1.close();
		
		System.out.println("La posicion donde aparece la palabra es en la linea numero: " + finalPosition);
		
		return finalPosition;
	}

}

//NO OLVIDAR EL CLOSE