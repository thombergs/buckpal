package io.reflectoring.reviewapp.adapter.persistence;

import io.reflectoring.reviewapp.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
