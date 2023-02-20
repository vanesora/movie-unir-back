package com.unir.movies.service;

import java.util.List;

import com.unir.movies.model.pojo.ElasticMovie;
import com.unir.movies.model.request.CreateMovieRequest;

public interface ElasticMoviesService {
  
  ElasticMovie createMovie(CreateMovieRequest request);

  ElasticMovie getMovieById(String movieId);

  ElasticMovie getMovieByName(String name);
    
  List<ElasticMovie> getMoviesByCategory(String category);
  
  List<ElasticMovie> searchByDirector(String director);

  List<ElasticMovie> searchByName(String productName);

  List<ElasticMovie> getAvailableMovies();
  
  Boolean deleteMovie (String movieId);

}
