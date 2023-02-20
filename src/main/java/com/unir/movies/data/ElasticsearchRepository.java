package com.unir.movies.data;

import com.unir.movies.model.pojo.ElasticMovie;
import com.unir.movies.model.pojo.Movie;

import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ElasticsearchRepository {

  private final String[] nameSearchFields =
      {"name.search", "name.search._2gram", "name.search._3gram"};
  
  private final String[] directorSearchFields =
      {"director.search", "director.search._2gram", "director.search._3gram"};

  private final ElasticMoviesRepository movieRepository;
  private final ElasticsearchOperations elasticClient;

  public ElasticMovie getById(String id) {
    return movieRepository.findById(id).orElse(null);
  }

  public ElasticMovie getByName(String name) {
    return movieRepository.findByName(name).orElse(null);
  }

  public List<ElasticMovie> getByCategory(String category) {
    return movieRepository.findByCategory(category);
  }

  public List<ElasticMovie> getVisible() {
    return movieRepository.findByVisible(true);
  }

  public List<ElasticMovie> searchByName(String namePart) {

    BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

    boolQuery.must(QueryBuilders.multiMatchQuery(namePart, nameSearchFields)
        .type(MultiMatchQueryBuilder.Type.BOOL_PREFIX));

    NativeSearchQueryBuilder nativeSearchQueryBuilder =
        new NativeSearchQueryBuilder().withQuery(boolQuery);
    Query query = nativeSearchQueryBuilder.build();

    SearchHits<ElasticMovie> result = elasticClient.search(query, ElasticMovie.class);

    return result.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
  }

  public List<ElasticMovie> searchByDirector(String directorPart) {

    BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

    boolQuery.must(QueryBuilders.multiMatchQuery(directorPart, directorSearchFields)
            .type(MultiMatchQueryBuilder.Type.BOOL_PREFIX));

    NativeSearchQueryBuilder nativeSearchQueryBuilder =
        new NativeSearchQueryBuilder().withQuery(boolQuery);
    Query query = nativeSearchQueryBuilder.build();

    SearchHits<ElasticMovie> result = elasticClient.search(query, ElasticMovie.class);

    return result.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
  }

  public ElasticMovie saveMovie(ElasticMovie movie) {
    return movieRepository.save(movie);
  }
  
  public Boolean deleteMovie(String id) {
	  ElasticMovie movie = movieRepository.findById(id).orElse(null);
		if (movie != null) {
			movieRepository.delete(movie);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	  }

}
