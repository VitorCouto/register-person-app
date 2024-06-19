package br.com.company.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import br.com.company.entity.Address;

class AddressServiceTest {
	
	@InjectMocks
    private AddressService addressService;

    @Mock
    private AddressDAO addressDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testGetAllAddress() {
        List<Address> addresses = Arrays.asList(new Address(), new Address());
        when(addressDAO.findAll()).thenReturn(addresses);

        List<Address> result = addressService.getAllAddress();
        assertEquals(2, result.size());
    }

    @Test
    void testFindByUserId() {
        List<Address> addresses = Arrays.asList(new Address(), new Address());
        when(addressDAO.findAllAddressByUser(1L)).thenReturn(addresses);

        List<Address> result = addressService.findByUserId(1L);
        assertEquals(2, result.size());
    }

    @Test
    void testCreateOrUpdate() {
        Address address = new Address();
        addressService.createOrUpdate(address);
        verify(addressDAO).createOrUpdate(address);
    }

    @Test
    void testDeleteAddress() {
        Address address = new Address();
        address.setId(1L);
        addressService.deleteAddress(address);
        verify(addressDAO).delete(1L);
    }

}
