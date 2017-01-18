package pt.ulisboa.ciencias.cinerush.dados;

import android.net.Uri;
import java.util.List;

public class Estreia extends FilmeBasico {

	String estreia;


	public Estreia (int numeroEstreia, String titulo, String tituloOriginal, String genero, String imagem, String estreia) {
        super(numeroEstreia, titulo, tituloOriginal, genero, imagem);
		this.estreia = estreia;
	}


	public String getEstreia() {
		return estreia;
	}
	public void setEstreia(String estreia) {
		this.estreia = estreia;
	}

}
