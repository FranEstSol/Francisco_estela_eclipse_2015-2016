import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class Main {

	public static void main(String[] args) throws SQLException {

		//Creamos las variables y los objetos necesarios y los inicializamos
		Connection conexion=null;
		Statement instruccion = null;
		ResultSet conjuntoResultados = null;
		final String CONTROLADOR_SQL="com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://sql4.freemysqlhosting.net/sql496405";	
		String user = "sql496405";
		String password = "hctCq3mcET";
		String resultadoBusqueda ="";

		//Intentamos cargar el controlador SQL
		try{
			Class.forName(CONTROLADOR_SQL);
			System.out.println("Carga del controlador SQL realizada con exito");
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}

		//Iniciamos las funciones relacionadas con la base de datos
		try {
			//A nuestro objeto conexion le asignamos decimos que coja la conexion y le damos una url un usuario y una contraseña
			conexion = DriverManager.getConnection(url,user,password);
			System.out.println("Conexion con la base de datos realizada con exito");
			//Al objeto instruccion le asignamos un nuevo statement con los datos de conexion
			instruccion = (Statement) conexion.createStatement();
			
			//BLOQUE QUE INSERTA INFORMACION EN LA BASE DE DATOS. ES MAS COMODO ASI QUE CON UNA INTERFAZ O CON INTERNET
			/*
			//Escribir datos
			//Al objeto instruccion le damos la orden de que ejecute un update y le damos la instruccion en forma de String
			instruccion.executeUpdate("INSERT INTO `Dam`(`Id`, `Asignatura`, `Nombre`, `Inicio`, `Fin`, `Entregada`) VALUES ('1','AAD','1-B','2015-10-10','2015-10-17','0')");
			instruccion.executeUpdate("INSERT INTO `Dam`(`Id`, `Asignatura`, `Nombre`, `Inicio`, `Fin`, `Entregada`) VALUES ('2','AAD','1-C','2015-11-11','2015-11-11','1')");
			instruccion.executeUpdate("INSERT INTO `Dam`(`Id`, `Asignatura`, `Nombre`, `Inicio`, `Fin`, `Entregada`) VALUES ('3','PMDM','1-A','2015-12-12','2015-12-12','1')");
			instruccion.executeUpdate("INSERT INTO `Dam`(`Id`, `Asignatura`, `Nombre`, `Inicio`, `Fin`, `Entregada`) VALUES ('4','SYP','1-A','2015-12-13','2015-12-13','0')");
			*/
			//FIN DEL BLOQUE DE INSERTAR INFORMACION 
			
			//BLOQUE QUE LEE LOS DATOS DE LA BASE DE DATOS
			//Al objeto conjuntoResultados le damos el objeto instruccion, le indicamos que ejecute una consulta y le damos la consulta en forma de String
			conjuntoResultados = instruccion.executeQuery("SELECT * FROM Dam");
			//Ciclo while que lee los resultados
			while (conjuntoResultados.next()){
				//Imprime los resultados
				System.out.println(
				"ID: "+conjuntoResultados.getObject(1)+
				"  Asignatura: "+conjuntoResultados.getObject(2)+
				"  Nombre: "+conjuntoResultados.getObject(3)+
				"  Inicio: "+conjuntoResultados.getObject(4)+
				"  Fin: "+conjuntoResultados.getObject(5)+
				"  Entregada: "+conjuntoResultados.getObject(6));}
			//FIN DEL BLOQUE QUE LEE LA INFORMACION DE LA BASE DE DATOS
		}
		//Catch que atrapa errores en caso de algun error de SQL
		catch (SQLException e) {
			//if else de experimentacion y prueba. Si el error recibido es el "42S02" (error de tabla) lanza un mensaje. Si es otro error propaga el error
			if (e.getSQLState().equals("42S02")){
				System.out.println("Error, no se puede encontrar la tabla");
			}
			else {
				throw e;
			}
		} 
		//En cualquier caso, tanto error como correcto funcionamiento, hace las pruebas por orden para en caso de que los objetos no sean nulos y no esten cerrados, cerrarlos
		finally {
			try {
				if (conjuntoResultados != null && !conjuntoResultados.isClosed()){
					conjuntoResultados.close();
				}
			} catch (SQLException e){

			}
			try {
				if (instruccion != null && !instruccion.isClosed()){
					instruccion.close();
				}
			} catch (SQLException e){

			}
			try {
				if (conexion != null && !conexion.isClosed()){
					conexion.close();
				}
			} catch (SQLException e){

			}
		}

	}

}
