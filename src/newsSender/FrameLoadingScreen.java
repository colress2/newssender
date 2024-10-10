package newsSender;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FrameLoadingScreen extends JFrame {
	
	private static final long serialVersionUID = 1L;
	//Creación del frame.
	public FrameLoadingScreen(){
		setBounds(700, 450, 800, 600);
		setTitle("NewsSender");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src\\resources\\outlook_express-5.png"));
		//Aquí haremos que al dar a la cruz aparezca un mensaje de confirmación de salida.
		WindowListener exitListener = (WindowListener) new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		    	int option = JOptionPane.showOptionDialog(null, "¿Está seguro de que desea salir?\n"
		    			+ "Todos los cambios no guardados se perderán." , "Salir", JOptionPane.YES_NO_CANCEL_OPTION, 
		    			JOptionPane.WARNING_MESSAGE, null, new String[]{"Sí", "No"}, "");
		    	if(option==0) {
		    		//Si se confirma, sale de la aplicación.
		    		System.exit(0);
		    	}
		    	//Si no, el botón no hace nada.
		    	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		   	}
		};
		addWindowListener(exitListener);
		getContentPane().setLayout(null);
		setResizable(false);
		setUndecorated(true);
		//Creamos y añadimos el panel de carga:
		ImageClass panel = new ImageClass();
		add(panel);
	}
}
