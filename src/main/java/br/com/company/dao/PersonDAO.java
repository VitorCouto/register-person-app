package br.com.company.dao;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.company.entity.Person;

public class PersonDAO {

	@Inject
	private EntityManager entityManager;

	public PersonDAO() {
	}

	public PersonDAO(EntityManager em) {
		this.entityManager = em;
	}

	public Person findById(Long id) {
		return entityManager.find(Person.class, id);
	}

	public List<Person> findByName(String name) {
		String jpql = "FROM Person WHERE name like :name";
		return entityManager.createQuery(jpql, Person.class).setParameter("name", "%" + name + "%").getResultList();
	}
	
	public List<Person> findAll() {
		return entityManager.createQuery("FROM Person", Person.class).getResultList();
	}

	public Person createOrUpdate(Person person) {
		if (person.getId() == null) {
			person.setDateCreate(new Date());
		}
		return entityManager.merge(person);
	}

	public void delete(Long id) {
		Person person = findById(id);
		if (person != null) {
			entityManager.remove(person);
		}
	}

}
