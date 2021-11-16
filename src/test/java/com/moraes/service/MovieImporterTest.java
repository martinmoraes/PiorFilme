package com.moraes.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.opencsv.exceptions.CsvValidationException;

@SpringBootTest
public class MovieImporterTest {

	@Autowired
	private MovieImporter movieImporter;
	private String separator = System.getProperty("file.separator");
	
	String largefile = System.getProperty("user.dir") + separator + "src" + separator + "test" + separator + "java"
			+ separator + "moviesFiles" + separator + "largefile_split";
	@Test
	public void whenYouHaveMoreThanOneFile_mustImportAll() throws IOException, CsvValidationException {
		Map<String, Integer> received = movieImporter.importAllFilesCSV(largefile);
		assertThat(received.get("files")).isGreaterThan(1);
		assertThat(received.get("records")).isGreaterThan(200);
	}
}
