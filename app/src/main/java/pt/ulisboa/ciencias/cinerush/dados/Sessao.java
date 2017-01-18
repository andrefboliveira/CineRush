package pt.ulisboa.ciencias.cinerush.dados;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sessao {
	
	int numeroSessao;
	int filme;
	int cinema;
	String preco;
	Date dataInicio;
	Date dataFim;
	String horario;
	Double latitude;
	Double longitude;

    static SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");

	public Sessao(){
	}

	public Sessao(int numeroSessao, int filme, int cinema, String preco, String dataInicio, String dataFim,
			String horario, Double latitude, Double longitude) {
		this.numeroSessao = numeroSessao;
		this.filme = filme;
		this.cinema = cinema;
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

	public int getFilme() {
		return filme;
	}

	public void setFilme(int filme) {
		this.filme = filme;
	}

	public int getCinema() {
		return cinema;
	}

	public void setCinema(int cinema) {
		this.cinema = cinema;
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
