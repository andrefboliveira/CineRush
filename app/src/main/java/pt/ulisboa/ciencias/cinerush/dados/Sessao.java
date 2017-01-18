package pt.ulisboa.ciencias.cinerush.dados;

import java.util.Date;

public class Sessao {
	
	int numeroSessao;
	FilmeBasico filme;
	Cinema cinema;
	String preco;
	Date dataInicio;
	Date dataFim;
	String horario;
	Double latitude;
	Double longitude;
	
	public Sessao(int numeroSessao, FilmeBasico filme, Cinema cinema, String preco, Date dataInicio, Date dataFim,
			String horario, Double latitude, Double longitude) {
		this.numeroSessao = numeroSessao;
		this.filme = filme;
		this.cinema = cinema;
		this.preco = preco;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.horario = horario;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getNumeroSessao() {
		return numeroSessao;
	}

	public void setNumeroSessao(int numeroSessao) {
		this.numeroSessao = numeroSessao;
	}

	public FilmeBasico getFilme() {
		return filme;
	}

	public void setFilme(FilmeBasico filme) {
		this.filme = filme;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
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

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
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
