package com.moraes.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.moraes.interfaces.MovieRepository;
import com.moraes.model.Movie;
import com.moraes.service.worstProducer.MinMaxInterval;
import com.moraes.service.worstProducer.WorstProducer;

@Component
public class WorstProducerUseCase {

	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private WorstProducer worstProducer;

	public MinMaxInterval execute() {
		List<Movie> movies = movieRepository.findByOrderByYearAsc();
		MinMaxInterval minMaxInterval = worstProducer.fetchWorstMaxMin(movies);
//		int page = 0;
//		int length = 50;
//		WorstProducer worstProducer = new WorstProducer();
//		List<Movie> movies = this.getMovieRecords(page, length);
//		while (movies.size() > 0) {
//			minMaxInterval = worstProducer.fetchWorstMaxMin(movies);
//			movies = this.getMovieRecords(page, length);
//			page += length;
//		}
		return minMaxInterval;
	}

	private List<Movie> getMovieRecords(int page, int length) {
//		Pageable orderByYearAsc = PageRequest.of(page, length, Sort.by("Year").ascending());
//		Page<Movie> moviesPage = movieRepository.findAll(orderByYearAsc);
		Pageable orderByYearAsc = PageRequest.of(page, length);
		Page<Movie> moviesPage = movieRepository.findByOrderByYearAsc(orderByYearAsc);
		return moviesPage.getContent();
	}

}
