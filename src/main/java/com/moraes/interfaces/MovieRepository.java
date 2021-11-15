package com.moraes.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.moraes.model.Movie;



public interface MovieRepository extends CrudRepository<Movie, Long>{

}
