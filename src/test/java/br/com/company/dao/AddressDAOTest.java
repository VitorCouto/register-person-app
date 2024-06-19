package br.com.company.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import br.com.company.entity.Address;

class AddressDAOTest {

	@InjectMocks
	private AddressDAO addressDAO;

	@Mock
	private EntityManager entityManager;

	@Mock
	private TypedQuery<Address> query;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	final void testFindById() {
		Address address = new Address();
		address.setId(1L);
		when(entityManager.find(Address.class, 1L)).thenReturn(address);

		Address result = addressDAO.findById(1L);
		assertEquals(1L, result.getId());
	}

	@Test
	void testFindAll() {
		List<Address> addresses = Arrays.asList(new Address(), new Address());
        when(entityManager.createQuery("FROM Address WHERE person_id = :id", Address.class)).thenReturn(query);
        when(query.setParameter("id", 1L)).thenReturn(query);
        when(query.getResultList()).thenReturn(addresses);

        List<Address> result = addressDAO.findAllAddressByUser(1L);
        assertEquals(2, result.size());
	}

	@Test
	void testFindAllAddressByUser() {
		List<Address> addresses = Arrays.asList(new Address(), new Address());
		when(entityManager.createQuery("FROM Address WHERE person_id = :id", Address.class)).thenReturn(query);
		when(query.setParameter("id", 1L)).thenReturn(query);
		when(query.getResultList()).thenReturn(addresses);

		List<Address> result = addressDAO.findAllAddressByUser(1L);
		assertEquals(2, result.size());
	}

	@Test
	final void testCreateOrUpdate() {
		Address address = new Address();
		when(entityManager.merge(address)).thenReturn(address);

		Address result = addressDAO.createOrUpdate(address);
		assertNotNull(result);
	}

	@Test
	final void testDelete() {
		Address address = new Address();
		address.setId(1L);
		when(entityManager.find(Address.class, 1L)).thenReturn(address);

		addressDAO.delete(1L);
		verify(entityManager).remove(address);
	}

}
