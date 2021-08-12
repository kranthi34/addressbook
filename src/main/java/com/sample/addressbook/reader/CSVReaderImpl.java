package com.sample.addressbook.reader;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Component;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.sample.addressbook.dto.CsvTransfer;

@Component
public class CSVReaderImpl<T> implements CSVReader<T>{

	/*
	 * Here we are converting or reading data from csv to desired beans or objects
	 * based on passed class
	 */
	@Override
    public List<T> readCsvToBean(Path path, Class<T> clazz) {
        CsvTransfer<T> csvTransfer = new CsvTransfer<>();
        ColumnPositionMappingStrategy<T> ms = new ColumnPositionMappingStrategy<>();
        ms.setType(clazz);
        try {
            Reader reader = Files.newBufferedReader(path);
            CsvToBean<T> cb = new CsvToBeanBuilder<T>(reader).withType(clazz)
                .withMappingStrategy(ms)
                .build();

            csvTransfer.setCsvList(cb.parse());
            reader.close();

        } catch (Exception ex) {
            
        }
        return csvTransfer.getCsvList();
    }
}
