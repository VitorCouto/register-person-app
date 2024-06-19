package br.com.company.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.company.dao.AddressDAO;
import br.com.company.dao.PersonDAO;
import br.com.company.entity.Address;
import br.com.company.entity.Person;

class PersonServiceTest {

	@InjectMocks
	private PersonService personService;

	@Mock
	private PersonDAO personDAO;

	@Mock
	private AddressDAO addressDAO;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreatePersonWithAddress() {
		Person person = new Person();
		Address address = new Address();
		when(personDAO.createOrUpdate(person)).thenReturn(person);

		personService.createPersonWithAddress(person, address);
		verify(personDAO).createOrUpdate(person);
		verify(addressDAO).createOrUpdate(address);
	}

	@Test
	void testUpdatePerson() {
		Person person = new Person();
		when(personDAO.createOrUpdate(person)).thenReturn(person);

		Person result = personService.updatePerson(person);
		assertNotNull(result);
		verify(personDAO).createOrUpdate(person);
	}

	@Test
	void testDeletePerson() {
		Person person = new Person();
		person.setId(1L);

		personService.deletePerson(person);
		verify(personDAO).delete(1L);
	}

	@Test
	void testGetAllPersons() {
		List<Person> persons = Arrays.asList(new Person(), new Person());
		when(personDAO.findAll()).thenReturn(persons);

		List<Person> result = personService.getAllPersons();
		assertEquals(2, result.size());
	}

	@Test
	void testFindPersonByName() {
		List<Person> persons = Arrays.asList(new Person(), new Person());
		when(personDAO.findByName("John")).thenReturn(persons);

		List<Person> result = personService.findPersonByName("John");
		assertEquals(2, result.size());
	}

}
