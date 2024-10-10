package newsSender;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PrefsClass extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JCheckBox chckbxElMundo;
	private JCheckBox chckbxBBC;
	private JCheckBox chckbxEuroNews;
	private JCheckBox chckbxElPaisI;
	private JCheckBox chckbxLaRazon;
	private JCheckBox chckbxABC;
	private JCheckBox chckbx20Minutos;
	private JCheckBox chckbxElPaisN;
	private JCheckBox chckbxElEconomista;
	private JCheckBox chckbxElConfidencial;
	private JCheckBox chckbxExpansion;
	private JCheckBox chckbxElPaisE;
	private JCheckBox chckbxMarca;
	private JCheckBox chckbxDiarioAS;
	private JCheckBox chckbxMundoDeportivo;
	private JCheckBox chckbxElPaisD;
	
	public void PrefsClassBuilder() {
		try {
		setBounds(0, 0, 434, 261);
		setLayout(null);
		
		JPanel panelPreferencias = new JPanel();
		panelPreferencias.setBounds(208, 65, 199, 145);
		panelPreferencias.setLayout(null);
		add(panelPreferencias);
		
		JPanel panel_deporte = new JPanel();
		panel_deporte.setLayout(null);
		panel_deporte.setBounds(0, 0, 199, 145);
		panelPreferencias.add(panel_deporte);
		/*
		 * 		Los checkboxes de este panel se marcan dependiendo del usuario
		 * 		que haya iniciado sesión actualmente en la aplicación. Además de
		 * 		ello, se pasa la casilla de la checkbox correspondiente a su
		 * 		página web. Si el usuario ya ha establecido sus preferencias, las
		 * 		checkboxes de deshabilitan, pero se pueden serguir observando cuales
		 * 		están marcadas y cuáles no.
		 * 
		 * 		> Llama a métodos de la clase FUNCTIONS:
		 * 
		 * 			checkcheck(): Comprueba si la checkbox ya se marcó por el usuario,
		 * 			pasandole el nombre del usuario que la marcó y la pagina correspondiente.
		 * 			alreadyPreferenced(): Comprueba si el usuario ya ha configurado sus
		 * 			preferencias, si es asi tira true, en caso contrario tira false.
		 * 		
		 */
		chckbxMarca = new JCheckBox("Marca");
		chckbxMarca.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxMarca.setBounds(6, 7, 97, 23);
		chckbxMarca.setSelected(Functions.checkcheck(Functions.currentUser, 1));
		chckbxMarca.setEnabled(Functions.alreadyPreferenced());
		panel_deporte.add(chckbxMarca);
		
		chckbxDiarioAS = new JCheckBox("DiarioAS");
		chckbxDiarioAS.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxDiarioAS.setBounds(6, 43, 97, 23);
		chckbxDiarioAS.setSelected(Functions.checkcheck(Functions.currentUser, 2));
		chckbxDiarioAS.setEnabled(Functions.alreadyPreferenced());
		panel_deporte.add(chckbxDiarioAS);
		
		chckbxMundoDeportivo = new JCheckBox("MundoDeportivo");
		chckbxMundoDeportivo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxMundoDeportivo.setBounds(6, 79, 142, 23);
		chckbxMundoDeportivo.setSelected(Functions.checkcheck(Functions.currentUser, 3));
		chckbxMundoDeportivo.setEnabled(Functions.alreadyPreferenced());
		panel_deporte.add(chckbxMundoDeportivo);
		
		chckbxElPaisD = new JCheckBox("El Pais");
		chckbxElPaisD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxElPaisD.setBounds(6, 115, 97, 23);
		chckbxElPaisD.setSelected(Functions.checkcheck(Functions.currentUser, 4));
		chckbxElPaisD.setEnabled(Functions.alreadyPreferenced());
		panel_deporte.add(chckbxElPaisD);
		
		JPanel panel_economia = new JPanel();
		panel_economia.setLayout(null);
		panel_economia.setBounds(0, 0, 199, 145);
		panelPreferencias.add(panel_economia);
		panel_economia.setVisible(false);
		
		chckbxElEconomista = new JCheckBox("El Economista");
		chckbxElEconomista.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxElEconomista.setBounds(6, 7, 129, 23);
		chckbxElEconomista.setSelected(Functions.checkcheck(Functions.currentUser, 5));
		chckbxElEconomista.setEnabled(Functions.alreadyPreferenced());
		panel_economia.add(chckbxElEconomista);
	
		chckbxElConfidencial = new JCheckBox("El Confidencial");
		chckbxElConfidencial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxElConfidencial.setBounds(6, 43, 129, 23);
		chckbxElConfidencial.setSelected(Functions.checkcheck(Functions.currentUser, 6));
		chckbxElConfidencial.setEnabled(Functions.alreadyPreferenced());
		panel_economia.add(chckbxElConfidencial);
		
		chckbxExpansion = new JCheckBox("Expansion");
		chckbxExpansion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxExpansion.setBounds(6, 79, 97, 23);
		chckbxExpansion.setSelected(Functions.checkcheck(Functions.currentUser, 7));
		chckbxExpansion.setEnabled(Functions.alreadyPreferenced());
		panel_economia.add(chckbxExpansion);
		
		chckbxElPaisE = new JCheckBox("El Pais");
		chckbxElPaisE.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxElPaisE.setBounds(6, 115, 97, 23);
		chckbxElPaisE.setSelected(Functions.checkcheck(Functions.currentUser, 8));
		chckbxElPaisE.setEnabled(Functions.alreadyPreferenced());
		panel_economia.add(chckbxElPaisE);
		
		JPanel panel_nacional = new JPanel();
		panel_nacional.setLayout(null);
		panel_nacional.setBounds(0, 0, 199, 145);
		panelPreferencias.add(panel_nacional);
		panel_nacional.setVisible(false);
		
		chckbxLaRazon = new JCheckBox("La Razon");
		chckbxLaRazon.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxLaRazon.setBounds(6, 7, 97, 23);
		chckbxLaRazon.setSelected(Functions.checkcheck(Functions.currentUser, 9));
		chckbxLaRazon.setEnabled(Functions.alreadyPreferenced());
		panel_nacional.add(chckbxLaRazon);
		
		chckbxABC = new JCheckBox("ABC");
		chckbxABC.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxABC.setBounds(6, 43, 97, 23);
		chckbxABC.setSelected(Functions.checkcheck(Functions.currentUser, 10));
		chckbxABC.setEnabled(Functions.alreadyPreferenced());
		panel_nacional.add(chckbxABC);
		
		chckbx20Minutos = new JCheckBox("20 Minutos");
		chckbx20Minutos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbx20Minutos.setBounds(6, 79, 97, 23);
		chckbx20Minutos.setSelected(Functions.checkcheck(Functions.currentUser, 11));
		chckbx20Minutos.setEnabled(Functions.alreadyPreferenced());
		panel_nacional.add(chckbx20Minutos);
		
		chckbxElPaisN = new JCheckBox("El Pais");
		chckbxElPaisN.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxElPaisN.setBounds(6, 115, 97, 23);
		chckbxElPaisN.setSelected(Functions.checkcheck(Functions.currentUser, 12));
		chckbxElPaisN.setEnabled(Functions.alreadyPreferenced());
		panel_nacional.add(chckbxElPaisN);
		
		JPanel panel_internacional = new JPanel();
		panel_internacional.setLayout(null);
		panel_internacional.setBounds(0, 0, 199, 145);
		panelPreferencias.add(panel_internacional);
		panel_internacional.setVisible(false);
		
		chckbxElMundo = new JCheckBox("El Mundo");
		chckbxElMundo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxElMundo.setBounds(6, 7, 97, 23);
		chckbxElMundo.setSelected(Functions.checkcheck(Functions.currentUser, 13));
		chckbxElMundo.setEnabled(Functions.alreadyPreferenced());
		panel_internacional.add(chckbxElMundo);
		
		chckbxBBC = new JCheckBox("BBC");
		chckbxBBC.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxBBC.setBounds(6, 43, 97, 23);
		chckbxBBC.setSelected(Functions.checkcheck(Functions.currentUser, 14));
		chckbxBBC.setEnabled(Functions.alreadyPreferenced());
		panel_internacional.add(chckbxBBC);
		
		chckbxEuroNews = new JCheckBox("EuroNews");
		chckbxEuroNews.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxEuroNews.setBounds(6, 79, 97, 23);
		chckbxEuroNews.setSelected(Functions.checkcheck(Functions.currentUser, 15));
		chckbxEuroNews.setEnabled(Functions.alreadyPreferenced());
		panel_internacional.add(chckbxEuroNews);
		
		chckbxElPaisI = new JCheckBox("El Pais");
		chckbxElPaisI.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxElPaisI.setBounds(6, 115, 97, 23);
		chckbxElPaisI.setSelected(Functions.checkcheck(Functions.currentUser, 16));
		chckbxElPaisI.setEnabled(Functions.alreadyPreferenced());
		panel_internacional.add(chckbxElPaisI);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(4);
		String[] list = {"Deportes", "Economia", "Nacional", "Internacional"};
		comboBox.setModel(new DefaultComboBoxModel(list));
		comboBox.setBounds(21, 65, 177, 22);
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
		
		JLabel lblCategoria = new JLabel("Selecciona la categoría:");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCategoria.setBounds(21, 34, 386, 22);
		add(lblCategoria);
		
		JLabel lblPaginaWeb = new JLabel("Selecciona una página web:");
		lblPaginaWeb.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPaginaWeb.setBounds(208, 34, 177, 22);
		add(lblPaginaWeb);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(10, 227, 122, 23);
		add(btnVolver);
		//El botón para volver a la pestaña anterior de la aplicación.
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuClass panelSiguiente = new MenuClass();
				setVisible(false);
				((JFrame) getTopLevelAncestor()).add(panelSiguiente);
				((JFrame) getTopLevelAncestor()).validate();
				panelSiguiente.MenuClassBuilder();
			}
		});
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(302, 227, 122, 23);
		btnAceptar.setEnabled(Functions.alreadyPreferenced());
		add(btnAceptar);
		/*
		 *		El botón para configurar las preferencias del usuario. Pregunta al usuario varias 
		 *		veces por confirmar sus preferencias, ya que no las podrá cambiar más adelante.
		 *		Funciona con una array de checkboxes que se la pasa al método setPreferences()
		 *		para que las que estén marcadas sean true y las no marcadas false.
		 *
		 *		> Llama a métodos de la clase FUNCTIONS:
		 *
		 *			setPreferences(): Asigna las checkboxes dependiendo de si están marcadas o no
		 *			a el usuario que ha iniciado sesión actualmente.
		 *			logout(): Cierra la sesión del usuario y elimina el usuario y correo actuales.
		 *			restart(): Borra las listas de los usuarios de la memoria para refrescar los datos.
		 */
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int progress = 0;
				int numbPity = 0;
				boolean bucle = true;
				//Creamos la array de checkboxes.
				JCheckBox[] JCheckboxArray = new JCheckBox[]{chckbxMarca, chckbxDiarioAS, chckbxMundoDeportivo, chckbxElPaisD,
						chckbxElEconomista, chckbxElConfidencial, chckbxExpansion, chckbxElPaisE, 
						chckbxLaRazon, chckbxABC, chckbx20Minutos, chckbxElPaisN,
						chckbxElMundo, chckbxBBC, chckbxEuroNews, chckbxElPaisI};
				while(bucle==true) {	
					switch(progress) {
					//Comprobar que al menos una noticia esté marcada, si no el botón no hará nada.
						case 0:
							for(JCheckBox checkbox : JCheckboxArray) {
								if(!checkbox.isSelected()) {
									numbPity++;
								}
							}
							if(numbPity==16) {
								JOptionPane.showMessageDialog(null, "No se ha escogido ninguna noticia.", "Error", JOptionPane.ERROR_MESSAGE);
								bucle = false;
							}else{
								progress++;
							}
						break;
					//Mostrar al usuario cuántas noticias ha marcado y si desea continuar.
						case 1:
							int option = 0;
							option = JOptionPane.showOptionDialog(null, "Ha escogido un total de " + (16-numbPity) + " de 16 noticias.\n "
									+ "Recuerde que puede escoger otra categoría de\n"
									+ "noticias utilizando el panel desplegable.\n"
									+ "¿Desea continuar con las noticias seleccionadas?" , "Confirmacion", JOptionPane.YES_NO_CANCEL_OPTION, 
									JOptionPane.QUESTION_MESSAGE, null, new String[]{"Sí", "No"}, "");
							if(option==0) {
								progress++;
							}else{
								bucle = false;
							}
						break;
					//Preguntamos al usuario de nuevo si desea continuar.
						case 2:
							option = 0;
							option = JOptionPane.showOptionDialog(null, "Estos ajustes no se podran cambiar en el futuro.\n"
									+ "¿Realmente está seguro de que quiere estos ajustes?" , "Confirmacion", JOptionPane.YES_NO_CANCEL_OPTION, 
									JOptionPane.WARNING_MESSAGE, null, new String[]{"Sí", "No"}, "");
							if(option==0) {
								progress++;
							}else{
								bucle = false;
							}
						break;
					//Ahora sí intentamos escribir las preferencias del usuario.
						case 3:
							if(Functions.setPreferences(JCheckboxArray)==false) {
								JOptionPane.showMessageDialog(null, "No se han podido escribir las preferencias correctamente.\n"
										+ "Consulte el problema enviando un correo al admin.", "Error", JOptionPane.ERROR_MESSAGE);
								bucle = false;
								break;
							}else{
								//Cierra la sesión y refresca los datos de la aplicación.
								JOptionPane.showMessageDialog(null, "Las preferencias han sido elegidas correctamente.\n"
										+ "La aplicación se va a reiniciar ahora.", "Success", JOptionPane.INFORMATION_MESSAGE);
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
								bucle = false;
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
		
		JLabel lblAdvertencia = new JLabel("Advertencia:");
		lblAdvertencia.setForeground(new Color(255, 0, 0));
		lblAdvertencia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAdvertencia.setBounds(21, 98, 84, 22);
		add(lblAdvertencia);
		
		JLabel lblPreferencias1 = new JLabel("Una vez se elijan las preferencias,");
		lblPreferencias1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPreferencias1.setBounds(21, 120, 187, 22);
		add(lblPreferencias1);
		
		JLabel lblPreferencias2 = new JLabel("estas ");
		lblPreferencias2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPreferencias2.setBounds(20, 136, 37, 22);
		add(lblPreferencias2);
		
		JLabel lblPreferencias3 = new JLabel("no se podran cambiar.");
		lblPreferencias3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPreferencias3.setBounds(54, 136, 144, 22);
		add(lblPreferencias3);
		
		JLabel lblPreferencias4 = new JLabel("Asegurese de consultar el resto");
		lblPreferencias4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPreferencias4.setBounds(21, 169, 177, 22);
		add(lblPreferencias4);
		
		JLabel lblPreferencias5 = new JLabel("de");
		lblPreferencias5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPreferencias5.setBounds(21, 184, 23, 22);
		add(lblPreferencias5);
		
		JLabel lblPreferencias6 = new JLabel("categorias.");
		lblPreferencias6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPreferencias6.setBounds(41, 184, 77, 22);
		add(lblPreferencias6);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Se ha producido un error inesperado.\n"
					+ "La aplicacion se va a cerrar ahora.", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(10);
		}
	}
		
}


