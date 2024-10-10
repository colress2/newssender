package newsSender;

import java.awt.Font;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AdminProbarNoticiasClass extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	public void AdminProbarNoticiasClassBuilder() {
		try {
		setBounds(0, 0, 434, 261);
		setLayout(null);
		/*
		 * 		Los label de éste panel recogen sus guiones utilizando los metodos getGuiones() para
		 * 		recoger los guiones de las páginas que se les pasen de listaURLs, para mostrarlo de
		 * 		manera visual al usuario.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			getGuiones(): Devuelve el guión de la página web que se le pase.
		 */
		JPanel panel_deporte = new JPanel();
		panel_deporte.setBounds(10, 64, 414, 152);
		add(panel_deporte);
		panel_deporte.setLayout(null);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMarca.setBounds(0, 0, 148, 22);
		panel_deporte.add(lblMarca);
		
		JLabel lblMundoDeportivo = new JLabel("MundoDeportivo:");
		lblMundoDeportivo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMundoDeportivo.setBounds(0, 66, 148, 22);
		panel_deporte.add(lblMundoDeportivo);
		
		JLabel lblElPaisDeporte = new JLabel("ElPais:");
		lblElPaisDeporte.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblElPaisDeporte.setBounds(0, 99, 148, 22);
		panel_deporte.add(lblElPaisDeporte);
		
		JLabel lblDiarioAS = new JLabel("DiarioAS:");
		lblDiarioAS.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDiarioAS.setBounds(0, 33, 148, 22);
		panel_deporte.add(lblDiarioAS);
		
		JLabel lblGuionMarca = new JLabel(Functions.getGuiones(Functions.listaURLs.getnDeporteMarca()));
		lblGuionMarca.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGuionMarca.setBounds(0, 16, 414, 22);
		panel_deporte.add(lblGuionMarca);
		
		JLabel lblGuionDiarioAS = new JLabel(Functions.getGuiones(Functions.listaURLs.getnDeporteDiaroAS()));
		lblGuionDiarioAS.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGuionDiarioAS.setBounds(0, 49, 414, 22);
		panel_deporte.add(lblGuionDiarioAS);
		
		JLabel lblGuionMundoDeportivo = new JLabel(Functions.getGuiones(Functions.listaURLs.getnDeporteMundoDeportivo()));
		lblGuionMundoDeportivo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGuionMundoDeportivo.setBounds(0, 82, 414, 22);
		panel_deporte.add(lblGuionMundoDeportivo);
		
		JLabel lblGuionElPaisDeporte = new JLabel(Functions.getGuiones(Functions.listaURLs.getnDeporteElPais()));
		lblGuionElPaisDeporte.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGuionElPaisDeporte.setBounds(0, 115, 414, 22);
		panel_deporte.add(lblGuionElPaisDeporte);
		
		JPanel panel_internacional = new JPanel();
		panel_internacional.setBounds(10, 64, 414, 152);
		add(panel_internacional);
		panel_internacional.setLayout(null);
		
		JLabel lblElMundo = new JLabel("El Mundo:");
		lblElMundo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblElMundo.setBounds(0, 0, 148, 22);
		panel_internacional.add(lblElMundo);
		
		JLabel lblGuionElMundo = new JLabel(Functions.getGuiones(Functions.listaURLs.getnInternacionalElMundo()));
		lblGuionElMundo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGuionElMundo.setBounds(0, 16, 414, 22);
		panel_internacional.add(lblGuionElMundo);
		
		JLabel lblBBC = new JLabel("BBC:");
		lblBBC.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBBC.setBounds(0, 33, 148, 22);
		panel_internacional.add(lblBBC);
		
		JLabel lblGuionBBC = new JLabel(Functions.getGuiones(Functions.listaURLs.getnInternacionalBBC()));
		lblGuionBBC.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGuionBBC.setBounds(0, 49, 414, 22);
		panel_internacional.add(lblGuionBBC);
		
		JLabel lblEuroNews = new JLabel("EuroNews:");
		lblEuroNews.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEuroNews.setBounds(0, 66, 148, 22);
		panel_internacional.add(lblEuroNews);
		
		JLabel lblGuionEuroNews = new JLabel(Functions.getGuiones(Functions.listaURLs.getnInternacionalEuroNews()));
		lblGuionEuroNews.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGuionEuroNews.setBounds(0, 82, 414, 22);
		panel_internacional.add(lblGuionEuroNews);
		
		JLabel lblElPaisInternacional = new JLabel("ElPais:");
		lblElPaisInternacional.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblElPaisInternacional.setBounds(0, 99, 148, 22);
		panel_internacional.add(lblElPaisInternacional);
		
		JLabel lblGuionElPaisInternacional = new JLabel(Functions.getGuiones(Functions.listaURLs.getnInternacionalElPais()));
		lblGuionElPaisInternacional.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGuionElPaisInternacional.setBounds(0, 115, 414, 22);
		panel_internacional.add(lblGuionElPaisInternacional);
		
		JPanel panel_nacional = new JPanel();
		panel_nacional.setBounds(10, 64, 414, 152);
		add(panel_nacional);
		panel_nacional.setLayout(null);
		
		JLabel lblLaRazon = new JLabel("La Razon:");
		lblLaRazon.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLaRazon.setBounds(0, 0, 148, 22);
		panel_nacional.add(lblLaRazon);
		
		JLabel lblGuionLaRazon = new JLabel(Functions.getGuiones(Functions.listaURLs.getnNacionalLaRazon()));
		lblGuionLaRazon.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGuionLaRazon.setBounds(0, 16, 414, 22);
		panel_nacional.add(lblGuionLaRazon);
		
		JLabel lblABC = new JLabel("ABC:");
		lblABC.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblABC.setBounds(0, 33, 148, 22);
		panel_nacional.add(lblABC);
		
		JLabel lblGuionABC = new JLabel(Functions.getGuiones(Functions.listaURLs.getnNacionalABC()));
		lblGuionABC.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGuionABC.setBounds(0, 49, 414, 22);
		panel_nacional.add(lblGuionABC);
		
		JLabel lbl20Minutos = new JLabel("20 Minutos:");
		lbl20Minutos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl20Minutos.setBounds(0, 66, 148, 22);
		panel_nacional.add(lbl20Minutos);
		
		JLabel lblGuion20Minutos = new JLabel(Functions.getGuiones(Functions.listaURLs.getnNacional20Minutos()));
		lblGuion20Minutos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGuion20Minutos.setBounds(0, 82, 414, 22);
		panel_nacional.add(lblGuion20Minutos);
		
		JLabel lblElPaisNacional = new JLabel("ElPais:");
		lblElPaisNacional.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblElPaisNacional.setBounds(0, 99, 148, 22);
		panel_nacional.add(lblElPaisNacional);
		
		JLabel lblGuionElPaisNacional = new JLabel(Functions.getGuiones(Functions.listaURLs.getnNacionalElPais()));
		lblGuionElPaisNacional.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGuionElPaisNacional.setBounds(0, 115, 414, 22);
		panel_nacional.add(lblGuionElPaisNacional);
		
		JPanel panel_economia = new JPanel();
		panel_economia.setBounds(10, 64, 414, 152);
		add(panel_economia);
		panel_economia.setLayout(null);
		
		JLabel lblElEconomista = new JLabel("El Economista:");
		lblElEconomista.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblElEconomista.setBounds(0, 0, 148, 22);
		panel_economia.add(lblElEconomista);
		
		JLabel lblGuionElEconomista = new JLabel(Functions.getGuiones(Functions.listaURLs.getnEconomiaElEconomista()));
		lblGuionElEconomista.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGuionElEconomista.setBounds(0, 16, 414, 22);
		panel_economia.add(lblGuionElEconomista);
		
		JLabel lblElConfidencial = new JLabel("El Confidencial:");
		lblElConfidencial.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblElConfidencial.setBounds(0, 33, 148, 22);
		panel_economia.add(lblElConfidencial);
		
		JLabel lblGuionElConfidencial = new JLabel(Functions.getGuiones(Functions.listaURLs.getnEconomiaElConfidencial()));
		lblGuionElConfidencial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGuionElConfidencial.setBounds(0, 49, 414, 22);
		panel_economia.add(lblGuionElConfidencial);
		
		JLabel lblExpansion = new JLabel("Expansion:");
		lblExpansion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExpansion.setBounds(0, 66, 148, 22);
		panel_economia.add(lblExpansion);
		
		JLabel lblGuionExpansion = new JLabel(Functions.getGuiones(Functions.listaURLs.getnEconomiaExpansion()));
		lblGuionExpansion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGuionExpansion.setBounds(0, 82, 414, 22);
		panel_economia.add(lblGuionExpansion);
		
		JLabel lblElPaisEconomia = new JLabel("ElPais:");
		lblElPaisEconomia.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblElPaisEconomia.setBounds(0, 99, 148, 22);
		panel_economia.add(lblElPaisEconomia);
		
		JLabel lblGuionElPaisEconomia = new JLabel(Functions.getGuiones(Functions.listaURLs.getnEconomiaElPais()));
		lblGuionElPaisEconomia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGuionElPaisEconomia.setBounds(0, 115, 414, 22);
		panel_economia.add(lblGuionElPaisEconomia);
		
		JLabel lblADMIN = new JLabel("Modo ADMIN");
		lblADMIN.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblADMIN.setBounds(10, 11, 71, 14);
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
		/*
		 * 		Éste botón se usa para enviar un correo al admin con todos los guiones de todas
		 * 		las páginas web.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			adminSendNews(): Similar a newsSender(), solo que se utiliza únicamente
		 * 			por el ADMIN y envía un correo a éste con todos los guiones de las páginas.
		 */
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(302, 227, 122, 23);
		add(btnEnviar);
		btnEnviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = 0;
				//Mensaje de confirmación al usuario.
				option = JOptionPane.showOptionDialog(null, "Esto enviara un correo con todas las noticias a su correo. ¿Seguro? " , "Confirmacion", JOptionPane.YES_NO_CANCEL_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, new String[]{"Sí", "No"}, "");
				if(option==0) {
					//Si el método falla, tira un error por interfaz.
					if(Functions.adminSendNews()==false) {
						JOptionPane.showMessageDialog(null, "Ha ocurrido un error intentando enviar el correo.\n"
								+ "Compruebe que su correo actual exista.\n"
								+ "Intentelo de nuevo mas tarde.", "Error", JOptionPane.ERROR_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "El correo ha sido enviado.", "Enviado", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		
		JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(4);
		String[] list = {"Deportes", "Economia", "Nacional", "Internacional"};
		comboBox.setModel(new DefaultComboBoxModel(list));
		comboBox.setBounds(216, 33, 177, 22);
		add(comboBox);
		//La acción del combobox para que dependiendo de lo que se seleccione se muestre ese panel.
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String option = (String)comboBox.getSelectedItem();
				switch(option) {
				case "Deportes":
					panel_deporte.setVisible(true);
					panel_economia.setVisible(false);
					panel_nacional.setVisible(false);
					panel_internacional.setVisible(false);
				break;
				case "Economia":
					panel_economia.setVisible(true);
					panel_nacional.setVisible(false);
					panel_internacional.setVisible(false);
					panel_deporte.setVisible(false);
				break;
				case "Nacional":
					panel_nacional.setVisible(true);
					panel_economia.setVisible(false);
					panel_internacional.setVisible(false);
					panel_deporte.setVisible(false);
				break;
				case "Internacional":
					panel_internacional.setVisible(true);
					panel_economia.setVisible(false);
					panel_nacional.setVisible(false);
					panel_deporte.setVisible(false);
				break;
				default:
					JOptionPane.showMessageDialog(null, "Se ha producido un error inesperado.\n"
							+ "La aplicacion se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
					System.exit(5);
				break;
				}
			}
		});
		
		JLabel lblSeleccionarCategoria = new JLabel("Selecciona la categoría:");
		lblSeleccionarCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeleccionarCategoria.setBounds(63, 31, 212, 22);
		add(lblSeleccionarCategoria);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Se ha producido un error inesperado.\n"
					+ "La aplicacion se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(10);
		}
		
	}

}