
public class Main {

/*
	 public static void main(String[] args) {

		//EJEMPLO 1 (ANONYMOUS)

	        new Thread(new Runnable(){
	            @Override
	            public void run() {
	                for(int i=0;i<10;i++){
	                    try {
	                        System.out.println("Hola anonymous "+i);
	                        Thread.sleep(1000); // Esperar 1 segundo
	                    } catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	                }
	            }
	        }).start();

	        new Thread(() -> {
	                    for(int i=0;i<10;i++){
	                        try {
	                            System.out.println("Hola lambda "+i);
	                            Thread.sleep(1000); // Esperar 1 segundo
	                        } catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	                    }
	                }
	            ).start();
*/
	//SALIDA
	/*
	        Hola anonymous 0
	        Hola lambda 0
	        Hola anonymous 1
	        Hola lambda 1
	        Así hasta el 9
	 */


	//EJEMPLO 2 (INHERIT)

	public static void main(String args[]){

		ThreadEjemplo ejemplo;

		ejemplo = new ThreadEjemplo();
		ejemplo.start();

		//(new ThreadEjemplo()).start();
	}

	static class ThreadEjemplo extends Thread{
		public void run(){
			for(int i=0;i<10;i++){
				try {
					System.out.println("Hola "+i);
					Thread.sleep(1000); // Esperar 1 segundo
				} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
			}
		} // run
	} // T1

	//SALIDA (doble contador si se quita el comentario)
	/*
	Hola 0
	Hola 1
	Hola 2
    Así hasta el 9
	 */
	
	
	//EJEMPLO 3 (Runnable)
/*	
	public static void main(String args[]){

        RunnableEjemplo runnableEjemplo;
        Thread thread;

        runnableEjemplo = new RunnableEjemplo();
        thread = new Thread(runnableEjemplo);
        thread.start();

        //(new Thread(new RunnableEjemplo())).start();

    }
	
	static class RunnableEjemplo implements Runnable{

        public void run(){
            for(int i=0;i<10;i++){
                try {
                    System.out.println("Hola runnable "+i);
                    Thread.sleep(1000); // Esperar 1 segundo
                } catch (InterruptedException ex) {Thread.currentThread().interrupt();}
            }
        } // run
    } // T1
*/	
	//SALIDA (doble contador si se quita el comentario)
		/*
		Hola runnable 0
		Hola runnable 1
		Hola runnable 2
	    Así hasta el 9
		 */

}




