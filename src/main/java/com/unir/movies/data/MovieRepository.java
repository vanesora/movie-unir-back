package com.unir.movies.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;

import com.unir.movies.model.pojo.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	@Query("SELECT p FROM Movie p WHERE CONCAT(p.name, ' ', p.category, ' ', p.director) LIKE %?1%")
	public List<Movie> search(String keyword);
}
