package br.dojo.dblunch.model;

import java.util.Calendar;

public class Voto {

	private Restaurante restaurante;

	private Profissional profissional;
	
	private Calendar data;
	
	public Voto(Restaurante restaurante, Profissional profissional, Calendar data) {
		this.restaurante = restaurante;
		this.profissional = profissional;
		this.data = data;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public boolean possuiData(Calendar data) {
		return this.data.get(Calendar.DAY_OF_YEAR) == data.get(Calendar.DAY_OF_YEAR);
	}
	
}
