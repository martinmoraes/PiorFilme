package com.moraes.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.moraes.model.*;
import com.moraes.usecase.MovieImportUseCase;
import com.moraes.interfaces.MovieRepository;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class MovieRepositoryTest {

	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private MovieImportUseCase movieImportUseCase;

	private String separator = System.getProperty("file.separator");

	@Test
	public void whenFindingCustomerById_thenCorrect() {
		movieRepository
				.save(new Movie(1980, "Can't Stop the Music", "Associated Film Distribution", "Allan Carr", "yes"));
		assertThat(movieRepository.findById(1L)).isInstanceOf(Optional.class);
		assertThat(movieRepository.findById(1L).get()).isInstanceOf(Movie.class);

	}

	@Test
	public void whenFindingAllMovies_thenCorrect() {
		movieRepository
				.save(new Movie(1980, "Can't Stop the Music", "Associated Film Distribution", "Allan Carr", "yes"));
		movieRepository.save(new Movie(1981, "Can't ", "Distribution", "Carr",""));
		List<Movie> movies = (List<Movie>) movieRepository.findAll();
		System.out.println(movies.get(0).toString());
		assertThat(movies.size()).isEqualTo(2);
	}

	@Test
	public void whenFindingAllWinners_thenCorrect() {
		String largefile = System.getProperty("user.dir") + separator + "src" + separator + "test" + separator + "java"
				+ separator + "moviesFiles" + separator + "largefile";
		movieImportUseCase.execute(largefile);
		List<Movie> movies = (List<Movie>) movieRepository.findAllByWinnerOrderByYearAsc("yes");
		assertThat(movies.size()).isEqualTo(42);
	}

}
