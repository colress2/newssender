package newsSender;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

public class AdminAjustarHoraClass extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JTextField textFieldHora;
	private JTextField textFieldMinutos;
	
	public void AdminAjustarHoraClassBuilder() {
		try {
		setBounds(0, 0, 434, 261);
		setLayout(null);
		
		JLabel lblADMIN = new JLabel("Modo ADMIN");
		lblADMIN.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblADMIN.setBounds(10, 11, 78, 14);
		add(lblADMIN);
		
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
		//Este botón devuelve al usuario al menu principal.
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
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(302, 227, 122, 23);
		add(btnAceptar);
		/*
		 * 		El botón Aceptar enviará la hora y los minutos introducidos en los campos de texto
		 * 		en los objetos de la aplicación y en el archivo linksyhora.txt, actualizando así la
		 * 		hora.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			ajustarHora(): Actualiza la hora en los objetos y el archivo de texto.
		 */
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean bucle = true;
				int progress = 0;
				while(bucle==true) {	
					switch(progress) {
					//Si los campos están vacíos, la hora no se actualiza.
						case 0:
							if(textFieldHora.getText().equals("")||textFieldMinutos.getText().equals("")) {
								JOptionPane.showMessageDialog(null, "No se ha introducido ningúna hora.", "Error", JOptionPane.ERROR_MESSAGE);
								bucle = false;
							}
							progress++;
						break;
					//Si la hora introducida excede de un número de hora normal, la hora no se actualiza.
						case 1:
							int hora = Integer.parseInt(textFieldHora.getText());
							int minutos = Integer.parseInt(textFieldMinutos.getText());
							if(hora>=24||minutos>=60) {
								JOptionPane.showMessageDialog(null, "No se ha introducido un formato de hora valido.", "Error", JOptionPane.ERROR_MESSAGE);
								bucle = false;
							}
							progress++;
						break;
					//Si solo se ha introducido 1 carácter en ambos campos, la hora no se actualiza.
						case 2:
							if (textFieldHora.getText().length() == 1 || textFieldMinutos.getText().length() == 1) {// limit textfield to 2 characters
								JOptionPane.showMessageDialog(null, "Por favor introduzca un 0 delante de un carácter solitario.", "Error", JOptionPane.ERROR_MESSAGE);
								bucle = false;
					    	}
							progress++;
						break;
					//Actualizamos la hora de la aplicación.
						case 3:
							if(Functions.ajustarHora(textFieldHora.getText(), textFieldMinutos.getText())==false) {
								JOptionPane.showMessageDialog(null, "Se ha producido un error inesperado.\n"
										+ "La aplicacion se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
								System.exit(6);
							}else{
								bucle = false;
								JOptionPane.showMessageDialog(null, "La hora se ha configurado correctamente.", "Success", JOptionPane.INFORMATION_MESSAGE);
							}
						break;
						default:
							JOptionPane.showMessageDialog(null, "Se ha producido un error inesperado.\n"
									+ "La aplicacion se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
							System.exit(4);
						break;
					}
				}
			}
		});
		
		textFieldHora = new JTextField(Functions.listaURLs.gethora());
		textFieldHora.setColumns(10);
		textFieldHora.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textFieldHora.setBounds(106, 145, 92, 29);
		add(textFieldHora);
		//Ésta función asegura que lo que se está introduciendo en el campo de texto no sobrepase los 2 carácteres ni que lo que se introduzca sea algo distinto a un número.
		textFieldHora.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (textFieldHora.getText().length() >= 2 ) {
		            e.consume(); 
		    	}
		        char c = e.getKeyChar();
		        if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
		            e.consume();
		        }
		    }  
		});
		
		JLabel lbldospuntos = new JLabel(":");
		lbldospuntos.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lbldospuntos.setBounds(205, 137, 23, 37);
		add(lbldospuntos);
		
		textFieldMinutos = new JTextField(Functions.listaURLs.getminutos());
		textFieldMinutos.setColumns(10);
		textFieldMinutos.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textFieldMinutos.setBounds(225, 145, 92, 29);
		add(textFieldMinutos);
		//Ésta función asegura que lo que se está introduciendo en el campo de texto no sobrepase los 2 carácteres ni que lo que se introduzca sea algo distinto a un número.
		textFieldMinutos.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (textFieldMinutos.getText().length() >= 2 ) {
		            e.consume(); 
		        }
		        char c = e.getKeyChar();
		        if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
		            e.consume();
		        }
		    }  
		});
		
		JLabel lblIntroducirHora = new JLabel("Introduce la hora (hh : mm) :");
		lblIntroducirHora.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIntroducirHora.setBounds(106, 112, 211, 22);
		add(lblIntroducirHora);
		
		JLabel lblinfo1 = new JLabel("La hora decidirá en que momento los usuarios");
		lblinfo1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblinfo1.setBounds(70, 53, 304, 22);
		add(lblinfo1);
		
		JLabel lblinfo2 = new JLabel("recibirán sus noticias preferidas.");
		lblinfo2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblinfo2.setBounds(70, 72, 304, 22);
		add(lblinfo2);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Se ha producido un error inesperado.\n"
					+ "La aplicacion se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(10);
		}
	}
	
}
