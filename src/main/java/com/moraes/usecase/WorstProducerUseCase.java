package com.moraes.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.moraes.interfaces.MovieRepository;
import com.moraes.model.Movie;
import com.moraes.service.worstProducer.MinMaxInterval;
import com.moraes.service.worstProducer.WorstProducer;

@RequestScope
@Component
public class WorstProducerUseCase {

	@Autowired
	private MovieRepository movieRepository;
	private WorstProducer worstProducer = null ;

	public MinMaxInterval execute() {
		worstProducer = new WorstProducer();
		MinMaxInterval minMaxInterval = this.fetchMinMaxIntervalOneQuery();
//		MinMaxInterval minMaxInterval = this.fetchMinMaxIntervalPageable();
		return minMaxInterval;
	}

	private MinMaxInterval fetchMinMaxIntervalPageable() {
		int page = 0;
		int length = 50;
		MinMaxInterval minMaxInterval = null;
		WorstProducer worstProducer = new WorstProducer();
		List<Movie> movies = this.getMovieRecords(page, length);
		while (movies.size() > 0) {
			minMaxInterval = worstProducer.fetchWorstMaxMin(movies);
			movies = this.getMovieRecords(page, length);
			page += length;
			System.out.println(page +" "+length);
		}
		return minMaxInterval;
	}

	private List<Movie> getMovieRecords(int page, int length) {
		Pageable pageable = PageRequest.of(page, length, Sort.by("Year").ascending());
		Page<Movie> moviesPage = movieRepository.findAll(pageable);
//		Pageable orderByYearAsc = PageRequest.of(page, length);
//		Page<Movie> moviesPage = movieRepository.findByOrderByYearAsc(orderByYearAsc);
		return moviesPage.getContent();
	}

	private MinMaxInterval fetchMinMaxIntervalOneQuery() {
		List<Movie> movies = movieRepository.findAllByWinnerOrderByYearAsc("yes");
		MinMaxInterval minMaxInterval = worstProducer.fetchWorstMaxMin(movies);
		return minMaxInterval;
	}
}
