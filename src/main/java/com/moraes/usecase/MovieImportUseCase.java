package com.moraes.usecase;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moraes.service.MovieImporter;

@Component
public class MovieImportUseCase {

	private MovieImporter movieImporter;

	@Autowired
	public MovieImportUseCase(MovieImporter movieImporter) {
		super();
		this.movieImporter = movieImporter;
	}

	public Map<String, Integer> execute(String directory) {

		try {
			return movieImporter.importAllFilesCSV(directory);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
