package br.dojo.dblunch.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Voto {

	@EmbeddedId
	private VotoId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("restaurante_id")
	private Restaurante restaurante;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("profissional_id")
	private Profissional profissional;

	@Column
	private LocalDateTime data;

	public Voto(Restaurante restaurante, Profissional profissional, LocalDateTime data) {
		this.restaurante = restaurante;
		this.profissional = profissional;
		this.data = data;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public boolean possuiData(LocalDateTime data) {
		return this.data.equals(data);
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

}
