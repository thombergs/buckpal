package io.reflectoring.reviewapp.adapter.web;

import io.reflectoring.reviewapp.application.port.in.RegisterBookUseCase;
import io.reflectoring.reviewapp.application.port.in.RegisterBookUseCase.RegisterBookCommand;
import io.reflectoring.reviewapp.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class RegisterBookController {

	private final RegisterBookUseCase registerBookUseCase;

	@PostMapping(path = "/books/register")
	void registerBook(@RequestBody Book book) {
		RegisterBookCommand command = new RegisterBookCommand(book.getTitle(), book.getAuthorId());
		registerBookUseCase.registerBook(command);
	}

}
