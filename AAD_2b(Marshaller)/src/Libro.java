import java.io.Serializable;

public class Libro implements Serializable{

	String titulo=null;
	String autor=null;
	String fechaPubli=null;
	String editor=null;
	int numPaginas=0;
	
	public Libro(String t, String a, String f, String e, int num) {
		titulo=t;
		autor=a;
		fechaPubli=f;
		editor=e;
		numPaginas=num;
	}

	//Getters y setters
	
	//Titulo
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	//Autor
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	//Fecha de publicacion
	public String getFechaPubli() {
		return fechaPubli;
	}
	
	public void setFechaPubli(String fechaPubli) {
		this.fechaPubli = fechaPubli;
	}
	
	//Editor
	public String getEditor() {
		return editor;
	}
	
	public void setEditor(String editor) {
		this.editor = editor;
	}
	
	//Numero de paginas
	public int getNumPaginas() {
		return numPaginas;
	}
	
	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}
	
	public void print(){
		System.out.println("Titulo: " + titulo + ", Autor: "+ autor + ", Fecha de publicacion: " + fechaPubli +", Editor: "+ editor + ", Numero de paginas: " + numPaginas);
	}

}
