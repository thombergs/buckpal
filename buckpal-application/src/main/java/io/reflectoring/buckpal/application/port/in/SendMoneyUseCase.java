package io.reflectoring.buckpal.application.port.in;

import io.reflectoring.buckpal.domain.Account.AccountId;
import io.reflectoring.buckpal.domain.Money;
import io.reflectoring.buckpal.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;

public interface SendMoneyUseCase {

	boolean sendMoney(SendMoneyCommand command);

	@Value
	@EqualsAndHashCode(callSuper = false)
	class SendMoneyCommand extends SelfValidating<SendMoneyCommand> {

		@NotNull
		private final AccountId sourceAccountId;

		@NotNull
		private final AccountId targetAccountId;

		@NotNull
		private final Money money;

		public SendMoneyCommand(
				AccountId sourceAccountId,
				AccountId targetAccountId,
				Money money) {
			this.sourceAccountId = sourceAccountId;
			this.targetAccountId = targetAccountId;
			this.money = money;
			this.validateSelf();
		}
	}

}
