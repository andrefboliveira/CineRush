package pt.ulisboa.ciencias.cinerush.backend;


import org.w3c.dom.Element;

/*
* Classe para guardar os dados processados
*/
public class Interprete {
	
	int numeroInterprete;
	String nome;
	String funcao;
	
	
	public Interprete(int numeroInterprete, Element element) {
		this.numeroInterprete = numeroInterprete;
		
		this.nome = element.getElementsByTagName("Interprete").item(0).getTextContent();
		this.funcao = element.getElementsByTagName("Funcao").item(0).getTextContent();

	}
	

	public int getNumeroInterprete() {
		return numeroInterprete;
	}


	public void setNumeroInterprete(int numeroInterprete) {
		this.numeroInterprete = numeroInterprete;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getFuncao() {
		return funcao;
	}


	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	

	
}
