package newsSender;

public class Usuario {
	
	public int id;
	public String usuario;
	public String contraseña;
	public String mail;
	public boolean esAdmin;
	public boolean yaRegistrado;
	
	public int getid() {
		return id;
	}
	public void setid(int id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public boolean isEsAdmin() {
		return esAdmin;
	}
	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}
	public boolean isyaRegistrado() {
		return yaRegistrado;
	}
	public void setyaRegistrado(boolean yaRegistrado) {
		this.yaRegistrado = yaRegistrado;
	}
	public Usuario(int id, String usuario, String contraseña, String mail, boolean esAdmin, boolean yaRegistrado) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.mail = mail;
		this.esAdmin = esAdmin;
		this.yaRegistrado = yaRegistrado;
	}
	
}
