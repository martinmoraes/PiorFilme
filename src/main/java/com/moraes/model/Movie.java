package com.moraes.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "films")
public class Movie {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	private int year;
	private String title;
	private String studios;
	private String producers;
	private String winner;
	
	public Movie() {
		
	}

	public Movie(int year, String title, String studios, String producers) {
		super();
		this.year = year;
		this.title = title;
		this.studios = studios;
		this.producers = producers;
	}
	
	public Movie(int year, String title, String studios, String producers, String winner) {
		super();
		this.year = year;
		this.title = title;
		this.studios = studios;
		this.producers = producers;
		this.winner = winner;
	}

	
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStudios() {
		return studios;
	}

	public void setStudios(String studios) {
		this.studios = studios;
	}

	public String getProducers() {
		return producers;
	}

	public void setProducers(String producers) {
		this.producers = producers;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id, producers, studios, title, winner, year);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return Id == other.Id && Objects.equals(producers, other.producers) && Objects.equals(studios, other.studios)
				&& Objects.equals(title, other.title) && Objects.equals(winner, other.winner) && year == other.year;
	}


	@Override
	public String toString() {
		return "Movie [Id=" + Id + ", year=" + year + ", title=" + title + ", studios=" + studios + ", producers="
				+ producers + ", winner=" + winner + "]";
	}


	
	
	
}
