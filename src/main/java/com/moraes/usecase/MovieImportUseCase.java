package com.moraes.usecase;

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


	public boolean execute(){
		String separator = System.getProperty("file.separator"); 
		String URI = System.getProperty("user.dir") + separator +"resources";
		
		try {
			movieImporter.importAllFilesCSV(URI);
//			new MovieImporter().importAllFilesCSV(URI);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
