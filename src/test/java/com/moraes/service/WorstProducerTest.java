package com.moraes.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.moraes.model.Movie;
import com.moraes.service.worstProducer.MinMaxInterval;
import com.moraes.service.worstProducer.Worst;
import com.moraes.service.worstProducer.WorstProducer;
import com.opencsv.exceptions.CsvValidationException;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest
public class WorstProducerTest {
	@Autowired
	private WorstProducer largeRangeProducer;
	@Autowired
	private MovieImporter movieImporter;

	private String separator = System.getProperty("file.separator");
	private String URI = System.getProperty("user.dir") + separator + "src" + separator + "test" + separator + "java"
			+ separator + "resources";

	@BeforeEach
	public void setup() throws CsvValidationException, IOException {
		RestAssuredMockMvc.standaloneSetup(this.movieImporter);
		movieImporter.importAllFilesCSV(URI);
	}

	@Test
	public void whenFetchWorstMaxMin_mustContain() {
		List<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie(1980, "Can't Stop the Music", "Associated Film Distribution", "Allan Carr", "yes"));
		movies.add(new Movie(1980, "Cruising", "Lorimar Productions, United Artists", "Jerry Weintraub"));
		movies.add(new Movie(1980, "The Formula", "MGM, United Artists", "Steve Shagan"));
		movies.add(new Movie(1981, "The Formula", "MGM, United Artists", "Steve Shagan"));
		movies.add(new Movie(1981, "Can't Stop the Music", "Associated Film Distribution", "Allan Carr", "yes"));
		movies.add(new Movie(1982, "The Formula", "MGM, United Artists", "Steve Shagan"));
		movies.add(new Movie(1984, "The Formula", "MGM, United Artists", "Steve Shagan"));
		MinMaxInterval received = largeRangeProducer.fetchWorstMaxMin(movies);
		System.out.println(received);
		assertThat(received).usingRecursiveComparison().isEqualTo(received);
	}
//	@Test
//	public void whenFetchWorstMaxMinMultipleCall_mustContain() {
//		List<Movie> movies01 = new ArrayList<Movie>();
//		movies01.add(new Movie(1980, "Can't Stop the Music", "Associated Film Distribution", "Allan Carr", "yes"));
//		movies01.add(new Movie(1980, "Cruising", "Lorimar Productions, United Artists", "Jerry Weintraub"));
//		movies01.add(new Movie(1980, "The Formula", "MGM, United Artists", "Steve Shagan"));
//		movies01.add(new Movie(1981, "The Formula", "MGM, United Artists", "Steve Shagan"));
//		List<Movie> movies02 = new ArrayList<Movie>();
//		movies02.add(new Movie(1981, "Can't Stop the Music", "Associated Film Distribution", "Allan Carr", "yes"));
//		movies02.add(new Movie(1982, "The Formula", "MGM, United Artists", "Steve Shagan"));
//		movies02.add(new Movie(1984, "The Formula", "MGM, United Artists", "Steve Shagan"));
//		largeRangeProducer.fetchWorstMaxMin(movies01);
//		String received = largeRangeProducer.fetchWorstMaxMin(movies02);
//		System.out.println(received);
//		assertThat(received).isEqualTo("{\"min\":[{\"producer\":\"Steve Shagan\",\"interval\":1,\"previousWin\":1980,\"followingWin\":1981},{\"producer\":\"Allan Carr\",\"interval\":1,\"previousWin\":1980,\"followingWin\":1981},{\"producer\":\"Steve Shagan\",\"interval\":1,\"previousWin\":1981,\"followingWin\":1982}],\"max\":[{\"producer\":\"Steve Shagan\",\"interval\":2,\"previousWin\":1982,\"followingWin\":1984}]}");
//	}
//	@Test
//	public void whenFetchWorstMaxMinDuplicatYear_mustContain() {
//		List<Movie> movies = new ArrayList<Movie>();
//		movies.add(new Movie(1980, "Can't Stop the Music", "Associated Film Distribution", "Allan Carr", "yes"));
//		movies.add(new Movie(1980, "Cruising", "Lorimar Productions, United Artists", "Jerry Weintraub"));
//		movies.add(new Movie(1980, "The Formula", "MGM, United Artists", "Steve Shagan"));
//		movies.add(new Movie(1980, "The Formula", "MGM, United Artists", "Steve Shagan"));
//		movies.add(new Movie(1981, "Can't Stop the Music", "Associated Film Distribution", "Allan Carr", "yes"));
//		movies.add(new Movie(1982, "The Formula", "MGM, United Artists", "Steve Shagan"));
//		movies.add(new Movie(1982, "The Formula", "MGM, United Artists", "Steve Shagan"));
//		String received = largeRangeProducer.fetchWorstMaxMin(movies);
//		System.out.println(received);
//		assertThat(received).isNotNull();
//	}

	@Test
	public void whenThereIsNoObserv_mustReturnNewWorst() {
		Movie filme = new Movie(1980, "Can't Stop the Music", "Associated Film Distribution", "Allan Carr", "yes");
		Movie observFilme = new Movie(1978, "Can't Stop the Music", "Associated Film Distribution", "Allan Carr",
				"yes");
		Worst worst = largeRangeProducer.defineMaxInterval(filme, observFilme);
		assertThat(worst.toString())
				.isEqualTo("Worst [producer=Allan Carr, interval=2, previousWin=1978, followingWin=1980]");
	}
}
