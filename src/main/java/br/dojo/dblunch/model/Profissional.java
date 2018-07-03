package br.dojo.dblunch.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "profissional")
public class Profissional {

	@Id
	@GeneratedValue(generator = "ProfissionalSequence", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column
	private String nome;

	@OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Voto> votos;

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
