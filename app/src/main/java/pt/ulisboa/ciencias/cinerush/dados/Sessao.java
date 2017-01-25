package pt.ulisboa.ciencias.cinerush.dados;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sessao {
	
	int numeroSessao;
	int numeroFilme;
	int numeroCinema;
	String preco;
	Date dataInicio;
	Date dataFim;
	String horario;
	Double latitude;
	Double longitude;

    static SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");

	public Sessao(){
	}

	public Sessao(int numeroSessao, int numeroFilme, int numeroCinema, String preco, String dataInicio, String dataFim,
                  String horario, Double latitude, Double longitude) {
		this.numeroSessao = numeroSessao;
		this.numeroFilme = numeroFilme;
		this.numeroCinema = numeroCinema;
		this.preco = preco;
		this.horario = horario;
		this.latitude = latitude;
		this.longitude = longitude;

        try {
            this.dataInicio = format.parse(dataInicio);
        } catch (ParseException e1) {
            this.dataInicio = null;
        }
        try {
            this.dataFim = format.parse(dataFim);
        } catch (ParseException e1) {
            this.dataFim = null;
        }
	}

	public int getNumeroSessao() {
		return numeroSessao;
	}

	public void setNumeroSessao(int numeroSessao) {
		this.numeroSessao = numeroSessao;
	}

	public int getNumeroFilme() {
		return numeroFilme;
	}

	public void setNumeroFilme(int numeroFilme) {
		this.numeroFilme = numeroFilme;
	}

	public int getNumeroCinema() {
		return numeroCinema;
	}

	public void setNumeroCinema(int numeroCinema) {
		this.numeroCinema = numeroCinema;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
        try {
            this.dataInicio = format.parse(dataInicio);
        } catch (ParseException e1) {
            this.dataInicio = null;
        }
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
        try {
            this.dataFim = format.parse(dataFim);
        } catch (ParseException e1) {
            this.dataFim = null;
        }
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
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

}
