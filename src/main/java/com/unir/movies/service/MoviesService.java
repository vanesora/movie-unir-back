package com.unir.movies.service;

import java.util.List;

import com.unir.movies.model.pojo.Movie;
import com.unir.movies.model.request.CreateMovieRequest;

public interface MoviesService {
	Movie getMovie(String movieId);
	Boolean removeMovie(String movieId);
	Movie createMovie(CreateMovieRequest request);
	List<Movie> getMovies(String filter);

}
