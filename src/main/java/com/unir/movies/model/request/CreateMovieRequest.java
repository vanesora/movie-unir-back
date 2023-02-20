package com.unir.movies.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMovieRequest {

	private String name;
	private String director;
	private String score;
	private String category;
	private String img;
	private String synopsis;
	private String duration;
	private String trailer;
	private Boolean visible;
}
