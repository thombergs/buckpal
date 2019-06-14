package io.reflectoring.reviewapp.application.port.in;

import io.reflectoring.reviewapp.application.port.in.RegisterBookUseCase.RegisterBookCommand;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class RegisterBookCommandTests {

  @Test
  void acceptsValidParameters() {
    new RegisterBookCommand("Get Your Hands Dirty on Clean Architecture", 42L);
    // no exception
  }

  @Test
  void rejectsEmptyTitle() {
    assertThatThrownBy(() -> new RegisterBookCommand("", 42L))
            .isInstanceOf(ConstraintViolationException.class);
    assertThatThrownBy(() -> new RegisterBookCommand(null, 42L))
            .isInstanceOf(ConstraintViolationException.class);
  }

  @Test
  void rejectsEmptyAuthor() {
    assertThatThrownBy(() -> new RegisterBookCommand("Get Your Hands Dirty on Clean Architecture", null))
            .isInstanceOf(ConstraintViolationException.class);
  }

}