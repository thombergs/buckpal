package io.reflectoring.cashpal.adapter.web;

import io.reflectoring.cashpal.application.port.in.SendMoneyUseCase;
import io.reflectoring.cashpal.application.port.in.SendMoneyUseCase.SendMoneyCommand;
import io.reflectoring.cashpal.domain.Account.AccountId;
import io.reflectoring.cashpal.domain.Money;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SendMoneyController {

	private final SendMoneyUseCase sendMoneyUseCase;

	@PostMapping(path = "/sendMoney")
	void sendMoney(@RequestBody SendMoneyForm form) {

		SendMoneyCommand command = new SendMoneyCommand(
				new AccountId(form.sourceAccountId),
				new AccountId(form.targetAccountId),
				Money.of(form.amount));

		sendMoneyUseCase.sendMoney(command);
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class SendMoneyForm {
		private Long sourceAccountId;
		private Long targetAccountId;
		private Long amount;
	}

}
