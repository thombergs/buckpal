package io.reflectoring.reviewapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@AllArgsConstructor
public class Author {

  @Id
  private Long id;
  private String name;

}
