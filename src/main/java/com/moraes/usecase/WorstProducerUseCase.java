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

	public MinMaxInterval execute() {
		int page = 0;
		int length = 10;
		MinMaxInterval minMaxInterval = null;
		WorstProducer worstProducer = new WorstProducer();
		List<Movie> movies = this.getMovieRecords(page, length);
		while (movies.size() > 0) {
			minMaxInterval = worstProducer.fetchWorstMaxMin(movies);
			page++;
			movies = this.getMovieRecords(page, length);
		}

		return minMaxInterval;
	}


	private List<Movie> getMovieRecords(int page, int length) {
		Pageable pageable = PageRequest.of(page, length, Sort.by("Year").ascending());
//		Page<Movie> moviesPage = movieRepository.findAllByWinnerOrderByYearAsc("yes", pageable);
		Page<Movie> moviesPage = movieRepository.findByOrderByYearAsc(pageable);
		return moviesPage.getContent();
	}

}
