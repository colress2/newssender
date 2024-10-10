package newsSender;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class FeedbackClass extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldAsunto;
	private JTextArea textArea;
	
	public void FeedbackClassBuilder() {
		try {
		setBounds(0, 0, 434, 261);
		setLayout(null);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDescripcion.setBounds(11, 64, 101, 23);
		add(lblDescripcion);
		
		textArea = new JTextArea();
		textArea.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textArea.setBounds(11, 93, 413, 93);
		add(textArea);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(302, 227, 122, 23);
		add(btnEnviar);
		/*
		 * 		Éste botón envia los contenidos de el textField y del textArea al correo que se
		 * 		quiere enviar al ADMIN.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			emailSend(): Construye el cuerpo del correo y lo envia al correo designado.
		 * 			checkAdmin(): Comprueba el correo del admin.
		 */
		btnEnviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Si los campos están vacíos, el envío no se realiza.
				if(textArea.getText().equals("")||textFieldAsunto.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "No puede enviar un correo sin un asunto o descripción.", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					//Si no se añade el usuario actual, el correo del admin y ambos campos de texto.
					if(Functions.emailSend(Functions.currentMail, Functions.checkAdmin(), textFieldAsunto.getText(), textArea.getText())==false) {
						JOptionPane.showMessageDialog(null, "Ha ocurrido un error intentando enviar el correo.\n"
								+ "Revise que su correo actual exista.\n"
								+ "Intentelo de nuevo mas tarde.", "Error", JOptionPane.ERROR_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "El correo ha sido enviado.", "Enviado", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		
		JLabel lblAsunto = new JLabel("Asunto:");
		lblAsunto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAsunto.setBounds(11, 11, 63, 23);
		add(lblAsunto);
		
		textFieldAsunto = new JTextField();
		textFieldAsunto.setColumns(10);
		textFieldAsunto.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textFieldAsunto.setBounds(11, 40, 413, 20);
		add(textFieldAsunto);
		//Ésta función asegura que lo que se está introduciendo en el campo de texto no sobrepase los 30 carácteres
		textFieldAsunto.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (textFieldAsunto.getText().length() >= 30 ) {
		            e.consume(); 
		    	}
		    }
		});
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 227, 122, 23);
		add(btnVolver);
		/*
		 * 		Botón para volver al menú de la aplicación. Si se ha escrito algo en alguno de los campos, 
		 * 		se pregunta al usuario si desea salir.
		 */
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = 0;
				if(!textArea.getText().equals("")||!textFieldAsunto.getText().equals("")) {
					option = JOptionPane.showOptionDialog(null, "¿Desea salir? Los cambios no se guardarán. " , "Salir", JOptionPane.YES_NO_CANCEL_OPTION, 
							JOptionPane.WARNING_MESSAGE, null, new String[]{"Sí", "No"}, "");
				}
				if(option==0) {
					MenuClass panelSiguiente = new MenuClass();
					setVisible(false);
					((JFrame) getTopLevelAncestor()).add(panelSiguiente);
					((JFrame) getTopLevelAncestor()).validate();
					panelSiguiente.MenuClassBuilder();
				}
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(333, 0, 101, 22);
		add(menuBar);
		
		JMenu mnMenuBar = new JMenu("?");
		mnMenuBar.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		menuBar.add(mnMenuBar);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mntmAcercaDe.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		mnMenuBar.add(mntmAcercaDe);
		mntmAcercaDe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Versión: 1.0.\n"
						+ "Autor: Daniel González Sanchís\n"
						+ "Fecha: 24 / 11 / 2023", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JLabel lblEditarPreferencias = new JLabel("Si desea editar sus preferencias, por favor describalas en detalle.");
		lblEditarPreferencias.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEditarPreferencias.setBounds(10, 193, 414, 23);
		add(lblEditarPreferencias);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Se ha producido un error inesperado.\n"
					+ "La aplicacion se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(10);
		}
	}
	
}
