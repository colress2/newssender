package newsSender;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class ImageClass extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public static Timer tempo;
	private JProgressBar barraDeProgreso;
	private JLabel lblLoading;
	private int i = 0;
	private Image backgroundImage;
	
	//Método que se ejectua automáticamente al ser llamado por la clase anterior FrameLoadingScreen.java.
	public ImageClass() {
		try {
		/*
		 * 		Mete la imágen procesada en una variable para ser utilizada por el método Override.
		 * 
		 * 		> Llama a métodos de su misma clase:
		 * 
		 * 			loadBackgroundImage(): Llama a la imagen del directorio de recursos y procesa la imagen.
		 */
		backgroundImage = loadBackgroundImage();
		setBounds(0, 0, 434, 261);
		setLayout(null);
		
		barraDeProgreso = new JProgressBar();
		barraDeProgreso.setBounds(10, 219, 414, 41);
		barraDeProgreso.setForeground(new Color(150, 150, 150));
		barraDeProgreso.setLayout(null);
		barraDeProgreso.setMaximum(100);
		add(barraDeProgreso);
		
		lblLoading = new JLabel("Cargando . . .");
		lblLoading.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLoading.setBounds(10, 195, 283, 19);
		lblLoading.setLayout(null);
		add(lblLoading);
		/*
		 * 		Iniciamos el temporizador para la barra de progreso.
		 * 
		 * 		> Llama a métodos de su misma clase:
		 * 
		 *			- temporizador(): El temporizador para avanzar la barra de progreso.
		 */
		temporizador();
		tempo.start();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Se ha producido un error inesperado.\n"
					+ "La aplicacion se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(10);
		}
	}
	//Método repetido para ser llamado por otras clases al reiniciar la aplicación.
	public void ImageClassBuilder() {
		try {
		/*
		 * 		Mete la imágen procesada en una variable para ser utilizada por el método Override.
		 * 
		 * 		> Llama a métodos de su misma clase:
		 * 
		 * 			loadBackgroundImage(): Llama a la imagen del directorio de recursos y procesa la imagen.
		 */
		backgroundImage = loadBackgroundImage();
		setBounds(0, 0, 434, 261);
		setLayout(null);
		
		barraDeProgreso = new JProgressBar();
		barraDeProgreso.setBounds(10, 219, 414, 41);
		barraDeProgreso.setForeground(new Color(150, 150, 150));
		barraDeProgreso.setLayout(null);
		barraDeProgreso.setMaximum(100);
		add(barraDeProgreso);
		
		lblLoading = new JLabel("Cargando . . .");
		lblLoading.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLoading.setBounds(10, 195, 283, 19);
		lblLoading.setLayout(null);
		add(lblLoading);
		/*
		 * 		Iniciamos el temporizador para la barra de progreso.
		 * 
		 * 		> Llama a métodos de su misma clase:
		 * 
		 *			- temporizador(): El temporizador para avanzar la barra de progreso.
		 */
		temporizador();
		tempo.start();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Se ha producido un error inesperado.\n"
					+ "La aplicacion se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(10);
		}
	}
	//Método Override para dibujar la imagen en la pantalla de carga.
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		g.drawImage(backgroundImage, 10, -90, this.getWidth(), 400, null);
	}
	/*
	 * 		> loadBackgroundImage() es un sencillo método para leer la imagen de src\resources\ y procesarla.
	 * 
	 * 		  Llamada por:
	 * 
	 * 			- backgroundImage (Image)
	 */
	private Image loadBackgroundImage() {
		
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(new File("src/resources/logo.png"));
		} catch(IOException e){
			JOptionPane.showMessageDialog(null, "Se ha producido un error inesperado.\n "
					+ "La aplicación no se ha podido iniciar.", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(10);
		}
		
		return image;
	}
	/*
	 * 		> temporizador() es un método sencillo para avanzar la barra de progreso,
	 * 		  y en cuanto llegue a un cierto progreso que ocurran ciertas acciones.
	 * 
	 * 		  Llamada por:
	 * 
	 * 			- ImageClass()
	 * 			- ImageClassBuilder()
	 */
	public void temporizador() {
		
		tempo = new Timer(150, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				i++;
				barraDeProgreso.setValue(i);
				switch (i){
				/*
				 * 		Comprobamos si los archivos existen y, si lo hacen, los cargamos en memoria y creamos un nuevo
				 * 		frame con el marco reestablecido, junto con el siguiente panel de inicio de sesión.
				 * 
				 * 		> Llama a métodos de la clase FUNCTIONS:
				 * 
				 * 			verifyFiles(): Verifica si los archivos existen y los carga en memoria.
				 */
					case 98:
						//Si los archivos no se encuentran, tira error y nos saca de la aplicación.
						if(Functions.verifyFiles()==false) {
							JOptionPane.showMessageDialog(null, "No se han podido cargar los archivos.\n "
									+ "La aplicación se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
							System.exit(1);
						}else {
							//Si existen y se cargan, se reestablece el frame con el panel siguiente.
							LoginClass panelSiguiente = new LoginClass();
							setVisible(false);
							((JFrame) getTopLevelAncestor()).dispose();
							((JFrame) getTopLevelAncestor()).setUndecorated(false);
							((JFrame) getTopLevelAncestor()).setVisible(true);
							((JFrame) getTopLevelAncestor()).add(panelSiguiente);
							((JFrame) getTopLevelAncestor()).validate();
							panelSiguiente.LoginClassBuilder();
						}
						tempo.stop();
					break;
				}
			}
		});
	}
	
}

