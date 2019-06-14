package io.reflectoring.reviewapp.adapter.persistence;

import io.reflectoring.reviewapp.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJdbcTest
class BookPersistenceAdapterTest {

  @Autowired
  private BookPersistenceAdapter bookPersistenceAdapter;

  @Autowired
  private BookRepository bookRepository;

  @Test
  @SqlGroup({
          @Sql(scripts = "single-book.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
          @Sql(scripts = "single-book-reset.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)})
  void findBookByTitle() {
    Optional<Book> optionalBook = bookPersistenceAdapter.findBookByTitle("Get Your Hands Dirty on Clean Architecture");
    assertThat(optionalBook).isPresent();
  }

  @Test
  @SqlGroup({
          @Sql(scripts = "single-book.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
          @Sql(scripts = "single-book-reset.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)})
  void savesBook() {
    Book book = new Book(null, "A Hitchhiker's Guide to the Galaxy", 42L);
    Book savedBook = bookPersistenceAdapter.saveBook(book);
    assertThat(bookRepository.findById(savedBook.getId())).isPresent();
  }

}