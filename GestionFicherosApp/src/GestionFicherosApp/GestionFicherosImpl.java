package GestionFicherosApp;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import gestionficheros.ByteFormat;
import gestionficheros.FormatoVistas;
import gestionficheros.GestionFicheros;
import gestionficheros.GestionFicherosException;
import gestionficheros.TipoOrden;

public class GestionFicherosImpl implements GestionFicheros{
	private File carpetaDeTrabajo=null;
	private Object[][] contenido;
	private int filas = 0;
	private int columnas = 3;
	private FormatoVistas formatoVistas = FormatoVistas.NOMBRES;
	private TipoOrden ordenado = TipoOrden.DESORDENADO;
	
	public GestionFicherosImpl(){
		carpetaDeTrabajo = File.listRoots()[0];
		actualiza();
	}

	public void actualiza(){
		System.out.println("Carpeta leida: " + carpetaDeTrabajo);
		//crea una StrinArray "ficheros" con la lista de nombres de la carpeta de trabajo.
		String[] ficheros = carpetaDeTrabajo.list(); //obtiene los nombres
		//divide el numero de ficheros leidos entre el numero de columnas que hay para calcular cuantas filas son necesarias
		filas = ficheros.length/columnas;
		//calcula si es necesario añadir alguna fila extra multiplicando filas x columnas y revisando que el resultado sea menor que la cantidad de ficheros.
		if (filas * columnas < ficheros.length){
			filas++; //si hay resto, añade una fila ya que se necesita
		}
		
		//dimensionar la matriz de contenido segun los resultados
		
		contenido = new String[filas][columnas];
		//rellenamos el contenido con los nombres obtenidos
		//con los 2 ciclos for, se recorre cada celda de la matriz
		//este ciclo recorre las 3 columnas
		for(int i = 0; i < columnas; i++){
			//este ciclo recorre las filas
			for (int j = 0; j < filas; j++){
				//creamos un puntero que indice que en celda esta en cada momento del ciclo
				int ind = j*columnas+i;
				//mientras el indicador sea menor que el numero de ficheros, introduce el valor en esa celda
				if (ind < ficheros.length){
					contenido[j][i] = ficheros[ind];
				}
				//si el indice es mayor que el numero de ficheros, deja las celdas en blanco ya que han sido creadas con antelacion.
				else {
					contenido[j][i]= "";
				}
			}
		}
	}
	
	//implementa la funcion del boton arriba, asignando a la carpeta de trabajo el "parent" de la carpeta de trabajo actual.
	@Override
	public void arriba() {
		if(carpetaDeTrabajo.getParentFile() != null){
			carpetaDeTrabajo = carpetaDeTrabajo.getParentFile();
			actualiza();
		}
		
	}

	@Override
	public void creaCarpeta(String arg0) throws GestionFicherosException {
		File file = new File (carpetaDeTrabajo, arg0);

		//controlamos los permisos de la carpeta
		if(carpetaDeTrabajo.canWrite()){
			//controlamos que el fichero no exista con anterioridad
			if(!file.exists()){
				file.mkdir();
			}
			else {
				throw new GestionFicherosException("Error. " + file.getAbsolutePath() + " ya existe");
			}
		}
		else {
			throw new GestionFicherosException("Error. Permisos insuficientes");
		}
				
		actualiza();
	}

