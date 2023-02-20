package com.unir.movies.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.unir.movies.model.pojo.ElasticMovie;
import com.unir.movies.model.request.CreateMovieRequest;
import com.unir.movies.service.ElasticMoviesService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins= {"*"}, maxAge = 16000, allowCredentials = "false" )
@RestController
@RequiredArgsConstructor
public class ElasticMoviesController {
  
  private final ElasticMoviesService service;
  
  @GetMapping("/elastic/movie/{movieId}")
  public ResponseEntity<ElasticMovie> getMovieById(@PathVariable String movieId) {

	  ElasticMovie movie = service.getMovieById(movieId);

    if (movie != null) {
      return ResponseEntity.ok(movie);
    } else {
      return ResponseEntity.notFound().build();
    }

  }
  
  @GetMapping("/elastic/movies")
  public ResponseEntity<List<ElasticMovie>> getMovies() {

    List<ElasticMovie> movie = service.getAvailableMovies();

    if (movie != null) {
      return ResponseEntity.ok(movie);
    } else {
      return ResponseEntity.notFound().build();
    }

  }
  
  @GetMapping("/elastic/movies/match/name/{value}")
  public ResponseEntity<ElasticMovie> getMovieByName(@PathVariable String value) {

	  ElasticMovie movie = service.getMovieByName(value);

    if (movie != null) {
      return ResponseEntity.ok(movie);
    } else {
      return ResponseEntity.notFound().build();
    }

  }
  
  @GetMapping("/elastic/movies/search/as-you-type/name/{value}")
  public ResponseEntity<List<ElasticMovie>> searchByName(@PathVariable String value) {

    List<ElasticMovie> movie = service.searchByName(value);

    if (movie != null) {
      return ResponseEntity.ok(movie);
    } else {
      return ResponseEntity.notFound().build();
    }

  }
  
  @GetMapping("/elastic/movies/search/as-you-type/director/{value}")
  public ResponseEntity<List<ElasticMovie>> searchByDirector(@PathVariable String value) {

    List<ElasticMovie> movie = service.searchByDirector(value);

    if (movie != null) {
      return ResponseEntity.ok(movie);
    } else {
      return ResponseEntity.notFound().build();
    }

  }
  
  @GetMapping("/elastic/movies/match/category/{value}")
  public ResponseEntity<List<ElasticMovie>> getMovieByCategory(@PathVariable String value) {

	  List<ElasticMovie> movies = service.getMoviesByCategory(value);

    if (movies != null) {
      return ResponseEntity.ok(movies);
    } else {
      return ResponseEntity.notFound().build();
    }

  }
  
  @PostMapping("/elastic/movies")
  public ResponseEntity<ElasticMovie> getMovies(@RequestBody CreateMovieRequest request) {

	  ElasticMovie createdMovie = service.createMovie(request);

    if (createdMovie != null) {
      return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
    } else {
      return ResponseEntity.badRequest().build();
    }

  }
  
  
  @DeleteMapping("/elastic/movie/{movieId}")
  public ResponseEntity<Void> deleteMovie(@PathVariable String movieId) {
	  Boolean removed = service.deleteMovie(movieId);

		if (Boolean.TRUE.equals(removed)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}

  }


}
