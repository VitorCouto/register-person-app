package br.com.company.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.company.entity.Address;

public class AddressDAO {

	@Inject
	private EntityManager entityManager;

	public AddressDAO() {
	}

	public AddressDAO(EntityManager em) {
		this.entityManager = em;
	}

	public Address findById(Long id) {
		return entityManager.find(Address.class, id);
	}

	public List<Address> findAll() {
		return entityManager.createQuery("From Address", Address.class).getResultList();
	}
	
	public List<Address> findAllAddressByUser(Long id) {
		String jpql = "FROM Address WHERE person_id = :id";
		return entityManager.createQuery(jpql, Address.class).setParameter("id", id).getResultList();
	}

	public Address createOrUpdate(Address address) {
		return entityManager.merge(address);
	}

	public void delete(Long id) {
		Address address = findById(id);
		if (address.getId() != null) {
			entityManager.remove(address);
		}
	}

}
