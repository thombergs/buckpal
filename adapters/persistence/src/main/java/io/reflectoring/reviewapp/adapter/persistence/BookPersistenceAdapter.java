package io.reflectoring.reviewapp.adapter.persistence;

import io.reflectoring.reviewapp.application.port.out.FindBookByTitlePort;
import io.reflectoring.reviewapp.application.port.out.PersistBookPort;
import io.reflectoring.reviewapp.common.PersistenceAdapter;
import io.reflectoring.reviewapp.domain.Book;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
class BookPersistenceAdapter implements FindBookByTitlePort, PersistBookPort {

  private final BookRepository bookRepository;

  @Override
  public Optional<Book> findBookByTitle(String title) {
    return bookRepository.findByTitle(title);
  }

  @Override
  public Book saveBook(Book book) {
    return bookRepository.save(book);
  }
}
