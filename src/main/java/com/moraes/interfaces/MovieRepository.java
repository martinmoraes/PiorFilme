package com.moraes.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moraes.model.Movie;



public interface MovieRepository extends JpaRepository<Movie, Long>{

	List<Movie> findByOrderByYearAsc();
}