	@Override
	public void creaFichero(String arg0) throws GestionFicherosException {
		File file = new File (carpetaDeTrabajo, arg0);

		//controlamos los permisos de la carpeta
		if(carpetaDeTrabajo.canWrite()){
			//controlamos que el fichero no exista con anterioridad
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					throw new GestionFicherosException("Error al crear el archivo " );
				}
			}
			else {
				throw new GestionFicherosException("Error. " + file.getAbsolutePath() + " ya existe");
			}
		}
		else {
			throw new GestionFicherosException("Error. Permisos insuficientes");
		}
		actualiza();
	}

	@Override
	public void elimina(String arg0) throws GestionFicherosException {
		File file = new File (carpetaDeTrabajo, arg0);
		
		//controlamos los permisos de la carpeta
		if(carpetaDeTrabajo.canWrite()){
			//controlamos que el fichero no exista con anterioridad
			if(file.exists()){
				file.delete();
			}
			else {
				throw new GestionFicherosException("Error. " + file.getAbsolutePath() + " ya no existe");
			}
		}
		else {
			throw new GestionFicherosException("Error. Permisos insuficientes");
		}
		
		actualiza();
	}

	
	@Override
	public void entraA(String arg0) throws GestionFicherosException {
		//crea un nuevo fichero con la ruta recibida de "carpetaDeTrabajo" + "arg0" (es decir, la carpeta actual + la carpeta a la que se le hace doble click)
		File file = new File(carpetaDeTrabajo,arg0);

		//controlamos que el fichero exista
		if(!file.exists()){
			throw new GestionFicherosException("Error. " + file.getAbsolutePath() + " no existe");
		}		
		//controlamos que sea un directorio
		if(!file.isDirectory()){
			throw new GestionFicherosException("Error. Se espera un directorio, pero " + file.getAbsolutePath() +" No es un directorio");
		}
		//controlamos los permisos de la carpeta
		if(!file.canRead()){
			throw new GestionFicherosException("Alerta. No tienes permiso para acceder a la ruta: " + file.getAbsolutePath());
		}

		//actualizar la carpeta de trabajo
		carpetaDeTrabajo = file;

		//actualizar el contenido de la tabla
		actualiza();


	}

	//Metodo que coje el numero de columnas y lo devuelve
	@Override
	public int getColumnas() {		
		return columnas;
	}

	//Meto que coje el contenido y lo devuelve
	@Override
	public Object[][] getContenido() {
		return contenido;
	}

	//metodo que coje la direccion de la carpeta y la devuelve 
	@Override
	public String getDireccionCarpeta() {
		return carpetaDeTrabajo.getAbsolutePath();
	}

	@Override
	public String getEspacioDisponibleCarpetaTrabajo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEspacioTotalCarpetaTrabajo() {
		// TODO Auto-generated method stub
		return null;
	}

	//Metodo que coje el numero de filas y lo devuelve
	@Override
	public int getFilas() {
		return filas;
	}

	//Metodo para establecer el formado de la vista.
	@Override
	public FormatoVistas getFormatoContenido() {
		return formatoVistas;
	}

	@Override
	public String getInformacion(String arg0) throws GestionFicherosException {		

		File file = new File(carpetaDeTrabajo,arg0);

		//controlamos que el fichero existe
		if(file.exists()){
			//controlamos los permisos del fichero
			if(file.canRead()) {		
				//declaracion e inicializacion de variables		
				//cadena de texto para el montaje del texto
				StringBuilder strBuilder=new StringBuilder();

				//objeto tipo File que recibe el arg0 (nombre de la carpeta) y la propia carpetaDeTrabajo en cuestion


				//variable millisec y objeto date (que recibe millisec) para poder montar despues la fecha de ultima actualizacion y que sea entendible
				long millisec = 0;
				Date date = new Date(millisec);

				//Controlar que existe. Si no, se lanzará una excepción
				//Controlar que haya permisos de lectura. Si no, se lanzará una excepción

				//Título
				strBuilder.append("INFORMACIÓN DEL SISTEMA");
				strBuilder.append("\n\n");

				//Nombre
				strBuilder.append("Nombre: ");
				strBuilder.append(arg0);
				strBuilder.append("\n");

				//Tipo: fichero o directorio
				if (file.isDirectory()){
					strBuilder.append("Tipo de fichero: es una carpeta");
					strBuilder.append("\n");
				}
				else if (file.isFile()) {
					strBuilder.append("Tipo de fichero: es un fichero");
					strBuilder.append("\n");
				}
				//Bloque else totalmente de prevencion, creo que esto no saltaria en ninguna ocasion ya que creo que no hay ningun archivo que no se clasifique como carpeta o fichero
				else {
					strBuilder.append("El fichero seleccionado es de origen desconocido");
					strBuilder.append("\n");
				}

				//cantidad de elementos en su interior
				if (file.isDirectory()){
					int nFiles = new File(file.getAbsolutePath()).listFiles().length;
					strBuilder.append("Contiene " + nFiles + " elementos");
					strBuilder.append("\n");

				}

				//Ubicación
				strBuilder.append("Directorio: " + file.getAbsolutePath());
				strBuilder.append("\n");


				//Fecha de última modificación
				millisec = file.lastModified();
				strBuilder.append("Fecha de la ultima modificacion: " + date);
				strBuilder.append("\n");

				//Si es un fichero oculto o no
				if (file.isHidden()==false){
					strBuilder.append("Es un fichero oculto: FALSO ");
					strBuilder.append("\n");
				}
				else {
					strBuilder.append("Es un fichero oculto: VERDADERO");
					strBuilder.append("\n");
				}

				//Si es directorio: Espacio libre, espacio disponible, espacio total

				if (file.isDirectory()){
					//creacion de las 3 variables long que almacenan el numero de bytes de sus respectivas funciones
					long free = file.getFreeSpace();
					long usable = file.getUsableSpace();
					long total = file.getTotalSpace();

					//Cambiamos el formato del numero long recibido arriba a una string y mediante la opcion "%,d" ponemos un punto cada 3 numeros desde la derecha
					String strfree = String.format("%,d", free);
					String strusable = String.format("%,d", usable);
					String strtotal = String.format("%,d", total);

					//Montamos el texto
					strBuilder.append("Espacio libre: " + strfree);
					strBuilder.append("\n");
					strBuilder.append("Espacio disponible: " + strusable);
					strBuilder.append("\n");
					strBuilder.append("Espacio total: " + strtotal);
					strBuilder.append("\n");
				}

				//Si es un fichero, tamaño total y con el resultado en bytes
				else if (file.isFile()) {
					long size = file.length();
					String strSize = String.format("%,d", size);
					strBuilder.append("Tamaño del archivo: " + strSize);
					strBuilder.append("\n");
				}
				
				//Bloque else totalmente de prevencion, creo que esto no saltaria en ninguna ocasion ya que creo que no hay ningun archivo que no se clasifique como carpeta o fichero
				else {
					strBuilder.append("El fichero seleccionado tiene un tamaño desconocido y no se puede leer");
					strBuilder.append("\n");
				}

				//si todo va bien, esto monta el letrero
				return strBuilder.toString();
			}
			//fin del test de permisos
			//en caso de no tener permisos
			else {
				throw new GestionFicherosException("Alerta. No tienes permiso para acceder a la ruta: " + file.getAbsolutePath());
			}
		}
		//fin del test de existencia
		//en caso de no existir
		else { 
			throw new GestionFicherosException("Error. " + file.getAbsolutePath() + " no existe");
		}	
	}


	@Override
	public boolean getMostrarOcultos() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getNombreCarpeta() {
		return carpetaDeTrabajo.getName();
	}

	@Override
	public TipoOrden getOrdenado() {
		return ordenado;
	}

	@Override
	public String[] getTituloColumnas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getUltimaModificacion(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String nomRaiz(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numRaices() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void renombra(String arg0, String arg1) throws GestionFicherosException {
		File file = new File (carpetaDeTrabajo, arg0);
		File newFileName = new File (carpetaDeTrabajo, arg1);
		
		//controlamos los permisos de la carpeta
		if(carpetaDeTrabajo.canWrite()){
			//controlamos que el fichero no exista con anterioridad
			if(file.exists()){
				file.renameTo(newFileName);
			}
			else {
				throw new GestionFicherosException("Error. " + file.getAbsolutePath() + " ya no existe");
			}
		}
		else {
			throw new GestionFicherosException("Error. Permisos insuficientes");
		}
		
		actualiza();				
	}

	@Override
	public boolean sePuedeEjecutar(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sePuedeEscribir(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sePuedeLeer(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	//Recibe el numero arg0 y asigna a la variable "columnas"
	@Override
	public void setColumnas(int arg0) {
		columnas = arg0;
		
	}

	//metodo que sirve para cambiar carpetas desde la barra de direcciones
	@Override
	public void setDirCarpeta(String arg0) throws GestionFicherosException {
		//crea un nuevo fichero con la ruta recibida "arg0"
		File file = new File(arg0);

		//controlamos que el fichero exista
		if(!file.exists()){
			throw new GestionFicherosException("Error. " + file.getAbsolutePath() + " no existe");
		}		
		//controlamos que sea un directorio
		if(!file.isDirectory()){
			throw new GestionFicherosException("Error. Se espera un directorio, pero " + file.getAbsolutePath() +" No es un directorio");
		}
		//controlamos los permisos de la carpeta
		if(!file.canRead()){
			throw new GestionFicherosException("Alerta. No tienes permiso para acceder a la ruta: " + file.getAbsolutePath());
		}
		
		//actualizar la carpeta de trabajo
		carpetaDeTrabajo = file;

		//actualizar el contenido de la tabla
		actualiza();
		
	}

	@Override
	public void setFormatoContenido(FormatoVistas arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMostrarOcultos(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOrdenado(TipoOrden arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSePuedeEjecutar(String arg0, boolean arg1) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSePuedeEscribir(String arg0, boolean arg1) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSePuedeLeer(String arg0, boolean arg1) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUltimaModificacion(String arg0, long arg1) throws GestionFicherosException {
		// TODO Auto-generated method stub
		
	}
}
