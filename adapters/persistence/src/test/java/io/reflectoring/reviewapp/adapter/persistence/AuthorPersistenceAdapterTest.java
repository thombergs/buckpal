package io.reflectoring.reviewapp.adapter.persistence;

import io.reflectoring.reviewapp.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.assertj.core.api.Assertions.*;

@DataJdbcTest
class AuthorPersistenceAdapterTest {

  @Autowired
  private AuthorPersistenceAdapter authorPersistenceAdapter;

  @Test
  @SqlGroup({
          @Sql(scripts = "single-author.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
          @Sql(scripts = "single-author-reset.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)})
  void findByAuthorId() {
    Author author = authorPersistenceAdapter.findAuthorById(42L);
    assertThat(author).isNotNull();
  }

}