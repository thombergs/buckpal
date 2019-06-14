package io.reflectoring.reviewapp.application.port.in;

import io.reflectoring.reviewapp.common.SelfValidating;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public interface RegisterBookUseCase {

  /**
   * Allows an author to register a new book to be reviewed.
   */
  void registerBook(RegisterBookCommand command) throws NonUniqueBookTitleException;


  @Getter
  final class RegisterBookCommand extends SelfValidating<RegisterBookCommand> {

    @NotEmpty
    private final String bookTitle;

    @NotNull
    private final Long authorId;

    public RegisterBookCommand(String bookTitle, Long authorId) {
      this.bookTitle = bookTitle;
      this.authorId = authorId;
      validateSelf();
    }
  }

  final class NonUniqueBookTitleException extends RuntimeException {

    public NonUniqueBookTitleException(String bookTitle) {
      super(String.format("A book title must be unique (book title in question: '%s').", bookTitle));
    }

  }

}
