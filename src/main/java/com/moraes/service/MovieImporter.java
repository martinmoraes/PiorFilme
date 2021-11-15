package com.moraes.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moraes.interfaces.MovieRepository;
import com.moraes.model.Movie;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

@Service
public class MovieImporter {
	@Autowired
	private MovieRepository movieRepository;

	public File[] fetchAllFiles(String URI) throws IOException {
		File f = new File(URI);
		return  f.listFiles();
	}

	public int importFileCSV(Path URI) throws IOException, CsvValidationException {
		final int MAX = 50;
		List<Movie> movies = new ArrayList<Movie>();
		int total = 0;
		try (CSVReader reader = getCSVReader(URI)) {
			String[] lineInArray;
			while ((lineInArray = reader.readNext()) != null) {
				Movie movie = new Movie(Integer.parseInt(lineInArray[0]), lineInArray[1], lineInArray[2],
						lineInArray[3]);
				movies.add(movie);
				if (movies.size() == MAX) {
					movieRepository.saveAll(movies);
					total += movies.size();
					movies.clear();
				}
			}
			total += movies.size();
			movieRepository.saveAll(movies);
		}
		return total;
	}

	public Map<String, Integer> importAllFilesCSV(String URI) throws IOException, CsvValidationException {
		int files = 0;
		int records = 0;
		Map<String, Integer> recordsAndFiles = new HashMap<String, Integer>();
		Stream<Path> pathAllFiles = this.fetchAllFiles(URI);
		System.out.println("pathAllFiles"+ pathAllFiles.count());
		Path[] arrayPathAllFiles =  pathAllFiles.toArray();
		System.out.println("arrayPathAllFiles"+ arrayPathAllFiles);
		for (Path path : arrayPathAllFiles) {
			records += this.importFileCSV(path);
			files++;
		}

		recordsAndFiles.put("files", files);
		recordsAndFiles.put("records", records);
		System.out.println(recordsAndFiles);
		return recordsAndFiles;
	}

	private CSVReader getCSVReader(Path URI) throws IOException, CsvValidationException {
		CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
		return new CSVReaderBuilder(new FileReader(URI.toFile())).withCSVParser(csvParser).withSkipLines(1).build();
	}
}
