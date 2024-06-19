package br.com.company.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.company.dao.AddressDAO;
import br.com.company.entity.Address;
import br.com.company.util.Transacional;

public class AddressService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AddressDAO addressDAO;

	@Transacional
	public void createOrUpdate(Address address) {
		addressDAO.createOrUpdate(address);
	}

	@Transacional
	public void deleteAddress(Address address) {
		addressDAO.delete(address.getId());
	}

	public List<Address> getAllAddress() {
		return addressDAO.findAll();
	}

	public List<Address> findByUserId(Long id) {
		return addressDAO.findAllAddressByUser(id);
	}

}
