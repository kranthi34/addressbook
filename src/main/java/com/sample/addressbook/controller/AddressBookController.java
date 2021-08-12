package com.sample.addressbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.addressbook.dto.AddressDTO;
import com.sample.addressbook.services.AddressBookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/addressBook")
@Api(description = "Address Book api's to manage personal address data")
public class AddressBookController {
	
	@Autowired
	private AddressBookService addressBookService;
	
	@GetMapping()
	@ApiOperation(notes = "Address Book api's to fetch personal address data", value = "Get Addresses")
	public ResponseEntity<List<AddressDTO>> getAddresses(@ApiParam(name = "searchValue", value = "search value") @RequestParam("searchValue") String searchValue){
		List<AddressDTO> addresses = addressBookService.getAddresses(searchValue);
		return new ResponseEntity<List<AddressDTO>>(addresses, HttpStatus.OK);
	}
	
}
