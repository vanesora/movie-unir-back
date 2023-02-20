package com.unir.movies.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unir.movies.model.pojo.Movie;
import com.unir.movies.model.request.CreateMovieRequest;
import com.unir.movies.service.MoviesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class MoviesController {

	private final MoviesService service;

	
	@GetMapping("/movies")
	public ResponseEntity<List<Movie>> getMovies(@RequestParam(value = "filter", required = false) String filter) {
		System.out.println("filtro: " + filter);
		List<Movie> movies = service.getMovies(filter);
		System.out.println(movies);
		if (movies != null) {
			return ResponseEntity.ok(movies);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}

	@GetMapping("/movies/{movieId}")
	public ResponseEntity<Movie> getMovie(@PathVariable String movieId) {
		Movie movie = service.getMovie(movieId);

		if (movie != null) {
			return ResponseEntity.ok(movie);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/movies/{movieId}")
	public ResponseEntity<Void> deleteMovie(@PathVariable String movieId) {

		Boolean removed = service.removeMovie(movieId);

		if (Boolean.TRUE.equals(removed)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/movies")
	public ResponseEntity<Movie> getMovie(@RequestBody CreateMovieRequest request) {

		Movie createdMovie = service.createMovie(request);

		if (createdMovie != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}

}
