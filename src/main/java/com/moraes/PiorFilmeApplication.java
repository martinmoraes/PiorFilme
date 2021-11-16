package com.moraes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.moraes.usecase.MovieImportUseCase;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.moraes"})
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
