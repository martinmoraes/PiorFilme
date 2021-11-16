package com.moraes.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;

import java.io.File;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.moraes.usecase.MovieImportUseCase;
import com.moraes.usecase.WorstProducerUseCase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class PiorFilmeTest {
	@Autowired
	private PiorFilme piorFilme;
	@Autowired
	private MovieImportUseCase movieImportUseCase;
	
	private String separator = System.getProperty("file.separator");

	@BeforeEach
	public void setup() {
		standaloneSetup(this.piorFilme);
	}

	@Test
	public void shouldReturnJson_whenaskedPiorFilme_oneFile() {
		String largefile = System.getProperty("user.dir") + separator + "src" + separator + "test" + separator + "java"
				+ separator + "moviesFiles" + separator + "largefile";
		movieImportUseCase.execute(largefile);

		given().accept(ContentType.JSON).when().get("/piorfilme").then().statusCode(HttpStatus.OK.value()).body("",
				equalTo(this.getJson("largefile.json")));

	}
	
	@Test
	public void shouldReturnJson_whenaskedPiorFilme_twoFiles() {
		String largefile = System.getProperty("user.dir") + separator + "src" + separator + "test" + separator + "java"
				+ separator + "moviesFiles" + separator + "largefile_split";
		movieImportUseCase.execute(largefile);

		given().accept(ContentType.JSON).when().get("/piorfilme").then().statusCode(HttpStatus.OK.value()).body("",
				equalTo(this.getJson("largefile.json")));

	}
	
	@Test
	public void shouldReturnJson_whenaskedPiorFilme_smallfile() {
		String largefile = System.getProperty("user.dir") + separator + "src" + separator + "test" + separator + "java"
				+ separator + "moviesFiles" + separator + "smallfile";
		movieImportUseCase.execute(largefile);

		given().accept(ContentType.JSON).when().get("/piorfilme").then().statusCode(HttpStatus.OK.value()).body("",
				equalTo(this.getJson("smallfile.json")));

	}

	private Map<Object, Object> getJson(String fileName) {
		String URI = System.getProperty("user.dir") + separator + "src" + separator + "test" + separator + "java"
				+ separator + "piorfilmeOutPut" + separator + fileName;

		JsonPath expectedJson = new JsonPath(new File(URI));
		return expectedJson.getMap("");

	}

}
