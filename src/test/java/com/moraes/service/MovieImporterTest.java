package com.moraes.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.opencsv.exceptions.CsvValidationException;

@SpringBootTest
public class MovieImporterTest {

	@Autowired
	private MovieImporter movieImporter;
	private String separator = System.getProperty("file.separator"); 
	private String URI = System.getProperty("user.dir") + 
			separator +"src"+ separator +"test"+ separator +"java"+ separator +"resources";

	@Test
	public void whenListAllFiles_mustContainOne() throws IOException {
		File[] received = movieImporter.fetchAllFiles(URI);
		assertThat(received.length).isGreaterThan(0);
	}
	
	@Test
	public void whenReadingEachLine_mustContainMoreThanTenLines() throws IOException, CsvValidationException {
			Stream<Path> pathAllFiles = movieImporter.fetchAllFiles(URI);
			Path firstFile = (Path) pathAllFiles.toArray()[0];
			int received = movieImporter.importFileCSV(firstFile);
			assertThat(received).isGreaterThan(100);
	}

	@Test
	public void	whenYouHaveMoreThanOneFile_mustImportAll() throws IOException, CsvValidationException {
			Map<String, Integer> received = movieImporter.importAllFilesCSV(URI);
			System.out.println(received.get("files")+" "+received.get("records"));
			assertThat(received.get("files")).isGreaterThan(1);
			assertThat(received.get("records")).isGreaterThan(200);
	}
}
