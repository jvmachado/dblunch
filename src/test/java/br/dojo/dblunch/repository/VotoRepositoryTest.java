package br.dojo.dblunch.repository;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.dojo.dblunch.model.Restaurante;
import net.andreinc.mockneat.MockNeat;

public class VotoRepositoryTest {

	private static final String ENTITY_MANAGER_HSQL = "DBLunch-HSQL";
	private static final String ENTITY_MANAGER_PGQL = "DBLunch-PgQL";

	EntityManager entityManager;
	MockNeat m = MockNeat.threadLocal();

	@Before
	public void prepararTeste() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(ENTITY_MANAGER_HSQL);
		entityManager = emf.createEntityManager();

		entityManager.getTransaction().begin();
		List<Restaurante> restaurantes = m.filler(() -> new Restaurante())
				.setter(Restaurante::setNome, m.countries().names()).list(() -> new ArrayList<>(), 10) // elements.
				.val();
		restaurantes.stream().forEach(umRestaurante -> entityManager.persist(umRestaurante));
		entityManager.getTransaction().commit();

	}

	@After
	public void finalizarCenario() {
		entityManager.clear();
		entityManager.createNativeQuery("TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK");
	}

	@Test
	public void testConsultaSingle() {
		Query restauranteQuery = entityManager.createQuery(String.format("SELECT r FROM %s r WHERE %s = :id",
				Restaurante.class.getSimpleName(), Restaurante.FIELD_ID));
		restauranteQuery.setParameter(Restaurante.FIELD_ID, 1);

		Restaurante restauranteConsulta = (Restaurante) restauranteQuery.getSingleResult();

	}

	@Test
	public void testListarTipada() {
		TypedQuery<Restaurante> restauranteQuery = entityManager
				.createQuery(String.format("SELECT r FROM %s r", Restaurante.class.getName()), Restaurante.class);
		List<Restaurante> restaurantesConsulta = restauranteQuery.getResultList();

		assertEquals(10, restaurantesConsulta.size());
	}

	@Test
	public void testIncluir() {
		entityManager.getTransaction().begin();
		Restaurante aIncluir = Restaurante.builder().nome("Teste Incluir DBLunch").build();
		entityManager.persist(aIncluir);
		entityManager.getTransaction().commit();
	}

	@Test
	public void testDelete() {
		entityManager.getTransaction().begin();
		Query deleteQuery = entityManager.createNamedQuery(Restaurante.QUERY_DELETE);
		deleteQuery.setParameter(Restaurante.FIELD_ID, 3);
		entityManager.getTransaction().commit();
	}

	@Test
	public void testBulkUpdate() {
		entityManager.getTransaction().begin();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<Restaurante> update = criteriaBuilder.createCriteriaUpdate(Restaurante.class);

		Root<Restaurante> restaurante = update.from(Restaurante.class);

		update.set(Restaurante.FIELD_NOME, "Teste Bulk Update DBLunch");
		update.where(criteriaBuilder.greaterThanOrEqualTo(restaurante.get(Restaurante.FIELD_ID), 5));

		entityManager.createQuery(update).executeUpdate();
		entityManager.getTransaction().commit();
	}

	@Test
	public void testCriteria() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Restaurante> restaurante = criteriaQuery.from(Restaurante.class);
		criteriaQuery.select(criteriaBuilder.count(restaurante));
		Query query = entityManager.createQuery(criteriaQuery);
		Long result = (Long) query.getSingleResult();
	}

	@Test
	public void testBulkDelete() {
		entityManager.getTransaction().begin();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaDelete<Restaurante> delete = cb.createCriteriaDelete(Restaurante.class);

		Root<Restaurante> root = delete.from(Restaurante.class);

		delete.where(cb.greaterThanOrEqualTo(root.get(Restaurante.FIELD_ID), 8));

		entityManager.createQuery(delete).executeUpdate();
		entityManager.getTransaction().commit();
	}

}
