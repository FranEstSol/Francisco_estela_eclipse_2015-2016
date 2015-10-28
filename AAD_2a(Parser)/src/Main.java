
public class Main {

	public static void main (String[] args) {
		
		//Creamos un objeto parser desde la clase Parser
		Parser parser=new Parser();
		
		//LLamamos al metodo parseFicheroXml (que esta en la clase Parser) con nuestro objeto parser
		parser.parseFicheroXml("biblioteca.xml");
		
		//LLamamos al metodo parseDocument (que esta en la clase Parser) con nuestro objeto parser
		parser.parseDocument();
		
		//LLamamos al metodo print (que esta en la clase Parser) con nuestro objeto parser
		parser.print();
	}	
}
