package br.dojo.dblunch.bd;

import java.util.ArrayList;
import java.util.List;

import br.dojo.dblunch.model.Voto;

public class BancoDados {

	private static BancoDados instance;
	private List<Voto> tabelaVotos = new ArrayList<Voto>();
	private BancoDados() {
	}

	public static BancoDados getInstance() {
		if (instance == null) {
			instance = new BancoDados();
		}

		return instance;
	}

	public void incluir(Voto voto) {
		tabelaVotos.add(voto);
	}

	public List<Voto> getListaVoto() {
		return tabelaVotos;
	}
	
	public void limparBanco() {
		tabelaVotos.clear();
	}
}
