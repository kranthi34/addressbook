package com.sample.addressbook.dto;

import com.opencsv.bean.CsvBindByPosition;

import lombok.Data;

@Data
public class AddressDTO {

	@CsvBindByPosition(position = 0)
	private String firstName;
	
	@CsvBindByPosition(position = 1)
	private String lastName;
	
	@CsvBindByPosition(position = 2)
	private String addressLine1;
	
	@CsvBindByPosition(position = 3)
	private String addressLine2;
	
	@CsvBindByPosition(position = 4)
	private String addressLine3;
	
	@CsvBindByPosition(position = 5)
	private String code;
}
