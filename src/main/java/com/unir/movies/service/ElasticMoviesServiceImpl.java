package com.unir.movies.service;

import com.unir.movies.data.ElasticsearchRepository;
import com.unir.movies.model.pojo.ElasticMovie;
import com.unir.movies.model.request.CreateMovieRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ElasticMoviesServiceImpl implements ElasticMoviesService {

  private final ElasticsearchRepository repo;

  @Override
  public ElasticMovie getMovieById(String movieId) {
    return repo.getById(movieId);
  }

  @Override
  public ElasticMovie getMovieByName(String name) {
    return repo.getByName(name);
  }
  
  @Override
  public List<ElasticMovie> getMoviesByCategory(String category) {
    return repo.getByCategory(category);
  }

  @Override
  public List<ElasticMovie> searchByDirector(String director) {
    return repo.searchByDirector(director);
  }

  @Override
  public List<ElasticMovie> searchByName(String productName) {
    return repo.searchByName(productName);
  }

  @Override
  public List<ElasticMovie> getAvailableMovies() {
    return repo.getVisible();
  }
  
  @Override
  public Boolean deleteMovie(String id) {
    return repo.deleteMovie(id);
  }

  @Override
  public ElasticMovie createMovie(CreateMovieRequest request) {

    if (request != null && StringUtils.hasLength(request.getName().trim())
        && StringUtils.hasLength(request.getDirector().trim())
        && StringUtils.hasLength(request.getScore().trim())
        && StringUtils.hasLength(request.getImg().trim())
        && StringUtils.hasLength(request.getSynopsis().trim())
        && StringUtils.hasLength(request.getDuration().trim())
        && StringUtils.hasLength(request.getTrailer().trim())
        && StringUtils.hasLength(request.getCategory().trim()) && request.getVisible() != null) {

    	ElasticMovie movie =
    			ElasticMovie.builder().id(String.valueOf(request.getName().hashCode()))
              .name(request.getName()).director(request.getDirector())
              .score(request.getScore())
              .img(request.getImg())
              .synopsis(request.getSynopsis())
              .duration(request.getDuration())
              .trailer(request.getTrailer())
              .category(request.getCategory()).visible(request.getVisible()).build();
      return repo.saveMovie(movie);
    } else {
      return null;
    }
  }



}
