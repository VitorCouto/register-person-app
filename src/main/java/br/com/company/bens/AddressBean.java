package br.com.company.bens;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.company.entity.Address;
import br.com.company.service.AddressService;

@Named
@ViewScoped
public class AddressBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AddressService addressService;

	private Address address = new Address();
	private List<Address> listAddress;

	public void saveAddress() {
		addressService.createOrUpdate(address);
		limpar();
		atualizarLista();
	}

	public void deleteAddress(Address address) {
		addressService.deleteAddress(address);
		atualizarLista();
	}

	public void editAddress(Address address) {
		this.address = address;
	}

	public List<Address> getAddresss() {
		if (listAddress == null) {
			atualizarLista();
		}
		return listAddress;
	}

	private void atualizarLista() {
		listAddress = addressService.getAllAddress();
	}

	private void limpar() {
		address = new Address();
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
