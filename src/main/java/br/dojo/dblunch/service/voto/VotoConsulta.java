package br.dojo.dblunch.service.voto;

import java.util.List;

import br.dojo.dblunch.model.Voto;

public interface VotoConsulta {
	List<Voto> listar();
	
	List<Voto> listarVotosDiaAtual();
}
