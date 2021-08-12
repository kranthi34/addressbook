package com.sample.addressbook.reader;

import java.nio.file.Path;
import java.util.List;

public interface CSVReader<T> {

	List<T> readCsvToBean(Path path, Class<T> clazz);

}
