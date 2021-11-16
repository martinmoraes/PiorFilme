package com.moraes;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.moraes.usecase.MovieImportUseCase;

@SpringBootApplication
public class PiorFilmeApplication {
//	@Autowired
//	MovieImportUseCase movieImportUseCase;

	public static void main(String[] args) {
//		SpringApplication.run(PiorFilmeApplication.class, args);
//		new PiorFilmeApplication().execute();
		ConfigurableApplicationContext appContext = SpringApplication.run(PiorFilmeApplication.class, args);
		MovieImportUseCase service = appContext.getBean(MovieImportUseCase.class);
		service.execute();
	   
	}
}
