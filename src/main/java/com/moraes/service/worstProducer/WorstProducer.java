package com.moraes.service.worstProducer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.moraes.model.Movie;

public class WorstProducer {
	private Map<String, Movie> observ = new HashMap<String, Movie>();
	private List<Worst> worstsMax = new ArrayList<Worst>();
	private List<Worst> worstsMin = new ArrayList<Worst>();

	public MinMaxInterval fetchWorstMaxMin(List<Movie> movies) {
		Movie observMovie = null;

		for (Movie movie : movies) {
			observMovie = observ.get(movie.getProducers());
			if (observMovie != null) {
				this.defineMaxInterval(movie, observMovie);
				this.defineMinInterval(movie, observMovie);
			}
			observ.put(movie.getProducers(), movie);
		}
		return new MinMaxInterval(worstsMin, worstsMax);
	}

	private Worst defineMinInterval(Movie movie, Movie observMovie) {
		int interval = movie.getYear() - observMovie.getYear();
		if (worstsMin.isEmpty()) {
			Worst worst = creatWorst(interval, movie, observMovie);
			worstsMin.add(worst);
			return worst;
		}

		Worst worstMin = worstsMin.get(0);
		if (worstMin.getInterval() < interval) {
			return null;
		}

		if (worstMin.getInterval() > interval) {
			worstsMin.clear();
		}

		Worst worst = creatWorst(interval, movie, observMovie);
		worstsMin.add(worst);
		return worst;

	}

	public Worst defineMaxInterval(Movie movie, Movie observMovie) {
		int interval = movie.getYear() - observMovie.getYear();
		if (worstsMax.isEmpty()) {
			Worst worst = creatWorst(interval, movie, observMovie);
			worstsMax.add(worst);
			return worst;
		}

		Worst worstMax = worstsMax.get(0);
		if (worstMax.getInterval() > interval) {
			return null;
		}

		if (worstMax.getInterval() < interval) {
			worstsMax.clear();
		}

		Worst worst = creatWorst(interval, movie, observMovie);
		worstsMax.add(worst);
		return worst;
	}

	public Worst creatWorst(int interval, Movie movie, Movie observMovie) {
		Worst worst = new Worst(movie.getProducers(), interval, observMovie.getYear(), movie.getYear());
		return worst;
	}

}
