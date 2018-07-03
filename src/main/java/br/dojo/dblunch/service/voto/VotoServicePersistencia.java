package br.dojo.dblunch.service.voto;

import java.io.IOException;
import java.time.LocalDate;

import br.dojo.dblunch.model.Profissional;
import br.dojo.dblunch.model.Restaurante;

public interface VotoServicePersistencia {
	void votar(Restaurante restaurante, Profissional profissional, LocalDate data) throws IOException;
}
