package io.reflectoring.reviewapp.application.port.out;

import io.reflectoring.reviewapp.domain.Book;

public interface PersistBookPort {

  Book saveBook(Book book);

}
