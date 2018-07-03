package br.dojo.dblunch.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Entity
@Table(name = "restaurante")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
@NamedQueries(value = {
		@NamedQuery(name = Restaurante.QUERY_UPDATE, query = "update Restaurante set nome = :nome where id =:id"),
		@NamedQuery(name = Restaurante.QUERY_DELETE, query = "delete Restaurante where id =:id") })
public class Restaurante {

	public static final String QUERY_UPDATE = "Restaurante.Named.Update";
	public static final String QUERY_DELETE = "Restaurante.Named.Delete";

	@Id
	@GeneratedValue(generator = "RestauranteSequence", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column
	private String nome;

	@OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Voto> votos;

	public Restaurante(String nome) {
		this.nome = nome;
	}
}
