package io.reflectoring.buckpal.application.service;

import java.time.LocalDateTime;

import io.reflectoring.buckpal.application.port.in.GetAccountBalanceQuery;
import io.reflectoring.buckpal.application.port.out.LoadAccountPort;
import io.reflectoring.buckpal.domain.Account.AccountId;
import io.reflectoring.buckpal.domain.Money;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class GetAccountBalanceService implements GetAccountBalanceQuery {

	private final LoadAccountPort loadAccountPort;

	@Override
	public Money getAccountBalance(AccountId accountId) {
		return loadAccountPort.loadAccount(accountId, LocalDateTime.now())
				.calculateBalance();
	}
}
