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
import com.moraes.interfaces.MovieRepository;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class MovieRepositoryTest {
	
	@Autowired
	private MovieRepository movieRepository;
	
	
	@Test
	public void whenFindingCustomerById_thenCorrect() {
		movieRepository.save(new Movie(1980,"Can't Stop the Music","Associated Film Distribution","Allan Carr","yes"));
		assertThat(movieRepository.findById(1L)).isInstanceOf(Optional.class);
		assertThat(movieRepository.findById(1L).get()).isInstanceOf(Movie.class);
		
	}
	
	@Test
    public void whenFindingAllMovies_thenCorrect() {
		movieRepository.save(new Movie(1980,"Can't Stop the Music","Associated Film Distribution","Allan Carr","yes"));
		movieRepository.save(new Movie(1981,"Can't ","Distribution","Carr"));
		List<Movie> movies = (List<Movie>) movieRepository.findAll();
        assertThat(movies.size()).isEqualTo(2);
    }

}
