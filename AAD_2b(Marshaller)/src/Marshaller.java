import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Marshaller {
	
	//Creacion del objeto tipo Document y del ArrayList que almacenara los libros
	private Document dom = null;
	private ArrayList<Libro> libros = null;

	//Metodo que recibe el Arraylist de libros desde la clase Main y le copia la informacion al ArrayList de esta clase (Marshaller)
	public Marshaller(ArrayList<Libro> l) {
		libros = l;
	}
	
	//Metodo que crea el documento
	public void crearDocumento() {
		
		//Creamos la factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			//Creamos el documentbuilder con la ayuda de la factory creada arriba
			DocumentBuilder db = dbf.newDocumentBuilder();

			//Creamos una instancia de DOM a partir del document
			dom = db.newDocument();		
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}

	}
	
	public void crearArbolDOM() {

		//Creamos el elemento raíz "libros"
		Element docEle = dom.createElement("libros");
		//Lo añadimos al arbol DOM
		dom.appendChild(docEle);

		//Recorremos el Arraylist de libros
		Iterator it = libros.iterator();
		while (it.hasNext()) {
			Libro l = (Libro) it.next();
			//creamos un elemento <libro> por cada libro que encontramos y lo añadimos a la raiz
			Element elementLibro = setLibro(l);
			docEle.appendChild(elementLibro);
		}

	}
	
	private Element setLibro(Libro l) {

		//Se crea el elemento "libro"
		Element elementLibro = dom.createElement("libro");

		//Creamos el elemento titulo y el nodo de texto y lo añadimos al elemento libro
		Element elementTitulo = dom.createElement("titulo");		
		Text nombreTexto = dom.createTextNode(l.getTitulo());
		elementTitulo.appendChild(nombreTexto);
		elementLibro.appendChild(elementTitulo);

		//Creamos el elemento autor y el nodo de texto y lo añadimos al elemento libro
		Element elementAutor = dom.createElement("autor");
		Text autorTexto = dom.createTextNode(l.getAutor());
		elementAutor.appendChild(autorTexto);
		elementLibro.appendChild(elementAutor);

		//Creamos el elemento fecha y el nodo de texto y lo añadimos al elemento libro
		Element elementFecha = dom.createElement("fecha");
		Text fechaTexto = dom.createTextNode(l.getFechaPubli());
		elementFecha.appendChild(fechaTexto);
		elementLibro.appendChild(elementFecha);

		//Creamos el elemento editor y el nodo de texto y lo añadimos al elemento libro
		Element elementEditor = dom.createElement("editor");
		Text editorTexto = dom.createTextNode(l.getEditor());
		elementEditor.appendChild(editorTexto);
		elementLibro.appendChild(elementEditor);

		//Creamos el elemento paginas y el nodo de texto y lo añadimos al elemento libro
		Element elementPaginas = dom.createElement("paginas");
		Text paginasTexto = dom.createTextNode(Integer.toString(l.getNumPaginas()));
		elementPaginas.appendChild(paginasTexto);
		elementLibro.appendChild(elementPaginas);

		
		return elementLibro;
	}

	public void escribirDocumentAXml(File file) throws TransformerException {

		//Se crea una instancia para escribir el resultado
		Transformer trans = TransformerFactory.newInstance().newTransformer();
		
		//Se le da un formato legible
		trans.setOutputProperty(OutputKeys.INDENT, "yes");

		//Le indicamos donde se escribe y desde donde recibe la informacion
		StreamResult result = new StreamResult(file);
		DOMSource source = new DOMSource(dom);
		trans.transform(source, result);
	}
}