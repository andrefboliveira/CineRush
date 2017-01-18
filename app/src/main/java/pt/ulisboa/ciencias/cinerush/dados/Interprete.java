package pt.ulisboa.ciencias.cinerush.dados;

public class Interprete {
	
	int numeroInterprete;
	String nome;
	String funcao;

	public Interprete(){
	}

	public Interprete(int numeroInterprete, String nome, String funcao) {
		this.numeroInterprete = numeroInterprete;
		this.nome = nome;
		this.funcao = funcao;
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
