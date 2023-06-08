package io.reflectoring.buckpal.application.port.in;

import io.reflectoring.buckpal.application.domain.model.Account.AccountId;
import io.reflectoring.buckpal.application.domain.model.Money;

public interface GetAccountBalanceQuery {

	Money getAccountBalance(AccountId accountId);

}
