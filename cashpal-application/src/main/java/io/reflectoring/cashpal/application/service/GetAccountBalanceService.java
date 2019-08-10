package io.reflectoring.cashpal.application.service;

import java.time.LocalDateTime;

import io.reflectoring.cashpal.application.port.in.GetAccountBalanceQuery;
import io.reflectoring.cashpal.application.port.out.LoadAccountPort;
import io.reflectoring.cashpal.domain.Account.AccountId;
import io.reflectoring.cashpal.domain.Money;
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
