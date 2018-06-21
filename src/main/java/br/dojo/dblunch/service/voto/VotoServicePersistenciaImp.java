package br.dojo.dblunch.service.voto;

import java.util.Calendar;
import java.util.List;

import br.dojo.dblunch.model.Profissional;
import br.dojo.dblunch.model.Restaurante;
import br.dojo.dblunch.model.Voto;
import br.dojo.dblunch.repository.VotoRepository;

public class VotoServicePersistenciaImp implements VotoServicePersistencia{

	private VotoRepository votoRepository;
	private VotoConsulta votoConsulta;
	
	public VotoServicePersistenciaImp() {
		votoRepository = new VotoRepository();
		votoConsulta = new VotoServiceConsultaImp();
	}

	@Override
	public void votar(Restaurante restaurante, Profissional profissional, Calendar data) throws Exception {
		Voto voto = new Voto(restaurante, profissional, data);
		validarVoto(voto);
		votoRepository.incluir(voto);
	}
	
	private void validarVoto(Voto voto) throws Exception{
		validaProfissionalJaVotou(voto);
	}

	private void validaProfissionalJaVotou(Voto voto) throws Exception{
		List<Voto> listaVotosDiaAtual = votoConsulta.listarVotosDiaAtual();
		if(!listaVotosDiaAtual.isEmpty() && profissionalJaVotouHoje(voto, listaVotosDiaAtual)) {
			throw new Exception("Usuário já votou hoje.");
		}
	}

	private boolean profissionalJaVotouHoje(Voto voto, List<Voto> listaVotos) {
		return listaVotos.stream().anyMatch(v -> v.getProfissional().possuiNome(voto.getProfissional().getNome()));
	}
}
