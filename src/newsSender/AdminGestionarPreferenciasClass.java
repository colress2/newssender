package newsSender;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AdminGestionarPreferenciasClass extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JButton btnResetear;
	
	public void AdminGestionarPreferenciasClassBuilder() {
		try {
		setBounds(0, 0, 434, 261);
		setLayout(null);
		
		JLabel lblModoADMIN = new JLabel("Modo ADMIN");
		lblModoADMIN.setBounds(10, 11, 71, 14);
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
		
		JLabel lblPreferencias = new JLabel("Aquí puedes editar las preferencias de los usuarios.");
		lblPreferencias.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPreferencias.setBounds(67, 40, 357, 22);
		add(lblPreferencias);
		/*
		 * 		Los label de éstos botones, al igual que con los label de la clase AdminGestionarUsuariosClass.java
		 * 		se actualizan automáticamente con el usuario que está dado de alta en esa ranura. Se le pasa la
		 * 		configuración 1 para que si no existe un usuario simplemente imprima "Nuevo usuario", seguido de su id.
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
		
		JButton btnModificar1 = new JButton("Modificar");
		btnModificar1.setBounds(213, 87, 153, 23);
		add(btnModificar1);
		/*
		 * 		Dependiendo de si el usuario tiene dada de alta sus preferencias o no, se habilitará
		 * 		el botón correspondiente al id que se le pase.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			checkPrefsExist(): Comprueba si existen preferencias del usuario designado, dependiendo
		 * 			del id que se le pase. Si existen, envia true y si no false.
		 */
		btnModificar1.setEnabled(Functions.checkPrefsExist(1));
		//Lleva al ADMIN a la pestaña de modificar preferencias, con su correspondiente id.
		btnModificar1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminPrefsClass panelSiguiente = new AdminPrefsClass();
				setVisible(false);
				((JFrame) getTopLevelAncestor()).add(panelSiguiente);
				((JFrame) getTopLevelAncestor()).validate();
				panelSiguiente.AdminPrefsClassBuilder(Functions.checkUsers(1,1));
			}
		});
		/*
		 * 		Con el boton rojo, el ADMIN puede borrar las preferencias del usuario del
		 * 		cuál se ha pulsado el botón rojo.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			resetPrefs(): Borra las preferencias del usuario que se le pase, así mismo
		 * 			habilitandole de nuevo la posibilidad de configurar sus preferencias de nuevo.
		 * 			reset(): Comprueba si existe al menos una preferencia de un usuario escrita en
		 * 			el fichero userprefs.txt.
		 * 			checkUsers(): Muestra cierta información de un usuario dependiendo de su id y
		 * 			de el parámetro que se le pase.
		 */
		JButton btnBorrar1 = new JButton();
		btnBorrar1.setIcon(new ImageIcon(new ImageIcon("src\\resources\\paperbin.png").getImage().getScaledInstance(18, 18, java.awt.Image.SCALE_SMOOTH)));
		btnBorrar1.setEnabled(Functions.checkPrefsExist(1));
		btnBorrar1.setBounds(376, 87, 25, 23);
		add(btnBorrar1);
		btnBorrar1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = 0;
				option = JOptionPane.showOptionDialog(null, "Se reestableceran las preferencias del usuario " + Functions.checkUsers(1,1) + ". ¿Esta seguro?" , "Confirmacion", JOptionPane.YES_NO_CANCEL_OPTION, 
						JOptionPane.WARNING_MESSAGE, null, new String[]{"Sí", "No"}, "");
				if(option==0) {
					if(Functions.resetPrefs(Functions.checkUsers(1,1))==false) {
						JOptionPane.showMessageDialog(null, "No se han podido borrar las preferencias.\n"
								+ "La aplicacion se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
							System.exit(8);
					}
					JOptionPane.showMessageDialog(null, "Las preferencias se han borrado correctamente.", "Success", JOptionPane.INFORMATION_MESSAGE);
					btnModificar1.setEnabled(false);
					btnBorrar1.setEnabled(false);
					btnResetear.setEnabled(Functions.reset());
				}
			}
			
		});
		
		JButton btnModificar2 = new JButton("Modificar");
		btnModificar2.setBounds(213, 120, 153, 23);
		add(btnModificar2);
		/*
		 * 		Dependiendo de si el usuario tiene dada de alta sus preferencias o no, se habilitará
		 * 		el botón correspondiente al id que se le pase.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			checkPrefsExist(): Comprueba si existen preferencias del usuario designado, dependiendo
		 * 			del id que se le pase. Si existen, envia true y si no false.
		 */
		btnModificar2.setEnabled(Functions.checkPrefsExist(2));
		//Lleva al ADMIN a la pestaña de modificar preferencias, con su correspondiente id.
		btnModificar2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminPrefsClass panelSiguiente = new AdminPrefsClass();
				setVisible(false);
				((JFrame) getTopLevelAncestor()).add(panelSiguiente);
				((JFrame) getTopLevelAncestor()).validate();
				panelSiguiente.AdminPrefsClassBuilder(Functions.checkUsers(2,1));
			}
		});
		/*
		 * 		Con el boton rojo, el ADMIN puede borrar las preferencias del usuario del
		 * 		cuál se ha pulsado el botón rojo.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			resetPrefs(): Borra las preferencias del usuario que se le pase, así mismo
		 * 			habilitandole de nuevo la posibilidad de configurar sus preferencias de nuevo.
		 * 			reset(): Comprueba si existe al menos una preferencia de un usuario escrita en
		 * 			el fichero userprefs.txt.
		 * 			checkUsers(): Muestra cierta información de un usuario dependiendo de su id y
		 * 			de el parámetro que se le pase.
		 */
		JButton btnBorrar2 = new JButton();
		btnBorrar2.setIcon(new ImageIcon(new ImageIcon("src\\resources\\paperbin.png").getImage().getScaledInstance(18, 18, java.awt.Image.SCALE_SMOOTH)));
		btnBorrar2.setEnabled(Functions.checkPrefsExist(2));
		btnBorrar2.setBounds(376, 120, 25, 23);
		add(btnBorrar2);
		btnBorrar2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = 0;
				option = JOptionPane.showOptionDialog(null, "Se reestableceran las preferencias del usuario " + Functions.checkUsers(2,1) + ". ¿Esta seguro?" , "Confirmacion", JOptionPane.YES_NO_CANCEL_OPTION, 
						JOptionPane.WARNING_MESSAGE, null, new String[]{"Sí", "No"}, "");
				if(option==0) {
					if(Functions.resetPrefs(Functions.checkUsers(2,1))==false) {
						JOptionPane.showMessageDialog(null, "No se han podido borrar las preferencias.\n"
								+ "La aplicacion se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
							System.exit(8);
					}
					JOptionPane.showMessageDialog(null, "Las preferencias se han borrado correctamente.", "Success", JOptionPane.INFORMATION_MESSAGE);
					btnModificar2.setEnabled(false);
					btnBorrar2.setEnabled(false);
					btnResetear.setEnabled(Functions.reset());
				}
			}
			
		});
		
		JButton btnModificar3 = new JButton("Modificar");
		btnModificar3.setBounds(213, 153, 153, 23);
		add(btnModificar3);
		/*
		 * 		Dependiendo de si el usuario tiene dada de alta sus preferencias o no, se habilitará
		 * 		el botón correspondiente al id que se le pase.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			checkPrefsExist(): Comprueba si existen preferencias del usuario designado, dependiendo
		 * 			del id que se le pase. Si existen, envia true y si no false.
		 */
		btnModificar3.setEnabled(Functions.checkPrefsExist(3));
		//Lleva al ADMIN a la pestaña de modificar preferencias, con su correspondiente id.
		btnModificar3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminPrefsClass panelSiguiente = new AdminPrefsClass();
				setVisible(false);
				((JFrame) getTopLevelAncestor()).add(panelSiguiente);
				((JFrame) getTopLevelAncestor()).validate();
				panelSiguiente.AdminPrefsClassBuilder(Functions.checkUsers(3,1));
			}
		});
		/*
		 * 		Con el boton rojo, el ADMIN puede borrar las preferencias del usuario del
		 * 		cuál se ha pulsado el botón rojo.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			resetPrefs(): Borra las preferencias del usuario que se le pase, así mismo
		 * 			habilitandole de nuevo la posibilidad de configurar sus preferencias de nuevo.
		 * 			reset(): Comprueba si existe al menos una preferencia de un usuario escrita en
		 * 			el fichero userprefs.txt.
		 * 			checkUsers(): Muestra cierta información de un usuario dependiendo de su id y
		 * 			de el parámetro que se le pase.
		 */
		JButton btnBorrar3 = new JButton();
		btnBorrar3.setIcon(new ImageIcon(new ImageIcon("src\\resources\\paperbin.png").getImage().getScaledInstance(18, 18, java.awt.Image.SCALE_SMOOTH)));
		btnBorrar3.setEnabled(Functions.checkPrefsExist(3));
		btnBorrar3.setBounds(376, 153, 25, 23);
		add(btnBorrar3);
		btnBorrar3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = 0;
				option = JOptionPane.showOptionDialog(null, "Se reestableceran las preferencias del usuario " + Functions.checkUsers(3,1) + ". ¿Esta seguro?" , "Confirmacion", JOptionPane.YES_NO_CANCEL_OPTION, 
						JOptionPane.WARNING_MESSAGE, null, new String[]{"Sí", "No"}, "");
				if(option==0) {
					if(Functions.resetPrefs(Functions.checkUsers(3,1))==false) {
						JOptionPane.showMessageDialog(null, "No se han podido borrar las preferencias.\n"
								+ "La aplicacion se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
							System.exit(8);
					}
					JOptionPane.showMessageDialog(null, "Las preferencias se han borrado correctamente.", "Success", JOptionPane.INFORMATION_MESSAGE);
					btnModificar3.setEnabled(false);
					btnBorrar3.setEnabled(false);
					btnResetear.setEnabled(Functions.reset());
				}
			}		
			
		});
		
		JLabel lblResetear = new JLabel("Resetear todo:");
		lblResetear.setForeground(new Color(255, 0, 0));
		lblResetear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblResetear.setBounds(30, 184, 163, 22);
		add(lblResetear);
		
		btnResetear = new JButton("Resetear");
		btnResetear.setBackground(new Color(255, 0, 0));
		btnResetear.setForeground(new Color(255, 255, 255));
		/*
		 * 		Dependiendo de si existe al menos una preferencia en el archivo o no, habilitamos
		 * 		el boton para reiniciar las preferencias.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			reset(): Comprueba si existe al menos una preferencia de un usuario escrita en
		 * 			el fichero userprefs.txt.
		 */
		btnResetear.setEnabled(Functions.reset());
		btnResetear.setBounds(213, 186, 188, 23);
		add(btnResetear);
		/*
		 * 		Con el boton de resetear, el ADMIN borra absolutamente todas las preferencias de la
		 * 		aplicación, permitiendo a los usuarios insertar sus preferencias de nuevo.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			resetAction(): Borra todo el fichero de preferencias, los objetos en memoria y
		 * 			habilita a los usuarios a rellenar sus preferencias de nuevo.
		 */
		btnResetear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = 0;
					option = JOptionPane.showOptionDialog(null, "Se borraran TODAS las preferencias de los usuarios.\n"
							+ "Esta accion no se puede deshacer. ¿Esta seguro?" , "Confirmacion", JOptionPane.YES_NO_CANCEL_OPTION, 
							JOptionPane.WARNING_MESSAGE, null, new String[]{"Sí", "No"}, "");
				if(option==0) {
					if(Functions.resetAction()==false) {
						JOptionPane.showMessageDialog(null, "No se han podido borrar las preferencias.\n"
								+ "La aplicacion se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
							System.exit(8);
					}
					JOptionPane.showMessageDialog(null, "Las preferencias se han borrado correctamente.", "Success", JOptionPane.INFORMATION_MESSAGE);
					btnModificar1.setEnabled(false);
					btnBorrar1.setEnabled(false);
					btnModificar2.setEnabled(false);
					btnBorrar2.setEnabled(false);
					btnModificar3.setEnabled(false);
					btnBorrar3.setEnabled(false);
					btnResetear.setEnabled(false);
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
