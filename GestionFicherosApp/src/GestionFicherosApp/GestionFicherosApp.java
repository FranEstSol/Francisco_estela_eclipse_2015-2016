package GestionFicherosApp;

import gestionficheros.MainGUI;

public class GestionFicherosApp {

	public static void main(String[] args) {
		//Creamos el objeto "getFicherosImpl" de tipo "GestionFicherosImpl". (viene desde la clase "GestionFicherosImpl")
		GestionFicherosImpl getFicherosImpl = new GestionFicherosImpl();
		//creamos un nuevo MainGUI, le enviamos el objeto "getFicherosImpl" y lo hacemos visible. (Ventana y contenido)
		//los botones, menus y todos esos elementos de MainGUI vienen de la libreria GestionFicherosIGU/MainGUI.    
		new MainGUI(getFicherosImpl).setVisible(true);

	}

}
