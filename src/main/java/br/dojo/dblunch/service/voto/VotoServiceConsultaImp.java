package br.dojo.dblunch.service.voto;

import java.util.List;

import br.dojo.dblunch.model.Voto;
import br.dojo.dblunch.repository.VotoRepository;

public class VotoServiceConsultaImp implements VotoConsulta{

	private VotoRepository votoRepository;
	
	public VotoServiceConsultaImp() {
		votoRepository = new VotoRepository();
	}
	
	@Override
	public List<Voto> listar() {
		return votoRepository.listar();
	}

	@Override
	public List<Voto> listarVotosDiaAtual() {
		return votoRepository.listarVotosDiaAtual();
	}
}
