package br.com.company.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.company.entity.Person;

class PersonDAOTest {

	@InjectMocks
	private PersonDAO personDAO;

	@Mock
	private EntityManager entityManager;

	@Mock
	private TypedQuery<Person> query;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() {
		Person person = new Person();
		person.setId(1L);
		when(entityManager.find(Person.class, 1L)).thenReturn(person);

		Person result = personDAO.findById(1L);
		assertEquals(1L, result.getId());
	}

	@Test
	void testFindByName() {
		List<Person> persons = Arrays.asList(new Person(), new Person());
		when(entityManager.createQuery(anyString(), eq(Person.class))).thenReturn(query);
		when(query.setParameter(anyString(), anyString())).thenReturn(query);
		when(query.getResultList()).thenReturn(persons);

		List<Person> result = personDAO.findByName("John");
		assertEquals(2, result.size());
	}

	@Test
	final void testFindAll() {
		List<Person> listPersons = Arrays.asList(new Person(), new Person());
		when(entityManager.createQuery("FROM Person", Person.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(listPersons);

        List<Person> result = personDAO.findAll();
        assertEquals(2, result.size());
	}

	@Test
	void testCreateOrUpdate() {
		Person person = new Person();
		when(entityManager.merge(person)).thenReturn(person);

		Person result = personDAO.createOrUpdate(person);
		assertNotNull(result);
	}

	@Test
	void testDelete() {
		Person person = new Person();
		person.setId(1L);
		when(entityManager.find(Person.class, 1L)).thenReturn(person);

		personDAO.delete(1L);
		verify(entityManager).remove(person);
	}

}
