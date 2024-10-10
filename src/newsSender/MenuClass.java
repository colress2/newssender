package newsSender;

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

public class MenuClass extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public void MenuClassBuilder() {
		try {
		setBounds(0, 0, 434, 261);
		setLayout(null);
		/*
		 * 		Los label se actualizan automáticamente dependiendo de si el usuario ya ha introducido
		 * 		sus preferencias o no.
		 * 
		 * 		> Llama a métodos de su misma clase:
		 * 
		 * 			alreadyPreferencedlabel(): Si el usuario ya ha introducido sus preferencias, actualiza el texto.
		 * 			alreadyPreferencedbutton(): Si el usuario ya ha actualizado sus preferencias, actualiza el botón.
		 */
		JLabel lblPreferencias = new JLabel(alreadyPreferencedlabel());
		lblPreferencias.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPreferencias.setBounds(48, 100, 181, 22);
		add(lblPreferencias);
		
		JButton btnPreferencias = new JButton(alreadyPreferencedbutton());
		btnPreferencias.setBounds(239, 102, 143, 23);
		add(btnPreferencias);
		//Éste botón te lleva a la pestaña de preferencias.
		btnPreferencias.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrefsClass panelSiguiente = new PrefsClass();
				setVisible(false);
				((JFrame) getTopLevelAncestor()).add(panelSiguiente);
				((JFrame) getTopLevelAncestor()).validate();
				panelSiguiente.PrefsClassBuilder();
			}
		});
		
		JLabel lblCorreo = new JLabel("Enviar un correo al admin:");
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCorreo.setBounds(48, 153, 181, 22);
		add(lblCorreo);
		
		JButton btnCorreo = new JButton("Enviar feedback");
		btnCorreo.setBounds(239, 155, 143, 23);
		add(btnCorreo);
		//Éste botón te envia a la pestaña de enviar correo al admin.
		btnCorreo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FeedbackClass panelSiguiente = new FeedbackClass();
				setVisible(false);
				((JFrame) getTopLevelAncestor()).add(panelSiguiente);
				((JFrame) getTopLevelAncestor()).validate();
				panelSiguiente.FeedbackClassBuilder();
			}
		});

		JButton btnCerrarSesion = new JButton("Cerrar sesión");
		btnCerrarSesion.setBounds(10, 227, 124, 23);
		add(btnCerrarSesion);
		/*
		 * 		Éste botón cierra la sesión y devuelve al usuario a la pestaña de Login.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			logout(): Borra las variables del usuario y correo que han iniciado sesión.
		 */
		btnCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showOptionDialog(null, "¿Desea cerrar sesion?" , "Cerrar Sesion", JOptionPane.YES_NO_CANCEL_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, new String[]{"Sí", "No"}, "");
				if(option==0) {
					Functions.logout();
					LoginClass panelSiguiente = new LoginClass();
					setVisible(false);
					((JFrame) getTopLevelAncestor()).add(panelSiguiente);
					((JFrame) getTopLevelAncestor()).validate();
					panelSiguiente.LoginClassBuilder();
				}
			}
		});
		
		JLabel lblBienvenida = new JLabel("Bienvenido, ");
		lblBienvenida.setFont(new Font("Times New Roman", Font.ITALIC, 30));
		lblBienvenida.setBounds(80, 35, 158, 29);
		add(lblBienvenida);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBounds(333, 0, 101, 22);
		add(menuBar_1);
		
		JMenu mnNewMenu_1 = new JMenu("?");
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		menuBar_1.add(mnNewMenu_1);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mntmAcercaDe.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		mnNewMenu_1.add(mntmAcercaDe);
		mntmAcercaDe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Versión: 1.0.\n"
						+ "Autor: Daniel González Sanchís\n"
						+ "Fecha: 24 / 11 / 2023", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		//Éste label se actualiza automáticamente con el usuario que ha iniciado sesión actualmente.
		JLabel lblUsuario = new JLabel(Functions.currentUser);
		lblUsuario.setFont(new Font("Times New Roman", Font.ITALIC, 30));
		lblUsuario.setBounds(224, 35, 181, 29);
		add(lblUsuario);
	}catch(Exception e) {
		JOptionPane.showMessageDialog(null, "Se ha producido un error inesperado.\n"
				+ "La aplicacion se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
		System.exit(10);
	}
	}
	/*
	 * 		> alreadyPreferencedlabel() se usa para comprobar si el usuario ya ha configurado sus preferencias
	 * 		  cambiar el nombre del label.
	 * 
	 * 		  Llamada por:
	 * 			
	 * 			- MenuClassBuilder()
	 */
	public static String alreadyPreferencedlabel() {
		if(Functions.alreadyPreferenced()==false) {
			return "Comprobar preferencias:";
		}
		return "Edita las preferencias aquí:";
	}
	/*
	 * 		> alreadyPreferencedbutton() se usa para comprobar si el usuario ya ha configurado sus preferencias
	 * 		  cambiar el nombre del botón.
	 * 
	 * 		  Llamada por:
	 * 			
	 * 			- MenuClassBuilder()
	 */
	public static String alreadyPreferencedbutton() {
		if(Functions.alreadyPreferenced()==false) {
			return "Comprobar";
		}
		return "Modificar";
	}
	
}
