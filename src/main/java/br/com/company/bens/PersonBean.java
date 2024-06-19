package br.com.company.bens;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import br.com.company.entity.Address;
import br.com.company.entity.Person;
import br.com.company.service.AddressService;
import br.com.company.service.PersonService;
import br.com.company.util.FacesMessages;

@Named
@ViewScoped
public class PersonBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PersonService personService;

	@Inject
	private AddressService addressService;

	@Inject
	private FacesMessages messages;

	@Inject
	private Person person;

	@Inject
	private Address address;

	@Inject
	private Address addressForm;

	private String toolbarSearchValue;

	private List<Person> listPersons;
	private List<Address> listAddresses;

	public void prepareNewPerson() {
		person = new Person();
	}

	public void prepareEditAddress() {
		if (person.getId() != null) {
			listAddresses = addressService.findByUserId(person.getId());
			addressForm = new Address();
		}
	}

	public void savePerson() {
		if (person.getCpf() != null && address.getCep() != null) {
			personService.createPersonWithAddress(person, address);
			updateTablePerson("Pessoa salva com sucesso!");
		}
	}

	public void editPerson() {
		if (person.getId() != null) {
			personService.updatePerson(person);
			updateTablePerson("Pessoa editada com sucesso!");
		}
	}

	public void saveEndereco() {
		if (addressForm.getCep() != null && person.getId() != null) {
			addressForm.setPerson(person);
			addressService.createOrUpdate(addressForm);
			addressForm = new Address();
			updateTablePerson("Endereço salvo com sucesso!");
		}
	}

	public void deletePerson() {
		if (person.getId() != null) {
			personService.deletePerson(person);
			person = null;
			updateTablePerson("Pessoa removida com sucesso!");
		}
	}

	public void updateTablePerson(String message) {
		updateTable();
		messages.info(message);
		PrimeFaces.current().ajax().update(Arrays.asList("frm:personsDataTable", "frm:messages"));
	}

	public void searchByName() {
		listPersons = personService.findPersonByName(toolbarSearchValue);
		if (listPersons.isEmpty()) {
			messages.info("Nenhum registro encontrado!");
		}
	}

	public boolean hasSearch() {
		return toolbarSearchValue != null && !"".equals(toolbarSearchValue);
	}
	
	public void allPersons() {
		listPersons = personService.getAllPersons();
	}

	public void updateTable() {
		if (hasSearch()) {
			searchByName();
		} else {
			allPersons();
		}
	}
	
	public boolean isPersonSelecied() {
		return person != null && person.getId() != null;
	}
	
	public boolean isAddressSelected() {
		return address != null && address.getId() != null;
	}

	public void deleteAddress() {
		if (address.getId() != null) {
			addressService.deleteAddress(address);
			refreshAddresses();
			messages.info("Endereço removido com sucesso!");
			PrimeFaces.current().ajax().update(Arrays.asList("frm:addressDataTable", "frm:msgsRemoveDialog"));
		}
	}

	private void refreshAddresses() {
		if (person.getId() != null) {
			listAddresses = addressService.findByUserId(person.getId());
		}
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Person> getListPersons() {
		return listPersons;
	}
	
	public String getToolbarSearchValue() {
		return toolbarSearchValue;
	}

	public void setToolbarSearchValue(String toolbarSearchValue) {
		this.toolbarSearchValue = toolbarSearchValue;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Address> getListAddresses() {
		return listAddresses;
	}

	public Address getAddressForm() {
		return addressForm;
	}
	
	public void setAddressForm(Address addressForm) {
		this.addressForm = addressForm;
	}

}
