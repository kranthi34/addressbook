package com.sample.addressbook.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sample.addressbook.dto.AddressDTO;
import com.sample.addressbook.services.AddressBookService;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@WebMvcTest()
public class AddressBookControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AddressBookService addressBookService;

	@InjectMocks
	@Spy
	private AddressBookController AddressBookController;

	@Test
	public void getAddresses() throws Exception {
		String json = "[{\"firstName\":\"Dave\",\"lastName\":\"Joe\",\"addressLine1\":\"123 main street\",\"addressLine2\":\"seattle\",\"addressLine3\":\"WA\",\"code\":null}]";

		List<AddressDTO> list = new ArrayList<>();
		AddressDTO addressDto = new AddressDTO();
		addressDto.setFirstName("Dave");
		addressDto.setLastName("Joe");
		addressDto.setAddressLine1("123 main street");
		addressDto.setAddressLine2("seattle");
		addressDto.setAddressLine3("WA");
		list.add(addressDto);

		given(addressBookService.getAddresses(anyString())).willReturn(list);
		MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/addressBook").param("searchValue", "dave"))
				.andDo(print()).andReturn();

		Integer statusCode = result.getResponse().getStatus();
		assertEquals(200, statusCode);
		assertTrue(json.equals(result.getResponse().getContentAsString()));

	}
}
