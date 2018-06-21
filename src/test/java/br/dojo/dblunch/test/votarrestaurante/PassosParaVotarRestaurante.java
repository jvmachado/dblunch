package br.dojo.dblunch.test.votarrestaurante;

import java.util.Calendar;

import org.junit.Assert;

import br.dojo.dblunch.bd.BancoDados;
import br.dojo.dblunch.model.Profissional;
import br.dojo.dblunch.model.Restaurante;
import br.dojo.dblunch.service.voto.VotoConsulta;
import br.dojo.dblunch.service.voto.VotoServiceConsultaImp;
import br.dojo.dblunch.service.voto.VotoServicePersistencia;
import br.dojo.dblunch.service.voto.VotoServicePersistenciaImp;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class PassosParaVotarRestaurante {

	private VotoServicePersistencia votoService;
	private VotoConsulta votoConsulta;
	private Restaurante restaurante;
	private String mensagemErro;
	private Profissional profissional;
	
	@After
	public void finalizarCenario() {
		BancoDados.getInstance().limparBanco();
	}

	@Before
	public void prepararCenario() {
		this.votoService = new VotoServicePersistenciaImp();
		this.votoConsulta = new VotoServiceConsultaImp();
	}

	@Dado("^que o profissional \"([^\"]*)\" selecionou o restaurante \"([^\"]*)\"$")
	public void que_o_profissional_selecionou_o_restaurante(String nomeProfissional, String nomeRestaurante) throws Throwable {
		restaurante = new Restaurante(nomeRestaurante);
		profissional = new Profissional(nomeProfissional);
	}

	@Dado("^que o profissional \"([^\"]*)\" votou no restaurante \"([^\"]*)\"$")
	public void que_o_profissional_votou_no_restaurante(String nomeProfissional, String nomeRestaurante) throws Throwable {
		Restaurante restaurante = new Restaurante(nomeRestaurante);
		Profissional profissional = new Profissional(nomeProfissional);
		votoService.votar(restaurante, profissional, Calendar.getInstance());
	}
	
	@Dado("^que no dia anterior o profissional \"([^\"]*)\" votou no restaurante \"([^\"]*)\"$")
	public void que_no_dia_anterior_o_profissional_votou_no_restaurante(String nomeProfissional, String nomeRestaurante) throws Throwable {
		Restaurante restaurante = new Restaurante(nomeRestaurante);
		Profissional profissional = new Profissional(nomeProfissional);
		Calendar dataAnterior = Calendar.getInstance();
		dataAnterior.add(Calendar.DATE, -1);
		votoService.votar(restaurante, profissional, dataAnterior);
	}

	@Quando("^votar no restaurante$")
	public void votar_no_restaurante() throws Throwable {
		try {
			votoService.votar(restaurante, profissional, Calendar.getInstance());
		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}
	}

	@Entao("^o voto não deve ser salvo$")
	public void o_voto_nao_deve_ser_salvo() throws Throwable {

	}

	@Entao("^deve receber uma mensagem de aviso \"([^\"]*)\"$")
	public void deve_receber_uma_mensagem_de_aviso(String mensagemEsperada) throws Throwable {
		Assert.assertEquals(mensagemEsperada, mensagemErro);
	}

	@Entao("^o voto deve ser salvo$")
	public void o_voto_deve_ser_salvo() throws Throwable {
		Assert.assertFalse(votoConsulta.listar().isEmpty());
	}

	@Entao("^deve receber uma mensagem de aviso$")
	public void deve_receber_uma_mensagem_de_aviso() throws Throwable {
		Assert.assertNotNull(mensagemErro);
	}

}
