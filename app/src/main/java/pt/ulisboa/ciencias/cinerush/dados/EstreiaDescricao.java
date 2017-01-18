package pt.ulisboa.ciencias.cinerush.dados;

import android.net.Uri;

import java.util.List;

public class EstreiaDescricao extends Estreia {

	String pais;
	int ano;
	String descricao;
	String duracao;
	List<Interprete> interpretes;

	public EstreiaDescricao(int numeroEstreia, String titulo, String tituloOriginal, String genero, String imagem, String pais, int ano,
							String estreia, String descricao, String duracao) {
        super(numeroEstreia, titulo, tituloOriginal, genero, imagem, estreia);
		this.pais = pais;
		this.ano = ano;
		this.descricao = descricao;
		this.duracao = duracao;
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

	public List<Interprete> getInterpretes() {
		return interpretes;
	}

//	public void setInterpretes(List<Interprete> interpretes) {
//		this.interpretes = interpretes;
//	}
	
	public void addInterpretes(Interprete interprete) {
		this.interpretes.add(interprete);
	}
	
	public void removeInterpretes(Interprete interprete) {
		this.interpretes.remove(interprete);
	}
}
