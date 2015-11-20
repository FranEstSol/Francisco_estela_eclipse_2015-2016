import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {

	private Document dom = null;
	private ArrayList<Libro> libros = new ArrayList<Libro>();
	/*libros = new ArrayList<Libro>();
	public Parser() {
		
	}*/

	//Metodo que crea el DocumentBuilder recibiendo el nombre del fichero xml
	public void parseFicheroXml(String fichero) {

		// creamos la "factory" que usaremos en el DocumentBuilder
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// creamos el DocumentBuilder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// "parseamos" el XML y obtenemos una representación DOM (arbol) el cual insertamos en la variable dom (tipo Document)
			dom = db.parse(fichero);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	//Metodo que convierte el Documento en formato DOM en un ArrayList<libros> de objetos libro
	public void parseDocument() {
		
		//traemos el "dom" y lo "transformamos" en un Element
		Element docEle = dom.getDocumentElement();

		//obtenemos una lista de elementos desde los nodos con el tag "LIBRO"
		NodeList nl = docEle.getElementsByTagName("LIBRO");
		//if de seguridad anti-explosiones
		if (nl != null && nl.getLength() > 0) {
			//ciclo que recorre la lista de elementos
			for (int i = 0; i < nl.getLength(); i++) {
				//obtenemos un elemento de la lista (libro)
				Element el = (Element) nl.item(i);
				//obtenemos un libro que se recibe desde el metodo getLibro (justo aqui abajo)
				Libro l = getLibro(el);
				//lo añadimos al array
				libros.add(l);
			}
		}
	}

	//Metodo que recibe un elemento libro y lo transforma en un objeto libro
	private Libro getLibro(Element libroEle){

		//para cada elemento libro, obtenemos su informacion con los metodos de abajo
		String titulo = getTextValue(libroEle,"TITULO");
		String autor = getTextValue(libroEle,"AUTOR");
		String fechaPubli = getTextValue(libroEle,"FECHA");
		String editor = getTextValue(libroEle,"EDITOR");
		int numPaginas=0;
		try {
			numPaginas = getIntValue(libroEle,"PAGINAS");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Creamos un nuevo libro con los elementos leídos del nodo
		Libro e = new Libro(titulo, autor, fechaPubli, editor, numPaginas);

		//Devolvemos el libro
		return e;		

	}

	//Metodo que coje los atributos de tipo "String"
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}		
		return textVal;
	}

	//Metodo que coje los atributos de tipo "integer" (es decir, el numero de paginas)
	private int getIntValue(Element ele, String tagName) throws IOException {				
		return Integer.parseInt(getTextValue(ele, tagName));
	}

	//Metodo print que imprime los libros que va encontrando dentro de la ArrayList llamando al metodo print (de la clase Libro)
	public void print(){
		Iterator it = libros.iterator();
		while(it.hasNext()) {
			Libro p=(Libro) it.next();
			p.print();
		}
	}
}
