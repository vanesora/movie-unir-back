package com.unir.movies.data;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

import com.unir.movies.model.pojo.ElasticMovie;

public interface ElasticMoviesRepository extends ElasticsearchRepository<ElasticMovie, String> {
  
  Optional<ElasticMovie> findByName(String name);
  
  Optional<ElasticMovie> findByDirector(String director);
  
  List<ElasticMovie> findByCategory(String category);
  
  List<ElasticMovie> findByVisible(Boolean visible);

}
