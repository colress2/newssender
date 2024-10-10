package newsSender;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main extends Thread{
	
	//Comienzo del programa.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Creación del frame y sus distintos parámetros.
					FrameLoadingScreen window = new FrameLoadingScreen();
					window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					window.setSize(450, 300);
					window.setVisible(true);
				} catch (Exception e) {
					System.exit(99);
				}
			}
		});
		Main Thread = new Main();
		Thread.run();
	}
	/*		
	 * 		- Hilo principal de la aplicación.
	 * 		
	 * 		Comprueba en un intervalo de 59 segundos 
	 * 		si la hora de la aplicación coincide con
	 * 		la hora local.
	 * 		
	 * 		> Llama a métodos de la clase FUNCTIONS:
	 * 		
	 * 			listaURLs: Lista de las URLs.
	 * 			newsSender(): Función principal para enviar noticias.
	 */
	public synchronized void run() {
		try {	
			do {
				/*	
				 * 		Al iniciar la aplicación, la memoria estará vacía (null),
				 * 		por lo que si se intenta realizar una comprobación dará
				 * 		error. Para ello, omitimos el resto de código mientras
				 * 		éste sea el caso y esperamos hasta que los datos se hayan
				 * 		cargado en la aplicación.
				 */
				if(Functions.listaURLs!=null) {
					//Aquí se realiza la comprobación.
					if(Functions.newsSender()==false) {
						JOptionPane.showMessageDialog(null, "Las noticias no han podido ser enviadas.\n"
								+ "Por favor revise si los correos registrados existen.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				//Intervalo de tiempo.
				this.wait(59000);
			}while(true);
		}catch(InterruptedException e) {
			JOptionPane.showMessageDialog(null, "Se ha producido un error inesperado.\n"
					+ "La aplicación se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(9);
		}
	}
	
}
