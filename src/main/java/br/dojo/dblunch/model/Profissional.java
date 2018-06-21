package br.dojo.dblunch.model;

public class Profissional {
	
	private String nome;

	public Profissional(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public Boolean possuiNome(String nome) {
		return this.nome.equals(nome);
	}
	
}
