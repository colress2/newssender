package newsSender;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AdminGestionarUsuariosClass extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	public static JButton btnVolver;
	public static JPanel panelModificarUsuario1;
	public static JPanel panelModificarUsuario2;
	public static JPanel panelModificarUsuario3;
	public static JPanel panelAgregarUsuario1;
	public static JPanel panelAgregarUsuario2;
	public static JPanel panelAgregarUsuario3;
	public static boolean integrity = true;
	
	public void AdminGestionarUsuariosClassBuilder() {
		try {
		setBounds(0, 0, 434, 261);
		setLayout(null);
		
		JLabel lblModoAdmin = new JLabel("Modo ADMIN");
		lblModoAdmin.setBounds(10, 11, 73, 14);
		lblModoAdmin.setFont(new Font("Tahoma", Font.ITALIC, 11));
		add(lblModoAdmin);
		
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
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 227, 122, 23);
		//La visibilidad del boton dependera de si al menos un usuario se ha modificado/borrado o no.
		btnVolver.setEnabled(integrity);
		add(btnVolver);
		//Éste botón devuelve directamente al menú principal.
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminMenuClass panelSiguiente = new AdminMenuClass();
				setVisible(false);
				((JFrame) getTopLevelAncestor()).add(panelSiguiente);
				((JFrame) getTopLevelAncestor()).validate();
				panelSiguiente.AdminMenuClassBuilder();
			}
		});
		
		JLabel lblModificar1 = new JLabel("Aquí puedes modificar y borrar usuarios.");
		lblModificar1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblModificar1.setBounds(55, 36, 229, 22);
		add(lblModificar1);
		
		JLabel lblModificar2 = new JLabel("Se necesita un reinicio al modificar al menos un usuario.");
		lblModificar2.setForeground(new Color(255, 0, 0));
		lblModificar2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblModificar2.setBounds(55, 52, 316, 22);
		add(lblModificar2);
		/*
		 * 		Los labels de ésta sección del archivo se actualizan automáticamente con el usuario
		 * 		correspondiendo a su id. Éstas funciones así mismo utilizan otro id, el cuál le
		 * 		paso para decidir que tipo de información mostrar con éste número. En éste caso
		 * 		pido que me muestre el usuario, y si no que muestre "Nuevo usuario", seguido de
		 * 		su correspondiente id.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			checkUsers(): Muestra cierta información de un usuario dependiendo de su id y
		 * 			de el parámetro que se le pase.
		 */
		JLabel lblUsuario1 = new JLabel(Functions.checkUsers(1,1));
		lblUsuario1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario1.setBounds(30, 85, 163, 22);
		add(lblUsuario1);
		
		JLabel lblUsuario2 = new JLabel(Functions.checkUsers(2,1));
		lblUsuario2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario2.setBounds(30, 118, 163, 22);
		add(lblUsuario2);
		
		JLabel lblUsuario3 = new JLabel(Functions.checkUsers(3,1));
		lblUsuario3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario3.setBounds(30, 151, 163, 22);
		add(lblUsuario3);
		
		JLabel lblUsuario4 = new JLabel(Functions.checkUsers(0,1) + " [ADMIN]");
		lblUsuario4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario4.setBounds(30, 184, 163, 22);
		add(lblUsuario4);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(302, 227, 122, 23);
		add(btnAceptar);
		/*
		 * 		El botón Aceptar confirma todos los cambios en el archivo user.txt y reinicia la aplicación.
		 * 		De ésta manera se refrescan todos los objetos en memoria.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			logout(): Cierra la sesión del usuario y elimina el usuario y correo actuales.
		 *			restart(): Borra las listas de los usuarios de la memoria para refrescar los datos.
		 */
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = 0;
				option = JOptionPane.showOptionDialog(null, "¿Desea reiniciar la aplicacion ahora?" , "Confirmacion", JOptionPane.YES_NO_CANCEL_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, new String[]{"Sí", "No"}, "");
				if(option==0) {
					btnVolver.setEnabled(true);
					integrity = true;
					Functions.logout();
					Functions.restart();
					ImageClass panelSiguiente = new ImageClass();
					setVisible(false);
					((JFrame) getTopLevelAncestor()).dispose();
					((JFrame) getTopLevelAncestor()).setUndecorated(true);
					((JFrame) getTopLevelAncestor()).setVisible(true);
					((JFrame) getTopLevelAncestor()).add(panelSiguiente);
					((JFrame) getTopLevelAncestor()).validate();
					panelSiguiente.ImageClassBuilder();
				}
			}
		});
		/*
		 * 		La visibilidad de los paneles dependerá de si el usuario con el id que se le ofrece
		 * 		existe o no (null). De ésta manera podremos mostrar los botones de modificar y borrar.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			checkUsersExist(): Comprueba si el usuario que se le pasa existe, si lo hace devuelve
		 * 			true y si no false.
		 */
		panelModificarUsuario1 = new JPanel();
		panelModificarUsuario1.setBounds(203, 85, 221, 24);
		add(panelModificarUsuario1);
		panelModificarUsuario1.setLayout(null);
		panelModificarUsuario1.setVisible(Functions.checkUsersExist(1));
		
		JButton btnModificar1 = new JButton("Modificar");
		btnModificar1.setBounds(10, 0, 89, 23);
		panelModificarUsuario1.add(btnModificar1);
		//Lleva al ADMIN a la pestaña de modificar usuario, con su correspondiente id.
		btnModificar1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminSignInClass panelSiguiente = new AdminSignInClass();
				setVisible(false);
				((JFrame) getTopLevelAncestor()).add(panelSiguiente);
				((JFrame) getTopLevelAncestor()).validate();
				panelSiguiente.AdminSignInClassBuilder(1);
			}
		});
		
		JButton btnBorrar1 = new JButton("Borrar");
		btnBorrar1.setBounds(109, 0, 89, 23);
		panelModificarUsuario1.add(btnBorrar1);
		/*
		 * 		Pregunta al ADMIN si desea borrar al usuario actual. Al borrar a éste usuario, su correspondiente
		 * 		id se guarda, pero sus preferencias se borran y su nombre de usuario, mail y contraseña se rellenan
		 * 		con el campo "null", indicando que éste usuario está dado de baja.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			deleteUsers(): Borra los datos del id del usuario que se le pase, borrando también sus preferencias.
		 */
		btnBorrar1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = 0;
				if(Functions.existingUsers()>1) {
					option = JOptionPane.showOptionDialog(null, "¿Esta realmente seguro de que desea borrar a este usuario? \n"
							+ "Esta accion no se puede deshacer." , "Confirmacion", JOptionPane.YES_NO_CANCEL_OPTION, 
							JOptionPane.WARNING_MESSAGE, null, new String[]{"Sí", "No"}, "");
					if(option==0) {
						if(Functions.deleteUsers(1)==false) {
							JOptionPane.showMessageDialog(null, "Se ha producido un error inesperado.\n"
									+ "La aplicacion se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
							System.exit(7);
						}else{
							JOptionPane.showMessageDialog(null, "El usuario se ha borrado correctamente", "Success", JOptionPane.INFORMATION_MESSAGE);
							integrity = false;
							btnVolver.setEnabled(false);
							panelModificarUsuario1.setVisible(false);
							panelAgregarUsuario1.setVisible(true);
							lblUsuario1.setText("Nuevo usuario 1");
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "No se pueden borrar mas de 2 usuarios.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		/*
		 * 		La visibilidad de los paneles dependerá de si el usuario con el id que se le ofrece
		 * 		existe o no (null). De ésta manera podremos mostrar los botones de modificar y borrar.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			checkUsersExist(): Comprueba si el usuario que se le pasa existe, si lo hace devuelve
		 * 			true y si no false.
		 */
		panelModificarUsuario2 = new JPanel();
		panelModificarUsuario2.setLayout(null);
		panelModificarUsuario2.setBounds(203, 118, 221, 24);
		add(panelModificarUsuario2);
		panelModificarUsuario2.setVisible(Functions.checkUsersExist(2));
		
		JButton btnModificar2 = new JButton("Modificar");
		btnModificar2.setBounds(10, 0, 89, 23);
		panelModificarUsuario2.add(btnModificar2);
		//Lleva al ADMIN a la pestaña de modificar usuario, con su correspondiente id.
		btnModificar2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminSignInClass panelSiguiente = new AdminSignInClass();
				setVisible(false);
				((JFrame) getTopLevelAncestor()).add(panelSiguiente);
				((JFrame) getTopLevelAncestor()).validate();
				panelSiguiente.AdminSignInClassBuilder(2);
			}
		});
		
		JButton btnBorrar2 = new JButton("Borrar");
		btnBorrar2.setBounds(109, 0, 89, 23);
		panelModificarUsuario2.add(btnBorrar2);
		/*
		 * 		Pregunta al ADMIN si desea borrar al usuario actual. Al borrar a éste usuario, su correspondiente
		 * 		id se guarda, pero sus preferencias se borran y su nombre de usuario, mail y contraseña se rellenan
		 * 		con el campo "null", indicando que éste usuario está dado de baja.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			deleteUsers(): Borra los datos del id del usuario que se le pase, borrando también sus preferencias.
		 */
		btnBorrar2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = 0;
				if(Functions.existingUsers()>1) {
					option = JOptionPane.showOptionDialog(null, "¿Esta realmente seguro de que desea borrar a este usuario? \n"
							+ "Esta accion no se puede deshacer." , "Confirmacion", JOptionPane.YES_NO_CANCEL_OPTION, 
							JOptionPane.WARNING_MESSAGE, null, new String[]{"Sí", "No"}, "");
					if(option==0) {
						if(Functions.deleteUsers(2)==false) {
							JOptionPane.showMessageDialog(null, "No se ha podido borrar al usuario.\n"
									+ "La aplicacion se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
							System.exit(7);
						}else{
							JOptionPane.showMessageDialog(null, "El usuario se ha borrado correctamente", "Success", JOptionPane.INFORMATION_MESSAGE);
							integrity = false;
							btnVolver.setEnabled(false);
							panelModificarUsuario2.setVisible(false);
							panelAgregarUsuario2.setVisible(true);
							lblUsuario2.setText("Nuevo usuario 2");
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "No se pueden borrar mas de 2 usuarios.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		/*
		 * 		La visibilidad de los paneles dependerá de si el usuario con el id que se le ofrece
		 * 		existe o no (null). De ésta manera podremos mostrar los botones de modificar y borrar.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			checkUsersExist(): Comprueba si el usuario que se le pasa existe, si lo hace devuelve
		 * 			true y si no false.
		 */
		panelModificarUsuario3 = new JPanel();
		panelModificarUsuario3.setLayout(null);
		panelModificarUsuario3.setBounds(203, 151, 221, 24);
		add(panelModificarUsuario3);
		panelModificarUsuario3.setVisible(Functions.checkUsersExist(3));
		
		JButton btnModificar3 = new JButton("Modificar");
		btnModificar3.setBounds(10, 0, 89, 23);
		panelModificarUsuario3.add(btnModificar3);
		//Lleva al ADMIN a la pestaña de modificar usuario, con su correspondiente id.
		btnModificar3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminSignInClass panelSiguiente = new AdminSignInClass();
				setVisible(false);
				((JFrame) getTopLevelAncestor()).add(panelSiguiente);
				((JFrame) getTopLevelAncestor()).validate();
				panelSiguiente.AdminSignInClassBuilder(3);
			}
		});
		
		JButton btnBorrar3 = new JButton("Borrar");
		btnBorrar3.setBounds(109, 0, 89, 22);
		panelModificarUsuario3.add(btnBorrar3);
		/*
		 * 		Pregunta al ADMIN si desea borrar al usuario actual. Al borrar a éste usuario, su correspondiente
		 * 		id se guarda, pero sus preferencias se borran y su nombre de usuario, mail y contraseña se rellenan
		 * 		con el campo "null", indicando que éste usuario está dado de baja.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			deleteUsers(): Borra los datos del id del usuario que se le pase, borrando también sus preferencias.
		 * 			existingUsers(): Comprueba la cantidad de usuarios que están registrados.
		 */
		btnBorrar3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = 0;
				if(Functions.existingUsers()>1) {
					option = JOptionPane.showOptionDialog(null, "¿Esta realmente seguro de que desea borrar a este usuario? \n"
							+ "Esta accion no se puede deshacer." , "Confirmacion", JOptionPane.YES_NO_CANCEL_OPTION, 
							JOptionPane.WARNING_MESSAGE, null, new String[]{"Sí", "No"}, "");
					if(option==0) {
						if(Functions.deleteUsers(3)==false) {
							JOptionPane.showMessageDialog(null, "Se ha producido un error inesperado.\n"
									+ "La aplicacion se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
							System.exit(7);
						}else{
							JOptionPane.showMessageDialog(null, "El usuario se ha borrado correctamente", "Success", JOptionPane.INFORMATION_MESSAGE);
							integrity = false;
							btnVolver.setEnabled(false);
							panelModificarUsuario3.setVisible(false);
							panelAgregarUsuario3.setVisible(true);
							lblUsuario3.setText("Nuevo usuario 3");
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "No se pueden borrar mas de 2 usuarios.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		/*
		 * 		La visibilidad de los paneles dependerá de si el usuario con el id que se le ofrece
		 * 		existe o no (null). De ésta manera podremos mostrar el botón de Nuevo.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			checkUsersDontExist(): Comprueba si el usuario que se le pasa no existe, si no lo hace
		 * 			devuelve true y si no false.
		 */
		panelAgregarUsuario1 = new JPanel();
		panelAgregarUsuario1.setVisible(false);
		panelAgregarUsuario1.setLayout(null);
		panelAgregarUsuario1.setBounds(203, 85, 221, 24);
		add(panelAgregarUsuario1);
		panelAgregarUsuario1.setVisible(Functions.checkUsersDontExist(1));
		
		JButton btnNuevo1 = new JButton("Nuevo");
		btnNuevo1.setBounds(10, 0, 188, 22);
		panelAgregarUsuario1.add(btnNuevo1);
		//Lleva al ADMIN a la pestaña de dar de alta a un usuario, con su correspondiente id.
		btnNuevo1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminSignInClass panelSiguiente = new AdminSignInClass();
				setVisible(false);
				((JFrame) getTopLevelAncestor()).add(panelSiguiente);
				((JFrame) getTopLevelAncestor()).validate();
				panelSiguiente.AdminSignInClassBuilder(1);
			}
		});
		/*
		 * 		La visibilidad de los paneles dependerá de si el usuario con el id que se le ofrece
		 * 		existe o no (null). De ésta manera podremos mostrar el botón de Nuevo.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			checkUsersDontExist(): Comprueba si el usuario que se le pasa no existe, si no lo hace
		 * 			devuelve true y si no false.
		 */
		panelAgregarUsuario2 = new JPanel();
		panelAgregarUsuario2.setVisible(false);
		panelAgregarUsuario2.setLayout(null);
		panelAgregarUsuario2.setBounds(203, 118, 221, 24);
		add(panelAgregarUsuario2);
		panelAgregarUsuario2.setVisible(Functions.checkUsersDontExist(2));
		
		JButton btnNuevo2 = new JButton("Nuevo");
		btnNuevo2.setBounds(10, 0, 188, 23);
		panelAgregarUsuario2.add(btnNuevo2);
		//Lleva al ADMIN a la pestaña de dar de alta a un usuario, con su correspondiente id.
		btnNuevo2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminSignInClass panelSiguiente = new AdminSignInClass();
				setVisible(false);
				((JFrame) getTopLevelAncestor()).add(panelSiguiente);
				((JFrame) getTopLevelAncestor()).validate();
				panelSiguiente.AdminSignInClassBuilder(2);
			}
		});
		/*
		 * 		La visibilidad de los paneles dependerá de si el usuario con el id que se le ofrece
		 * 		existe o no (null). De ésta manera podremos mostrar el botón de Nuevo.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			checkUsersDontExist(): Comprueba si el usuario que se le pasa no existe, si no lo hace
		 * 			devuelve true y si no false.
		 */
		panelAgregarUsuario3 = new JPanel();
		panelAgregarUsuario3.setVisible(false);
		panelAgregarUsuario3.setLayout(null);
		panelAgregarUsuario3.setBounds(203, 151, 221, 24);
		add(panelAgregarUsuario3);
		panelAgregarUsuario3.setVisible(Functions.checkUsersDontExist(3));
		
		JButton btnNuevo3 = new JButton("Nuevo");
		btnNuevo3.setBounds(10, 0, 188, 23);
		panelAgregarUsuario3.add(btnNuevo3);
		//Lleva al ADMIN a la pestaña de dar de alta a un usuario, con su correspondiente id.
		btnNuevo3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminSignInClass panelSiguiente = new AdminSignInClass();
				setVisible(false);
				((JFrame) getTopLevelAncestor()).add(panelSiguiente);
				((JFrame) getTopLevelAncestor()).validate();
				panelSiguiente.AdminSignInClassBuilder(3);
			}
		});
		
		JButton btnModificar0 = new JButton("Modificar");
		btnModificar0.setBounds(213, 186, 188, 23);
		add(btnModificar0);
		//Lleva al ADMIN a la pestaña de modificar usuario, con su correspondiente id.
		btnModificar0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminSignInClass panelSiguiente = new AdminSignInClass();
				setVisible(false);
				((JFrame) getTopLevelAncestor()).add(panelSiguiente);
				((JFrame) getTopLevelAncestor()).validate();
				panelSiguiente.AdminSignInClassBuilder(0);
			}
		});
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Se ha producido un error inesperado.\n"
					+ "La aplicacion se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(10);
		}
		
	}
	
}
