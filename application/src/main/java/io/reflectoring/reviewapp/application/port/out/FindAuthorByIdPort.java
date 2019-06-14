package io.reflectoring.reviewapp.application.port.out;

import io.reflectoring.reviewapp.domain.Author;

public interface FindAuthorByIdPort {

  Author findAuthorById(Long authorId) throws AuthorNotFoundException;

  class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(Long authorId) {
      super(String.format("Author with ID '%d' does not exist!", authorId));
    }
  }

}
