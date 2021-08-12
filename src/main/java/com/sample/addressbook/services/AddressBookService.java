package com.sample.addressbook.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.addressbook.constants.HelperConstants;
import com.sample.addressbook.dto.AddressDTO;
import com.sample.addressbook.reader.CSVReader;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AddressBookService {

	@Autowired
	private CSVReader<AddressDTO> csvReader;

	public List<AddressDTO> getAddresses(String searchValue) {
		URI uri;
		try {
			uri = ClassLoader.getSystemResource(HelperConstants.ADDRESS_BOOK_CSV).toURI();
			List<AddressDTO> addresses = csvReader.readCsvToBean(Paths.get(uri), AddressDTO.class);
			return addresses.stream()
					.filter(address -> address.getFirstName().toLowerCase().contains(searchValue.toLowerCase())
							|| address.getLastName().toLowerCase().contains(searchValue.toLowerCase())
							|| address.getAddressLine1().toLowerCase().contains(searchValue.toLowerCase())
							|| address.getAddressLine2().toLowerCase().contains(searchValue.toLowerCase())
							|| address.getAddressLine3().toLowerCase().contains(searchValue.toLowerCase()))
					.collect(Collectors.toList());
		} catch (URISyntaxException e) {
			log.debug("No such file with the given path");
		}
		return null;
	}
}
