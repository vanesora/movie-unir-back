package com.unir.movies.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

@Document(indexName = "movies", createIndex = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ElasticMovie {

  @Id
  private String id;

  @MultiField(mainField = @Field(type = FieldType.Keyword, name = "name"),
      otherFields = @InnerField(suffix = "search", type = FieldType.Search_As_You_Type))
  private String name;
  
  @MultiField(mainField = @Field(type = FieldType.Keyword, name = "director"),
	      otherFields = @InnerField(suffix = "search", type = FieldType.Search_As_You_Type))
	  private String director;

  @Field(type = FieldType.Keyword, name = "category")
  private String category;

  @Field(type = FieldType.Text, name = "img")
  private String img;
  
  @Field(type = FieldType.Text, name = "synopsis")
  private String synopsis;
  
  @Field(type = FieldType.Text, name = "duration")
  private String duration;
  
  @Field(type = FieldType.Text, name = "score")
  private String score;
  
  @Field(type = FieldType.Text, name = "trailer")
  private String trailer;

  @Field(type = FieldType.Boolean, name = "visible")
  private Boolean visible;

}
