package newsSender;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class LoginClass extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField passwordField;
	private int pity = 0;
	private boolean hide = true;
	
	public void LoginClassBuilder() {
		try {
		setBounds(0, 0, 434, 261);
		setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Times New Roman", Font.ITALIC, 30));
		lblLogin.setBounds(169, 31, 154, 40);
		add(lblLogin);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsuario.setBounds(37, 106, 74, 14);
		add(lblUsuario);
		
		textField = new JTextField();
		textField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textField.setBounds(142, 105, 240, 20);
		add(textField);
		textField.setColumns(10);
		//Ésta función asegura que lo que se está introduciendo en el campo de texto no sobrepase los 30 carácteres
		textField.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (textField.getText().length() >= 30 ) {
		            e.consume(); 
		    	}
		    }
		});
		
		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblContrasena.setBounds(37, 160, 99, 14);
		add(lblContrasena);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		passwordField.setBounds(142, 159, 205, 20);
		add(passwordField);
		//Ésta función asegura que lo que se está introduciendo en el campo de texto no sobrepase los 30 carácteres.
		passwordField.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (passwordField.getText().length() >= 30 ) {
		            e.consume(); 
		    	}
		    }
		});

		JButton lblEye = new JButton();
		lblEye.setIcon(new ImageIcon(new ImageIcon("src\\resources\\eyetrue.png").getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
		lblEye.setBounds(357, 158, 25, 23);
		lblEye.setFont(new Font("", Font.PLAIN, 9));
		add(lblEye);
		//El botón del ojo cambiará los carácteres del passwordField si el parámetro hide está en true o false.
		lblEye.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(hide==true) {
					passwordField.setEchoChar((char)0);	
					lblEye.setIcon(new ImageIcon(new ImageIcon("src\\resources\\eyefalse.png").getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
					hide = false;
				}else {
					passwordField.setEchoChar('•');	
					lblEye.setIcon(new ImageIcon(new ImageIcon("src\\resources\\eyetrue.png").getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
					hide = true;
				}
				
			}
			
		});
		
		JButton btnAcceso = new JButton("Acceder");
		btnAcceso.setBounds(335, 227, 89, 23);
		add(btnAcceso);
		/*
		 * 		El botón Acceder comprobará si el usuario y la contraseña son correctos, así como si el usuario que se está introduciendo 
		 * 		es ADMIN.
		 * 
		 *  	> Llama a métodos de la clase FUNCTIONS:
		 *  
		 *			login(): Comprueba si el usuario y la contraseña existen y si el usuario que está iniciando sesión es ADMIN.
		 */
		btnAcceso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//El valor pity incrementa con la cantidad de intentos fallidos. Si llega a 5, el programa se cierra automáticamente.
				if(pity==5) {
					JOptionPane.showMessageDialog(null, "Demasiados intentos erroneos.\n"
							+ "La aplicacion se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
					System.exit(2);
				}
				//Introduce el parámetro que está recibiendo del método login().
				int x = Functions.login(textField.getText(), passwordField.getText());
				switch (x) {
				//En caso de que el usuario y contraseña sea el correcto:
					case 1:
						JOptionPane.showMessageDialog(null, "Bienvenido, " + textField.getText(), "Login Success", JOptionPane.INFORMATION_MESSAGE);
						MenuClass panelSiguiente1 = new MenuClass();
						setVisible(false);
						((JFrame) getTopLevelAncestor()).add(panelSiguiente1);
						((JFrame) getTopLevelAncestor()).validate();
						panelSiguiente1.MenuClassBuilder();
					break;
				//En el caso adiccional de que el usuario que esté entrando sea ADMIN.
					case 2:
						JOptionPane.showMessageDialog(null, "Bienvenido, " + textField.getText() + ". Acceso permitido como ADMIN. \n"
								+ "Se han desbloqueado las funciones de desarrollador.", "Login Success", JOptionPane.WARNING_MESSAGE);
						AdminMenuClass panelSiguiente2 = new AdminMenuClass();
						setVisible(false);
						((JFrame) getTopLevelAncestor()).add(panelSiguiente2);
						((JFrame) getTopLevelAncestor()).validate();
						panelSiguiente2.AdminMenuClassBuilder();
					break;
				//En el caso de que el usuario y contraseña no coincidan. Se incrementa pity con el número de intentos fallidos.
					case 0:
						JOptionPane.showMessageDialog(null, "El usuario o la contraseña son incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
						pity++;
					break;
				//En caso de error.
					default:
						JOptionPane.showMessageDialog(null, "Se ha producido un error inesperado.\n"
								+ "La aplicacion se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
						System.exit(3);
					break;
				}
			}
		});
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(10, 227, 89, 23);
		add(btnSalir);
		//El botón salir actúa igual que la cruz, solo te saca de la aplicación.
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showOptionDialog(null, "¿Está seguro de que desea salir?" , "Salir", JOptionPane.YES_NO_CANCEL_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, new String[]{"Sí", "No"}, "");
				if(option==0) {
					System.exit(0);
				}
			}
		});
		
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
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Se ha producido un error inesperado.\n"
					+ "La aplicacion se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(10);
		}
		
	}
	
}
