package com.moraes.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.moraes.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	List<Movie> findByOrderByYearAsc();

	Page<Movie> findByOrderByYearAsc(Pageable pageable);

	Page<Movie> findAll(Pageable pageable);

}
