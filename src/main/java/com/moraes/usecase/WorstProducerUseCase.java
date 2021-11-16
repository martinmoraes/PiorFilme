package com.moraes.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moraes.interfaces.MovieRepository;
import com.moraes.model.Movie;
import com.moraes.service.WorstProducer;

@Component
public class WorstProducerUseCase {
	
	private WorstProducer worstProducer;
	private MovieRepository movieRepository;

	@Autowired
	public WorstProducerUseCase(WorstProducer worstProducer, MovieRepository movieRepository) {
		super();
		this.worstProducer = worstProducer;
		this.movieRepository = movieRepository;
	}




	public String execute() {
		List<Movie> movies = movieRepository.findByOrderByYearAsc();
		String resultJson = worstProducer.fetchWorstMaxMin(movies);
		return resultJson;
	}

}
