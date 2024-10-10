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
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class AdminSignInClass extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private static JTextField textFieldUsuario;
	private static JTextField textFieldCorreo;
	private static JTextField textFieldContrasena;
	
	public void AdminSignInClassBuilder(int id) {
		try {
		setBounds(0, 0, 434, 261);
		setLayout(null);
		
		JLabel lblModoADMIN = new JLabel("Modo ADMIN");
		lblModoADMIN.setBounds(10, 11, 73, 14);
		lblModoADMIN.setFont(new Font("Tahoma", Font.ITALIC, 11));
		add(lblModoADMIN);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(333, 0, 101, 22);
		add(menuBar);
		
		JMenu mnNewMenu = new JMenu("?");
		mnNewMenu.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mntmAcercaDe.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		mnNewMenu.add(mntmAcercaDe);
		mntmAcercaDe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Versión: 1.0.\n"
						+ "Autor: Daniel González Sanchís\n"
						+ "Fecha: 24 / 11 / 2023", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 227, 122, 23);
		add(btnVolver);
		//Éste botón devuelve directamente al menú principal. Si se han introducido datos en los campos de texto, se pregunta al ADMIN si desea guardar los cambios o salir.
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = 0;
				if(!textFieldUsuario.getText().equals("")||!textFieldCorreo.getText().equals("")||!textFieldContrasena.getText().equals("")) {
					option = JOptionPane.showOptionDialog(null, "¿Desea salir? Los cambios no se guardarán. " , "Salir", JOptionPane.YES_NO_CANCEL_OPTION, 
							JOptionPane.WARNING_MESSAGE, null, new String[]{"Sí", "No"}, "");
				}
				if(option==0) {
					AdminGestionarUsuariosClass panelSiguiente = new AdminGestionarUsuariosClass();
					setVisible(false);
					((JFrame) getTopLevelAncestor()).add(panelSiguiente);
					((JFrame) getTopLevelAncestor()).validate();
					panelSiguiente.AdminGestionarUsuariosClassBuilder();
				}
			}
		});
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(302, 227, 122, 23);
		add(btnAceptar);
		/*
		 * 		El botón Aceptar realiza una larga serie de comprobaciones para confirmar si lo que se está introduciendo en
		 * 		los campos de texto para los usuarios es información aceptable. Éstas condiciones se comprueban gracias a
		 * 		una serie de métodos, así mismo como una comparación de carácteres para comprobar que no se están introduciendo
		 * 		carácteres no validos, ya que por ejemplo un ";" arruinaría la lectura de los ficheros, ya que así es como
		 * 		están separados.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			checkUsers(): Muestra cierta información de un usuario dependiendo de su id y
		 * 			de el parámetro que se le pase.
		 * 			checkRepeatedUsers(): Comprueba si un usuario que se le está pasando se encuentra
		 * 			repetido, devolviendo true o false.
		 * 			checkRepeatedMails(): Comprueba si un correo que se le está pasando se encuentra
		 * 			repetido, devolviendo true o false.
		 * 			modifyUsers(): Modifica la información de los usuarios que se le están pasando,
		 * 			en base al id que está recibiendo del panel anterior.
		 */
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int progress = 0;
				boolean bucle = true;
				while(bucle) {
					switch(progress) {
					//Primero comprobamos si los campos se encuentran vacíos. Si es así, tiramos mensaje de error.
						case 0:
							if(textFieldUsuario.getText().equals("")||textFieldCorreo.getText().equals("")||textFieldContrasena.getText().equals("")) {
								JOptionPane.showMessageDialog(null, "No se pueden dejar campos en blanco.", "Error", JOptionPane.ERROR_MESSAGE);
								bucle = false;
							}else{
								progress++;
							}
						break;
					//Luego comprobamos si la longitud de carácteres tanto de usuario como de contraseña no es menor de 6 carácteres.
						case 1:
							if(textFieldUsuario.getText().length() <= 6 || textFieldContrasena.getText().length() <= 6) {
								JOptionPane.showMessageDialog(null, "El usuario o la contraseña son demasiado pequeños.\n"
										+ "Por favor introduzca un Usuario y Contraseña con un\n"
										+ "tamaño superior a 6 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
								bucle = false;
							}else{
								progress++;
							}
						break;
					//Luego comprobamos que no se estén insertando puntos y comas en los campos de texto.
						case 2:
							if(textFieldUsuario.getText().contains(";")||textFieldCorreo.getText().contains(";")||textFieldContrasena.getText().contains(";")) {
								JOptionPane.showMessageDialog(null, "Por favor introduzca caracteres validos.", "Error", JOptionPane.ERROR_MESSAGE);
								bucle = false;
							}else{
								progress++;
							}
						break;
					//Luego comprobamos si se está introduciendo (al menos) un arroba en el campo de correo, para confirmar que lo que se está insertando es un correo.
						case 3:
							if(!textFieldCorreo.getText().contains("@")) {
								JOptionPane.showMessageDialog(null, "Por favor introduzca una direccion de correo valida.", "Error", JOptionPane.ERROR_MESSAGE);
								bucle = false;
							}else{
								progress++;
							}
						break;
					//Luego comprobamos si el usuario que se está intentando modificar ya existe junto con su correo, omitiendo que sea el nombre del usuario actual.
						case 4:
							if(!(textFieldUsuario.getText().equals(Functions.checkUsers(id,2)))) {
								if(Functions.checkRepeatedUsers(textFieldUsuario.getText())==true) {
									JOptionPane.showMessageDialog(null, "No pueden existir usuarios duplicados.", "Error", JOptionPane.ERROR_MESSAGE);
									bucle = false;
								}
							}
							if(!(textFieldCorreo.getText().equals(Functions.checkUsers(id,3)))) {
								if(Functions.checkRepeatedMails(textFieldCorreo.getText())==true) {
									JOptionPane.showMessageDialog(null, "No pueden existir correos duplicados.", "Error", JOptionPane.ERROR_MESSAGE);
									bucle = false;
								}
							}
							progress++;
						break;
					//Por último, probamos a modificar el usuario, y si falla, tiramos error.
						case 5:
							if(Functions.modifyUsers(id, textFieldUsuario.getText(), textFieldCorreo.getText(), textFieldContrasena.getText())==false) {
								JOptionPane.showMessageDialog(null, "Se ha producido un error inesperado.\n"
										+ "La aplicacion se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
								bucle = false;
								System.exit(8);
							}else {
								bucle = false;
								JOptionPane.showMessageDialog(null, "Se han actualizado los datos correctamente.", "Success", JOptionPane.INFORMATION_MESSAGE);
								AdminGestionarUsuariosClass panelSiguiente = new AdminGestionarUsuariosClass();
								setVisible(false);
								((JFrame) getTopLevelAncestor()).add(panelSiguiente);
								((JFrame) getTopLevelAncestor()).validate();
								panelSiguiente.AdminGestionarUsuariosClassBuilder();
								AdminGestionarUsuariosClass.btnVolver.setEnabled(false);
								AdminGestionarUsuariosClass.integrity = false;
							}
						break;
					}
				}
			}
		});
		
		JLabel lblNombreUsuario = new JLabel("Nombre de usuario:");
		lblNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreUsuario.setBounds(10, 36, 163, 22);
		add(lblNombreUsuario);
		/*
		 * 		Dependiendo del id de usuario que se haya pasado de la clase anterior, se mostrarán
		 * 		los datos de ese usuario junto con la instrucción que se le haya enviado en el
		 * 		campo de texto.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			checkUsers(): Muestra cierta información de un usuario dependiendo de su id y
		 * 			de el parámetro que se le pase.
		 */
		textFieldUsuario = new JTextField(Functions.checkUsers(id,2));
		textFieldUsuario.setColumns(10);
		textFieldUsuario.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textFieldUsuario.setBounds(10, 62, 413, 20);
		add(textFieldUsuario);
		//Ésta función asegura que lo que se está introduciendo en el campo de texto no sobrepase los 30 carácteres
		textFieldUsuario.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) { 
				if (textFieldUsuario.getText().length() >= 30 ) {
					e.consume(); 
				}
			}
		});
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCorreo.setBounds(10, 90, 163, 22);
		add(lblCorreo);
		
		textFieldCorreo = new JTextField(Functions.checkUsers(id,3));
		textFieldCorreo.setColumns(10);
		textFieldCorreo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textFieldCorreo.setBounds(10, 116, 413, 20);
		add(textFieldCorreo);
		
		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContrasena.setBounds(10, 144, 163, 22);
		add(lblContrasena);
		
		textFieldContrasena = new JTextField(Functions.checkUsers(id,4));
		textFieldContrasena.setColumns(10);
		textFieldContrasena.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textFieldContrasena.setBounds(10, 170, 413, 20);
		add(textFieldContrasena);
		//Ésta función asegura que lo que se está introduciendo en el campo de texto no sobrepase los 30 carácteres
		textFieldContrasena.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) { 
					if (textFieldContrasena.getText().length() >= 30 ) {
						e.consume(); 
					}
			}
		});
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Se ha producido un error inesperado.\n"
					+ "La aplicacion se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(10);
		}
		
	}
	
}
