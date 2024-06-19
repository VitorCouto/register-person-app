package br.com.company.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.company.dao.AddressDAO;
import br.com.company.dao.PersonDAO;
import br.com.company.entity.Address;
import br.com.company.entity.Person;
import br.com.company.util.Transacional;

public class PersonService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PersonDAO personDAO;

	@Inject
	private AddressDAO addressDAO;

	@Transacional
	public void createPersonWithAddress(Person person, Address address) {
		Person p = personDAO.createOrUpdate(person);
		address.setPerson(p);
		addressDAO.createOrUpdate(address);
	}

	@Transacional
	public Person updatePerson(Person person) {
		return personDAO.createOrUpdate(person);
	}

	@Transacional
	public void deletePerson(Person person) {
		personDAO.delete(person.getId());
	}

	public List<Person> getAllPersons() {
		return personDAO.findAll();
	}

	public List<Person> findPersonByName(String name) {
		return personDAO.findByName(name);
	}

}
