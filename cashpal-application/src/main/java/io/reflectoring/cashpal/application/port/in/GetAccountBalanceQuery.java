package io.reflectoring.cashpal.application.port.in;

import io.reflectoring.cashpal.domain.Account.AccountId;
import io.reflectoring.cashpal.domain.Money;

public interface GetAccountBalanceQuery {

	Money getAccountBalance(AccountId accountId);

}
