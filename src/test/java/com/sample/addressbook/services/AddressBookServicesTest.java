package com.sample.addressbook.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sample.addressbook.constants.HelperConstants;
import com.sample.addressbook.dto.AddressDTO;
import com.sample.addressbook.reader.CSVReader;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class AddressBookServicesTest {

	@InjectMocks
	@Spy
	AddressBookService addressBookService;

	@Mock
	CSVReader<AddressDTO> csvReader;

	@Test
	void getAddresses() throws URISyntaxException {

		List<AddressDTO> list = new ArrayList<>();
		AddressDTO addressDto = new AddressDTO();
		addressDto.setFirstName("Dave");
		addressDto.setLastName("Joe");
		addressDto.setAddressLine1("123 main street");
		addressDto.setAddressLine2("seattle");
		addressDto.setAddressLine3("WA");
		AddressDTO addressDto1 = new AddressDTO();
		addressDto1.setFirstName("Martha");
		addressDto1.setLastName("Neilson");
		addressDto1.setAddressLine1("123 main street");
		addressDto1.setAddressLine2("seattle");
		addressDto1.setAddressLine3("WA");
		list.add(addressDto);
		list.add(addressDto1);
		URI uri = ClassLoader.getSystemResource(HelperConstants.ADDRESS_BOOK_CSV).toURI();
		given(csvReader.readCsvToBean(Paths.get(uri), AddressDTO.class)).willReturn(list);
		List<AddressDTO> addresses = addressBookService.getAddresses("dave");
		assertEquals(1, addresses.size());
		assertEquals("Dave", addresses.get(0).getFirstName());
		then(csvReader).should(times(1)).readCsvToBean(Paths.get(uri), AddressDTO.class);
	}

	@Test
	void getAddressesFailure() throws URISyntaxException {

		List<AddressDTO> list = new ArrayList<>();
		AddressDTO addressDto = new AddressDTO();
		addressDto.setFirstName("Dave");
		addressDto.setLastName("Joe");
		addressDto.setAddressLine1("123 main street");
		addressDto.setAddressLine2("seattle");
		addressDto.setAddressLine3("WA");
		AddressDTO addressDto1 = new AddressDTO();
		addressDto1.setFirstName("Martha");
		addressDto1.setLastName("Neilson");
		addressDto1.setAddressLine1("123 main street");
		addressDto1.setAddressLine2("seattle");
		addressDto1.setAddressLine3("WA");
		list.add(addressDto);
		list.add(addressDto1);
		URI uri = ClassLoader.getSystemResource(HelperConstants.ADDRESS_BOOK_CSV).toURI();
		given(csvReader.readCsvToBean(Paths.get(uri), AddressDTO.class)).willReturn(list);
		List<AddressDTO> addresses = addressBookService.getAddresses("Jones");
		assertEquals(0, addresses.size());
		then(csvReader).should(times(1)).readCsvToBean(Paths.get(uri), AddressDTO.class);
	}

	@Test
	void getAddressesFailureException() throws URISyntaxException {

		List<AddressDTO> list = new ArrayList<>();
		AddressDTO addressDto = new AddressDTO();
		addressDto.setFirstName("Dave");
		addressDto.setLastName("Joe");
		addressDto.setAddressLine1("123 main street");
		addressDto.setAddressLine2("seattle");
		addressDto.setAddressLine3("WA");
		AddressDTO addressDto1 = new AddressDTO();
		addressDto1.setFirstName("Martha");
		addressDto1.setLastName("Neilson");
		addressDto1.setAddressLine1("123 main street");
		addressDto1.setAddressLine2("seattle");
		addressDto1.setAddressLine3("WA");
		list.add(addressDto);
		list.add(addressDto1);
		URI uri = ClassLoader.getSystemResource(HelperConstants.ADDRESS_BOOK_CSV).toURI();
		given(csvReader.readCsvToBean(Paths.get(uri), AddressDTO.class)).willAnswer(invocation -> {
			throw new URISyntaxException("No such path error", "failed to open file with given path");
		});
		List<AddressDTO> addresses = addressBookService.getAddresses("Jones");
		assertEquals(null, addresses);
		then(csvReader).should(times(1)).readCsvToBean(Paths.get(uri), AddressDTO.class);
	}

}
