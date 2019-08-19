package io.reflectoring.buckpal.application.port.in;

import javax.validation.constraints.NotNull;

import io.reflectoring.buckpal.testdata.SelfValidating;
import io.reflectoring.buckpal.domain.Account;
import io.reflectoring.buckpal.domain.Money;
import lombok.EqualsAndHashCode;
import lombok.Value;

public interface SendMoneyUseCase {

	boolean sendMoney(SendMoneyCommand command);

	@Value
	@EqualsAndHashCode(callSuper = false)
	class SendMoneyCommand extends SelfValidating<SendMoneyCommand> {

		@NotNull
		private final Account.AccountId sourceAccountId;

		@NotNull
		private final Account.AccountId targetAccountId;

		@NotNull
		private final Money money;

		public SendMoneyCommand(
				Account.AccountId sourceAccountId,
				Account.AccountId targetAccountId,
				Money money) {
			this.sourceAccountId = sourceAccountId;
			this.targetAccountId = targetAccountId;
			this.money = money;
			this.validateSelf();
		}
	}

}
