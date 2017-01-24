package pt.ulisboa.ciencias.cinerush.backend;


import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

/*
* Classe para guardar os dados processados
*/

public class Filme {
	
	int numeroFilme;

	String titulo;
	String tituloOriginal;
	String genero;
	String pais;
	int ano;
	String estreia;
	static final String PATH_IMAGEM = "http://sercultur.pt/images/filmes/";
	URL imagem;
	String sumario;
	String descricao;
	String duracao;
	String minData;
	String maxData;
	private List<Interprete> interpretesInfo;
	List<Integer> interpretes;

	

	
	public Filme(int numeroFilme, Element element) {
		this.numeroFilme = numeroFilme;
		this.titulo = element.getElementsByTagName("Filme").item(0).getTextContent();
		this.tituloOriginal = element.getElementsByTagName("TituloOriginal").item(0).getTextContent();;
		this.genero = element.getElementsByTagName("Genero").item(0).getTextContent();;
		this.pais = element.getElementsByTagName("Pais").item(0).getTextContent();
		try {
			this.ano  = Integer.parseInt(element.getElementsByTagName("Ano").item(0).getTextContent());
		} catch (NumberFormatException e1) {
			System.err.println("Ano nao definido");		
		}
		this.estreia = element.getElementsByTagName("Estreia").item(0).getTextContent();

		String imagemParcial = element.getElementsByTagName("Imagem").item(0).getTextContent();

		try {
			this.imagem = new URL(PATH_IMAGEM + imagemParcial);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		this.sumario = element.getElementsByTagName("Sumario").item(0).getTextContent();
		this.descricao = element.getElementsByTagName("Descricao").item(0).getTextContent();
		this.duracao = element.getElementsByTagName("Duracao").item(0).getTextContent();

		this.minData = element.getElementsByTagName("MinData").item(0).getTextContent();
		this.maxData = element.getElementsByTagName("MaxData").item(0).getTextContent();
	
		this.interpretesInfo = new ArrayList<>();
		this.interpretes = new ArrayList<>();
		

	}
		
	public int getNumeroFilme() {
		return numeroFilme;
	}
	
	public void setNumeroFilme(int numeroFilme) {
		this.numeroFilme = numeroFilme;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getTituloOriginal() {
		return tituloOriginal;
	}
	
	public void setTituloOriginal(String tituloOriginal) {
		this.tituloOriginal = tituloOriginal;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
		
	public String getPais() {
		return pais;
	}
	
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public int getAno() {
		return ano;
	}
	
	public void setAno(int ano) {
		this.ano = ano;
	}
	
	public String getEstreia() {
		return estreia;
	}
	
	public void setEstreia(String estreia) {
		this.estreia = estreia;
	}
	
	
	public URL getImagem() {
		return imagem;
	}
	
	public void setImagem(URL imagem) {
		this.imagem = imagem;
	}
	
	public String getSumario() {
		return sumario;
	}
	
	public void setSumario(String sumario) {
		this.sumario = sumario;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDuracao() {
		return duracao;
	}
	
	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}
	
	public String getMinData() {
		return minData;
	}
	
	public void setMinData(String minData) {
		this.minData = minData;
	}
	
	public String getMaxData() {
		return maxData;
	}
	
	public void setMaxData(String maxData) {
		this.maxData = maxData;
	}
	
	
	public void addInterpretes(Interprete interprete) {
		this.interpretesInfo.add(interprete);
		this.interpretes.add(interprete.getNumeroInterprete());
	}
	
	public void removeInterpretes(Interprete interprete) {
		this.interpretesInfo.remove(interprete);
		this.interpretes.remove(interprete.getNumeroInterprete());
	}
}
