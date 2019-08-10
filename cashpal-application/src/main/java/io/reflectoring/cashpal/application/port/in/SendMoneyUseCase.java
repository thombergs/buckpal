package io.reflectoring.cashpal.application.port.in;

import javax.validation.constraints.NotNull;

import io.reflectoring.cashpal.testdata.SelfValidating;
import io.reflectoring.cashpal.domain.Account;
import io.reflectoring.cashpal.domain.Money;
import lombok.EqualsAndHashCode;
import lombok.Value;

public interface SendMoneyUseCase {

	boolean sendMoney(SendMoneyCommand command);

	@Value
	@EqualsAndHashCode(callSuper = false)
	class SendMoneyCommand extends SelfValidating<SendMoneyCommand> {

		@NotNull
		private Account.AccountId sourceAccountId;

		@NotNull
		private Account.AccountId targetAccountId;

		@NotNull
		private Money money;

		public SendMoneyCommand(Account.AccountId sourceAccountId, Account.AccountId targetAccountId, Money money) {
			this.sourceAccountId = sourceAccountId;
			this.targetAccountId = targetAccountId;
			this.money = money;
			this.validateSelf();
		}
	}

}
