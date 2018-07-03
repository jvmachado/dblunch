package br.dojo.dblunch.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class VotoId implements Serializable {

	@Column(name = "restaurante_id")
	private Integer restauranteId;
	@Column(name = "profissional_id")
	private Integer profissionalId;
}
