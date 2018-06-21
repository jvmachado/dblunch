package br.dojo.dblunch.test.votarrestaurante;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/cucumber/US01VotarRestaurante.feature", monochrome = true, strict = true)
public class VotarRestauranteTest {
	
}
