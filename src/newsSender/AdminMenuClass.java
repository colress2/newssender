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

public class AdminMenuClass extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public void AdminMenuClassBuilder() {
		try {
		setBounds(0, 0, 434, 261);
		setLayout(null);
		
		JLabel lblPreferencias = new JLabel("Probar las preferencias:");
		lblPreferencias.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPreferencias.setBounds(46, 75, 181, 22);
		add(lblPreferencias);
		
		JButton btnPreferencias = new JButton("Probar preferencias");
		btnPreferencias.setBounds(222, 77, 171, 23);
		add(btnPreferencias);
		//Boton para llevar al ADMIN a la ventana con los guiones de las páginas.
		btnPreferencias.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminProbarNoticiasClass panelSiguiente = new AdminProbarNoticiasClass();
				setVisible(false);
				((JFrame) getTopLevelAncestor()).add(panelSiguiente);
				((JFrame) getTopLevelAncestor()).validate();
				panelSiguiente.AdminProbarNoticiasClassBuilder();
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
		
		JLabel lblBienvenido = new JLabel("Bienvenido, ");
		lblBienvenido.setFont(new Font("Times New Roman", Font.ITALIC, 30));
		lblBienvenido.setBounds(80, 35, 158, 29);
		add(lblBienvenido);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(333, 0, 101, 22);
		add(menuBar);
		
		JMenu mnMewMenu = new JMenu("?");
		mnMewMenu.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		menuBar.add(mnMewMenu);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mntmAcercaDe.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		mnMewMenu.add(mntmAcercaDe);
		mntmAcercaDe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Versión: 1.0.\n"
						+ "Autor: Daniel González Sanchís\n"
						+ "Fecha: 24 / 11 / 2023", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		//Label que se autoactualiza con el usuario que ha iniciado sesión.
		JLabel lblUsuario = new JLabel(Functions.currentUser);
		lblUsuario.setFont(new Font("Times New Roman", Font.ITALIC, 30));
		lblUsuario.setBounds(224, 35, 181, 29);
		add(lblUsuario);
		
		JLabel lblAjustarHora = new JLabel("Ajustar la hora:");
		lblAjustarHora.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAjustarHora.setBounds(46, 108, 181, 22);
		add(lblAjustarHora);
		
		JButton btnAjustarHora = new JButton("Ajustar hora");
		btnAjustarHora.setBounds(222, 110, 171, 23);
		add(btnAjustarHora);
		//Botón para llevar a la pestaña de ajustar la hora.
		btnAjustarHora.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminAjustarHoraClass panelSiguiente = new AdminAjustarHoraClass();
				setVisible(false);
				((JFrame) getTopLevelAncestor()).add(panelSiguiente);
				((JFrame) getTopLevelAncestor()).validate();
				panelSiguiente.AdminAjustarHoraClassBuilder();
			}
		});
		
		JLabel lblGestionarUsuarios = new JLabel("Gestionar usuarios:");
		lblGestionarUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGestionarUsuarios.setBounds(46, 141, 181, 22);
		add(lblGestionarUsuarios);
		
		JButton btnGestionarUsuarios = new JButton("Gestionar usuarios");
		btnGestionarUsuarios.setBounds(222, 143, 171, 23);
		add(btnGestionarUsuarios);
		//Botón para llevar a la pestaña de la gestión de usuarios.
		btnGestionarUsuarios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminGestionarUsuariosClass panelSiguiente = new AdminGestionarUsuariosClass();
				setVisible(false);
				((JFrame) getTopLevelAncestor()).add(panelSiguiente);
				((JFrame) getTopLevelAncestor()).validate();
				panelSiguiente.AdminGestionarUsuariosClassBuilder();
			}
		});
		JLabel lblGestionarPreferencias = new JLabel("Gestionar preferencias:");
		lblGestionarPreferencias.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGestionarPreferencias.setBounds(46, 174, 181, 22);
		add(lblGestionarPreferencias);
		
		JButton btnGestionarPreferencias = new JButton("Gestionar preferencias");
		btnGestionarPreferencias.setBounds(222, 176, 171, 23);
		add(btnGestionarPreferencias);
		//Botón para llevar a la pestaña de gestionar preferencias.
		btnGestionarPreferencias.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminGestionarPreferenciasClass panelSiguiente = new AdminGestionarPreferenciasClass();
				setVisible(false);
				((JFrame) getTopLevelAncestor()).add(panelSiguiente);
				((JFrame) getTopLevelAncestor()).validate();
				panelSiguiente.AdminGestionarPreferenciasClassBuilder();
			}
		});
		
		JLabel lblADMIN = new JLabel("Modo ADMIN");
		lblADMIN.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblADMIN.setBounds(10, 11, 78, 14);
		add(lblADMIN);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Se ha producido un error inesperado.\n"
					+ "La aplicacion se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(10);
		}
	}
}
