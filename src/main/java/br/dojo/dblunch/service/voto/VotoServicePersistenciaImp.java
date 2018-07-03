package br.dojo.dblunch.service.voto;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import br.dojo.dblunch.model.Profissional;
import br.dojo.dblunch.model.Restaurante;
import br.dojo.dblunch.model.Voto;
import br.dojo.dblunch.repository.VotoRepository;

public class VotoServicePersistenciaImp implements VotoServicePersistencia {

	private VotoRepository votoRepository;
	private VotoConsulta votoConsulta;

	public VotoServicePersistenciaImp() {
		votoRepository = new VotoRepository();
		votoConsulta = new VotoServiceConsultaImp(votoRepository);
	}

	@Override
	public void votar(Restaurante restaurante, Profissional profissional, LocalDate data) throws IOException {
		Voto voto = new Voto(restaurante, profissional, data);
		validarVoto(voto);
		votoRepository.incluir(voto);
	}

	private void validarVoto(Voto voto) throws IOException {
		validaProfissionalJaVotou(voto);
	}

	private void validaProfissionalJaVotou(Voto voto) throws IOException {
		List<Voto> listaVotosDiaAtual = votoConsulta.listarVotosDiaAtual();
		if (!listaVotosDiaAtual.isEmpty() && profissionalJaVotouHoje(voto, listaVotosDiaAtual)) {
			throw new IOException("Usuario ja votou hoje.");
		}
	}

	private boolean profissionalJaVotouHoje(Voto voto, List<Voto> listaVotos) {
		return listaVotos.stream().anyMatch(v -> v.getProfissional().possuiNome(voto.getProfissional().getNome()));
	}
}
