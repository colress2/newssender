package newsSender;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JCheckBox;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Functions {
	/*
	 * 		Declaración de las ArrayLists para convertir los datos de los archivos en objetos:
	 * 	
	 * 			> listaUsuarios: Recoge los datos de los usuarios en sus objetos.
	 * 
	 * 				- Id (int): Contiene el id del usuario. Es único y no puede cambiar.
	 * 				- Usuario (String): Nombre de usuario.
	 * 				- Contraseña (String): Contraseña.
	 * 				- Mail (String): Correo.
	 * 				- esAdmin (boolean): Comprueba si es admin o no.
	 * 				- yaRegistrado (boolean): Comprueba si ya está registrado o no.
	 * 
	 * 			> listaPreferencias: Recoge las preferencias de los usuarios en sus objetos.
	 * 
	 * 				- Usuario (String): El usuario con el que se corresponde las noticias.
	 * 				- n* (boolean): Comprueba si ésta noticia está elegida por el usuario.
	 * 
	 * 			> listaURLs: Recoge la única linea del archivo de noticias, recogiendo
	 * 						 así todos los enlaces de las páginas así como otros atributos.
	 * 
	 * 				- hora (String): La hora de la aplicación.
	 * 				- minutos (String): Los minutos de la aplicación.
	 * 				- n* (String): El enlace a la noticia de la aplicación.
	 * 
	 * 			* : Representa un nombre distinto correspondiendo a una de las 16 noticias.
	 */
	public static ArrayList<Usuario> listaUsuarios = new ArrayList <Usuario>();
	public static ArrayList<Preferencias> listaPreferencias = new ArrayList <Preferencias>();
	public static URLs listaURLs;
	/*
	 * 		Variables que se utilizaran para el usuario que haya iniciado sesión, recogiendo
	 * 		su nombre de usuario y correo. Ésto será útil para muchas operaciones a lo largo
	 * 		de la aplicación.
	 */
	public static String currentUser = "";
	public static String currentMail = "";
	/*
	 * 		> verifyFiles() verifica si los archivos con los datos dentro de la ruta del proyecto
	 * 		  (src\resources\) existen, y si lo hace cargar instantaneamente sus datos en memoria,
	 * 		  es decir, las ArrayLists y el objeto de URLs para los enlaces de las páginas web.
	 * 
	 * 		  Llamada por:
	 * 
	 * 			- ImageClass.java
	 */
	public static boolean verifyFiles() {
		//Creación de archivos en memoria.
		File archivoLinks = new File("src\\resources\\linksyhora.txt");
		File archivoPreferencias = new File("src\\resources\\userprefs.txt");
		File archivoUsuarios = new File("src\\resources\\users.txt");
		/*
		 * 		Comprobar si alguno de los tres existen. En caso contrario, devolver false, tirar un
		 * 		mensaje de error por interfaz con JOptionPane y abortar la aplicación. 
		 */
		if(!archivoLinks.exists()&&!archivoPreferencias.exists()&&!archivoUsuarios.exists()) {	
			return false;			
		}			
		try {
			//Convertimos el archivo en un BufferedReader para leerlos por partes con .split().
			BufferedReader lectorLinks = new BufferedReader(new FileReader(archivoLinks));
			String read;
			while((read=lectorLinks.readLine())!=null) {
				//Los datos están siempre separados por un ;
				String array[] = read.split(";");
				if(array.length == 18) {
					//Metemos los datos por orden en su respectivo objeto.
					listaURLs = new URLs(array[0], array[1], array[2], array[3], array[4], array[5], 
							array[6], array[7], array[8], array[9], array[10],
							array[11], array[12], array[13], array[14], array[15],
							array[16], array[17]);
				}
			}
			//Los enlaces y la hora ya están cargadas en memoria como objeto.
			lectorLinks.close();
			//Convertimos el archivo en un BufferedReader para leerlos por partes con .split().
			BufferedReader lectorPreferencias = new BufferedReader(new FileReader(archivoPreferencias));
			while((read=lectorPreferencias.readLine())!=null) {
				//Los datos están siempre separados por un ;
				String array[] = read.split(";");
				if(array.length == 17) {	
					//Metemos los datos por orden en su respectivo objeto.
					Preferencias prefs = new Preferencias(array[0], Boolean.parseBoolean(array[1]),
							Boolean.parseBoolean(array[2]), Boolean.parseBoolean(array[3]), Boolean.parseBoolean(array[4]), 
							Boolean.parseBoolean(array[5]), Boolean.parseBoolean(array[6]), Boolean.parseBoolean(array[7]), 
							Boolean.parseBoolean(array[8]), Boolean.parseBoolean(array[9]), Boolean.parseBoolean(array[10]), 
							Boolean.parseBoolean(array[11]), Boolean.parseBoolean(array[12]), Boolean.parseBoolean(array[13]), 
							Boolean.parseBoolean(array[14]), Boolean.parseBoolean(array[15]), Boolean.parseBoolean(array[16]));
					//Y añadimos el objeto recien creado a su respectiva lista de objetos.
					listaPreferencias.add(prefs);
				}
			}
			//Las preferencias con sus respectivos datos ya están cargadas en memoria como objeto.
			lectorPreferencias.close();
			//Convertimos el archivo en un BufferedReader para leerlos por partes con .split().
			BufferedReader lectorUsuarios = new BufferedReader(new FileReader(archivoUsuarios));
			while((read=lectorUsuarios.readLine())!=null) {
				//Los datos están siempre separados por un ;
				String array[] = read.split(";");
				if(array.length == 6) {	
					//Metemos los datos por orden en su respectivo objeto.
					Usuario users = new Usuario(Integer.parseInt(array[0]), array[1], array[2], array[3], Boolean.parseBoolean(array[4]), Boolean.parseBoolean(array[5]));
					listaUsuarios.add(users);
				}
			}
			//Los usuarios con sus respectivos datos ya están cargadas en memoria como objeto.
			lectorUsuarios.close();
		}catch(IOException e) {
			return false;
		}
		return true;
	}
	/*
	 * 		> login() comprueba si el usuario y su contraseña existen, en el caso
	 * 		  de hacerlo el usuario podrá iniciar sesión y su usuario y correo se
	 * 		  guardaran temporalmente en la aplicación para su uso posterior.
	 * 			
	 * 		  Llamada por:
	 * 
	 * 			- LoginClass.java
	 *  			
	 * 		  Recibe los siguientes datos:
	 * 
	 * 			- usuario (String): El usuario introducido
	 * 			- contraseña (String): La contraseña introducida	
	 * 				
	 */
	public static int login(String usuario, String contraseña) {
		int x = 0;
		//Iteramos la lista de usuarios una por una.
			Iterator<Usuario> iUsuarios = listaUsuarios.iterator();
			while(iUsuarios.hasNext()) {
				//Guardamos temporalmente cada usuario que sacamos.
				Usuario user = iUsuarios.next();
				//Si el usuario no está registrado (null) devolvemos un 0 (Usuario o contraseña incorrectos)
				if(usuario.equals("null")){
					return x;
				}
				/*
				 * 		Si el usuario actual que está siendo comprobado coinciden sus nombre de usuario y contraseña
				 * 		la opción se actualiza a 1 (Le da la bienvenida al usuario y le lleva al menú). También
				 * 		mete su usuario y correo actual en memoria.
				 */
				if(user.usuario.equals(usuario)&&user.contraseña.equals(contraseña)) {
					x++;
					currentUser = user.usuario;
					currentMail = user.mail;
					/*
					 * 		Adicionalmente, si su booleano de ser admin es true, actualizamos la opción a 2
					 * 		(Le da la bienvenida al usuario, el cuál es reconozido como ADMIN y le lleva al
					 * 		menú de ADMIN), y se actualiza la memoria con su usuario y correo actuales.
					 */
					if(user.esAdmin==true) {
						x++;
						currentUser = user.usuario;
						currentMail = user.mail;
					}
				}
			}
			//Si falla algunas de las comprobaciones superiores, el método devolverá 0 (Usuario o contraseña incorrectos)
		return x;
	}
	/*
	 * 		> logout() se llama cuando el usuario cierra la sesión, por lo que le devuelve a la
	 * 		  pestaña de inicio de sesión y refresca la memoria de la aplicación, siendo su
	 * 		  nombre de usuario y correo.
	 * 
	 * 		  Llamada por:
	 * 			
	 * 			- MenuClass.java
	 * 			- AdminMenuClass.java
	 */
	public static void logout() {
		currentUser = "";
		currentMail = "";
	}
	/*
	 * 		> restart() es llamada cuando se quiere reiniciar la aplicación por los cambios que se
	 * 		  hayan realizado principalmente en los archivos de datos de ésta. Éste método siempre
	 * 		  dará lugar a que se ejecute de nuevo ImageClass.java para cargar todos los datos en
	 * 		  memoria de nuevo, después de haber limpiado todas las listas de objetos (para listasURLs
	 * 		  se declaran todos los datos como null para luego volver a rellenarlos con sus datos.
	 * 
	 * 		  Llamada por:
	 * 
	 * 			- PrefsClass.java
	 * 			- AdminGestionarUsuariosClass.java
	 */
	public static void restart() {
		listaUsuarios.removeAll(listaUsuarios);
		listaPreferencias.removeAll(listaPreferencias);
		listaURLs = new URLs(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
	}
	/*
	 * 		> newsSender() es el método más importante de la aplicación, ya que, si la hora local y de la aplicación coinciden, se comprobarán
	 * 		  las preferencias de todos los usuarios en la aplicación (menos el ADMIN) y, si existen, relacionar sus preferencias con los
	 * 		  enlaces de las páginas web y construir el cuerpo del mail automatizado en relación a eso. Al terminar de construirse, se envia el
	 * 		  correo a su cuenta de correo, y se repite el proceso con el siguiente usuario.
	 * 
	 * 		  Llamada por:
	 * 
	 * 			- Main.java (Hilo de ejecución)
	 */
	public static boolean newsSender() {
		try {	
			//Comparación de la hora en la aplicación el listaURLs con la hora local del ordenador.
			String hora = Integer.toString(LocalTime.now().getHour()) + ":" + Integer.toString(LocalTime.now().getMinute());
			if(hora.equals(listaURLs.gethora() + ":" + listaURLs.getminutos())){
				//Construimos 2 arrays, una con los enlaces de las páginas y otra con sus títulos para el cuerpo del correo.
				String[] arrayString;
				arrayString = new String[] {listaURLs.getnDeporteMarca(), listaURLs.getnDeporteDiaroAS(), listaURLs.getnDeporteMundoDeportivo(), listaURLs.getnDeporteElPais(), 
						listaURLs.getnEconomiaElEconomista(), listaURLs.getnEconomiaElConfidencial(), listaURLs.getnEconomiaExpansion(), listaURLs.getnEconomiaElPais(), 
						listaURLs.getnNacionalLaRazon(), listaURLs.getnNacionalABC(), listaURLs.getnNacional20Minutos(), listaURLs.getnNacionalElPais(), 
						listaURLs.getnInternacionalElMundo(), listaURLs.getnInternacionalBBC(), listaURLs.getnInternacionalEuroNews(), listaURLs.getnInternacionalElPais()};
				String[] titleString;
				titleString = new String[] {"- Marca: ", "- DiarioAS: ", "- MundoDeportivo: ", "- Deportes ElPais: ", 
						"- El Economista: ", "- El Confidencial: ", "- Expansion: ", "- Economia ElPais: ", 
						"- La Razon: ", "- ABC: ", "- 20 Minutos: ", "- Nacional ElPais: ", 
						"- El Mundo: ", "- BBC: ", "- EuroNews: ", "- Internacional ElPais: "};
				//Iteramos la lista de usuarios una por una.
				Iterator<Usuario> iUsuarios = listaUsuarios.iterator();
				while(iUsuarios.hasNext()) {
					//Guardamos temporalmente cada usuario que sacamos.
					Usuario user = iUsuarios.next();
					//Si su variable booleana de que ya está registrado es true significa que sus preferencias existen. Si no salta al siguiente usuario.
					if(user.isyaRegistrado()==true) {
						//Iteramos la lista de preferencias una por una.
						Iterator<Preferencias> iPreferencias = listaPreferencias.iterator();
						while(iPreferencias.hasNext()) {
							//Guardamos temporalmente cada preferencia que sacamos.
							Preferencias prefs = iPreferencias.next();
							/*
							 * 		Si la preferencia encontrada coincide con la del usuario que se está recorriendo en el momento comenzamos a escribir
							 * 		el cuerpo de su correo acorde a sus preferencias seleccionadas.
							 */
							if(prefs.getUsuario().equals(user.getUsuario())) {
								String body = "¡Nuevas noticias! De parte de NewsSender o/\n"
										+ "\n"
										+ "[Sistema automatizado]\n"
										+ "\n";
								//Creamos una array con sus preferencias.
								boolean[] prefsBoolean;
								prefsBoolean = new boolean[] {prefs.isnDeporteMarca(), prefs.isnDeporteDiaroAS(), prefs.isnDeporteMundoDeportivo(), prefs.isnDeporteElPais(),
										prefs.isnEconomiaElEconomista(), prefs.isnEconomiaElConfidencial(), prefs.isnEconomiaExpansion(), prefs.isnEconomiaElPais(),
										prefs.isnNacionalLaRazon(), prefs.isnNacionalABC(), prefs.isnNacional20Minutos(), prefs.isnNacionalElPais(),
										prefs.isnInternacionalElMundo(), prefs.isnInternacionalBBC(), prefs.isnInternacionalEuroNews(), prefs.isnInternacionalElPais(),};
								/*
								 * 		Y ahora recorremos cualquiera de las arrays por longitud. Si la preferencia que se está recorriendo actualmente 
								 * 		es true, construimos el cuerpo con la misma casilla del booleano en el enlace la página web, añadiendola al
								 * 		cuerpo para que el usuario pueda acceder a la página web y a parte consiguiendo su guíón con el método getGuiones().
								 * 
								 * 		> Llama a métodos de su misma clase:
								 * 			
								 * 			getGuiones(): Método para conseguir el titular de una aplicación en base al enlace que se le pase.
								 */
								for(int i=0; i<arrayString.length; i++) {
									if(prefsBoolean[i]==true) {
										body += titleString[i] + getGuiones(arrayString[i]) + ": " + arrayString[i] + "\n";
									}
								}
								body += "\n"
										+ "Que pase un buen día ( ´ ω ` )";
								/*
								 * 		Una vez el cuerpo entero se haya construido, llamamos a emailSend() para que procese el correo al usuario actual.
								 * 
								 * 		> Llama a métodos de su misma clase:
								 * 
								 * 			emailSend(): Método para crear el correo y enviarlo. Se le pasa el emisor, receptor, titulo y cuerpo.
								 */
								if(emailSend("NewsSender", user.getMail(), "¡Nuevas noticias!", body)==false) {
									return false;
								}
							}
						}
					}
				}
			}
		}catch(Exception e) {
			//Si alguno de los métodos anteriores fallaran, la aplicación lanzará un error visual de que el correo no pudo ser enviado.
			return false;
		}
		return true;
	}
	/*
	 * 		> getGuiones() se conecta a la página web que se le pase y recoge su contenido html, para subsecuentemente retornar
	 * 		  el guión de las noticia más reciente.
	 * 
	 * 		  Llamada por:
	 * 
	 * 			- newsSender()
	 * 			- adminSendNews()
	 * 			- AdminProbarNoticiasClass.java
	 * 
	 * 		  Recibe los siguientes datos:
	 * 
	 * 			- web (String): El enlace de la web que se le pase, principalmente de listasURLs.
	 */
	public static String getGuiones(String web) {
		Document document = null;
		String returnStatement = "";
		try {
			//Se conecta a la página web.
			document = Jsoup.connect(web).get();
		} catch (IOException e) {
			return "ERROR: No se ha podido localizar la pagina web.";
		}
		/*
		 * 		Con el documento y la propia página web, lo enviamos a otro método exclusivo a éste método el cuál dependiendo de la
		 * 		página web seleccionada elegirá una configuración para sacar el guión de ésta correctamente, y de eso devolverlo a
		 * 		la interfaz o al método que lo esté pidiendo.
		 * 
		 * 		> Llama a métodos de su misma clase:
		 * 
		 * 			fixGuiones(): Método para arreglar el guión y devolverlo a éste método.
		 */
		returnStatement = fixGuiones(document, web);
		return returnStatement;
	}
	/*
	 * 		> fixGuiones() es encargado únicamente de extraer del documento de la página del método superior y devolverlo.
	 * 		  Por desgracia, algunos ajustes no podrán devolver el guión de la noticia con un 100% de exactitud, debido a
	 * 		  que los html de algunas páginas son muy volátiles y tienden a cambiar de aspecto con bastante frecuencia, por
	 * 		  lo que se puede colar algúna etiqueta html en el titular de la noticia.
	 * 
	 * 		  Llamada por:
	 * 
	 * 			- getGuiones()
	 * 
	 * 		  Recibe los siguientes datos:
	 * 
	 * 			- document (Document): El documento entero de la página web que se obtuvo en el método anterior.
	 * 			- web (String): El enlace de la web que se le pase, principalmente de listasURLs.
	 */
	private static String fixGuiones(Document document, String web) {
		String headline = "";
		int n = 100;
		//Creamos una array con todos los enlaces de las páginas web.
		String[] arrayString;
		arrayString = new String[] {listaURLs.getnDeporteMarca(), listaURLs.getnDeporteDiaroAS(), listaURLs.getnDeporteMundoDeportivo(), listaURLs.getnDeporteElPais(), 
				listaURLs.getnEconomiaElEconomista(), listaURLs.getnEconomiaElConfidencial(), listaURLs.getnEconomiaExpansion(), listaURLs.getnEconomiaElPais(), 
				listaURLs.getnNacionalLaRazon(), listaURLs.getnNacionalABC(), listaURLs.getnNacional20Minutos(), listaURLs.getnNacionalElPais(), 
				listaURLs.getnInternacionalElMundo(), listaURLs.getnInternacionalBBC(), listaURLs.getnInternacionalEuroNews(), listaURLs.getnInternacionalElPais()};
		//Y comparamos hasta que la página que hemos pasado coincida con la de la array. Asignaremos a cada posición su correspondientes ajustes.
		for(int i=0; i<arrayString.length; i++) {
			if(web.equals(arrayString[i])) {
				n = i;
				break;
			}
		}
		try {
			switch(n) {
				//Para sacar el guión de Marca.
				case 0:
					Elements guionMarca = document.getElementsByClass("ue-c-cover-content__headline");
					Element firstElementMarca = guionMarca.get(0);
					headline = firstElementMarca.html();
					break;
					//Para sacar el guión de AS.
				case 1:
					Elements guionAS = document.getElementsByClass("s__tl");
					Element firstElementAS = guionAS.get(0);
					headline = firstElementAS.html().substring(firstElementAS.html().indexOf(">")+1, firstElementAS.html().length()-4);
					break;
					//Para sacar el guión de MundoDeportivo.
				case 2:
					Elements guionMundoDeportivo = document.getElementsByClass("title");
					Element firstElementMundoDeportivo = guionMundoDeportivo.get(0);
					headline = firstElementMundoDeportivo.html().substring(firstElementMundoDeportivo.html().indexOf(">")+1, firstElementMundoDeportivo.html().length()-4);
					break;
					//Para sacar el guión de ElPais Deportes.
				case 3:
					Elements guionElPaisDeportes = document.getElementsByClass("c_t");
					Element firstElementElPaisDeportes = guionElPaisDeportes.get(0);
					String headlinetemp1 = firstElementElPaisDeportes.html().substring(firstElementElPaisDeportes.html().indexOf("</span>")+1, firstElementElPaisDeportes.html().length()-4);
					headline = headlinetemp1.substring(headlinetemp1.indexOf(">")+1);
					break;
					//Para sacar el guión de Economista.
				case 4:
					Elements guionElEconomista = document.getElementsByClass("h1");
					Element firstElementElEconomista = guionElEconomista.get(0);
					headline = firstElementElEconomista.html().substring(firstElementElEconomista.html().indexOf("r>")+5, firstElementElEconomista.html().indexOf("</center>")-2);
					break;
					//Para sacar el guión de ElConfidencial.
				case 5:
					Elements guionElConfidencial = document.getElementsByClass("openingBalcony__title");
					Element firstElementElConfidencial = guionElConfidencial.get(0);
					headline = firstElementElConfidencial.html();
					break;
					//Para sacar el guión de Expansion.
				case 6:
					Elements guionExpansion = document.getElementsByClass("ue-c-cover-content__headline");
					Element firstElementExpansion = guionExpansion.get(0);
					headline = firstElementExpansion.html();
					break;
					//Para sacar el guión de ElPais Economia.
				case 7:
					Elements guionElPaisEconomia = document.getElementsByClass("c_t");
					Element firstElementElPaisEconomia = guionElPaisEconomia.get(0);
					String headlinetemp2 = firstElementElPaisEconomia.html().substring(firstElementElPaisEconomia.html().indexOf("</span>")+1, firstElementElPaisEconomia.html().length()-4);
					headline = headlinetemp2.substring(headlinetemp2.indexOf(">")+1);
					break;
					//Para sacar el guión de LaRazon.
				case 8:
					Elements guionLaRazon = document.getElementsByClass("article__title");
					Element firstElementLaRazon = guionLaRazon.get(7);
					headline = firstElementLaRazon.html().substring(firstElementLaRazon.html().indexOf(">")+1, firstElementLaRazon.html().length()-4);
					break;
					//Para sacar el guión de ABC.
				case 9:
					Elements guionABC = document.getElementsByClass("voc-article-container");
					Element firstElementABC = guionABC.get(0);
					String headlinetemp6 = firstElementABC.html().substring(firstElementABC.html().indexOf("h2"), firstElementABC.html().indexOf("</a>"));
					String headlinetemp7 = headlinetemp6.substring(headlinetemp6.indexOf(">")+1);
					headline = headlinetemp7.substring(headlinetemp7.indexOf(">")+1);
					break;
					//Para sacar el guión de 20Minutos.
				case 10:
					Elements guion20Minutos = document.getElementsByClass("media-content");
					Element firstElement20Minutos = guion20Minutos.get(0);
					String headlinetemp5 = firstElement20Minutos.html().substring(firstElement20Minutos.html().indexOf("</span>")+7, firstElement20Minutos.html().indexOf("</a>"));
					headline = headlinetemp5.substring(headlinetemp5.indexOf(">")+1);
					break;
					//Para sacar el guión de ElPais Nacional.
				case 11:
					Elements guionElPaisNacional = document.getElementsByClass("c_t");
					Element firstElementElPaisNacional = guionElPaisNacional.get(0);
					String headlinetemp3 = firstElementElPaisNacional.html().substring(firstElementElPaisNacional.html().indexOf("</span>")+1, firstElementElPaisNacional.html().length()-4);
					headline = headlinetemp3.substring(headlinetemp3.indexOf(">")+1);
					break;
					//Para sacar el guión de ElMundo.
				case 12:
					Elements guionElMundo = document.getElementsByClass("ue-c-cover-content__headline");
					Element firstElementElMundo = guionElMundo.get(0);
					headline = firstElementElMundo.html().substring(firstElementElMundo.html().indexOf(">")+1, firstElementElMundo.html().length());
					break;
					//Para sacar el guión de BBC.
				case 13:
					Elements guionBBC = document.getElementsByClass("promo-text");
					Element firstElementBBC = guionBBC.get(0);
					String headlinetemp8 = firstElementBBC.html().substring(firstElementBBC.html().indexOf(">")+1, firstElementBBC.html().length()-7);
					headline = headlinetemp8.substring(headlinetemp8.indexOf(">")+1, headlinetemp8.indexOf("</a>"));
					break;
					//Para sacar el guión de EuroNews.
				case 14:
					Elements guionEuroNews = document.getElementsByClass("m-object__title__link   m-object__title__link--big-title");
					Element firstElementEuroNews = guionEuroNews.get(0);
					headline = firstElementEuroNews.html().substring(firstElementEuroNews.html().indexOf(">")+1, firstElementEuroNews.html().length());
					break;
					//Para sacar el guión de ElPais Internacional.
				case 15:
					Elements guionElPaisInternacional = document.getElementsByClass("c_t");
					Element firstElementElPaisInternacional = guionElPaisInternacional.get(0);
					String headlinetemp4 = firstElementElPaisInternacional.html().substring(firstElementElPaisInternacional.html().indexOf("</span>")+1, firstElementElPaisInternacional.html().length()-4);
					headline = headlinetemp4.substring(headlinetemp4.indexOf(">")+1);
					break;
					//Si por alguna razón no se encuentra la página web que se le pase tiramos un error visual.
				case 100:
					headline = "ERROR: No se ha podido encontrar la cabecera.";
					break;
				default:
					headline = "ERROR: No se ha podido encontrar la cabecera.";
					break;
			}
		}catch(Exception e) {
			headline = "ERROR: No se pudo obtener el elemento HTML.";
		}
		return headline;
	}
	/*
	 * 		> setPreferences() escribe las preferencias del usuario en el fichero userprefs.txt. Cuando se llama a este método, suele
	 * 		  ir acompañado por un restart() para que los nuevos ajustes se carguen de nuevo en memoria. Éste método solo puede ocurrir
	 * 		  una vez por usuario hasta que el ADMIN decida modificarlo.
	 * 
	 * 		Llamada por:
	 * 
	 * 		  - PrefsClass.java
	 * 
	 * 		Recibe los siguientes datos:
	 * 
	 * 		  - checkbox (JCheckBox[]): Una array de checkboxes de la interfaz, donde dependiendo de cuáles estén marcadas o no se ajustará
	 * 			la preferencia de la correspondiente página web en true o false para el usuario que está creando las preferencias.
	 */
	public static boolean setPreferences(JCheckBox[] checkbox) {
		try{
			//Creamos un escritor para el archivo de usuarios y de preferencias, ya que vamos a sobreescribir ambos.
			BufferedWriter bufferedWriterUser = new BufferedWriter(new FileWriter("src\\resources\\users.txt"));
			BufferedWriter bufferedWriterPrefs = new BufferedWriter(new FileWriter("src\\resources\\userprefs.txt"));
			//Cremaos una array de booleanos que se corresponderá con las preferencias del usuario.
			boolean[] booleanArray;
			booleanArray = new boolean[16];
			int i = 0;
			//Recorremos la array, y por cada casilla marcada ésta será true en la array, y si no false.
			for(JCheckBox c : checkbox) {
				if(c.isSelected()) {
					booleanArray[i] = true;
					i++;
				}else{
					booleanArray[i] = false;
					i++;
				}
			}
			//Iteramos la lista de preferencias una por una.
			Iterator<Preferencias> iPreferencias = listaPreferencias.iterator();
			while(iPreferencias.hasNext()) {
				//Guardamos temporalmente cada preferencia que sacamos. Recogemos todos sus datos y los volvemos a escribir de nuevo en el archivo.
				Preferencias pref = iPreferencias.next();
				bufferedWriterPrefs.write(pref.getUsuario() + ";" + String.valueOf(pref.isnDeporteMarca()) + ";" + String.valueOf(pref.isnDeporteDiaroAS()) + ";" + String.valueOf(pref.isnDeporteMundoDeportivo()) 
						+ ";" + String.valueOf(pref.isnDeporteElPais()) + ";" + String.valueOf(pref.isnEconomiaElEconomista()) + ";" + String.valueOf(pref.isnEconomiaElConfidencial()) + ";" + String.valueOf(pref.isnEconomiaExpansion())
						+ ";" + String.valueOf(pref.isnEconomiaElPais()) + ";" + String.valueOf(pref.isnNacionalLaRazon()) + ";" + String.valueOf(pref.isnNacionalABC()) + ";" + String.valueOf(pref.isnNacional20Minutos())
						+ ";" + String.valueOf(pref.isnNacionalElPais()) + ";" + String.valueOf(pref.isnInternacionalElMundo()) + ";" + String.valueOf(pref.isnInternacionalBBC()) + ";" + String.valueOf(pref.isnInternacionalEuroNews())
						+ ";" + String.valueOf(pref.isnInternacionalElPais()) + "\n");
			}
			/*
			 * 		Una vez todo el contenido del archivo preferencias se haya escrito, escribimos la nueva preferencia, con el usuario actual y 
			 * 		los valores que tengamos en la array de booleanos correspondiente a lo seleccionado con las checkboxes.
			 */
			bufferedWriterPrefs.write(currentUser + ";" + String.valueOf(booleanArray[0]) + ";" + String.valueOf(booleanArray[1]) + ";" + String.valueOf(booleanArray[2]) + ";" + String.valueOf(booleanArray[3])
			+ ";" + String.valueOf(booleanArray[4]) + ";" + String.valueOf(booleanArray[5]) + ";" + String.valueOf(booleanArray[6]) + ";" + String.valueOf(booleanArray[7]) + ";" + String.valueOf(booleanArray[8])
			+ ";" + String.valueOf(booleanArray[9]) + ";" + String.valueOf(booleanArray[10]) + ";" + String.valueOf(booleanArray[11]) + ";" + String.valueOf(booleanArray[12]) + ";" + String.valueOf(booleanArray[13])
			+ ";" + String.valueOf(booleanArray[14]) + ";" + String.valueOf(booleanArray[15]));
			bufferedWriterPrefs.newLine();
			//Iteramos la lista de usuarios una por una.
			Iterator<Usuario> iUsuarios = listaUsuarios.iterator();
			while(iUsuarios.hasNext()) {
				//Guardamos temporalmente cada usuario que sacamos.
				Usuario user = iUsuarios.next();
				/*
				 * 		Y si el usuario que sacamos es igual al usuario que ha iniciado sesión en la aplicación cambiamos su booleano de yaRegistrado
				 * 		a true para saber que ya está registrado.
				 */
				if(user.usuario.equals(currentUser)) {
					user.setyaRegistrado(true);
				}
				//Sobreescribimos el archivo de usuarios con lo que había antes y el nuevo ajuste del usuario que está en la sesión actual.
				bufferedWriterUser.write(user.getid() + ";" + user.getUsuario() + ";" + user.getContraseña() + ";" + user.getMail() + ";" + String.valueOf(user.isEsAdmin())+ ";" + String.valueOf(user.isyaRegistrado()) + "\n");
			}		
			bufferedWriterUser.close();
			bufferedWriterPrefs.close();
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	/*
	 *		> checkAdmin() tiene un propósito muy simple y es devolver el correo del admin actual. Ya que éste puede ser modificado tendrémos que
	 *		  saber cuál es incluso si se modifica a la hora de que un usuario quiera enviarle un correo.
	 *
	 *		  Llamada por:
	 *
	 *			- FeedbackClass.java
	 */
	public static String checkAdmin(){
		String correo = "";
		//Iteramos la lista de usuarios una por una.
	 	Iterator<Usuario> iUsuarios = listaUsuarios.iterator();
		while(iUsuarios.hasNext()) {
			//Guardamos temporalmente cada usuario que sacamos.
			Usuario user = iUsuarios.next();
			//Si el usuario es ADMIN, devolvemos su correo actual.
			if(user.isEsAdmin()==true) {
				correo = user.getMail();
			}
		}
		return correo;		
 	}
	/*
	 * 		> alreadyPreferenced() comprueba si el usuario ya se ha registrado anteriormente en la aplicación comprobando su booleano yaRegistrado.
	 * 		  Si devuelve false, los botones, checkboxes y labels que estén llamando a éste método cambiaran de estado para que no se pueda editar.
	 * 
	 * 		  Llamada por:
	 * 
	 * 			- MenuClass.java
	 * 			- PrefsClass.java
	 */
	public static boolean alreadyPreferenced() {
		//Iteramos la lista de usuarios una por una.
		Iterator<Usuario> iUsuarios = listaUsuarios.iterator();
		while(iUsuarios.hasNext()) {
			//Guardamos temporalmente cada usuario que sacamos.
			Usuario user = iUsuarios.next();
			//Comprobamos si el usuario que está ahora mismo en sesión tenga la variable en true o false.
			if(user.getUsuario().equals(currentUser)) {
				if(user.isyaRegistrado()==true) {
					return false;
				}
			}
		}
		return true;
	}
	/*
	 * 		> adminSendNews() realiza básicamente la misma función que newsSender() solo que ésta vez se envian todas las noticias
	 * 		  de la aplicación con solo el click de un botón al admin.
	 * 
	 * 		  Llamada por:
	 * 
	 * 			- AdminProbarNoticiasClass.java
	 */
	public static boolean adminSendNews() {
		try {
			String body = "";
			//Creamos la array con las páginas web y sus enunciados.
			String[] arrayString;
			arrayString = new String[] {listaURLs.getnDeporteMarca(), listaURLs.getnDeporteDiaroAS(), listaURLs.getnDeporteMundoDeportivo(), listaURLs.getnDeporteElPais(), 
					listaURLs.getnEconomiaElEconomista(), listaURLs.getnEconomiaElConfidencial(), listaURLs.getnEconomiaExpansion(), listaURLs.getnEconomiaElPais(), 
					listaURLs.getnNacionalLaRazon(), listaURLs.getnNacionalABC(), listaURLs.getnNacional20Minutos(), listaURLs.getnNacionalElPais(), 
					listaURLs.getnInternacionalElMundo(), listaURLs.getnInternacionalBBC(), listaURLs.getnInternacionalEuroNews(), listaURLs.getnInternacionalElPais()};
			String[] titleString;
			titleString = new String[] {"- Marca: ", "- DiarioAS: ", "- MundoDeportivo: ", "- Deportes ElPais: ", 
					"- El Economista: ", "- El Confidencial: ", "- Expansion: ", "- Economia ElPais: ", 
					"- La Razon: ", "- ABC: ", "- 20 Minutos: ", "- Nacional ElPais: ", 
					"- El Mundo: ", "- BBC: ", "- EuroNews: ", "- Internacional ElPais: "};
			/*
			 * 		Y ahora recorremos las arrays, por cada recorrido añadimos la página que se está recorriendo en el momento al cuerpo, junto
			 * 		con su encabezado, guión y enlace a la página web.
			 * 
			 * 		> Llama a métodos de su misma clase:
			 * 
			 * 		  getGuiones(): Consigue los guiones del enlace web que se le pase.
			 */
			for(int i=0; i<arrayString.length; i++) {
				body += titleString[i] + getGuiones(arrayString[i]) + ": " + arrayString[i] + "\n";
			}
			/*
			 * 		Una vez el cuerpo entero se haya construido, llamamos a emailSend() para que procese el correo al usuario actual.
			 * 
			 * 		> Llama a métodos de su misma clase:
			 * 
			 * 			emailSend(): Método para crear el correo y enviarlo. Se le pasa el emisor, receptor, titulo y cuerpo.
			 */
			if(emailSend("NewsSender", currentMail, "Prueba de noticias", body)==false) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	/*
	 * 		> ajustarHora() es un método que se usa para ajustar la hora en la aplicación en tiempo real y actualiza el
	 * 		  archivo linksyhora.txt con la configuración introducida.
	 * 
	 * 		  Llamada por:
	 * 
	 * 			- AdminAjustarHoraClass.java
	 * 
	 * 		  Recibe los siguientes datos:
	 * 
	 * 			- hora (String): La hora que se introduzca en el cambo de texto.
	 * 			- minutos (String): Los minutos que se introducen en el campo de texto. 
	 */
	public static boolean ajustarHora(String hora, String minutos) {
		try {
			//Rescribimos el archivo entero con los objetos en memoria y con la nueva hora y minutos.
			BufferedWriter bufferedWriterLinks = new BufferedWriter(new FileWriter("src\\resources\\linksyhora.txt"));
			bufferedWriterLinks.write(hora + ";" + minutos + ";" + listaURLs.getnDeporteMarca() + ";" + listaURLs.getnDeporteDiaroAS() + ";"
					+ listaURLs.getnDeporteMundoDeportivo() + ";" + listaURLs.getnDeporteElPais() + ";" + listaURLs.getnEconomiaElEconomista() + ";" + listaURLs.getnEconomiaElConfidencial() + ";"
					+ listaURLs.getnEconomiaExpansion() + ";" + listaURLs.getnEconomiaElPais() + ";" + listaURLs.getnNacionalLaRazon() + ";" + listaURLs.getnNacionalABC() + ";"
					+ listaURLs.getnNacional20Minutos() + ";" + listaURLs.getnNacionalElPais() + ";" + listaURLs.getnInternacionalElMundo() + ";" + listaURLs.getnInternacionalBBC() + ";"
					+ listaURLs.getnInternacionalEuroNews() + ";" + listaURLs.getnInternacionalElPais());
			//Y actualizamos también ambos campos en los objetos.
			listaURLs.sethora(hora);
			listaURLs.setminutos(minutos);
			bufferedWriterLinks.close();
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	/*
	 * 		> checkUsers() es simplemente un método utilizado para recoger un dato en concreto del usuario que
	 * 		  se requiera por su id. Es utilizado para mostrar información en la interfaz.
	 * 
	 * 		  Llamada por:
	 * 
	 * 			- AdminGestionarUsuariosClass.java
	 * 			- AdminSignInClass.java
	 * 			- AdminGestionarPreferenciasClass.java
	 * 
	 * 		  Recibe los siguientes datos:
	 * 
	 * 			- id (int): El id para reconocer al usuario.
	 * 			- n (int): La instrucción que le queremos pasar para que nos saque un tipo de información diferente.
	 */
	public static String checkUsers(int id, int n) {
		//Iteramos la lista de usuarios una por una.
		Iterator<Usuario> iUsuarios = listaUsuarios.iterator();
		String say = "";
		while(iUsuarios.hasNext()) {
			//Guardamos temporalmente cada usuario que sacamos.
			Usuario user = iUsuarios.next();
			//Recogemos el usuario del id que le hemos pasado.
			if(user.getid()==id) {
				//Dependiendo de n, sacamos un tipo de información distinta.
				switch(n) {
					case 1:
						//En caso de que el usuario no exista.
						if(user.getUsuario().equals("null")){
							say = "Nuevo usuario " + id;
						//Si no, sacamos su usuario.
						}else{
							say = user.getUsuario();
						}
					break;
					case 2:
						//En caso de que el usuario no exista.
						if(user.getUsuario().equals("null")){
							say = "";
						//Si no, sacamos su usuario.
						}else{
							say = user.getUsuario();
						}
					break;
					case 3:
						//En caso de que el usuario no exista.
						if(user.getMail().equals("null")){
							say = "";
						//Si no, sacamos su correo.
						}else{
							say = user.getMail();
						}
					break;
					case 4:
						//En caso de que el usuario no exista.
						if(user.getContraseña().equals("null")){
							say = "";
						//Si no, sacamos su contraseña.
						}else{
							say = user.getContraseña();
						}
					break;
				}
			}
		}
		return say;
	}
	/*
	 * 		> checkRepeatedUsers() comprueba si el usuario que se está introduciendo ya existe, y si ese es el caso
	 * 		  tiramos mensaje de error.
	 * 
	 * 		  Llamada por:
	 * 
	 * 			- AdminSignInClass.java
	 * 
	 * 		  Recibe los siguientes datos:
	 * 
	 * 			- u (String): El usuario que está siendo introducido en el campo de texto.
	 */
	public static boolean checkRepeatedUsers(String u) {
		//Iteramos la lista de usuarios una por una.
		Iterator<Usuario> iUsuarios = listaUsuarios.iterator();
		while(iUsuarios.hasNext()) {
			//Guardamos temporalmente cada usuario que sacamos.
			Usuario user = iUsuarios.next();
			//Si el usuario que se está iterando coincide con el introducido tiramos error.
			if(user.getUsuario().equals(u)) {
				return true;
			}
			//Si está intentando introducir un usuario nulo también tiramos error.
			if(u.equals("null")) {
				return true;
			}
			
		}
		return false;
	}
	/*
	 * 		> checkRepeatedUsers() comprueba si el correo que se está introduciendo ya existe, y si ese es el caso
	 * 		  tiramos mensaje de error.
	 * 
	 * 		  Llamada por:
	 * 
	 * 			- AdminSignInClass.java
	 * 
	 * 		  Recibe los siguientes datos:
	 * 
	 * 			- m (String): El correo que está siendo introducido en el campo de texto.
	 */
	public static boolean checkRepeatedMails(String m) {
		//Iteramos la lista de usuarios una por una.
		Iterator<Usuario> iUsuarios = listaUsuarios.iterator();
		while(iUsuarios.hasNext()) {
			//Guardamos temporalmente cada usuario que sacamos.
			Usuario user = iUsuarios.next();
			//Si el mail del usuario que se está iterando coincide con el introducido tiramos error.
			if(user.getMail().equals(m)) {
				return true;
			}
			//Si está intentando introducir un correo nulo también tiramos error.
			if(m.equals("null")) {
				return true;
			}
			
		}
		return false;
	}
	/*
	 * 		> existingUsers() es un método al que se llama cuando se está intentando borrar más de 2 usuarios
	 * 		  ya que solo puede existir al menos 1 usuario en la aplicación.
	 * 
	 * 		  Llamada por:
	 * 
	 * 			- AdminSignInClass.java
	 */
	public static int existingUsers() {
		//Iteramos la lista de usuarios una por una.
		Iterator<Usuario> iUsuarios = listaUsuarios.iterator();
		int quantity = 3;
		while(iUsuarios.hasNext()) {
			//Guardamos temporalmente cada usuario que sacamos.
			Usuario user = iUsuarios.next();
			//Si un usuario no existe, decrementamos la cantidad de usuarios y lo devolvemos.
			if(user.getUsuario().equals("null")){
				quantity--;
			}
		}
		return quantity;
	}
	/*
	 * 		> checkUsersExist() es un método que se utiliza en la interfaz para determinar que, si el usuario existe,
	 * 		  se muestren las opciones de modificar y borrar del respectivo usuario.
	 * 
	 * 		  Llamada por:
	 * 
	 * 		 	- AdminGestionarUsuariosClass.java
	 * 
	 * 		  Recibe los siguientes datos:
	 * 
	 * 			- id (int): El id del usuario del cuál se quiere comprobar su existencia.
	 */
	public static boolean checkUsersExist(int id) {
		//Iteramos la lista de usuarios una por una.
		Iterator<Usuario> iUsuarios = listaUsuarios.iterator();
		while(iUsuarios.hasNext()) {
			//Guardamos temporalmente cada usuario que sacamos.
			Usuario user = iUsuarios.next();
			//Si el usuario actual está borrado (null), que se muestre el panel de edición de usuario.
			if(user.getid()==id) {
				if(user.getUsuario().equals("null")) {
					return false;
				}
			}
		}
		return true;
	}
	/*
	 * 		> checkUsersDontExist() es exactamente todo lo contrario del método superior. Si los usuarios
	 * 		  no existen, mostramos la opción de dar de alta al usuario.
	 * 
	 * 		  Llamada por:
	 * 
	 * 		 	- AdminGestionarUsuariosClass.java
	 * 
	 * 		  Recibe los siguientes datos:
	 * 
	 * 			- id (int): El id del usuario del cuál se quiere comprobar su existencia.
	 */
	public static boolean checkUsersDontExist(int id) {
		//Iteramos la lista de usuarios una por una.
		Iterator<Usuario> iUsuarios = listaUsuarios.iterator();
		while(iUsuarios.hasNext()) {
			//Guardamos temporalmente cada usuario que sacamos.
			Usuario user = iUsuarios.next();
			//Si el usuario actual está borrado (null), que se muestre el panel de dar de alta de usuario.
			if(user.getid()==id) {
				if(user.getUsuario().equals("null")) {
					return true;
				}
			}
		}
		return false;
	}
	/*
	 * 		> checkPrefsExist() es un método para la interfaz que comprueba que las preferencias del usuario
	 * 		  que se le pase existan y, si lo hacen, que habilite el botón para modificar las preferencias de éste.
	 * 
	 * 		  Llamada por:
	 * 
	 * 		 	- AdminGestionarPreferenciasClass.java
	 * 
	 * 		  Recibe los siguientes datos:
	 * 
	 * 			- id (int): El id del usuario del cuál se quiere comprobar sus preferencias.
	 */
	public static boolean checkPrefsExist(int id) {
		//Iteramos la lista de usuarios una por una.
		Iterator<Usuario> iUsuarios = listaUsuarios.iterator();
		while(iUsuarios.hasNext()) {
			//Guardamos temporalmente cada usuario que sacamos.
			Usuario user = iUsuarios.next();
			//Para empezar si el usuario no existe directamente devolvemos false.
			if(user.getid()==id) {
				if(user.getUsuario().equals("null")) {
					return false;
				}
				String u = user.getUsuario();
				//Iteramos la lista de preferencias una por una.
				Iterator<Preferencias> iPreferencias = listaPreferencias.iterator();
				while(iPreferencias.hasNext()) {
					//Guardamos temporalmente cada preferencia que sacamos.
					Preferencias prefs = iPreferencias.next();
					//Si existen, devolvemos true y habilitamos el boton.
					if(prefs.getUsuario().equals(u)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	/*
	 * 		> reset() es un método de interfaz que comprueba que al menos 1 usuario tiene escrita 
	 * 		  sus preferencias y, si lo hace, que habilite el botón de resetear las preferencias.
	 * 
	 * 		  Llamada por:
	 * 
	 * 			- AdminGestionarPreferenciasClass.java
	 */
	public static boolean reset() {
		//Iteramos la lista de preferencias una por una.
		Iterator<Preferencias> iPreferencias = listaPreferencias.iterator();
		int stagesOfGrief = 0;
		while(iPreferencias.hasNext()) {
			//Guardamos temporalmente cada preferencia que sacamos.
			Preferencias prefs = iPreferencias.next();
			String u = prefs.getUsuario();
			//Iteramos la lista de usuarios una por una.
			Iterator<Usuario> iUsuarios = listaUsuarios.iterator();
			while(iUsuarios.hasNext()) {
				//Guardamos temporalmente cada usuario que sacamos.
				Usuario user = iUsuarios.next();
				//Y si el usuario tiene preferencias, sumamos 1 para indicar que existe al menos 1 usuario con preferencias.
				if(user.usuario.equals(u)) {
					stagesOfGrief++;;
				}
			}
		}
		if(stagesOfGrief==0) {
			return false;
		}
		return true;
	}
	/*
	 * 		> deleteUsers() es un método encargado de borrar el usuario seleccionado, el cuál se identifica por su id.
	 * 		  Al ser borrado, pasa al estado de null, donde se considera como una casilla vacía para añadir a otro
	 * 		  usuario.
	 * 
	 * 		Llamada por:
	 * 
	 * 		  - AdminGestionarUsuariosClass.java
	 * 
	 * 		Recibe los siguientes datos:
	 * 
	 * 		  - homicide (int): El id del usuario a ser borrado. El nombre es solo por propósito comédico.
	 */
	public static boolean deleteUsers(int homicide) {
	 	try {	
	 		BufferedWriter bufferedWriterUser = new BufferedWriter(new FileWriter("src\\resources\\users.txt"));
	 		BufferedWriter bufferedWriterPrefs = new BufferedWriter(new FileWriter("src\\resources\\userprefs.txt"));
	 		//Iteramos la lista de usuarios una por una.
	 		Iterator<Usuario> iUsuarios = listaUsuarios.iterator();
			String userprefs = "";
	 		while(iUsuarios.hasNext()) {
	 			//Guardamos temporalmente cada usuario que sacamos.
	 			Usuario user = iUsuarios.next();
	 			/*
	 			 * 		Si el usuario que recogemos coincide con el que se le pasa por id, reemplazamos todos sus datos con "null",
	 			 * 		y de paso declaramos sus preferencias como false, porque también las vamos a borrar. También guardamos su
	 			 * 		antiguo usuario para identificar sus preferencias.
	 			 */
	 			if(user.getid()==homicide) {
	 				userprefs = user.getUsuario();
	 				user.setUsuario("null");
	 				user.setContraseña("null");
	 				user.setMail("null");
	 				user.setyaRegistrado(false);
	 			}
	 			//Reescribimos la información del archivo entera con el usuario borrado actualizado.
	 			bufferedWriterUser.write(String.valueOf(user.getid()) + ";" + user.getUsuario() + ";" + user.getContraseña() + ";" + user.getMail() + ";" + String.valueOf(user.isEsAdmin())+ ";" + String.valueOf(user.isyaRegistrado()) + "\n");
	 		}
	 		//Iteramos la lista de preferencias una por una.
	 		Iterator<Preferencias> iPreferencias = listaPreferencias.iterator();
	 		while(iPreferencias.hasNext()) {
	 			//Guardamos temporalmente cada preferencia que sacamos.
	 			Preferencias pref = iPreferencias.next();
	 			//Creamos una condición que si el usuario y sus preferencias coinciden que las omita en la reescritura del fichero.
	 			if(!(pref.getUsuario().equals(userprefs))) {
	 				bufferedWriterPrefs.write(pref.getUsuario() + ";" + String.valueOf(pref.isnDeporteMarca()) + ";" + String.valueOf(pref.isnDeporteDiaroAS()) + ";" + String.valueOf(pref.isnDeporteMundoDeportivo()) 
	 				+ ";" + String.valueOf(pref.isnDeporteElPais()) + ";" + String.valueOf(pref.isnEconomiaElEconomista()) + ";" + String.valueOf(pref.isnEconomiaElConfidencial()) + ";" + String.valueOf(pref.isnEconomiaExpansion())
	 				+ ";" + String.valueOf(pref.isnEconomiaElPais()) + ";" + String.valueOf(pref.isnNacionalLaRazon()) + ";" + String.valueOf(pref.isnNacionalABC()) + ";" + String.valueOf(pref.isnNacional20Minutos())
	 				+ ";" + String.valueOf(pref.isnNacionalElPais()) + ";" + String.valueOf(pref.isnInternacionalElMundo()) + ";" + String.valueOf(pref.isnInternacionalBBC()) + ";" + String.valueOf(pref.isnInternacionalEuroNews())
	 				+ ";" + String.valueOf(pref.isnInternacionalElPais()) + "\n");
	 			}
	 		}
	 		bufferedWriterUser.close();
	 		bufferedWriterPrefs.close();
	 	}catch(Exception e) {
	 		return false;
	 	}
	 	return true;
	}
	/*
	 * 		> modifyUsers() es un método encargado de modificar la información del usuario. Es casi idéntica a la de borrar usuarios
	 * 		excepto que en vez de convertirlo en null le pasamos los datos a actualizar.
	 * 
	 * 		Llamada por:
	 * 
	 * 		  - AdminSignInClass.java
	 * 
	 * 		Recibe los siguientes datos:
	 * 
	 *		  - id (int): El id del usuario a mofidicar.
	 *		  - Usuario (String): El nuevo nombre de usuario que se desea insertar.
	 *		  - Correo (String): El nuevo correo del usuario.
	 *		  - Contraseña (String): La nueva contraseña de usuario.
	 */
	public static boolean modifyUsers(int id, String Usuario, String Correo, String Contraseña) {
		try {	
	 		BufferedWriter bufferedWriterUser = new BufferedWriter(new FileWriter("src\\resources\\users.txt"));
	 		BufferedWriter bufferedWriterPrefs = new BufferedWriter(new FileWriter("src\\resources\\userprefs.txt"));
	 		//Iteramos la lista de usuarios una por una.
	 		Iterator<Usuario> iUsuarios = listaUsuarios.iterator();
			String userprefs = "";
	 		while(iUsuarios.hasNext()) {
	 			//Guardamos temporalmente cada usuario que sacamos.
	 			Usuario user = iUsuarios.next();
	 			/*
	 			 * 		Si el usuario que recogemos coincide con el que se le pasa por id, reemplazamos todos sus datos con
	 			 * 		los que le pasamos de los campos de texto, y guardamos su antiguo usuario.
	 			 */
	 			if(user.getid()==id) {
	 				userprefs = user.getUsuario();
	 				user.setUsuario(Usuario);
	 				user.setMail(Correo);
	 				user.setContraseña(Contraseña);
	 			}
	 			//Reescribimos la información del archivo entera con el usuario borrado actualizado.
	 			bufferedWriterUser.write(String.valueOf(user.getid()) + ";" + user.getUsuario() + ";" + user.getContraseña() + ";" + user.getMail() + ";" + String.valueOf(user.isEsAdmin())+ ";" + String.valueOf(user.isyaRegistrado()) + "\n");
	 		}
	 		//Iteramos la lista de preferencias una por una.
	 		Iterator<Preferencias> iPreferencias = listaPreferencias.iterator();
	 		while(iPreferencias.hasNext()) {
	 			//Guardamos temporalmente cada preferencia que sacamos.
	 			Preferencias pref = iPreferencias.next();
	 			//Si encontramos su usuario, se lo actualizamos por el nuevo.
	 			if(pref.getUsuario().equals(userprefs)) {
	 				pref.setUsuario(Usuario);
	 			}
	 			//Y reescribimos el fichero entero con los datos actualizados.
	 			bufferedWriterPrefs.write(pref.getUsuario() + ";" + String.valueOf(pref.isnDeporteMarca()) + ";" + String.valueOf(pref.isnDeporteDiaroAS()) + ";" + String.valueOf(pref.isnDeporteMundoDeportivo()) 
	 			+ ";" + String.valueOf(pref.isnDeporteElPais()) + ";" + String.valueOf(pref.isnEconomiaElEconomista()) + ";" + String.valueOf(pref.isnEconomiaElConfidencial()) + ";" + String.valueOf(pref.isnEconomiaExpansion())
	 			+ ";" + String.valueOf(pref.isnEconomiaElPais()) + ";" + String.valueOf(pref.isnNacionalLaRazon()) + ";" + String.valueOf(pref.isnNacionalABC()) + ";" + String.valueOf(pref.isnNacional20Minutos())
	 			+ ";" + String.valueOf(pref.isnNacionalElPais()) + ";" + String.valueOf(pref.isnInternacionalElMundo()) + ";" + String.valueOf(pref.isnInternacionalBBC()) + ";" + String.valueOf(pref.isnInternacionalEuroNews())
	 			+ ";" + String.valueOf(pref.isnInternacionalElPais()) + "\n");
	 		}
	 		bufferedWriterUser.close();
	 		bufferedWriterPrefs.close();
	 	}catch(Exception e) {
	 		return false;
	 	}
		return true;
	}
	/*
	 * 		> checkcheck() es un método de interfaz que comprueba las preferencias del usuario actual y las muestra
	 * 		  en la pantalla en forma de las checkboxes marcadas. Ésto se usa tanto por el usuario para visualizar
	 * 		  sus preferencias como por el ADMIN para editarlas.
	 * 
	 * 		Llamada por:
	 * 
	 * 		  - PrefsClass.java
	 * 		  - AdminPrefsClass.java
	 * 
	 * 		Recibe los siguientes datos:
	 * 
	 * 		  - user (String): El usuario actual o del cuál se están editando sus preferencias.
	 * 		  - n (int): La casilla de la cuál se comprueba si está marcada en ajustes o no.
	 */
	public static boolean checkcheck(String user, int n) {
		//Iteramos la lista de preferencias una por una.
		Iterator<Preferencias> iPreferencias = listaPreferencias.iterator();
		while(iPreferencias.hasNext()) {
			//Guardamos temporalmente cada preferencia que sacamos.
			Preferencias pref = iPreferencias.next();
			//Encontramos al usuario actual o del cual se están editando sus preferencias.
 			if(pref.getUsuario().equals(user)) {
 				//Dependiendo de la checkbox, comprobamos si el usuario la ha marcado o no.
 				switch(n) {
 					case 1:
 						if(pref.isnDeporteMarca()==true) {
 							return true;
 						}
 					break;
 					case 2:
 						if(pref.isnDeporteDiaroAS()==true) {
 							return true;
 						}
 	 				break;
 					case 3:
 						if(pref.isnDeporteMundoDeportivo()==true) {
 							return true;
 						}
 	 				break;
 					case 4:
 						if(pref.isnDeporteElPais()==true) {
 							return true;
 						}
 	 				break;
 					case 5:
 						if(pref.isnEconomiaElEconomista()==true) {
 							return true;
 						}
 	 				break;
 					case 6:
 						if(pref.isnEconomiaElConfidencial()==true) {
 							return true;
 						}
 	 				break;
 					case 7:
 						if(pref.isnEconomiaExpansion()==true) {
 							return true;
 						}
 	 				break;
 					case 8:
 						if(pref.isnEconomiaElPais()==true) {
 							return true;
 						}
 	 				break;
 					case 9:
 						if(pref.isnNacionalLaRazon()==true) {
 							return true;
 						}
 	 				break;
 					case 10:
 						if(pref.isnNacionalABC()==true) {
 							return true;
 						}
 	 				break;
 					case 11:
 						if(pref.isnNacional20Minutos()==true) {
 							return true;
 						}
 	 				break;
 					case 12:
 						if(pref.isnNacionalElPais()==true) {
 							return true;
 						}
 	 				break;
 					case 13:
 						if(pref.isnInternacionalElMundo()==true) {
 							return true;
 						}
 	 				break;
 					case 14:
 						if(pref.isnInternacionalBBC()==true) {
 							return true;
 						}
 	 				break;
 					case 15:
 						if(pref.isnInternacionalEuroNews()==true) {
 							return true;
 						}
 	 				break;
 					case 16:
 						if(pref.isnInternacionalElPais()==true) {
 							return true;
 						}
 	 				break;
 				}
 			}
		}
		return false;
	}
	/*
	 * 		> modifyPreferences() es un método utilizado por el ADMIN para actualizar las preferencias
	 * 		  del usuario que se está editando.
	 * 
	 * 		Llamada por:
	 * 
	 * 		  - AdminPrefsClass.java
	 * 
	 * 		Recibe los siguientes datos:
	 * 
	 * 		  - checkbox (JCheckBox[]): La array de checkboxes exactamente igual que en cuando el usuario introduce
	 *			sus preferencias por primera vez.
	 */
	public static boolean modifyPreferences(JCheckBox[] checkbox) {
		try{
			BufferedWriter bufferedWriterPrefs = new BufferedWriter(new FileWriter("src\\resources\\userprefs.txt"));
			//Creamos una array de booleanos asociados a las preferencias del usuario.
			boolean[] booleanArray;
			booleanArray = new boolean[16];
			int i = 0;
			//Hacemos un forEach a la array de checkboxes y si está marcada, le metemos un true a la primera iteración, sino false.
			for(JCheckBox c : checkbox) {
				if(c.isSelected()) {
					booleanArray[i] = true;
					i++;
				}else{
					booleanArray[i] = false;
					i++;
				}
			}
			//Iteramos la lista de preferencias una por una.
			Iterator<Preferencias> iPreferencias = listaPreferencias.iterator();
			while(iPreferencias.hasNext()) {
				//Guardamos temporalmente cada preferencia que sacamos.
				Preferencias pref = iPreferencias.next();
				//Y para el usuario actual registrado le actualizamos todas las preferencias en sus objetos.
				if(pref.getUsuario().equals(currentUser)) {	
					pref.setnDeporteMarca(booleanArray[0]);
					pref.setnDeporteDiaroAS(booleanArray[1]);
					pref.setnDeporteMundoDeportivo(booleanArray[2]);
					pref.setnDeporteElPais(booleanArray[3]);
					pref.setnEconomiaElEconomista(booleanArray[4]);
					pref.setnEconomiaElConfidencial(booleanArray[5]);
					pref.setnEconomiaExpansion(booleanArray[6]);
					pref.setnEconomiaElPais(booleanArray[7]);
					pref.setnNacionalLaRazon(booleanArray[8]);
					pref.setnNacionalABC(booleanArray[9]);
					pref.setnNacional20Minutos(booleanArray[10]);
					pref.setnNacionalElPais(booleanArray[11]);
					pref.setnInternacionalElMundo(booleanArray[12]);
					pref.setnInternacionalBBC(booleanArray[13]);
					pref.setnInternacionalEuroNews(booleanArray[14]);
					pref.setnInternacionalElPais(booleanArray[15]);
				}
				//Y reescribimos el fichero de preferencias entero con los datos actualizados.
				bufferedWriterPrefs.write(pref.getUsuario() + ";" + String.valueOf(pref.isnDeporteMarca()) + ";" + String.valueOf(pref.isnDeporteDiaroAS()) + ";" + String.valueOf(pref.isnDeporteMundoDeportivo()) 
				+ ";" + String.valueOf(pref.isnDeporteElPais()) + ";" + String.valueOf(pref.isnEconomiaElEconomista()) + ";" + String.valueOf(pref.isnEconomiaElConfidencial()) + ";" + String.valueOf(pref.isnEconomiaExpansion())
				+ ";" + String.valueOf(pref.isnEconomiaElPais()) + ";" + String.valueOf(pref.isnNacionalLaRazon()) + ";" + String.valueOf(pref.isnNacionalABC()) + ";" + String.valueOf(pref.isnNacional20Minutos())
				+ ";" + String.valueOf(pref.isnNacionalElPais()) + ";" + String.valueOf(pref.isnInternacionalElMundo()) + ";" + String.valueOf(pref.isnInternacionalBBC()) + ";" + String.valueOf(pref.isnInternacionalEuroNews())
				+ ";" + String.valueOf(pref.isnInternacionalElPais()) + "\n");
	
			}
			bufferedWriterPrefs.close();
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	/*
	 * 		> resetPrefs() es el método utilizado para resetear las preferencias de un usuario en específico.
	 * 		  al hacerlo, éste usuario puede volver a introducir sus preferencias de nuevo.
	 * 
	 * 		Llamada por:
	 * 
	 * 		  - AdminGestionarPreferenciasClass.java
	 * 
	 * 		Recibe los siguientes datos:
	 * 
	 * 		  - users (String): El usuario en específico del cuál se quieren borrar las preferencias.
	 * 			Se le llama desde el método checkUsers con los parámetros del id del usuario desde la interfaz.
	 */
	public static boolean resetPrefs(String users) {
		try {
			BufferedWriter bufferedWriterUser = new BufferedWriter(new FileWriter("src\\resources\\users.txt"));
			BufferedWriter bufferedWriterPrefs = new BufferedWriter(new FileWriter("src\\resources\\userprefs.txt"));
			//Preparamos la celda de la lista que se va a borrar.
			Preferencias deletePref = new Preferencias(users, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false);
			//Iteramos la lista de preferencias una por una.
			Iterator<Preferencias> iPreferencias = listaPreferencias.iterator();
			while(iPreferencias.hasNext()){
				//Guardamos temporalmente las preferenicias que recoge.
				Preferencias pref = iPreferencias.next();
				//Y si coincide el usuario de las preferencias con el que se le ha enviado omitimos de reescribir sus preferencias y borramos sus datos de memoria.
				if(!(pref.getUsuario().equals(users))) {
					bufferedWriterPrefs.write(pref.getUsuario() + ";" + String.valueOf(pref.isnDeporteMarca()) + ";" + String.valueOf(pref.isnDeporteDiaroAS()) + ";" + String.valueOf(pref.isnDeporteMundoDeportivo()) 
					+ ";" + String.valueOf(pref.isnDeporteElPais()) + ";" + String.valueOf(pref.isnEconomiaElEconomista()) + ";" + String.valueOf(pref.isnEconomiaElConfidencial()) + ";" + String.valueOf(pref.isnEconomiaExpansion())
					+ ";" + String.valueOf(pref.isnEconomiaElPais()) + ";" + String.valueOf(pref.isnNacionalLaRazon()) + ";" + String.valueOf(pref.isnNacionalABC()) + ";" + String.valueOf(pref.isnNacional20Minutos())
					+ ";" + String.valueOf(pref.isnNacionalElPais()) + ";" + String.valueOf(pref.isnInternacionalElMundo()) + ";" + String.valueOf(pref.isnInternacionalBBC()) + ";" + String.valueOf(pref.isnInternacionalEuroNews())
					+ ";" + String.valueOf(pref.isnInternacionalElPais()) + "\n");
				}else {
					deletePref = pref;
				}
			}
			listaPreferencias.remove(deletePref);
			//Iteramos la lista de usuarios una por una.
			Iterator<Usuario> iUsuarios = listaUsuarios.iterator();
			while(iUsuarios.hasNext()) {
				//Guardamos temporalmente cada usuario que sacamos.
				Usuario user = iUsuarios.next();
				//Y si el usuario que se le pasa coincide con el usuario de la lista, actualizamos su estado registrado a false y lo escribimos en el archivo.
				if(user.getUsuario().equals(users)) {
					user.setyaRegistrado(false);
				}
				bufferedWriterUser.write(user.getid() + ";" + user.getUsuario() + ";" + user.getContraseña() + ";" + user.getMail() + ";" + String.valueOf(user.isEsAdmin())+ ";" + String.valueOf(user.isyaRegistrado()) + "\n");
			}
			bufferedWriterUser.close();
			bufferedWriterPrefs.close();
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	/*
	 * 		> resetAction() es el método utilizado para resetear todas las preferencias de todos los usuarios.
	 * 		  Al resetearse, todos los usuarios pueden volver a escribir sus preferencias de nuevo.
	 * 
	 * 		Llamada por:
	 * 
	 * 		  - AdminGestionarPreferenciasClass.java
	 */
	public static boolean resetAction() {
		try {
			BufferedWriter bufferedWriterUser = new BufferedWriter(new FileWriter("src\\resources\\users.txt"));
			BufferedWriter bufferedWriterPrefs = new BufferedWriter(new FileWriter("src\\resources\\userprefs.txt"));
			//Vaciamos el archivo preferencias y sus objetos.
			bufferedWriterPrefs.write("");
			listaPreferencias.clear();
			//Iteramos la lista de usuarios una por una.
			Iterator<Usuario> iUsuarios = listaUsuarios.iterator();
			while(iUsuarios.hasNext()) {
				//Guardamos temporalmente cada usuario que sacamos.
				Usuario user = iUsuarios.next();
				//Y actualizamos su estado yaRegistrado como false, y lo reescribimos de nuevo en su archivo.
				user.setyaRegistrado(false);
				bufferedWriterUser.write(user.getid() + ";" + user.getUsuario() + ";" + user.getContraseña() + ";" + user.getMail() + ";" + String.valueOf(user.isEsAdmin())+ ";" + String.valueOf(user.isyaRegistrado()) + "\n");
			}
			bufferedWriterUser.close();
			bufferedWriterPrefs.close();
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	/*
	 * 		> emailSend() es el método principal para enviar correos tanto a los usuarios como al ADMIN.
	 * 		  construye el cliente del correo y después llama a otro método para contruir el correo, emailSend()
	 *		
	 *		Llamada por:
	 *
	 *		  - newsSender()
	 *		  - adminSendNews()
	 *		  - FeedbackClass.java
	 *
	 *		Recibe los siguientes datos:
	 *		
	 *		  - fromEmail (String): El emisor del correo.
	 *		  - toEmail (String): El receptor del correo.
	 *		  - title (String): El titulo del correo.
	 *		  - body (String): El cuerpo del correo.
	 */
	public static boolean emailSend(String fromEmail, String toEmail, String title, String body) {
		
		final String defaultEmail = "daniel.gonzalez.dosa1@gmail.com";
		final String password = "nqim vbmp aviu ntyh";
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP de Gmail
		props.put("mail.smtp.socketFactory.port", "465"); //Puerto SSL
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory
		props.put("mail.smtp.auth", "true"); //Activar autenticación SMTP
		props.put("mail.smtp.port", "465"); //SMTP Port
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(defaultEmail, password);
			}
		};
		Session session = Session.getDefaultInstance(props, auth); //Crea una sesión con todas las propiedades.
		/*
		 * 		> Llama a métodos de su misma clase:
		 * 		
		 * 			sendEmail(): Construye el correo y lo envía.
		 */
		if(sendEmail(session, fromEmail, toEmail, title, body)==false) {
			return false;
		}else {
			return true;
		}
	}
	/*
	 * 		> sendEmail() es el método añadido de emailSend() para procesar la sesión creada de gmail, construir
	 * 		  el cuerpo del correo y enviarlo.
	 *		
	 *		Llamada por:
	 *
	 *		  - emailSend()
	 *
	 *		Recibe los siguientes datos:
	 *			
	 *		  - session (Session): La sesión creada por el método superior emailSend()
	 *		  - fromEmail (String): El emisor del correo.
	 *		  - toEmail (String): El receptor del correo.
	 *		  - title (String): El titulo del correo.
	 *		  - body (String): El cuerpo del correo.
	 */
	private static boolean sendEmail(Session session, String fromEmail, String toEmail, String subject, String body) {
		try {
			MimeMessage msg = new MimeMessage(session);
			//Configurar cabeceras
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			
			msg.setFrom(new InternetAddress("no_reply@newsSender.com", fromEmail)); //Datos de ejemplo
			msg.setReplyTo(InternetAddress.parse("no_reply@newsSender.com", false));
			
			msg.setSubject(subject, "UTF-8");
			msg.setText(body, "UTF-8");
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			
			//Envía en mensaje.
			Transport.send(msg);
			
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
}
