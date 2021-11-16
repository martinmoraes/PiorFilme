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

import com.moraes.usecase.MovieImportUseCase;
import com.moraes.usecase.WorstProducerUseCase;

import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

@SpringBootTest
public class PiorFilmeTest {
	@Autowired
	private PiorFilme piorFilme;
	@Autowired
	private MovieImportUseCase movieImportUseCase;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.piorFilme);
	}

	@Test
	public void shouldReturnJson_whenaskedPiorFilme() {

		movieImportUseCase.execute();

		given().accept(ContentType.JSON).when().get("/piorfilme").then().statusCode(HttpStatus.OK.value()).body("",
				equalTo(this.getJson("piorfilmes01.json")));

	}

	private Map<Object, Object> getJson(String fileName) {
		String separator = System.getProperty("file.separator");
		String URI = System.getProperty("user.dir") + separator + "src" + separator + "test" + separator + "java"
				+ separator + "piorfilmeOutPut" + separator + fileName;

		JsonPath expectedJson = new JsonPath(new File(URI));
		return expectedJson.getMap("");

	}

}
