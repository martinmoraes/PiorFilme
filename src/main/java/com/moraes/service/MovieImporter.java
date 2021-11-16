package com.moraes.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.moraes.interfaces.MovieRepository;
import com.moraes.model.Movie;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

@Component
public class MovieImporter {
	private MovieRepository movieRepository;
	private final int MAX = 50;

	public MovieImporter(MovieRepository movieRepository) {
		super();
		this.movieRepository = movieRepository;
	}

	public int importFileCSV(File URI) throws IOException, CsvValidationException {
		List<Movie> movies = new ArrayList<Movie>();
		int total = 0;
		try (CSVReader reader = getCSVReader(URI)) {
			String[] lineInArray;
			while ((lineInArray = reader.readNext()) != null) {
				Movie movie = new Movie(Integer.parseInt(lineInArray[0]), lineInArray[1], lineInArray[2],
						lineInArray[3], lineInArray[4]);
				movies.add(movie);
				if (movies.size() == MAX) {
					movieRepository.saveAll(movies);
					total += movies.size();
					movies.clear();
				}
			}
			total += movies.size();
			if (!movies.isEmpty()) {
				movieRepository.saveAll(movies);
			}
		}
		return total;
	}

	public Map<String, Integer> importAllFilesCSV(String URI) throws IOException, CsvValidationException {
		int files = 0;
		int records = 0;
		Map<String, Integer> recordsAndFiles = new HashMap<String, Integer>();
		File[] allFiles = this.fetchAllFiles(URI);
		for (File file : allFiles) {
			records += this.importFileCSV(file);
			files++;
		}

		recordsAndFiles.put("files", files);
		recordsAndFiles.put("records", records);
		return recordsAndFiles;
	}

	public File[] fetchAllFiles(String URI) throws IOException {
		File f = new File(URI);
		return f.listFiles();
	}

	private CSVReader getCSVReader(File URI) throws IOException, CsvValidationException {
		CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
		return new CSVReaderBuilder(new FileReader(URI)).withCSVParser(csvParser).withSkipLines(1).build();
	}

}
