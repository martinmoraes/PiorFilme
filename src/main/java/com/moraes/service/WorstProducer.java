package com.moraes.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.moraes.model.Movie;

@Component
public class WorstProducer {
	private Map<String, Movie> observ = new HashMap<String, Movie>();
	private List<Worst> worstsMax = new ArrayList<Worst>();
	private List<Worst> worstsMin = new ArrayList<Worst>();

	public String fetchWorstMaxMin(List<Movie> movies) {

		Movie observMovie = null;

		for (Movie movie : movies) {
			observMovie = observ.get(movie.getProducers());
			if (observMovie != null) {
				Worst worstMax = this.defineMaxInterval(movie, observMovie);
				Worst worstMin = this.defineMinInterval(movie, observMovie);
			}
			observ.put(movie.getProducers(), movie);
		}

		return this.getJson();
	}

	private String getJson() {
		Map<String, List<Worst>> originToJson = new HashMap<String, List<Worst>>();
		originToJson.put("max", worstsMax);
		originToJson.put("min", worstsMin);
		Gson gson = new Gson();
		return gson.toJson(originToJson);

	}

	private Worst defineMinInterval(Movie movie, Movie observMovie) {
		int interval = movie.getYear() - observMovie.getYear();
		if (worstsMin.isEmpty()) {
			Worst worst = creatWorst(interval, movie, observMovie);
			worstsMin.add(worst);
			return worst;
		}

		Worst worstMin = worstsMin.get(0);
		if (worstMin.interval < interval) {
			return null;
		}

		if (worstMin.interval > interval) {
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
		if (worstMax.interval > interval) {
			return null;
		}

		if (worstMax.interval < interval) {
			worstsMax.clear();
		}

		Worst worst = creatWorst(interval, movie, observMovie);
		worstsMax.add(worst);
		return worst;
	}

	public Worst creatWorst(int interval, Movie movie, Movie observMovie) {
		Worst worst = new Worst();
		worst.producer = movie.getProducers();
		worst.interval = interval;
		worst.previousWin = observMovie.getYear();
		worst.followingWin = movie.getYear();
		return worst;
	}

}

class Worst {
	public String producer;
	public int interval;
	public int previousWin;
	public int followingWin;

	@Override
	public String toString() {
		return "Worst [producer=" + producer + ", interval=" + interval + ", previousWin=" + previousWin
				+ ", followingWin=" + followingWin + "]";
	}
}