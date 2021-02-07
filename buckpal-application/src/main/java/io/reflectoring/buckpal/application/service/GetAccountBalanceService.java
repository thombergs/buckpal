package io.reflectoring.buckpal.application.service;

import io.reflectoring.buckpal.application.port.in.GetAccountBalanceQuery;
import io.reflectoring.buckpal.application.port.out.LoadAccountPort;
import io.reflectoring.buckpal.domain.Account.AccountId;
import io.reflectoring.buckpal.domain.Money;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
class GetAccountBalanceService implements GetAccountBalanceQuery {

	private final LoadAccountPort loadAccountPort;

	@Override
	public Money getAccountBalance(AccountId accountId) {
		return loadAccountPort.loadAccount(accountId, LocalDateTime.now())
				.calculateBalance();
	}
}
