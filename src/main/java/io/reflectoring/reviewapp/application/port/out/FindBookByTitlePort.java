package io.reflectoring.reviewapp.application.port.out;

import io.reflectoring.reviewapp.domain.Book;

import java.util.Optional;

public interface FindBookByTitlePort {

  Optional<Book> findBookByTitle(String title);

}
