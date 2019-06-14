package io.reflectoring.reviewapp.application.service;

import io.reflectoring.reviewapp.application.port.in.RegisterBookUseCase;
import io.reflectoring.reviewapp.application.port.out.FindAuthorByIdPort;
import io.reflectoring.reviewapp.application.port.out.FindBookByTitlePort;
import io.reflectoring.reviewapp.application.port.out.PersistBookPort;
import io.reflectoring.reviewapp.domain.Author;
import io.reflectoring.reviewapp.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterBookService implements RegisterBookUseCase {

  private final FindAuthorByIdPort findAuthorByIdPort;
  private final FindBookByTitlePort findBookByTitlePort;
  private final PersistBookPort saveBookPort;

  @Override
  public void registerBook(RegisterBookCommand command) {

    Author author = findAuthorByIdPort.findAuthorById(command.getAuthorId());

    requireUniqueTitle(command.getBookTitle());
    requireAuthorHasPremiumAccount(author);

    // more business validations ...

    Book book = new Book(null, command.getBookTitle(), author.getId());
    saveBookPort.saveBook(book);
  }

  private void requireAuthorHasPremiumAccount(Author author) {
    // some business validation ...
  }

  private void requireUniqueTitle(String bookTitle) {
    if (findBookByTitlePort.findBookByTitle(bookTitle).isPresent()) {
      throw new NonUniqueBookTitleException(bookTitle);
    }
  }

}
