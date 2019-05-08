package io.reflectoring.reviewapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@AllArgsConstructor
public class Author {

  @Id
  private Long id;
  private String name;

  // imagine some insanely complex business logic methods ...

}
