package pt.ulisboa.ciencias.cinerush.backend;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

/*
* Classe para guardar os dados processados
*/

public class Cinema {
	int numeroCinema;
	String nome;
	String morada;
	String codigoPostal;
	String distrito;
	String concelho;
	Double latitude;
	Double longitude;
	String telefone;
	URL webSite;
	URL bilheteira;
	String email;
	List<Integer> listaSessoes;
	
	public Cinema(int numeroCinema, Element element){
		this.numeroCinema = numeroCinema;
		
		this.nome = element.getElementsByTagName("Cinema").item(0).getTextContent();
		this.morada = element.getElementsByTagName("Morada").item(0).getTextContent();
		
		String codigoPostal1 = element.getElementsByTagName("CodPostal").item(0).getTextContent();
		String codigoPostal2 = element.getElementsByTagName("CodPostal2").item(0).getTextContent();
		
		String codigoPostalLocal = element.getElementsByTagName("CodPostalDesc").item(0).getTextContent();
		this.codigoPostal = getCodigoPostal(codigoPostal1, codigoPostal2, codigoPostalLocal);
		
		this.distrito = element.getElementsByTagName("Distrito").item(0).getTextContent();
		this.concelho = element.getElementsByTagName("Concelho").item(0).getTextContent();
	
		
		this.latitude = Double.parseDouble(element.getElementsByTagName("Latitude1").item(0).getTextContent());
		this.longitude = Double.parseDouble(element.getElementsByTagName("Longitude1").item(0).getTextContent());
		this.telefone = element.getElementsByTagName("TelefoneLocal").item(0).getTextContent();
		
		try {
			this.webSite = new URL(element.getElementsByTagName("WebSite").item(0).getTextContent());
		} catch (Exception e) {
			System.err.println("webSite nao definida");
		}
		
		try {
			this.bilheteira = new URL(element.getElementsByTagName("BilheteiraSimples").item(0).getTextContent());
		} catch (Exception e) {
			System.err.println("bilheteira nao definida");
		}
		
		this.email = element.getElementsByTagName("Email").item(0).getTextContent();
		this.listaSessoes = new ArrayList<>();
	};

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
	
	public static String getCodigoPostal(String codigoPostal1, String codigoPostal2, String codigoPostalLocal) {
		StringBuilder builder = new StringBuilder();
		builder.append(codigoPostal1);
		builder.append("-");
		builder.append(codigoPostal2);
		builder.append(" ");
		builder.append(codigoPostalLocal);
		return builder.toString();
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

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public URL getWebSite() {
		return webSite;
	}

	public void setWebSite(URL webSite) {
		this.webSite = webSite;
	}

	public URL getBilheteira() {
		return bilheteira;
	}

	public void setBilheteira(URL bilheteira) {
		this.bilheteira = bilheteira;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void addSessao(int numeroSessao) {
		this.listaSessoes.add(numeroSessao);
	}
	
	public void removeSessao(int numeroSessao) {
		this.listaSessoes.remove(numeroSessao);
	}
	
}
