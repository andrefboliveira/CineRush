package pt.ulisboa.ciencias.cinerush.backend;


import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/*
* Classe para guardar os dados processados
*/

public class Sessao {

	int numeroSessao;
	int numeroFilme;
	int numeroCinema;
	String preco;
	String dataInicio;
	String dataFim;
	String horario;
	
	private Double latitude;
	private Double longitude;
	

	
	public Sessao(Element element, Filme filme, Cinema cinema) {
		this.numeroSessao = Integer.parseInt(element.getElementsByTagName("NumeroExibicao").item(0).getTextContent());
		this.preco = element.getElementsByTagName("PrecoEntrada").item(0).getTextContent();
		this.dataInicio = (element.getElementsByTagName("DataInicio").item(0).getTextContent());
		this.dataFim = element.getElementsByTagName("DataFim").item(0).getTextContent();
		this.horario = element.getElementsByTagName("Horario").item(0).getTextContent();
		this.numeroFilme = filme.getNumeroFilme();
		this.numeroCinema = cinema.getNumeroCinema();
		this.latitude = cinema.getLatitude();
		this.longitude = cinema.getLongitude();
		cinema.addSessao(numeroSessao);

	}
	

	public int getNumeroSessao() {
		return numeroSessao;
	}


	public void setNumeroSessao(int numeroSessao) {
		this.numeroSessao = numeroSessao;
	}


	public String getPreco() {
		return preco;
	}


	public void setPreco(String preco) {
		this.preco = preco;
	}


	public String getDataInicio() {
		return dataInicio;
	}


	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}


	public String getDataFim() {
		return dataFim;
	}


	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}


	public String getHorario() {
		return horario;
	}


	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	
}
