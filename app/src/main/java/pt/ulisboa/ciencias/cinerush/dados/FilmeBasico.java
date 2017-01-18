package pt.ulisboa.ciencias.cinerush.dados;

import android.net.Uri;

public class FilmeBasico {

	int numeroFilme;
	String titulo;
	String tituloOriginal;
	String genero;
	Uri imagem;
	
	public FilmeBasico(int id, String titulo, String tituloOriginal, String genero, Uri imagem) {
		this.numeroFilme = id;
		this.titulo = titulo;
        this.tituloOriginal = tituloOriginal;
        this.genero = genero;
		this.imagem = imagem;
	}
	public int getNumeroFilme() {
		return numeroFilme;
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
	public Uri getImagem() {
		return imagem;
	}
	public void setImagem(Uri imagem) {
		this.imagem = imagem;
	}
}
