package br.dojo.dblunch.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.dojo.dblunch.bd.BancoDados;
import br.dojo.dblunch.model.Voto;

public class VotoRepository {
	private BancoDados bancoDados;

	public VotoRepository() {
		bancoDados = BancoDados.getInstance();
	}

	public List<Voto> listar() {
		return bancoDados.getListaVoto();
	}

	public void incluir(Voto voto) {
		bancoDados.incluir(voto);
	}

	public List<Voto> listarVotosDiaAtual() {
		return bancoDados.getListaVoto().stream().filter(v -> v.possuiData(LocalDate.now()))
				.collect(Collectors.toList());
	}
}
