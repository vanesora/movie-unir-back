package com.unir.movies.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unir.movies.data.MovieRepository;
import com.unir.movies.model.pojo.Movie;
import com.unir.movies.model.request.CreateMovieRequest;

@Service
public class MoviesServiceImpl implements MoviesService {
	@Autowired
	private MovieRepository repository;

	@Override
	public List<Movie> getMovies(String filter) {
		List<Movie> movies;
		if (filter != null) {
			movies = repository.search(filter.toUpperCase());
		} else {
			movies = repository.findAll();
		}
		System.out.println("movies2: "+movies.toString());
		return movies.isEmpty() ? null : movies;
	}

	@Override
	public Movie getMovie(String movieId) {
		return repository.findById(Long.valueOf(movieId)).orElse(null);
	}

	@Override
	public Boolean removeMovie(String movieId) {

		Movie movie = repository.findById(Long.valueOf(movieId)).orElse(null);

		if (movie != null) {
			repository.delete(movie);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	@Override
	public Movie createMovie(CreateMovieRequest request) {

		if (request != null && StringUtils.hasLength(request.getName().trim())
				&& StringUtils.hasLength(request.getDirector().trim())
				&& StringUtils.hasLength(request.getScore().trim()) 
				&& StringUtils.hasLength(request.getDuration().trim())
				&& StringUtils.hasLength(request.getCategory().trim())
				&& StringUtils.hasLength(request.getImg().trim())
				&& StringUtils.hasLength(request.getSynopsis().trim())
				&& StringUtils.hasLength(request.getTrailer().trim())) {
			Movie movie = Movie.builder()
					.name(request.getName().toUpperCase())
					.director(request.getDirector().toUpperCase())
					.score(request.getScore())
					.duration(request.getDuration())
					.category(request.getCategory().toUpperCase())
					.img(request.getImg())
					.synopsis(request.getSynopsis())
					.trailer(request.getTrailer())
					.build();

			return repository.save(movie);
		} else {
			return null;
		}
	}
}
