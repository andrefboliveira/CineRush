package pt.ulisboa.ciencias.cinerush.dados;

public class Cinema {

	int numeroCinema;
	String nome;
	String morada;
	String codigoPostal;
	String distrito;
	String concelho;
	String telefone;
	String webSite;
	String bilheteira;
	String email;
	
	public Cinema(int numeroCinema, String nome, String morada, String codigoPostal, String distrito, String concelho,
			String telefone, String webSite, String bilheteira, String email) {
		this.numeroCinema = numeroCinema;
		this.nome = nome;
		this.morada = morada;
		this.codigoPostal = codigoPostal;
		this.distrito = distrito;
		this.concelho = concelho;
		this.telefone = telefone;
		this.webSite = webSite;
		this.bilheteira = bilheteira;
		this.email = email;
	}

	public int getNumeroCinema() {
		return numeroCinema;
	}

	public void setNumeroCinema(int numeroCinema) {
		this.numeroCinema = numeroCinema;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getConcelho() {
		return concelho;
	}

	public void setConcelho(String concelho) {
		this.concelho = concelho;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getBilheteira() {
		return bilheteira;
	}

	public void setBilheteira(String bilheteira) {
		this.bilheteira = bilheteira;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
