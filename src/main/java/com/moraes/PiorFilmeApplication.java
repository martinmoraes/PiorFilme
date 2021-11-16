package com.moraes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.moraes.usecase.MovieImportUseCase;

@SpringBootApplication
public class PiorFilmeApplication {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext appContext = SpringApplication.run(PiorFilmeApplication.class, args);
		MovieImportUseCase service = appContext.getBean(MovieImportUseCase.class);
		service.execute(getImportDiretory());
	   
	}
	
	private static String getImportDiretory() {
		String separator = System.getProperty("file.separator"); 
		String URI = System.getProperty("user.dir") + separator +"resources";
		
		return URI;
	}
	
}
