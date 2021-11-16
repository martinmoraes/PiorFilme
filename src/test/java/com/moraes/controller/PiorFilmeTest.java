package com.moraes.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;

import com.moraes.usecase.WorstProducerUseCase;

import io.restassured.http.ContentType;

@WebMvcTest
public class PiorFilmeTest {
	@Autowired
	private PiorFilme piorFilme;
	
	@Autowired
	private WorstProducerUseCase worstProducerUseCase;
	
	
	@BeforeEach
	public void setup() {
//		standaloneSetup(this.piorFilme);
	}
	
	@Test
	public void shouldReturnJson_whenaskedPiorFilme() {
		

//		when(this.worstProducerUseCase.execute())
//			.thenReturn("{\r\n"
//					+ "  \"min\": [\r\n"
//					+ "    {\r\n"
//					+ "      \"producer\": \"Yoram Globus and Menahem Golan\",\r\n"
//					+ "      \"interval\": 1,\r\n"
//					+ "      \"previousWin\": 1986,\r\n"
//					+ "      \"followingWin\": 1987\r\n"
//					+ "    },\r\n"
//					+ "    {\r\n"
//					+ "      \"producer\": \"Wyck Godfrey, Stephenie Meyer and Karen Rosenfelt\",\r\n"
//					+ "      \"interval\": 1,\r\n"
//					+ "      \"previousWin\": 2011,\r\n"
//					+ "      \"followingWin\": 2012\r\n"
//					+ "    }\r\n"
//					+ "  ],\r\n"
//					+ "  \"max\": [\r\n"
//					+ "    {\r\n"
//					+ "      \"producer\": \"Jerry Weintraub\",\r\n"
//					+ "      \"interval\": 9,\r\n"
//					+ "      \"previousWin\": 1980,\r\n"
//					+ "      \"followingWin\": 1989\r\n"
//					+ "    }\r\n"
//					+ "  ]\r\n"
//					+ "}\r\n"
//					+ "");
//		
//		given()
//			.accept(ContentType.JSON)
//		.when()
//			.get("/piorfilme")
//		.then()
//			.statusCode(HttpStatus.OK.value());
	}

}
